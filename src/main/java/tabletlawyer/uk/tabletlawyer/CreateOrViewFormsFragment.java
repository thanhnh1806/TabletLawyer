package tabletlawyer.uk.tabletlawyer;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CreateOrViewFormsFragment extends ListFragment {

    private OnItemSelectedListener listener;
    private View view;
    private static String formSelected;
    private static String userType;

    Button createFormBtn;
    LinearLayout barLayout;

    // Connection detector class
    ConnectionDetector cd;
    // flag for Internet connection status
    Boolean isInternetPresent = false;
    private static String FILE_NAME;

    // Progress Dialog
    private ProgressDialog pDialog;

    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();

    private static ArrayList<HashMap<String, String>> clientsList;

    // url to get all clients list
      private static String url_all_clients = "http://tabletlawyer.uk/tablet_lawyer/get_all_clients.php";

    // url to get CF03 clients list
    private static String url_CF03_clients = "http://tabletlawyer.uk/tablet_lawyer/get_CF03_clients.php";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_CLIENTS = "clients";
    private static final String TAG_CLIENT_NAME = "ClientName";
    private static final String TAG_CASE_NO = "CaseNo";

    // products JSONArray
    JSONArray clients = null;
    JSONObject json = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_create_or_view_forms, container, false);

        cd = new ConnectionDetector(getActivity().getApplicationContext());
        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstancestate) {
        super.onActivityCreated(savedInstancestate);

//        getView().setFocusableInTouchMode(true);
//        getView().requestFocus();
//
//        getView().setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                if (event.getAction() == KeyEvent.ACTION_DOWN) {
//                    if (keyCode == KeyEvent.KEYCODE_BACK) {
//                        getActivity().getSupportFragmentManager().popBackStack(null,FragmentManager.POP_BACK_STACK_INCLUSIVE);
//                        return true;
//                    }
//                }
//                return false;
//            }
//        });
//
        userType = (String) getActivity().getIntent().getCharSequenceExtra("userType");
        if(formSelected.equalsIgnoreCase("Crim.CF001 Client Matter Form")&& userType.equalsIgnoreCase("admin") || userType.equalsIgnoreCase("user")) {
            createFormBtn = (Button) view.findViewById(R.id.createFormBtn);
            barLayout = (LinearLayout) view.findViewById(R.id.barLayout);

            createFormBtn.setVisibility(view.VISIBLE);
            barLayout.setVisibility(view.VISIBLE);

            createFormBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //calling ClientFormsFragment through Main Activity
                    listener.createForm(formSelected);
                }
            });
        }
        if(formSelected.equalsIgnoreCase("Crim.CF001 Client Matter Form") || formSelected.equalsIgnoreCase("Crim.CF002 Case Dispatch Form")) {
            FILE_NAME = "allClientList.txt";
        }else
            FILE_NAME = "otherClientList.txt";

        // Hashmap for ListView
        clientsList = new ArrayList<HashMap<String, String>>();

        // Check if Internet present
        isInternetPresent = cd.isConnectingToInternet();
        if (!isInternetPresent) {
            // Internet Connection is not present
                try {
                    BufferedReader bReader = new BufferedReader(new InputStreamReader(getActivity().openFileInput(FILE_NAME)));
                    String line;
                    StringBuffer text = new StringBuffer();
                    while ((line = bReader.readLine()) != null) {
                        text.append(line);

                    try {
                        json = new JSONObject(text.toString());
                    } catch (JSONException e) {
                        Log.e("JSON Parser", "Error parsing data " + e.toString());
                    }
                    try {
                        // Checking for SUCCESS TAG
                        int success = json.getInt(TAG_SUCCESS);

                        if (success == 1) {
                            // clients found
                            // Getting Array of Clients
                            clients = json.getJSONArray(TAG_CLIENTS);

                            // looping through All Clients
                            for (int i = 0; i < clients.length(); i++) {
                                JSONObject c = clients.getJSONObject(i);

                                // Storing each json item in variable
                                String clientName = c.getString(TAG_CLIENT_NAME);
                                String caseNo = c.getString(TAG_CASE_NO);


                                // creating new HashMap
                                HashMap<String, String> map = new HashMap<String, String>();

                                // adding each child node to HashMap key => value
                                map.put(TAG_CLIENT_NAME, clientName);
                                map.put(TAG_CASE_NO, caseNo);


                                // adding HashList to ArrayList
                                clientsList.add(map);
                            }
                        } else {
                            // no clients found
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getActivity(), "No Client Added", Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    /**
                     * Updating parsed JSON data into ListView
                     * */
                    ListAdapter adapter = new SimpleAdapter(
                            getActivity(), clientsList,
                            R.layout.forms_db_list, new String[]{TAG_CASE_NO,
                            TAG_CLIENT_NAME},
                            new int[]{R.id.caseNo, R.id.clientName});
                    // updating listview
                    setListAdapter(adapter);
                     }
                } catch (IOException e) {
                    e.printStackTrace();
                }


        } else {
            // Internet Connection is present
            // Loading clients in Background Thread
            new LoadAllClients().execute();
        }


        // Get listview
        ListView lv = getListView();

        // on selecting single client
        // launching ClientFormsFragment Screen
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // getting values from selected ListItem
                String caseNo = ((TextView) view.findViewById(R.id.caseNo)).getText()
                        .toString();

                //calling ClientFormsFragment through Main Activity
                listener.openForm(formSelected,caseNo);

            }
        });

    }

    public interface OnItemSelectedListener {
        public void openForm(String Form, String CaseNo);
        public void createForm(String Form);
    }



    public void viewOrCreateForms(String form) {

        formSelected = form;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (OnItemSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnItemSelectedListener");
        }
    }

    /**
     * Background Async Task to Load all clients by making HTTP Request
     * */
    class LoadAllClients extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Loading clients. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
             pDialog.show();
        }

        /**
         * getting All clients from url
         * */
        protected String doInBackground(String... args) {
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            // getting JSON string from URL
            if(formSelected.equalsIgnoreCase("Crim.CF003 Fee Earner Update") ||
               formSelected.equalsIgnoreCase("Crim.CF004 Fee Earner Claim for Costs at Police Station Form") ||
               formSelected.equalsIgnoreCase("Crim.CF005 Case Dispatch Form Plea Hearing") ||
               formSelected.equalsIgnoreCase("Crim.CF006 Fee Earner Update Plea Hearing") ||
               formSelected.equalsIgnoreCase("Crim.CF007 Fee Earner Claim for Costs at Plea Hearing") ||
               formSelected.equalsIgnoreCase("Crim.CF008 Crown Court Case Prep Form") ||
               formSelected.equalsIgnoreCase("Crim.CF009 Magistrates Court Case Prep Form") ||
               formSelected.equalsIgnoreCase("Crim.CF010 Case Dispatch For Trial") ||
               formSelected.equalsIgnoreCase("Crim.CF011 Fee Earner Update Trial") ||
               formSelected.equalsIgnoreCase("Crim.CF012 Fee Earner Claim for Costs at Trial") ||
                    formSelected.equalsIgnoreCase("Crim.CF020 Client Due Diligence Risk Assessment Form") ||
                    formSelected.equalsIgnoreCase("Crim.CF025 File Opening Closing Form") ||
                    formSelected.equalsIgnoreCase("Crim.CF037 Client due diligence risk tables") ||
                    formSelected.equalsIgnoreCase("Crim.CF090 Money Laundering  ID precedent") ||
                    formSelected.equalsIgnoreCase("Crim.CF091 File  Note Precedent Form") ||
               formSelected.equalsIgnoreCase("Crim.MISC001 Client Care Letter Criminal Legal Aid") ||
               formSelected.equalsIgnoreCase("Crim.MISC002 Police Station Outcome NFA") ||
               formSelected.equalsIgnoreCase("Crim.MISC003 Police Station Outcome Caution") ||
               formSelected.equalsIgnoreCase("Crim.MISC004 Police Station Bailed with Date to Return to Police Station") ||
               formSelected.equalsIgnoreCase("Crim.MISC005 Police Station Outcome Charged with Bail") ||
               formSelected.equalsIgnoreCase("Crim.MISC006 Police Station Outcome Charged remain in Custody") ||
               formSelected.equalsIgnoreCase("Crim.MISC007 Plea Hearing Outcome Case Adjourned with no Plea Given and Bail") ||
               formSelected.equalsIgnoreCase("Crim.MISC008 Plea Hearing Outcome Case Adjourned with no Plea Given Detained in Custody") ||
               formSelected.equalsIgnoreCase("Crim.MISC009 Plea Hearing Outcome to Guilty Plea and awaiting Pre-sentencing Report") ||
               formSelected.equalsIgnoreCase("Crim.MISC010 Plea Hearing Outcome to Guilty Plea and Sentenced") ||
               formSelected.equalsIgnoreCase("Crim.MISC011 Plea Hearing Outcome Not Guilty Plea and Remanded in Custody") ||
               formSelected.equalsIgnoreCase("Crim.MISC012 Plea Hearing Outcome to Not Guilty Plea and Bailed") ||
               formSelected.equalsIgnoreCase("Crim.MISC013 Custody Record Request Letter") ||
               formSelected.equalsIgnoreCase("Crim.MISC014 Letter to Tape Librarian") ||
               formSelected.equalsIgnoreCase("Crim.MISC015 Trial Hearing Outcome Case Adjourned with no conclusion Given and Bail") ||
               formSelected.equalsIgnoreCase("Crim.MISC016 Trial Hearing Outcome Case Adjourned with no conclusion given Remanded in Custody") ||
               formSelected.equalsIgnoreCase("Crim.MISC017 Trial Hearing Outcome to Guilty Verdict and awaiting Pre-sentencing Report") ||
               formSelected.equalsIgnoreCase("Crim.MISC018 Trial Hearing Outcome to Guilty Verdict and sentenced") ||
               formSelected.equalsIgnoreCase("Crim.MISC019 Trial Hearing Outcome to Not Guilty Verdict") ||
               formSelected.equalsIgnoreCase("Crim.MISC020 Letter to Client regarding Legal Aid Application")) {
                json = jParser.makeHttpRequest(url_CF03_clients, "GET", params);
                try {
                    FileOutputStream fos = getActivity().openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
                    fos.write(json.toString().getBytes());
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                     }
                }
            else {
                json = jParser.makeHttpRequest(url_all_clients, "GET", params);
                try {
                    FileOutputStream fos = getActivity().openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
                    fos.write(json.toString().getBytes());
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            // Check your log cat for JSON reponse
//             Log.d("All Clients: ", json.toString());

            try {
                // Checking for SUCCESS TAG
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // clients found
                    // Getting Array of Clients
                    clients = json.getJSONArray(TAG_CLIENTS);

                    // looping through All Clients
                    for (int i = 0; i < clients.length(); i++) {
                        JSONObject c = clients.getJSONObject(i);

                        // Storing each json item in variable
                        String clientName = c.getString(TAG_CLIENT_NAME);
                        String caseNo = c.getString(TAG_CASE_NO);


                        // creating new HashMap
                        HashMap<String, String> map = new HashMap<String, String>();

                        // adding each child node to HashMap key => value
                        map.put(TAG_CLIENT_NAME, clientName);
                        map.put(TAG_CASE_NO, caseNo);


                        // adding HashList to ArrayList
                        clientsList.add(map);
                    }
                } else {
                    // no clients found
                   getActivity().runOnUiThread(new Runnable() {
                       @Override
                       public void run() {
                           Toast.makeText(getActivity(),"No Client Added",Toast.LENGTH_LONG).show();
                       }
                   });
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog after getting all clients
            if(pDialog != null)
                pDialog.dismiss();
            // updating UI from Background Thread
            getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    /**
                     * Updating parsed JSON data into ListView
                     * */
                    ListAdapter adapter = new SimpleAdapter(
                            getActivity(), clientsList,
                            R.layout.forms_db_list, new String[]{TAG_CASE_NO,
                            TAG_CLIENT_NAME},
                            new int[]{R.id.caseNo, R.id.clientName});
                    // updating listview
                    setListAdapter(adapter);
                }
            });

        }

    }

}