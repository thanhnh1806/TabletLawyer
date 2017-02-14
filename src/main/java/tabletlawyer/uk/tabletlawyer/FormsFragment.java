package tabletlawyer.uk.tabletlawyer;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by rakachowdhury on 31/10/2014.
 */
public class FormsFragment extends Fragment {

    private OnItemSelectedListener listener;
    private View view;
    private Boolean isGroupExp;
    private Intent i;
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listFormHeader;
    HashMap<String, List<String>> listFormChild;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_forms, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstancestate) {
        super.onActivityCreated(savedInstancestate);

        // get the listview
        expListView = (ExpandableListView) view.findViewById(R.id.formsList);

        // preparing list data
        prepareFormList();

        listAdapter = new ExpandableListAdapter(getActivity(), listFormHeader, listFormChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        // Listview on child click listener
        expListView.setOnChildClickListener(new OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                String formType = listFormHeader.get(groupPosition);
                String form = listFormChild.get(listFormHeader.get(groupPosition)).get(childPosition);

                 i = new Intent(Intent.ACTION_VIEW);

                /**Open Court Forms*/
                if(formType.equals("Court Forms")){
                    if(form.equals("AF1 Version 7 December 2013"))
                    {
                        String fileName = "af1_version_7_december_2013.pdf";
                        i.setDataAndType(Uri.parse("content://tabletlawyer.uk.tabletlawyer/"+fileName), "application/pdf");
                        try {
                            startActivity(i);
                        }
                        catch (ActivityNotFoundException e)
                        {
                            Toast.makeText(getActivity(), "NO Pdf Viewer", Toast.LENGTH_SHORT).show();
                        }
                    }
                    if(form.equals("CRM18a Version 2 April 2013"))
                    {
                        String fileName = "crm18a_version_2_april_2013.pdf";
                        i.setDataAndType(Uri.parse("content://tabletlawyer.uk.tabletlawyer/"+fileName), "application/pdf");
                        try {
                            startActivity(i);
                        }
                        catch (ActivityNotFoundException e)
                        {
                            Toast.makeText(getActivity(), "NO Pdf Viewer", Toast.LENGTH_SHORT).show();
                        }
                    }
                    if(form.equals("Legal Aid Advocate Fee Redetermination"))
                    {
                        String fileName = "legal_aid_advocate_fee_redetermination.pdf";
                        i.setDataAndType(Uri.parse("content://tabletlawyer.uk.tabletlawyer/"+fileName), "application/pdf");
                        try {
                            startActivity(i);
                        }
                        catch (ActivityNotFoundException e)
                        {
                            Toast.makeText(getActivity(), "NO Pdf Viewer", Toast.LENGTH_SHORT).show();
                        }

                    }
                    if(form.equals("Legal Aid CRM1"))
                    {
                        String fileName = "legal_aid_CRM1.pdf";
                        i.setDataAndType(Uri.parse("content://tabletlawyer.uk.tabletlawyer/"+fileName), "application/pdf");
                        try {
                            startActivity(i);
                        }
                        catch (ActivityNotFoundException e)
                        {
                            Toast.makeText(getActivity(), "NO Pdf Viewer", Toast.LENGTH_SHORT).show();
                        }

                    }
                    if(form.equals("Legal Aid CRM7 Non Standard Fee Assessment and Appeal"))
                    {
                        String fileName = "legal_aid_crm7_non_standard_fee_assessment_and_appeal.pdf";
                        i.setDataAndType(Uri.parse("content://tabletlawyer.uk.tabletlawyer/"+fileName), "application/pdf");
                        try {
                            startActivity(i);
                        }
                        catch (ActivityNotFoundException e)
                        {
                            Toast.makeText(getActivity(), "NO Pdf Viewer", Toast.LENGTH_SHORT).show();
                        }

                    }
                    if(form.equals("Legal Aid CRM8 Assigned Counsel"))
                    {
                        String fileName = "legal_aid_crm8_assigned_counsel.pdf";
                        i.setDataAndType(Uri.parse("content://tabletlawyer.uk.tabletlawyer/"+fileName), "application/pdf");
                        try {
                            startActivity(i);
                        }
                        catch (ActivityNotFoundException e)
                        {
                            Toast.makeText(getActivity(), "NO Pdf Viewer", Toast.LENGTH_SHORT).show();
                        }

                    }
                    if(form.equals("Legal Aid CRM11 Cost Summary"))
                    {
                        String fileName = "legal_aid_crm11_cost_summary.pdf";
                        i.setDataAndType(Uri.parse("content://tabletlawyer.uk.tabletlawyer/"+fileName), "application/pdf");
                        try {
                            startActivity(i);
                        }
                        catch (ActivityNotFoundException e)
                        {
                            Toast.makeText(getActivity(), "NO Pdf Viewer", Toast.LENGTH_SHORT).show();
                        }

                    }
                    if(form.equals("Legal Aid CRM14"))
                    {
                        String fileName = "legal_aid_CRM14.pdf";
                        i.setDataAndType(Uri.parse("content://tabletlawyer.uk.tabletlawyer/"+fileName), "application/pdf");
                        try {
                            startActivity(i);
                        }
                        catch (ActivityNotFoundException e)
                        {
                            Toast.makeText(getActivity(), "NO Pdf Viewer", Toast.LENGTH_SHORT).show();
                        }

                    }
                    if(form.equals("Legal Aid CRM15"))
                    {
                        String fileName = "legal_aid_CRM15.pdf";
                        i.setDataAndType(Uri.parse("content://tabletlawyer.uk.tabletlawyer/"+fileName), "application/pdf");
                        try {
                            startActivity(i);
                        }
                        catch (ActivityNotFoundException e)
                        {
                            Toast.makeText(getActivity(), "NO Pdf Viewer", Toast.LENGTH_SHORT).show();
                        }

                    }
                    if(form.equals("Legal Aid CRM15c"))
                    {
                        String fileName = "legal_aid_CRM15c.pdf";
                        i.setDataAndType(Uri.parse("content://tabletlawyer.uk.tabletlawyer/"+fileName), "application/pdf");
                        try {
                            startActivity(i);
                        }
                        catch (ActivityNotFoundException e)
                        {
                            Toast.makeText(getActivity(), "NO Pdf Viewer", Toast.LENGTH_SHORT).show();
                        }

                    }
                    if(form.equals("Legal Aid CRM18 Escape Fee Claim"))
                    {
                        String fileName = "legal_aid_crm18_escape_fee_claim.pdf";
                        i.setDataAndType(Uri.parse("content://tabletlawyer.uk.tabletlawyer/"+fileName), "application/pdf");
                        try {
                            startActivity(i);
                        }
                        catch (ActivityNotFoundException e)
                        {
                            Toast.makeText(getActivity(), "NO Pdf Viewer", Toast.LENGTH_SHORT).show();
                        }

                    }
                    if(form.equals("Legal Aid Declaration Criminal Online"))
                    {
                        String fileName = "legal_aid_declaration_criminal_online.pdf";
                        i.setDataAndType(Uri.parse("content://tabletlawyer.uk.tabletlawyer/"+fileName), "application/pdf");
                        try {
                            startActivity(i);
                        }
                        catch (ActivityNotFoundException e)
                        {
                            Toast.makeText(getActivity(), "NO Pdf Viewer", Toast.LENGTH_SHORT).show();
                        }

                    }
                    if(form.equals("Legal Aid Fixed Fee Claim"))
                    {
                        String fileName = "legal_aid_fixed_fee_claim.pdf";
                        i.setDataAndType(Uri.parse("content://tabletlawyer.uk.tabletlawyer/"+fileName), "application/pdf");
                        try {
                            startActivity(i);
                        }
                        catch (ActivityNotFoundException e)
                        {
                            Toast.makeText(getActivity(), "NO Pdf Viewer", Toast.LENGTH_SHORT).show();
                        }

                    }
                    if(form.equals("Legal Aid Graduated Fee Claim Form"))
                    {
                        String fileName = "legal_aid_graduated_fee_claim_form.pdf";
                        i.setDataAndType(Uri.parse("content://tabletlawyer.uk.tabletlawyer/"+fileName), "application/pdf");
                        try {
                            startActivity(i);
                        }
                        catch (ActivityNotFoundException e)
                        {
                            Toast.makeText(getActivity(), "NO Pdf Viewer", Toast.LENGTH_SHORT).show();
                        }

                    }
                    if(form.equals("Legal Aid Graduated Fee Claim Online"))
                    {
                        String fileName = "legal_aid_graduated_fee_claim_online.pdf";
                        i.setDataAndType(Uri.parse("content://tabletlawyer.uk.tabletlawyer/"+fileName), "application/pdf");
                        try {
                            startActivity(i);
                        }
                        catch (ActivityNotFoundException e)
                        {
                            Toast.makeText(getActivity(), "NO Pdf Viewer", Toast.LENGTH_SHORT).show();
                        }

                    }
                    if(form.equals("Legal Aid Guidance CRM14 and CRM15"))
                    {
                        String fileName = "legal_aid_guidance_CRM14_and_CRM15.pdf";
                        i.setDataAndType(Uri.parse("content://tabletlawyer.uk.tabletlawyer/"+fileName), "application/pdf");
                        try {
                            startActivity(i);
                        }
                        catch (ActivityNotFoundException e)
                        {
                            Toast.makeText(getActivity(), "NO Pdf Viewer", Toast.LENGTH_SHORT).show();
                        }

                    }
                    if(form.equals("Legal Aid Guidance for Reporting Crime Lower Work"))
                    {
                        String fileName = "legal_aid_guidance_for_reporting_crime_lower_work.pdf";
                        i.setDataAndType(Uri.parse("content://tabletlawyer.uk.tabletlawyer/"+fileName), "application/pdf");
                        try {
                            startActivity(i);
                        }
                        catch (ActivityNotFoundException e)
                        {
                            Toast.makeText(getActivity(), "NO Pdf Viewer", Toast.LENGTH_SHORT).show();
                        }

                    }
                    if(form.equals("Legal Aid Historic DCA Rates and Areas"))
                    {
                        String fileName = "legal_aid_historic_DCA_rates_and_areas.pdf";
                        i.setDataAndType(Uri.parse("content://tabletlawyer.uk.tabletlawyer/"+fileName), "application/pdf");
                        try {
                            startActivity(i);
                        }
                        catch (ActivityNotFoundException e)
                        {
                            Toast.makeText(getActivity(), "NO Pdf Viewer", Toast.LENGTH_SHORT).show();
                        }

                    }
                    if(form.equals("Legal Aid Interim Claim"))
                    {
                        String fileName = "legal_aid_interim_claim.pdf";
                        i.setDataAndType(Uri.parse("content://tabletlawyer.uk.tabletlawyer/"+fileName), "application/pdf");
                        try {
                            startActivity(i);
                        }
                        catch (ActivityNotFoundException e)
                        {
                            Toast.makeText(getActivity(), "NO Pdf Viewer", Toast.LENGTH_SHORT).show();
                        }

                    }
                    if(form.equals("Legal Aid LAC1 Committal Form"))
                    {
                        String fileName = "legal_aid_lac1_committal_form.pdf";
                        i.setDataAndType(Uri.parse("content://tabletlawyer.uk.tabletlawyer/"+fileName), "application/pdf");
                        try {
                            startActivity(i);
                        }
                        catch (ActivityNotFoundException e)
                        {
                            Toast.makeText(getActivity(), "NO Pdf Viewer", Toast.LENGTH_SHORT).show();
                        }

                    }
                    if(form.equals("Legal Aid Litigator Fee Review"))
                    {
                        String fileName = "legal_aid_litigator_fee_review.pdf";
                        i.setDataAndType(Uri.parse("content://tabletlawyer.uk.tabletlawyer/"+fileName), "application/pdf");
                        try {
                            startActivity(i);
                        }
                        catch (ActivityNotFoundException e)
                        {
                            Toast.makeText(getActivity(), "NO Pdf Viewer", Toast.LENGTH_SHORT).show();
                        }

                    }
                    if(form.equals("Legal Aid Special Prep Claim"))
                    {
                        String fileName = "legal_aid_special_prep_claim.pdf";
                        i.setDataAndType(Uri.parse("content://tabletlawyer.uk.tabletlawyer/"+fileName), "application/pdf");
                        try {
                            startActivity(i);
                        }
                        catch (ActivityNotFoundException e)
                        {
                            Toast.makeText(getActivity(), "NO Pdf Viewer", Toast.LENGTH_SHORT).show();
                        }

                    }

                }
                /**Open Court Forms Guidance*/
                else if(formType.equals("Guidance Notes for Court Forms")){

                    if(form.equals("Legal Aid AF1 Checklist"))
                    {
                        String fileName = "legal_aid_af1_checklist.pdf";
                        i.setDataAndType(Uri.parse("content://tabletlawyer.uk.tabletlawyer/"+fileName), "application/pdf");
                        try {
                            startActivity(i);
                        }
                        catch (ActivityNotFoundException e)
                        {
                            Toast.makeText(getActivity(), "NO Pdf Viewer", Toast.LENGTH_SHORT).show();
                        }
                    }
                    if(form.equals("Legal Aid AF1 Guidance"))
                    {
                        String fileName = "legal_aid_af1_guidance.pdf";
                        i.setDataAndType(Uri.parse("content://tabletlawyer.uk.tabletlawyer/"+fileName), "application/pdf");
                        try {
                            startActivity(i);
                        }
                        catch (ActivityNotFoundException e)
                        {
                            Toast.makeText(getActivity(), "NO Pdf Viewer", Toast.LENGTH_SHORT).show();
                        }
                    }
                    if(form.equals("Legal Aid CRM18a Guidance"))
                    {
                        String fileName = "legal_aid_crm18a_guidance.pdf";
                        i.setDataAndType(Uri.parse("content://tabletlawyer.uk.tabletlawyer/"+fileName), "application/pdf");
                        try {
                            startActivity(i);
                        }
                        catch (ActivityNotFoundException e)
                        {
                            Toast.makeText(getActivity(), "NO Pdf Viewer", Toast.LENGTH_SHORT).show();
                        }
                    }
                    if(form.equals("Legal Aid LAC1 Guidance"))
                    {
                        String fileName = "legal_aid_lac1_guidance.pdf";
                        i.setDataAndType(Uri.parse("content://tabletlawyer.uk.tabletlawyer/"+fileName), "application/pdf");
                        try {
                            startActivity(i);
                        }
                        catch (ActivityNotFoundException e)
                        {
                            Toast.makeText(getActivity(), "NO Pdf Viewer", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                /**Open Client Forms*/
                else
                listener.formSelected(form);

                return false;
            }
        });



    }


    public interface OnItemSelectedListener {
            public void formSelected(String Form);
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

    /*
      * Preparing the form list
      */
    private void prepareFormList() {
        listFormHeader = new ArrayList<String>();
        listFormChild = new HashMap<String, List<String>>();

        // Adding child data
        listFormHeader.add("Client Forms");
        listFormHeader.add("Court Forms");
        listFormHeader.add("Guidance Notes for Court Forms");


        // Adding child data
        List<String> clientForms = new ArrayList<String>();
        clientForms.add("Crim.CF001 Client Matter Form");
        clientForms.add("Crim.CF002 Case Dispatch Form");
        clientForms.add("Crim.CF003 Fee Earner Update");
        clientForms.add("Crim.CF004 Fee Earner Claim for Costs at Police Station Form");
        clientForms.add("Crim.CF005 Case Dispatch Form Plea Hearing");
        clientForms.add("Crim.CF006 Fee Earner Update Plea Hearing");
        clientForms.add("Crim.CF007 Fee Earner Claim for Costs at Plea Hearing");
        clientForms.add("Crim.CF008 Crown Court Case Prep Form");
        clientForms.add("Crim.CF009 Magistrates Court Case Prep Form");
        clientForms.add("Crim.CF010 Case Dispatch For Trial");
        clientForms.add("Crim.CF011 Fee Earner Update Trial");
        clientForms.add("Crim.CF012 Fee Earner Claim for Costs at Trial");
 //       clientForms.add("Crim.CF020 Client Due Diligence Risk Assessment Form");
 //       clientForms.add("Crim.CF025 File Opening Closing Form");
        clientForms.add("Crim.CF037 Client due diligence risk tables");
 //       clientForms.add("Crim.CF090 Money Laundering  ID precedent");
 //       clientForms.add("Crim.CF091 File  Note Precedent Form");
        clientForms.add("Crim.MISC001 Client Care Letter Criminal Legal Aid");
        clientForms.add("Crim.MISC002 Police Station Outcome NFA");
        clientForms.add("Crim.MISC003 Police Station Outcome Caution");
        clientForms.add("Crim.MISC004 Police Station Bailed with Date to Return to Police Station");
        clientForms.add("Crim.MISC005 Police Station Outcome Charged with Bail");
        clientForms.add("Crim.MISC006 Police Station Outcome Charged remain in Custody");
        clientForms.add("Crim.MISC007 Plea Hearing Outcome Case Adjourned with no Plea Given and Bail");
        clientForms.add("Crim.MISC008 Plea Hearing Outcome Case Adjourned with no Plea Given Detained in Custody");
        clientForms.add("Crim.MISC009 Plea Hearing Outcome to Guilty Plea and awaiting Pre-sentencing Report");
        clientForms.add("Crim.MISC010 Plea Hearing Outcome to Guilty Plea and Sentenced");
        clientForms.add("Crim.MISC011 Plea Hearing Outcome Not Guilty Plea and Remanded in Custody");
        clientForms.add("Crim.MISC012 Plea Hearing Outcome to Not Guilty Plea and Bailed");
        clientForms.add("Crim.MISC013 Custody Record Request Letter");
        clientForms.add("Crim.MISC014 Letter to Tape Librarian");
        clientForms.add("Crim.MISC015 Trial Hearing Outcome Case Adjourned with no conclusion Given and Bail");
        clientForms.add("Crim.MISC016 Trial Hearing Outcome Case Adjourned with no conclusion given Remanded in Custody");
        clientForms.add("Crim.MISC017 Trial Hearing Outcome to Guilty Verdict and awaiting Pre-sentencing Report");
        clientForms.add("Crim.MISC018 Trial Hearing Outcome to Guilty Verdict and sentenced");
        clientForms.add("Crim.MISC019 Trial Hearing Outcome to Not Guilty Verdict");
        clientForms.add("Crim.MISC020 Letter to Client regarding Legal Aid Application");





        List<String> courtForms = new ArrayList<String>();
        courtForms.add("AF1 Version 7 December 2013");
        courtForms.add("CRM18a Version 2 April 2013");
        courtForms.add("Legal Aid Advocate Fee Redetermination");
        courtForms.add("Legal Aid CRM1");
        courtForms.add("Legal Aid CRM7 Non Standard Fee Assessment and Appeal");
        courtForms.add("Legal Aid CRM8 Assigned Counsel");

        courtForms.add("Legal Aid CRM11 Cost Summary");
        courtForms.add("Legal Aid CRM14");
        courtForms.add("Legal Aid CRM15");
        courtForms.add("Legal Aid CRM15c");
        courtForms.add("Legal Aid CRM18 Escape Fee Claim");
        courtForms.add("Legal Aid Declaration Criminal Online");

        courtForms.add("Legal Aid Fixed Fee Claim");
        courtForms.add("Legal Aid Graduated Fee Claim Form");
        courtForms.add("Legal Aid Graduated Fee Claim Online");
        courtForms.add("Legal Aid Guidance CRM14 and CRM15");
        courtForms.add("Legal Aid Guidance for Reporting Crime Lower Work");
        courtForms.add("Legal Aid Historic DCA Rates and Areas");

        courtForms.add("Legal Aid Interim Claim");
        courtForms.add("Legal Aid LAC1 Committal Form");
        courtForms.add("Legal Aid Litigator Fee Review");
        courtForms.add("Legal Aid Special Prep Claim");

        List<String> guidanceNotes = new ArrayList<String>();
        guidanceNotes.add("Legal Aid AF1 Checklist");
        guidanceNotes.add("Legal Aid AF1 Guidance");
        guidanceNotes.add("Legal Aid CRM18a Guidance");
        guidanceNotes.add("Legal Aid LAC1 Guidance");


        listFormChild.put(listFormHeader.get(0), clientForms); // Header, Child data
        listFormChild.put(listFormHeader.get(1), courtForms);
        listFormChild.put(listFormHeader.get(2), guidanceNotes);

    }

}

