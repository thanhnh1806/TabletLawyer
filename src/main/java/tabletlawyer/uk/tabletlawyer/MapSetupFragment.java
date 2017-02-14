package tabletlawyer.uk.tabletlawyer;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class MapSetupFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private OnItemSelectedListener listener;
    int[] locationArray = new int[5];
    private int spinnerPos;
    Spinner spinner;
    CheckBox checkBox1;
    CheckBox checkBox2;
    CheckBox checkBox3;
    CheckBox checkBox4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mapsetup, container, false);

        spinner = (Spinner) view.findViewById(R.id.regionSelector);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),R.array.regionArray, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);






         checkBox1 = (CheckBox) view.findViewById(R.id.checkbox1);
         checkBox2 = (CheckBox) view.findViewById(R.id.checkbox2);
         checkBox3 = (CheckBox) view.findViewById(R.id.checkbox3);
         checkBox4 = (CheckBox) view.findViewById(R.id.checkbox4);



        Button searchButton = (Button) view.findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                spinnerPos = spinner.getSelectedItemPosition();
                locationArray[0]=spinnerPos;


                if (checkBox1.isChecked())
                    locationArray[1] =1;
                else
                    locationArray[1] =0;

                if (checkBox2.isChecked())
                    locationArray[2] =1;
                else
                    locationArray[2] =0;

                if (checkBox3.isChecked())
                    locationArray[3] =1;
                else
                    locationArray[3] =0;

                if (checkBox4.isChecked())
                    locationArray[4] =1;
                else
                    locationArray[4] =0;

                listener.mapSetupMethod(locationArray);
            }
        });


        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {



    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public interface OnItemSelectedListener {
        public void mapSetupMethod(int[] marker);
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




}
