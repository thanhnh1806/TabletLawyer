package tabletlawyer.uk.tabletlawyer;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SettingsFragment extends Fragment {

    private OnItemSelectedListener listener;
    private View view;
    ListView listView;
    private static String deliveryPartner;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_settings, container, false);


        return view;
    }

    public interface OnItemSelectedListener {
        public void settingsMethod();
    }



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnItemSelectedListener) {
            listener = (OnItemSelectedListener) activity;
        } else {
            throw new ClassCastException(activity.toString()
                    + " must implemenet MyListFragment.OnItemSelectedListener");
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstancestate) {
        super.onActivityCreated(savedInstancestate);

        deliveryPartner = (String) getActivity().getIntent().getCharSequenceExtra("DeliveryPartner");

        if(deliveryPartner.equals("")){
            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case DialogInterface.BUTTON_POSITIVE:
                            // Exit application
                            System.exit(0);
                            break;
                    }
                }
            };

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Alert")
                    .setMessage("Delivery Partner is not set. Please contact admin.")
                    .setPositiveButton("Ok", dialogClickListener).show();
        }

        if(deliveryPartner.equals("admin")){

            // get the listview
            listView = (ListView) view.findViewById(R.id.settingsList);

            // Defined Array values to show in ListView
            String[] values = new String[] {"User Management"};

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                    R.layout.forms_header_items,R.id.listHeader, values);

            // Assign adapter to ListView
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {

                  if(position==0)
                      listener.settingsMethod();
                }
            });
        }
    }



}
