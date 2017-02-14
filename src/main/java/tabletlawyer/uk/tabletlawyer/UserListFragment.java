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

public class UserListFragment extends ListFragment {

    private OnItemSelectedListener listener;
    private View view;
    private static String email;


    // Connection detector class
    ConnectionDetector cd;
    // flag for Internet connection status
    Boolean isInternetPresent = false;

    // Progress Dialog
    private ProgressDialog pDialog;

    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();

    private static ArrayList<HashMap<String, String>> usersList;

    // url to get all clients list
      private static String url_all_users = "http://tabletlawyer.uk/tablet_lawyer/get_all_users.php";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_USERS = "users";
    private static final String TAG_EMAIL = "email";
    private static final String TAG_UID = "uid";

    // products JSONArray
    JSONArray users = null;
    JSONObject json = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_view_users, container, false);

        cd = new ConnectionDetector(getActivity().getApplicationContext());
        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstancestate) {
        super.onActivityCreated(savedInstancestate);

        // Hashmap for ListView
        usersList = new ArrayList<HashMap<String, String>>();

        // Check if Internet present
        isInternetPresent = cd.isConnectingToInternet();
        if (!isInternetPresent) {
            Toast.makeText(getActivity(),"Please check internet connection",Toast.LENGTH_LONG).show();
        } else {
            // Internet Connection is present
            // Loading users in Background Thread
            new LoadAllUsers().execute();
        }


        // Get listview
        ListView lv = getListView();

        // on selecting single user
        // launching UserDetail Screen
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // getting values from selected ListItem
                email = ((TextView) view.findViewById(R.id.Title)).getText()
                        .toString();

                //calling ClientFormsFragment through Main Activity
                listener.openUserDetails(email);

            }
        });

    }

    public interface OnItemSelectedListener {
        public void openUserDetails(String email);
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
    class LoadAllUsers extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Loading users. Please wait...");
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

                json = jParser.makeHttpRequest(url_all_users, "GET", params);

            // Check your log cat for JSON reponse
//             Log.d("All Clients: ", json.toString());

            try {
                // Checking for SUCCESS TAG
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // clients found
                    // Getting Array of Clients
                    users = json.getJSONArray(TAG_USERS);

                    // looping through All Clients
                    for (int i = 0; i < users.length(); i++) {
                        JSONObject c = users.getJSONObject(i);

                        // Storing each json item in variable
                        String email = c.getString(TAG_EMAIL);
                        String uid = c.getString(TAG_UID);

                        // creating new HashMap
                        HashMap<String, String> map = new HashMap<String, String>();

                        // adding each child node to HashMap key => value
                        map.put(TAG_EMAIL, email);
                        map.put(TAG_UID, uid);

                        // adding HashList to ArrayList
                        usersList.add(map);
                    }
                } else {
                    // no clients found
                   getActivity().runOnUiThread(new Runnable() {
                       @Override
                       public void run() {
                           Toast.makeText(getActivity(),"No User Added",Toast.LENGTH_LONG).show();
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
                            getActivity(), usersList,
                            R.layout.agenda_db_list, new String[]{TAG_UID,
                            TAG_EMAIL},
                            new int[]{R.id.serialNo, R.id.Title});
                    // updating listview
                    setListAdapter(adapter);
                }
            });

        }

    }

}