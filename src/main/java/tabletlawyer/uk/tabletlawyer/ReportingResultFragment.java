package tabletlawyer.uk.tabletlawyer;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReportingResultFragment extends ListFragment {

    private View view;
    private static String filter;
    private static String button;
    private static ListAdapter adapter;

    // Connection detector class
    ConnectionDetector cd;
    // flag for Internet connection status
    Boolean isInternetPresent = false;

    // Progress Dialog
    private ProgressDialog pDialog;
    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();
    JSONObject json = new JSONObject();
    ArrayList<HashMap<String, String>> reportList;

    // url to get reports
    private static String url_report1 = "http://tabletlawyer.uk/tablet_lawyer/get_report1.php";
    private static String url_report2PS = "http://tabletlawyer.uk/tablet_lawyer/get_report2PS.php";
    private static String url_report2FE = "http://tabletlawyer.uk/tablet_lawyer/get_report2FE.php";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_CLIENT_INFO = "clientInfo";
    private static final String TAG_FEE_EARNER = "FeeEarner";
    private static final String TAG_COUNT_FE = "countFeeEarner";
    private static final String TAG_POLICE_STATION = "PoliceStation";
    private static final String TAG_COUNT_PS = "countPoliceStation";
    private static final String TAG_ETA = "Eta";
    private static String TAG;

    // products JSONArray
    JSONArray client = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_reporting_result, container, false);

        cd = new ConnectionDetector(getActivity().getApplicationContext());
        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstancestate) {
        super.onActivityCreated(savedInstancestate);
        // Hashmap for ListView
        reportList = new ArrayList<HashMap<String, String>>();

        // Check if Internet present
        isInternetPresent = cd.isConnectingToInternet();
        if (!isInternetPresent) {
            // Internet Connection is not present
            Toast.makeText(getActivity(), "Please check internet connection", Toast.LENGTH_LONG).show();
        } else {
            // Internet Connection is present
            // Getting result in background thread
            new GetReport().execute();
        }

    }


    public void prepareResult(String Filter, String Button) {

        filter = Filter;
        button = Button;
    }

    /**
     * Background Async Task to Load result1 by making HTTP Request
     * */
    class GetReport extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Loading report. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        /**
         * getting result from url
         * */
        protected String doInBackground(String... args) {

            int success;
            try{
                // Building Parameters
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                if(button.equals("get1"))
                    json = jParser.makeHttpRequest(url_report1, "GET", params);
                else if(button.equals("get2") && filter.equals("PS"))
                    json = jParser.makeHttpRequest(url_report2PS, "GET", params);
                else if(button.equals("get2") && filter.equals("FE"))
                    json = jParser.makeHttpRequest(url_report2FE, "GET", params);


                // Check your log cat for JSON reponse
                Log.d("All Clients: ", json.toString());


                // Checking for SUCCESS TAG
                success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // agenda found
                    // Getting Array of report
                    client = json.getJSONArray(TAG_CLIENT_INFO);

                    // looping through All result
                    for (int i = 0; i < client.length(); i++) {
                        JSONObject c = client.getJSONObject(i);

                        // creating new HashMap
                        HashMap<String, String> map = new HashMap<String, String>();

                        if(button.equals("get1")) {
                            // Storing each json item in variable
                            String feeEarner = c.getString(TAG_FEE_EARNER);
                            String policeStation = c.getString(TAG_POLICE_STATION);
                            String eta = c.getString(TAG_ETA);

                            // adding each child node to HashMap key => value
                            if (filter.equals("FE")) {
                                map.put(TAG_FEE_EARNER, feeEarner);
                            } else {
                                map.put(TAG_POLICE_STATION, policeStation);
                            }
                            map.put(TAG_ETA, eta);
                        }
                        if(button.equals("get2") && filter.equals("PS")) {
                            // Storing each json item in variable
                            String policeStation = c.getString(TAG_POLICE_STATION);
                            String countPS = c.getString(TAG_COUNT_PS);

                            // adding each child node to HashMap key => value
                                map.put(TAG_POLICE_STATION, policeStation);
                                map.put(TAG_COUNT_PS, countPS);

                        }
                        if(button.equals("get2") && filter.equals("FE")) {
                            // Storing each json item in variable
                            String feeEarner = c.getString(TAG_FEE_EARNER);
                            String countFE = c.getString(TAG_COUNT_FE);

                            // adding each child node to HashMap key => value
                            map.put(TAG_FEE_EARNER, feeEarner);
                            map.put(TAG_COUNT_FE, countFE);

                        }

                        // adding HashList to ArrayList
                        reportList.add(map);
                    }
                } else {
                    // no agenda found
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity(),"No Report Found",Toast.LENGTH_LONG).show();
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
        protected void onPostExecute(final String file_url) {
            // dismiss the dialog after getting all clients
            if(pDialog != null)
                pDialog.dismiss();
            // updating UI from Background Thread
            getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    if(button.equals("get1")) {
                        if (filter.equals("FE"))
                            TAG = "FeeEarner";
                        else
                            TAG = "PoliceStation";
                        /**
                         * Updating parsed JSON data into ListView
                         * */
                                adapter = new SimpleAdapter(
                                getActivity(), reportList,
                                R.layout.reporting_db_list, new String[]{TAG,
                                TAG_ETA},
                                new int[]{R.id.reportValue, R.id.reportResult});
                    }
                    if(button.equals("get2") && filter.equals("PS")) {
                        /**
                         * Updating parsed JSON data into ListView
                         * */
                        adapter = new SimpleAdapter(
                                getActivity(), reportList,
                                R.layout.reporting_db_list, new String[]{TAG_POLICE_STATION,
                                TAG_COUNT_PS},
                                new int[]{R.id.reportValue, R.id.reportResult});
                    }
                    if(button.equals("get2") && filter.equals("FE")) {
                        /**
                         * Updating parsed JSON data into ListView
                         * */
                        adapter = new SimpleAdapter(
                                getActivity(), reportList,
                                R.layout.reporting_db_list, new String[]{TAG_FEE_EARNER,
                                TAG_COUNT_FE},
                                new int[]{R.id.reportValue, R.id.reportResult});
                    }
                    // updating listview
                    setListAdapter(adapter);
                }
            });

        }

    }

}