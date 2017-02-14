package tabletlawyer.uk.tabletlawyer;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/**
 * Created by rakachowdhury on 16/02/2015.
 */
public class ReportingFragment extends Fragment {

    private OnItemSelectedListener listener;

    private View view;

    private static Spinner reportSpinner1;
 //   private static EditText startDate1;
 //   private static EditText endDate1;
    private static Button get1;
    private static String filter1;
    private static ArrayAdapter<CharSequence> reportAdapter1;
    private static Spinner reportSpinner2;
 //   private static EditText startDate2;
 //   private static EditText endDate2;
    private static Button get2;
    private static String filter2;
    private static ArrayAdapter<CharSequence> reportAdapter2;


    JSONObject json = new JSONObject();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_reporting, container, false);
        return view;
    }

    public interface OnItemSelectedListener {
        public void openReport(String Filter,String Button);
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

    @Override
    public void onActivityCreated(Bundle savedInstancestate) {
        super.onActivityCreated(savedInstancestate);

        reportSpinner1 = (Spinner)view.findViewById(R.id.reportSpinner1);
    //    startDate1 = (EditText)view.findViewById(R.id.startDate1);
    //    endDate1 = (EditText)view.findViewById(R.id.endDate1);
        get1 = (Button)view.findViewById(R.id.getButton1);


        reportAdapter1 = ArrayAdapter.createFromResource(getActivity(), R.array.reportArray1, android.R.layout.simple_spinner_item);
        reportAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        reportSpinner1.setAdapter(reportAdapter1);
        reportSpinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(String.valueOf(reportSpinner1.getSelectedItemPosition()).equals("0"))
                    filter1 = "FE";
                else
                    filter1 = "Area";
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        get1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.openReport(filter1,"get1");

            }
        });

        reportSpinner2 = (Spinner)view.findViewById(R.id.reportSpinner2);
     //   startDate2 = (EditText)view.findViewById(R.id.startDate2);
     //   endDate2 = (EditText)view.findViewById(R.id.endDate2);
        get2 = (Button)view.findViewById(R.id.getButton2);


        reportAdapter2 = ArrayAdapter.createFromResource(getActivity(), R.array.reportArray2, android.R.layout.simple_spinner_item);
        reportAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        reportSpinner2.setAdapter(reportAdapter2);
        reportSpinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(String.valueOf(reportSpinner2.getSelectedItemPosition()).equals("0"))
                    filter2 = "PS";
                else
                    filter2 = "FE";
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        get2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listener.openReport(filter2,"get2");
            }
        });


//        View.OnClickListener showDatePickerClick = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final View finalview = v;
//
//                final Calendar c = Calendar.getInstance();
//                int year = c.get(Calendar.YEAR);
//                int month = c.get(Calendar.MONTH);
//                int day = c.get(Calendar.DAY_OF_MONTH);
//
//                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int year, int month, int day) {
//                        if(finalview.getId() == R.id.startDate1){
//                            startDate1.setText(new StringBuilder().append(day)
//                                    .append("-").append(month + 1).append("-").append(year)
//                                    .append(" "));
//                        } else if(finalview.getId() == R.id.endDate1){
//                            endDate1.setText(new StringBuilder().append(day)
//                                    .append("-").append(month + 1).append("-").append(year)
//                                    .append(" "));
//                        } else if(finalview.getId() == R.id.startDate2){
//                            startDate2.setText(new StringBuilder().append(day)
//                                    .append("-").append(month + 1).append("-").append(year)
//                                    .append(" "));
//                        } else if(finalview.getId() == R.id.endDate2){
//                            endDate2.setText(new StringBuilder().append(day)
//                                    .append("-").append(month + 1).append("-").append(year)
//                                    .append(" "));
//                        }
//                    }
//                }, year, month, day);
//                datePickerDialog.show();
//
//
//
//            }
//        };
//
//        View.OnFocusChangeListener showDatePickerFocus = new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//
//                if (hasFocus) {
//                    final View finalview = v;
//                    final Calendar c = Calendar.getInstance();
//                    int year = c.get(Calendar.YEAR);
//                    int month = c.get(Calendar.MONTH);
//                    int day = c.get(Calendar.DAY_OF_MONTH);
//
//                    DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
//                        @Override
//                        public void onDateSet(DatePicker view, int year, int month, int day) {
//                            if(finalview.getId() == R.id.startDate1){
//                                startDate1.setText(new StringBuilder().append(day)
//                                        .append("-").append(month + 1).append("-").append(year)
//                                        .append(" "));
//                            } else if(finalview.getId() == R.id.endDate1){
//                                endDate1.setText(new StringBuilder().append(day)
//                                        .append("-").append(month + 1).append("-").append(year)
//                                        .append(" "));
//                            } else if(finalview.getId() == R.id.startDate2){
//                                startDate2.setText(new StringBuilder().append(day)
//                                        .append("-").append(month + 1).append("-").append(year)
//                                        .append(" "));
//                            } else if(finalview.getId() == R.id.endDate2){
//                                endDate2.setText(new StringBuilder().append(day)
//                                        .append("-").append(month + 1).append("-").append(year)
//                                        .append(" "));
//                            }
//                        }
//                    }, year, month, day);
//                    datePickerDialog.show();
//
//
//                }
//            }
//        };
//
//        startDate1.setOnClickListener(showDatePickerClick);
//        startDate1.setOnFocusChangeListener(showDatePickerFocus);
//        endDate1.setOnClickListener(showDatePickerClick);
//        endDate1.setOnFocusChangeListener(showDatePickerFocus);
//
//        startDate2.setOnClickListener(showDatePickerClick);
//        startDate2.setOnFocusChangeListener(showDatePickerFocus);
//        endDate2.setOnClickListener(showDatePickerClick);
//        endDate2.setOnFocusChangeListener(showDatePickerFocus);

    }



    }
