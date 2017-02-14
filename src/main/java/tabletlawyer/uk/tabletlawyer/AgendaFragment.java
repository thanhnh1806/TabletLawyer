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
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AgendaFragment extends ListFragment {

    private OnItemSelectedListener listener;
    private View view;
    private static String userType;
    private static String consultant;

    Button createAgendaBtn;
    LinearLayout barLayout;

    // Progress Dialog
    private ProgressDialog pDialog;

    // Connection detector class
    ConnectionDetector cd;
    // flag for Internet connection status
    Boolean isInternetPresent = false;
    private static String FILE_NAME;


    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();
    JSONObject json = new JSONObject();

    ArrayList<HashMap<String, String>> agendaList;

    // url to get all agenda list
    private static String url_all_agenda = "http://tabletlawyer.uk/tablet_lawyer/get_all_agenda.php";
    private static String url_admin_agenda = "http://tabletlawyer.uk/tablet_lawyer/get_admin_agenda.php";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_AGENDA_INFO = "agendaInfo";
    private static final String TAG_SERIAL_NO = "SerialNo";
    private static final String TAG_CONSULTANT = "Consultant";
    private static final String TAG_AGENDA_TITLE = "AgendaTitle";



    // products JSONArray
    JSONArray agenda = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_agenda, container, false);

        cd = new ConnectionDetector(getActivity());
        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstancestate) {
        super.onActivityCreated(savedInstancestate);



        userType = (String) getActivity().getIntent().getCharSequenceExtra("userType");
        consultant = (String) getActivity().getIntent().getCharSequenceExtra("name");

        if(userType.equalsIgnoreCase("admin") || userType.equalsIgnoreCase("user")) {
            FILE_NAME = "adminAgenda.txt";
            createAgendaBtn = (Button) view.findViewById(R.id.createAgendaBtn);
            barLayout = (LinearLayout) view.findViewById(R.id.barLayout1);

            createAgendaBtn.setVisibility(view.VISIBLE);
            barLayout.setVisibility(view.VISIBLE);

            createAgendaBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //calling AgendaDetailFragment through Main Activity
                    listener.createAgenda();
                }
            });
        }else
            FILE_NAME = "allAgenda.txt";


        // Hashmap for ListView
        agendaList = new ArrayList<HashMap<String, String>>();

        // Check if Internet present
        isInternetPresent = cd.isConnectingToInternet(getActivity());
        if (!isInternetPresent) {
            // Internet Connection is not present
            try {
                BufferedReader bReader = new BufferedReader(new InputStreamReader(getActivity().openFileInput(FILE_NAME)));
                String line;
                StringBuffer text = new StringBuffer();
                while ((line = bReader.readLine()) != null) {
                    text.append(line);
                    try{

                        // Checking for SUCCESS TAG
                       int success = json.getInt(TAG_SUCCESS);

                        if (success == 1) {
                            // agenda found
                            // Getting Array of Agenda
                            agenda = json.getJSONArray(TAG_AGENDA_INFO);

                            // looping through All Agenda
                            for (int i = 0; i < agenda.length(); i++) {
                                JSONObject c = agenda.getJSONObject(i);

                                // Storing each json item in variable
                                String serialNo = c.getString(TAG_SERIAL_NO);
                                String agendaTitle = c.getString(TAG_AGENDA_TITLE);


                                // creating new HashMap
                                HashMap<String, String> map = new HashMap<String, String>();

                                // adding each child node to HashMap key => value
                                map.put(TAG_SERIAL_NO, serialNo);
                                map.put(TAG_AGENDA_TITLE, agendaTitle);
                                //    map.put(TAG_AGENDA, agenda);


                                // adding HashList to ArrayList
                                agendaList.add(map);
                            }
                        } else {
                            // no agenda found
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getActivity(),"No Agenda Added",Toast.LENGTH_LONG).show();
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
                            getActivity(), agendaList,
                            R.layout.agenda_db_list, new String[]{TAG_SERIAL_NO,
                            TAG_AGENDA_TITLE},
                            new int[]{R.id.serialNo, R.id.Title});
                    // updating listview
                    setListAdapter(adapter);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Internet Connection is present
            // Loading agenda in Background Thread
            new LoadAllAgenda().execute();
        }

        // Get listview
        ListView lv = getListView();

        // on selecting single agenda
        // launching AgendaDetailFragment Screen
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // getting values from selected ListItem
                String serialNo = ((TextView) view.findViewById(R.id.serialNo)).getText()
                        .toString();

                //calling AgendaDetailFragment through Main Activity
                listener.openAgenda(serialNo);

            }
        });

    }

    public interface OnItemSelectedListener {
        public void openAgenda(String SerialNo);
        public void createAgenda();
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
     * Background Async Task to Load all agenda by making HTTP Request
     * */
    class LoadAllAgenda extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Loading agenda. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        /**
         * getting All agenda from url
         * */
        protected String doInBackground(String... args) {

            int success;
            try{
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("Consultant", consultant));
            // getting JSON string from URL
            if(userType.equals("admin") || userType.equals("user")) {
                json = jParser.makeHttpRequest(url_admin_agenda, "GET", params);
                try {
                    FileOutputStream fos = getActivity().openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
                    fos.write(json.toString().getBytes());
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else {
                json = jParser.makeHttpRequest(url_all_agenda, "POST", params);
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


                // Checking for SUCCESS TAG
                success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // agenda found
                    // Getting Array of Agenda
                    agenda = json.getJSONArray(TAG_AGENDA_INFO);

                    // looping through All Agenda
                    for (int i = 0; i < agenda.length(); i++) {
                        JSONObject c = agenda.getJSONObject(i);

                        // Storing each json item in variable
                        String serialNo = c.getString(TAG_SERIAL_NO);
                        String agendaTitle = c.getString(TAG_AGENDA_TITLE);


                        // creating new HashMap
                        HashMap<String, String> map = new HashMap<String, String>();

                        // adding each child node to HashMap key => value
                        map.put(TAG_SERIAL_NO, serialNo);
                        map.put(TAG_AGENDA_TITLE, agendaTitle);
                    //    map.put(TAG_AGENDA, agenda);


                        // adding HashList to ArrayList
                        agendaList.add(map);
                    }
                } else {
                    // no agenda found
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity(),"No Agenda Added",Toast.LENGTH_LONG).show();
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
                            getActivity(), agendaList,
                            R.layout.agenda_db_list, new String[]{TAG_SERIAL_NO,
                            TAG_AGENDA_TITLE},
                            new int[]{R.id.serialNo, R.id.Title});
                    // updating listview
                    setListAdapter(adapter);
                }
            });

        }

    }

}