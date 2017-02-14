package tabletlawyer.uk.tabletlawyer;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by niladreebhattacharjee on 05/11/2014.
 */
public class AgendaDetailFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private View view;
    private OnItemSelectedListener listener;

    private static String serialNumber;
    private static String agendaOption;
    private static String userType;
    private static EditText agendaTitle;
    private static EditText agendaDetails;
    private static Spinner consultant;
    private static LinearLayout consultantView;
    private static ArrayAdapter<String> adapterAgenda;
    private static Spinner deliveryPartner;
    private static LinearLayout dpView;
    private static ArrayAdapter<String> adapterAgenda1;

    private static Button saveButton;
    private static Button deleteButton;

    // Progress Dialog
    private ProgressDialog pDialog;

    JSONParser jsonParser = new JSONParser();

    ArrayList<HashMap<String, String>> consultantList;
    ArrayList<HashMap<String, String>> partnerList;
    // products JSONArray
    JSONArray consultantArray = null;
    JSONArray partnerArray = null;

    // url to create new agenda
    private static String url_create_agenda = "http://tabletlawyer.uk/tablet_lawyer/create_agenda.php";

    // single agenda detail url
    private static final String url_agenda_details = "http://tabletlawyer.uk/tablet_lawyer/get_agenda_details.php";

    // consultants url
    private static final String url_consultants = "http://tabletlawyer.uk/tablet_lawyer/get_consultants.php";

    // deliveryPartner url
    private static final String url_deliveryPartner = "http://tabletlawyer.uk/tablet_lawyer/get_deliveryPartner.php";

    // url to update agenda
    private static final String url_update_agenda = "http://tabletlawyer.uk/tablet_lawyer/update_agenda.php";

    // url to delete agenda
    private static final String url_delete_agenda = "http://tabletlawyer.uk/tablet_lawyer/delete_agenda.php";


    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_AGENDA_INFO = "agendaInfo";
    private static final String TAG_SERIAL_NO = "SerialNo";
    private static final String TAG_AGENDA_TITLE = "AgendaTitle";
    private static final String TAG_AGENDA = "Agenda";
    private static final String TAG_CONSULTANT = "Consultant";
    private static final String TAG_USERS = "users";
    private static final String TAG_NAME = "name";
    private static final String TAG_DP_NAME = "DPName";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_agenda_detail, container, false);

        return view;
    }

    public void prepareAgenda(String serialNo, String view) {

        serialNumber = serialNo;
        agendaOption = view;
    }

    public void prepareAgenda(String create) {

        agendaOption = create;
    }


    public interface OnItemSelectedListener {
        public void showAgenda();

    }

    @Override
    public void onActivityCreated(Bundle savedInstancestate) {
        super.onActivityCreated(savedInstancestate);

        ///Avoid android.os.NetworkOnMainThreadException
        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        userType = (String) getActivity().getIntent().getCharSequenceExtra("userType");
        saveButton = (Button) view.findViewById(R.id.saveButton);

        deleteButton = (Button) view.findViewById(R.id.deleteButton);

        agendaTitle = (EditText) view.findViewById(R.id.agendaTitle);
        agendaDetails = (EditText) view.findViewById(R.id.agendaDetails);

        if (agendaOption.equalsIgnoreCase("View")) {

            // Getting complete agenda details in background thread
            new GetAgendaDetails().execute();


            if (userType.equalsIgnoreCase("admin") || userType.equalsIgnoreCase("user") || userType.equalsIgnoreCase("consultant")) {

                // Save button click event
                saveButton.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View arg0) {
                        // starting background task to update client


                        new SaveAgendaDetails().execute();
                    }


                });
            }
            if (userType.equalsIgnoreCase("admin")) {

                // Delete button click event
                deleteButton.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View arg0) {
                        // Create dialog before delete
                        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case DialogInterface.BUTTON_POSITIVE:
                                        // deleting client in background thread
                                        new DeleteAgenda().execute();
                                        break;

                                    case DialogInterface.BUTTON_NEGATIVE:
                                        //No button clicked
                                        break;
                                }
                            }
                        };

                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setTitle("Delete Agenda")
                                .setMessage("Are you sure?")
                                .setPositiveButton("Yes", dialogClickListener)
                                .setNegativeButton("No", dialogClickListener).show();

                    }
                });

            }
            if (userType.equalsIgnoreCase("consultant")) {


                saveButton.setVisibility(View.GONE);
                deleteButton.setVisibility(View.GONE);
                agendaTitle.setEnabled(false);
                agendaDetails.setEnabled(false);

            }
        }

        if (agendaOption.equalsIgnoreCase("Create")) {


            consultant = (Spinner) view.findViewById(R.id.consultant);
            consultantView = (LinearLayout) view.findViewById(R.id.consultantView);
            consultant.setAdapter(adapterAgenda);

            deliveryPartner = (Spinner) view.findViewById(R.id.deliveryPartner);
            dpView = (LinearLayout) view.findViewById(R.id.dpView);
            deliveryPartner.setAdapter(adapterAgenda1);

            // Hashmap for ListView
            consultantList = new ArrayList<HashMap<String, String>>();
            // Getting consultants in background thread
            new GetConsultants().execute();

            // Hashmap for ListView
            partnerList = new ArrayList<HashMap<String, String>>();
            // Getting consultants in background thread
            new GetDeliveryPartners().execute();

            consultantView.setVisibility(View.VISIBLE);
            dpView.setVisibility(View.VISIBLE);
            deleteButton.setVisibility(View.GONE);
            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    new CreateNewAgenda().execute();
                }
            });
        }


    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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
     * Background Async Task to Create new agenda
     */
    class CreateNewAgenda extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Saving Agenda..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Creating agenda
         */
        protected String doInBackground(String... args) {
//            String stAgendaTitle = agendaTitle.getText().toString();
//            String stAgendaDetails = agendaDetails.getText().toString();
//            String stConsultant = consultant.getSelectedItem().toString();
//            String stDeliveryPartner = deliveryPartner.getSelectedItem().toString();
            String stAgendaTitle = "";
            String stAgendaDetails = "";
            String stConsultant = "";
            String stDeliveryPartner = "";

            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("AgendaTitle", stAgendaTitle));
            params.add(new BasicNameValuePair("Agenda", stAgendaDetails));
            params.add(new BasicNameValuePair("Consultant", stConsultant));
            params.add(new BasicNameValuePair("DeliveryPartner", stDeliveryPartner));

            // getting JSON Object
            // Note that create form url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(url_create_agenda, "POST", params);

            // check log cat fro response
            Log.d("Create Response", json.toString());

            // check for success tag
            try {
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // successfully created agenda
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            listener.showAgenda();
                        }
                    });

                } else {
                    // failed to create agenda
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            if (pDialog != null)
                pDialog.dismiss();
        }

    }


    /*****************************************************************
     * Background Async Task to Get complete agenda details
     */
    class GetAgendaDetails extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Loading agenda details. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Getting agenda details in background thread
         */
        protected String doInBackground(String... params) {

            // updating UI from Background Thread
            getActivity().runOnUiThread(new Runnable() {
                                            public void run() {
                                                // Check for success tag
                                                int success;
                                                try {
                                                    // Building Parameters
                                                    List<NameValuePair> params = new ArrayList<NameValuePair>();
                                                    params.add(new BasicNameValuePair("SerialNo", serialNumber));

                                                    // getting agenda details by making HTTP request
                                                    // Note that agenda details url will use GET request
                                                    JSONObject json = jsonParser.makeHttpRequest(
                                                            url_agenda_details, "GET", params);

                                                    // check your log for json response
                                                    Log.d("Single Agenda Details", json.toString());

                                                    // json success tag
                                                    success = json.getInt(TAG_SUCCESS);
                                                    if (success == 1) {
                                                        // successfully received agenda details
                                                        JSONArray clientObj = json
                                                                .getJSONArray(TAG_AGENDA_INFO); // JSON Array

                                                        // get first agenda object from JSON Array
                                                        JSONObject agendaInfo = clientObj.getJSONObject(0);

                                                        // agenda with this serial no found
                                                        // display agenda data in EditText


                                                        agendaTitle.setText(agendaInfo.getString(TAG_AGENDA_TITLE));
                                                        agendaDetails.setText(agendaInfo.getString(TAG_AGENDA));

                                                    } else {
                                                        // agenda with serial no not found
                                                    }
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        }

            );

            return null;
        }


        /**
         * After completing background task Dismiss the progress dialog
         **/

        protected void onPostExecute(String file_url) {
            // dismiss the dialog once got all details
            pDialog.dismiss();
        }
    }

    /*****************************************************************
     * Background Async Task to Get consultants
     */
    class GetConsultants extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Loading consultants. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Getting consultants in background thread
         */
        protected String doInBackground(String... args) {
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            // getting JSON string from URL
            JSONObject json;

            json = jsonParser.makeHttpRequest(url_consultants, "GET", params);

            // Check your log cat for JSON reponse
//             Log.d("All Clients: ", json.toString());

            try {

                // Checking for SUCCESS TAG
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // consultant found
                    // Getting Array of Consultants
                    consultantArray = json.getJSONArray(TAG_USERS);

                    adapterAgenda = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item);
                    adapterAgenda.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    // looping through All Consultants
                    for (int i = 0; i < consultantArray.length(); i++) {
                        JSONObject c = consultantArray.getJSONObject(i);

                        // Storing each json item in variable
                        String name = c.getString(TAG_NAME);

                        adapterAgenda.add(name);
                    }
                    // updating UI from Background Thread
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            consultant.setAdapter(adapterAgenda);
                        }
                    });
                    ;

                } else {
                    // no consultant found
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity(), "No Consultant Added", Toast.LENGTH_LONG).show();
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
         **/

        protected void onPostExecute(String file_url) {
            // dismiss the dialog once got all details
            pDialog.dismiss();
        }
    }

    /*****************************************************************
     * Background Async Task to Get consultants
     */
    class GetDeliveryPartners extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Getting partners in background thread
         */
        protected String doInBackground(String... args) {
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            // getting JSON string from URL
            JSONObject json;

            json = jsonParser.makeHttpRequest(url_deliveryPartner, "GET", params);

            // Check your log cat for JSON reponse
//             Log.d("All Clients: ", json.toString());

            try {

                // Checking for SUCCESS TAG
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // consultant found
                    // Getting Array of Consultants
                    partnerArray = json.getJSONArray(TAG_USERS);

                    adapterAgenda1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item);
                    adapterAgenda1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    // looping through All Consultants
                    for (int i = 0; i < partnerArray.length(); i++) {
                        JSONObject c = partnerArray.getJSONObject(i);

                        // Storing each json item in variable
                        String dpName = c.getString(TAG_DP_NAME);

                        adapterAgenda1.add(dpName);
                    }
                    // updating UI from Background Thread
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            deliveryPartner.setAdapter(adapterAgenda1);
                        }
                    });
                    ;

                } else {
                    // no consultant found
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity(), "No Delivery Partners Added", Toast.LENGTH_LONG).show();
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
         **/

        protected void onPostExecute(String file_url) {
            // dismiss the dialog once got all details
            pDialog.dismiss();
        }
    }

    /*************************************************************************
     * Background Async Task to  Save agenda Details
     */
    class SaveAgendaDetails extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Saving agenda ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Saving agenda
         */
        protected String doInBackground(String... args) {

            // getting updated data from EditTexts
            String stAgendaTitle = agendaTitle.getText().toString();
            String stAgendaDetails = agendaDetails.getText().toString();


            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair(TAG_SERIAL_NO, serialNumber));
            params.add(new BasicNameValuePair(TAG_AGENDA_TITLE, stAgendaTitle));
            params.add(new BasicNameValuePair(TAG_AGENDA, stAgendaDetails));

            // sending modified data through http request
            // Notice that update agenda url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(url_update_agenda,
                    "POST", params);

            // check json success tag
            try {
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // successfully updated
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            listener.showAgenda();
                        }
                    });
                } else {
                    // failed to update agenda
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }


        /**
         * After completing background task Dismiss the progress dialog
         **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once agenda updated
            pDialog.dismiss();
        }
    }


    /**************************************************************************
     * Background Async Task to Delete agenda
     */
    class DeleteAgenda extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Deleting Agenda...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Deleting agenda
         */
        protected String doInBackground(String... args) {

            // Check for success tag
            int success;
            try {
                // Building Parameters
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("SerialNo", serialNumber));

                // getting agenda details by making HTTP request
                JSONObject json = jsonParser.makeHttpRequest(
                        url_delete_agenda, "POST", params);

                // check your log for json response
                Log.d("Delete Agenda", json.toString());

                // json success tag
                success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                    // client successfully deleted
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            listener.showAgenda();
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
         **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once agenda deleted
            pDialog.dismiss();

        }

    }


}
