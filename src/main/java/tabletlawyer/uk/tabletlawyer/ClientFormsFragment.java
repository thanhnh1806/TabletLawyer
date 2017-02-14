package tabletlawyer.uk.tabletlawyer;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by niladreebhattacharjee on 05/11/2014.
 */
public class ClientFormsFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private View view;
    private OnItemSelectedListener listener;

    LinearLayout mContent;
    View mView;
    signature mSignature;
    Button mClear, mGetSign, mCancel;
    public static String tempDir;
    public String current = null;
    private Bitmap mBitmap;
    File mypath;
    private String uniqueId;

    // Connection detector class
    ConnectionDetector cd;
    // flag for Internet connection status
    Boolean isInternetPresent = false;

    private static String formSelected;
    private static String caseNoID;
    private static String formOption;
    private static String userType;
    private static EditText caseNumber;
    /**CF01*/
    private static EditText clientNameCF01;
    private static EditText presentDate;
    private static EditText dob;
    private static EditText addressCF01;
    private static Spinner policeStation;
    private static EditText custodyRecordNo;
    private static Spinner allegation;
    private static EditText incidentDate;
    private static EditText incidentTime;
    private static EditText coAccused;
    private static EditText callTime;
    private static EditText interviewTime;
    private static EditText officerView;
    private static Spinner previousClient;
    private static EditText dispatchDate;
    private static EditText dispatchTime;
    private static ArrayAdapter<CharSequence> adapter1CF1;
    private static ArrayAdapter<CharSequence> adapter2CF1;
    private static ArrayAdapter<CharSequence> adapter3CF1;

    /**CF02*/
    private static Spinner feeEarner;
    private static EditText pin;
    private static EditText solicitor;
    private static EditText distancePS;
    private static EditText eta;
    private static EditText dispatchDateFE;
    private static EditText dispatchTimeFE;
    private static EditText acceptDateFE;
    private static EditText acceptTimeFE;
    private static EditText allocateFE;
    private static ArrayAdapter<CharSequence> adapter1CF2;

    /**CF03*/
    private static EditText arrivalTimePS;
    private static EditText timeEngaged;
    private static EditText attestingOfficer;
    private static EditText arrestTime;
    private static EditText arrestPlace;
    private static EditText arrivalTimeP_S;
    private static EditText detentionTime;
    private static EditText officerInCase;
    private static EditText disclosureDetails;

    private static Spinner sample1;
    private static Spinner sample2;
    private static Spinner sample3;
    private static Spinner sample4;
    private static EditText sample5;
    private static EditText search1;
    private static Spinner search2;
    private static EditText search3;
    private static EditText search4;
    private static Spinner coAccused1;
    private static Spinner coAccused2;
    private static EditText coAccused3;
    private static Spinner coAccused4;

    private static EditText medicalCondition1;
    private static Spinner medicalCondition2;
    private static Spinner medicalCondition3;
    private static EditText medicalCondition4;
    private static Spinner medicalCondition5;
    private static EditText medicalCondition6;
    private static Spinner medicalCondition7;
    private static Spinner medicalCondition8;
    private static EditText medicalCondition8Detail;
    private static Spinner medicalCondition9;
    private static EditText medicalCondition9Detail;
    private static EditText medicalCondition10;
    private static Spinner medicalCondition11;
    private static Spinner medicalCondition12;

    private static EditText clientInstruction;

    private static Spinner question1;
    private static Spinner question2;
    private static Spinner question3;
    private static Spinner question4;
    private static Spinner question5;
    private static Spinner question6;
    private static Spinner question7;
    private static Spinner question8;
    private static Spinner question9;
    private static Spinner question10;
    private static Spinner question11;
    private static Spinner question12;
    private static Spinner question13;
    private static Spinner question14;
    private static Spinner question15;
    private static Spinner question16;
    private static Spinner question17;
    private static Spinner question18;
    private static Spinner question19;
    private static Spinner question20;
    private static Spinner question21;
    private static Spinner question22;

    private static EditText notesOfAdvice;
    private static EditText signRep;
    private static EditText signClient;
    private static EditText signInterpreter;
    private static String signRepContent ="";
    private static String signClientContent ="";
    private static String signInterpreterContent ="";
    private static ImageView signRepImage;
    private static ImageView signClientImage;
    private static ImageView signInterpreterImage;
    private static EditText printInterpreter;

    private static EditText interview1Date,interview2Date,interview3Date,interview4Date;
    private static EditText interview1StartTime,interview2StartTime,interview3StartTime,interview4StartTime;
    private static EditText interview1EndTime,interview2EndTime,interview3EndTime,interview4EndTime;
    private static EditText interview1Officer,interview2Officer,interview3Officer,interview4Officer;
    private static EditText interview1Others,interview2Others,interview3Others,interview4Others;
    private static EditText interview1Tape,interview2Tape,interview3Tape,interview4Tape;
    private static Spinner interview1Caution,interview2Caution,interview3Caution,interview4Caution;

    private static EditText noteInterview;

    private static EditText nfa;
    private static Spinner cautionOffence;

    private static EditText bailDate;
    private static EditText bailTime;
    private static Spinner bailLocation;
    private static Spinner bailType;
    private static EditText bailCondition;
    private static LinearLayout bailConditionLayout;
    private static EditText bailReason;

    private static EditText remandDate;
    private static EditText remandTime;
    private static Spinner remandLocation;

    private static Spinner chargedOffence;
    private static Spinner chargedBailType;
    private static EditText chargedCondition;
    private static LinearLayout chargedConditionLayout;
    private static EditText chargedDate;
    private static EditText chargedTime;
    private static Spinner chargedLocation;

    private static Spinner custodyOffence;
    private static EditText custodyDate;
    private static EditText custodyTime;
    private static Spinner custodyLocation;

    private static EditText hubDate,hubTime;
    private static Spinner clientCare,outcomeLetter;

    private static ArrayAdapter<CharSequence> adapter1CF3;
    private static ArrayAdapter<CharSequence> adapter2CF3;
    private static ArrayAdapter<CharSequence> adapter3CF3;
    private static ArrayAdapter<CharSequence> adapter4CF3;
    private static ArrayAdapter<CharSequence> adapter5CF3;
    /**CF04 and CF07*/
    private static EditText autoCalCF04,autoSolCF04;
    private static EditText autoCalCF07,autoSolCF07;
    /**CF05*/
    private static EditText dateCF05;
    private static EditText timeCF05;
    private static Spinner feeEarnerCF05;
    private static EditText pinCF05;
    private static EditText solicitorCF05;
    private static EditText legalAid;
    private static EditText acceptDateFECF05;
    private static EditText acceptTimeFECF05;
    /**CF06*/
    private static EditText arrivalCourt;
    private static EditText timeEngCF06;
    private static EditText clientInstCF06;
    private static Spinner investigation1;
    private static Spinner investigation2;
    private static Spinner investigation3;
    private static EditText investigation4;
    private static Spinner investigation5;
    private static Spinner investigation6;
    private static Spinner investigation7;
    private static Spinner investigation8;
    private static Spinner investigation9;
    private static Spinner investigation10;
    private static Spinner investigation11;
    private static Spinner investigation12;
    private static Spinner investigation13;
    private static EditText investigation14;

    private static Spinner adjournedReason;
    private static EditText adjournedDate;
    private static EditText adjournedTime;
    private static Spinner adjournedLocation;
    private static Spinner adjournedBail;
    private static Spinner adjournedBailType;
    private static EditText adjournedCondition;
    private static LinearLayout adjournedConditionLayout;

    private static EditText guiltyAwaitingCharges;
    private static EditText guiltyAwaitingDate;
    private static EditText guiltyAwaitingTime;
    private static Spinner guiltyAwaitingLocation;

    private static EditText guiltySentencedCharges;
    private static EditText guiltySentencedTerms;

    private static Spinner notGuiltyCustodyDetail1;
    private static Spinner notGuiltyCustodyDetail2;
    private static EditText notGuiltyCustodyDate;
    private static EditText notGuiltyCustodyTime;
    private static Spinner notGuiltyCustodyLocation;

    private static Spinner notGuiltyDetail1;
    private static Spinner notGuiltyDetail2;
    private static EditText notGuiltyDate;
    private static EditText notGuiltyTime;
    private static Spinner notGuiltyLocation;
    private static Spinner notGuiltyBailType;
    private static EditText notGuiltyConditions;
    private static LinearLayout notGuiltyConditionLayout;

    private static EditText hubDateCF06;
    private static EditText hubTimeCF06;
    private static Spinner outcomeLetterCF06;

    private static ArrayAdapter<CharSequence> adapter1CF6;
    /**CF08*/
    private static EditText convictionDateCF08;
    private static EditText casePreparedDateCF08;
    private static EditText listingOfficerDateCF08;
    private static EditText numberOfPagesCF08;
    private static EditText ongoingMatterNumberCF08;
    /**CF09*/
    private static EditText convictionDateCF09;
    private static EditText casePreparedDateCF09;
    private static EditText listingOfficerDateCF09;
    private static EditText numberOfPagesCF09;
    private static EditText ongoingMatterNumberCF09;
    /**CF10*/
    private static EditText dateCF10;
    private static EditText timeCF10;
    private static Spinner feeEarnerCF10;
    private static EditText pinCF10;
    private static EditText solicitorCF10;
    private static EditText legalAidCF10;
    private static EditText acceptDateFECF10;
    private static EditText acceptTimeFECF10;
    private static EditText courtBundleDate;
    private static EditText courtBundleTime;
    private static EditText courtBundleAcceptDate;
    private static EditText courtBundleAcceptTime;
    /**CF11*/
    private static EditText arrivalCourtCF11;
    private static EditText timeEngCF11;
    private static EditText clientCommentCF11;
    private static EditText adviceViewCF11;
    private static Spinner adjournedReasonCF11;
    private static EditText adjournedDateCF11;
    private static EditText adjournedTimeCF11;
    private static Spinner adjournedLocationCF11;
    private static Spinner adjournedBailCF11;
    private static Spinner adjournedBailTypeCF11;
    private static LinearLayout adjournedConditionLayoutCF11;
    private static EditText adjournedConditionCF11;

    private static Spinner awaitingIssuedCharges;
    private static Spinner awaitingUnfoundCharges;
    private static Spinner awaitingBailCF11;
    private static Spinner awaitingBailTypeCF11;
    private static LinearLayout awaitingConditionLayoutCF11;
    private static EditText awaitingConditionCF11;
    private static EditText guiltyAwaitingDateCF11;
    private static EditText guiltyAwaitingTimeCF11;
    private static Spinner guiltyAwaitingLocationCF11;

    private static Spinner sentencedIssuedCharges;
    private static Spinner sentencedUnfoundCharges;
    private static EditText sentenceReceived;
    private static EditText notGuiltyVerdict;

    private static EditText hubDateCF11;
    private static EditText hubTimeCF11;
    private static Spinner outcomeLetterCF11;
    /**CF12*/
    private static EditText autoCalViewCF12;
    private static EditText autoSolViewCF12;
    /**CF20*/
    /**CF25*/
    /**CF90*/
    /**CF91*/
    /**MISC01*/
    private static EditText dateMisc01;
    private static EditText caseHandler;
    private static EditText fe;
    private static EditText supervisor;
    private static EditText emailCH;
    private static EditText telephone01;
    private static EditText emailSuper;
    /**MISC02*/
    private static EditText dateMisc02;
    private static EditText telephone02;

    /**MISC03*/
    /**MISC04*/
    /**MISC05*/
    /**MISC06*/
    /**MISC07*/
    /**MISC08*/
    /**MISC09*/
    /**MISC10*/
    /**MISC11*/
    /**MISC12*/
    /**MISC13*/
    /**MISC14*/
    /**MISC15*/
    /**MISC16*/
    /**MISC17*/
    /**MISC18*/
    /**MISC19*/
    /**MISC20*/

    private static Button saveButton;
    private static Button deleteButton;

    // Progress Dialog
    private ProgressDialog pDialog;

    JSONParser jsonParser = new JSONParser();

    // url to create new form
  //  private static String url_create_client = "http://10.40.56.105/TabletLawyer/create_client.php";
    private static String url_create_client = "http://tabletlawyer.uk/tablet_lawyer/create_client.php";

    // single client detail url
    private static final String url_client_details = "http://tabletlawyer.uk/tablet_lawyer/get_client_details.php";

    // url to update client
    private static final String url_update_client = "http://tabletlawyer.uk/tablet_lawyer/update_client.php";

    // url to update form CF02
    private static final String url_update_formCF02 = "http://tabletlawyer.uk/tablet_lawyer/update_formCF02.php";

    // url to update form CF03
    private static final String url_update_formCF03 = "http://tabletlawyer.uk/tablet_lawyer/update_formCF03.php";

    // url to update form CF04
    private static final String url_update_formCF04 = "http://tabletlawyer.uk/tablet_lawyer/update_formCF04.php";

    // url to update form CF05
    private static final String url_update_formCF05 = "http://tabletlawyer.uk/tablet_lawyer/update_formCF05.php";

    // url to update form CF06
    private static final String url_update_formCF06 = "http://tabletlawyer.uk/tablet_lawyer/update_formCF06.php";

    // url to update form CF07
    private static final String url_update_formCF07 = "http://tabletlawyer.uk/tablet_lawyer/update_formCF07.php";

    // url to update form CF08
    private static final String url_update_formCF08 = "http://tabletlawyer.uk/tablet_lawyer/update_formCF08.php";

    // url to update form CF09
    private static final String url_update_formCF09 = "http://tabletlawyer.uk/tablet_lawyer/update_formCF09.php";

    // url to update form CF10
    private static final String url_update_formCF10 = "http://tabletlawyer.uk/tablet_lawyer/update_formCF10.php";

    // url to update form CF11
    private static final String url_update_formCF11 = "http://tabletlawyer.uk/tablet_lawyer/update_formCF11.php";

    // url to update form CF12
    private static final String url_update_formCF12 = "http://tabletlawyer.uk/tablet_lawyer/update_formCF12.php";

    // url to update form MISC01
    private static final String url_update_formMISC01 = "http://tabletlawyer.uk/tablet_lawyer/update_formMISC01.php";

    // url to delete client
    private static final String url_delete_client = "http://tabletlawyer.uk/tablet_lawyer/delete_client.php";


    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_CLIENT = "client";
    private static final String TAG_CLIENT_NAME = "ClientName";
    private static final String TAG_CASE_NO = "CaseNo";
    private static final String TAG_DATE = "Date";
    private static final String TAG_DOB = "DOB";
    private static final String TAG_ADDRESS = "Address";
    private static final String TAG_POLICE_STATION = "PoliceStation";
    private static final String TAG_CUSTODY_RECORD_NO = "CustodyRecordNo";
    private static final String TAG_ALLEGATION = "Allegation";
    private static final String TAG_INCIDENT_DATE_TIME = "IncidentDateTime";
    private static final String TAG_CO_ACCUSED = "CoAccused";
    private static final String TAG_INTERVIEW_TIME= "InterviewTime";
    private static final String TAG_CALL_TIME = "CallTime";
    private static final String TAG_OFFICER_IN_CHARGE = "OfficerInCharge";
    private static final String TAG_PREVIOUS_CLIENT = "PreviousClient";
    private static final String TAG_DISPATCH_DATE = "DispatchDate";
    private static final String TAG_DISPATCH_TIME = "DispatchTime";

    private static final String TAG_FEE_EARNER = "FeeEarner";
    private static final String TAG_PIN = "Pin";
    private static final String TAG_SOLICITOR = "Solicitor";
    private static final String TAG_DISTANCE_PS= "DistancePS";
    private static final String TAG_ETA= "Eta";
    private static final String TAG_DISPATCH_DATE_FE = "DispatchDateFE";
    private static final String TAG_DISPATCH_TIME_FE = "DispatchTimeFE";
    private static final String TAG_ACCEPT_DATE_FE = "AcceptDateFE";
    private static final String TAG_ACCEPT_TIME_FE = "AcceptTimeFE";
    private static final String TAG_ALLOCATE_FE = "AllocateFE";

    private static final String TAG_CF03_DATA = "CF03Data";
    private static final String TAG_SIGN_REP = "SignRep";
    private static final String TAG_SIGN_CLIENT = "SignClient";
    private static final String TAG_SIGN_INTER = "SignInter";
    private static final String TAG_CF04_DATA = "CF04Data";
    private static final String TAG_CF05_DATA = "CF05Data";
    private static final String TAG_CF06_DATA = "CF06Data";
    private static final String TAG_CF07_DATA = "CF07Data";
    private static final String TAG_CF08_DATA = "CF08Data";
    private static final String TAG_CF09_DATA = "CF09Data";
    private static final String TAG_CF10_DATA = "CF10Data";
    private static final String TAG_CF11_DATA = "CF11Data";
    private static final String TAG_CF12_DATA = "CF12Data";
    private static final String TAG_MISC01_DATA = "MISC01Data";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        // Inflate the layout for this fragment

        if(formSelected.equals("Crim.CF001 Client Matter Form"))
                view = inflater.inflate(R.layout.client_form01, container, false);

        if(formSelected.equals("Crim.CF002 Case Dispatch Form"))
                view = inflater.inflate(R.layout.client_form02, container, false);

        if(formSelected.equals("Crim.CF003 Fee Earner Update"))
                view = inflater.inflate(R.layout.client_form03, container, false);

        if(formSelected.equals("Crim.CF004 Fee Earner Claim for Costs at Police Station Form"))
                view = inflater.inflate(R.layout.client_form04, container, false);

        if(formSelected.equals("Crim.CF005 Case Dispatch Form Plea Hearing"))
            view = inflater.inflate(R.layout.client_form05, container, false);

        if(formSelected.equals("Crim.CF006 Fee Earner Update Plea Hearing"))
            view = inflater.inflate(R.layout.client_form06, container, false);

        if(formSelected.equals("Crim.CF007 Fee Earner Claim for Costs at Plea Hearing"))
            view = inflater.inflate(R.layout.client_form07, container, false);

        if(formSelected.equals("Crim.CF008 Crown Court Case Prep Form"))
            view = inflater.inflate(R.layout.client_form08, container, false);

        if(formSelected.equals("Crim.CF009 Magistrates Court Case Prep Form"))
            view = inflater.inflate(R.layout.client_form09, container, false);

        if(formSelected.equals("Crim.CF010 Case Dispatch For Trial"))
            view = inflater.inflate(R.layout.client_form10, container, false);

        if(formSelected.equals("Crim.CF011 Fee Earner Update Trial"))
            view = inflater.inflate(R.layout.client_form11, container, false);

        if(formSelected.equals("Crim.CF012 Fee Earner Claim for Costs at Trial"))
            view = inflater.inflate(R.layout.client_form12, container, false);

        if(formSelected.equals("Crim.CF020 Client Due Diligence Risk Assessment Form"))
            view = inflater.inflate(R.layout.client_form20, container, false);

        if(formSelected.equals("Crim.CF025 File Opening Closing Form"))
            view = inflater.inflate(R.layout.client_form25, container, false);

        if(formSelected.equals("Crim.CF037 Client due diligence risk tables"))
            view = inflater.inflate(R.layout.client_form37, container, false);

        if(formSelected.equals("Crim.CF090 Money Laundering  ID precedent"))
            view = inflater.inflate(R.layout.client_form90, container, false);

        if(formSelected.equals("Crim.CF091 File  Note Precedent Form"))
            view = inflater.inflate(R.layout.client_form91, container, false);

        if(formSelected.equals("Crim.MISC001 Client Care Letter Criminal Legal Aid"))
            view = inflater.inflate(R.layout.client_misc_form01, container, false);

        if(formSelected.equals("Crim.MISC002 Police Station Outcome NFA"))
            view = inflater.inflate(R.layout.client_misc_form02, container, false);

        if(formSelected.equals("Crim.MISC003 Police Station Outcome Caution"))
            view = inflater.inflate(R.layout.client_misc_form03, container, false);

        if(formSelected.equals("Crim.MISC004 Police Station Bailed with Date to Return to Police Station"))
            view = inflater.inflate(R.layout.client_misc_form04, container, false);

        if(formSelected.equals("Crim.MISC005 Police Station Outcome Charged with Bail"))
            view = inflater.inflate(R.layout.client_misc_form05, container, false);

        if(formSelected.equals("Crim.MISC006 Police Station Outcome Charged remain in Custody"))
            view = inflater.inflate(R.layout.client_misc_form06, container, false);

        if(formSelected.equals("Crim.MISC007 Plea Hearing Outcome Case Adjourned with no Plea Given and Bail"))
            view = inflater.inflate(R.layout.client_misc_form07, container, false);

        if(formSelected.equals("Crim.MISC008 Plea Hearing Outcome Case Adjourned with no Plea Given Detained in Custody"))
            view = inflater.inflate(R.layout.client_misc_form08, container, false);

        if(formSelected.equals("Crim.MISC009 Plea Hearing Outcome to Guilty Plea and awaiting Pre-sentencing Report"))
            view = inflater.inflate(R.layout.client_misc_form09, container, false);

        if(formSelected.equals("Crim.MISC010 Plea Hearing Outcome to Guilty Plea and Sentenced"))
            view = inflater.inflate(R.layout.client_misc_form10, container, false);

        if(formSelected.equals("Crim.MISC011 Plea Hearing Outcome Not Guilty Plea and Remanded in Custody"))
            view = inflater.inflate(R.layout.client_misc_form11, container, false);

        if(formSelected.equals("Crim.MISC012 Plea Hearing Outcome to Not Guilty Plea and Bailed"))
            view = inflater.inflate(R.layout.client_misc_form12, container, false);

        if(formSelected.equals("Crim.MISC013 Custody Record Request Letter"))
            view = inflater.inflate(R.layout.client_misc_form13, container, false);

        if(formSelected.equals("Crim.MISC014 Letter to Tape Librarian"))
            view = inflater.inflate(R.layout.client_misc_form14, container, false);

        if(formSelected.equals("Crim.MISC015 Trial Hearing Outcome Case Adjourned with no conclusion Given and Bail"))
            view = inflater.inflate(R.layout.client_misc_form15, container, false);

        if(formSelected.equals("Crim.MISC016 Trial Hearing Outcome Case Adjourned with no conclusion given Remanded in Custody"))
            view = inflater.inflate(R.layout.client_misc_form16, container, false);

        if(formSelected.equals("Crim.MISC017 Trial Hearing Outcome to Guilty Verdict and awaiting Pre-sentencing Report"))
            view = inflater.inflate(R.layout.client_misc_form17, container, false);

        if(formSelected.equals("Crim.MISC018 Trial Hearing Outcome to Guilty Verdict and sentenced"))
            view = inflater.inflate(R.layout.client_misc_form18, container, false);

        if(formSelected.equals("Crim.MISC019 Trial Hearing Outcome to Not Guilty Verdict"))
            view = inflater.inflate(R.layout.client_misc_form19, container, false);

        if(formSelected.equals("Crim.MISC020 Letter to Client regarding Legal Aid Application"))
            view = inflater.inflate(R.layout.client_misc_form20, container, false);

        cd = new ConnectionDetector(getActivity().getApplicationContext());

        return view;
    }



    public interface OnItemSelectedListener {
        public void showClients();

    }

    @Override
    public void onActivityCreated(Bundle savedInstancestate){
        super.onActivityCreated(savedInstancestate);

        ///Avoid android.os.NetworkOnMainThreadException
        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        //Today's Date
        final Calendar c = Calendar.getInstance();
        final int todaysYear = c.get(Calendar.YEAR);
        final int todaysMonth = c.get(Calendar.MONTH);
        final int todaysDay = c.get(Calendar.DAY_OF_MONTH);
        //Today's Time
        final int todaysHour = c.get(Calendar.HOUR_OF_DAY);
        final int todaysMinute = c.get(Calendar.MINUTE);



        userType = (String) getActivity().getIntent().getCharSequenceExtra("userType");
        saveButton = (Button) view.findViewById(R.id.saveButton);



        if(formSelected.equals("Crim.CF001 Client Matter Form")) {
            deleteButton = (Button)view.findViewById(R.id.deleteButton);

            clientNameCF01 = (EditText) view.findViewById(R.id.clientNameView);
            caseNumber = (EditText) view.findViewById(R.id.caseNoView);
            caseNumber.setEnabled(false);
            presentDate = (EditText) view.findViewById(R.id.dateView);
            presentDate.setEnabled(false);
            dob = (EditText) view.findViewById(R.id.dobView);
            addressCF01 = (EditText) view.findViewById(R.id.addressView);
            policeStation = (Spinner) view.findViewById(R.id.psView);
            policeStation.setFocusable(true);
            custodyRecordNo = (EditText) view.findViewById(R.id.crNoView);
            allegation = (Spinner) view.findViewById(R.id.allegationView);
            allegation.setFocusable(true);
            incidentDate = (EditText) view.findViewById(R.id.incidentDate);
            incidentTime = (EditText) view.findViewById(R.id.incidentTime);
            coAccused = (EditText) view.findViewById(R.id.coAccused);
            callTime = (EditText) view.findViewById(R.id.callTimeView);
            interviewTime = (EditText) view.findViewById(R.id.interviewTimeView);
            officerView = (EditText) view.findViewById(R.id.officerView);
            previousClient = (Spinner) view.findViewById(R.id.clientSpinner);
            previousClient.setFocusable(true);
            dispatchDate = (EditText) view.findViewById(R.id.dispatchDateView);
            dispatchDate.setEnabled(false);
            dispatchTime = (EditText) view.findViewById(R.id.dispatchTimeView);
            dispatchTime.setEnabled(false);

            adapter1CF1 = ArrayAdapter.createFromResource(getActivity(), R.array.policeStationArray, android.R.layout.simple_spinner_item);
            adapter1CF1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            policeStation.setAdapter(adapter1CF1);
            policeStation.setOnItemSelectedListener(this);

            adapter2CF1 = ArrayAdapter.createFromResource(getActivity(), R.array.allegationArray, android.R.layout.simple_spinner_item);
            adapter2CF1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            allegation.setAdapter(adapter2CF1);
            allegation.setOnItemSelectedListener(this);

            adapter3CF1 = ArrayAdapter.createFromResource(getActivity(), R.array.noYesArray, android.R.layout.simple_spinner_item);
            adapter3CF1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            previousClient.setAdapter(adapter3CF1);
            previousClient.setOnItemSelectedListener(this);
        }
        if(formSelected.equals("Crim.CF002 Case Dispatch Form")) {
            clientNameCF01 = (EditText) view.findViewById(R.id.clientNameView);
            caseNumber = (EditText) view.findViewById(R.id.caseNoView);
            caseNumber.setEnabled(false);
            dispatchDate = (EditText) view.findViewById(R.id.dispatchDateView);
            dispatchTime = (EditText) view.findViewById(R.id.dispatchTimeView);
            dispatchDate.setEnabled(false);
            dispatchTime.setEnabled(false);
            clientNameCF01.setEnabled(false);
            policeStation = (Spinner) view.findViewById(R.id.psView);
            policeStation.setEnabled(false);
            feeEarner = (Spinner)view.findViewById(R.id.feeEarnerView);
            feeEarner.setFocusable(true);
            pin = (EditText)view.findViewById(R.id.pinView);
            pin.setEnabled(false);
            solicitor = (EditText)view.findViewById(R.id.solicitorView);
            solicitor.setEnabled(false);
            distancePS = (EditText)view.findViewById(R.id.distancePSView);
            eta = (EditText) view.findViewById(R.id.etaView);
            dispatchDateFE = (EditText) view.findViewById(R.id.dispatchDateFE);
            dispatchDateFE.setEnabled(false);
            dispatchTimeFE = (EditText) view.findViewById(R.id.dispatchTimeFE);
            dispatchTimeFE.setEnabled(false);
            acceptDateFE = (EditText) view.findViewById(R.id.acceptDateFE);
            acceptDateFE.setEnabled(false);
            acceptTimeFE = (EditText) view.findViewById(R.id.acceptTimeFE);
            acceptTimeFE.setEnabled(false);
            allocateFE = (EditText) view.findViewById(R.id.allocateFE);
            allocateFE.setEnabled(false);

            adapter1CF1 = ArrayAdapter.createFromResource(getActivity(), R.array.policeStationArray, android.R.layout.simple_spinner_item);
            adapter1CF1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            policeStation.setAdapter(adapter1CF1);
            policeStation.setOnItemSelectedListener(this);

            adapter1CF2 = ArrayAdapter.createFromResource(getActivity(), R.array.feeEarnerArray, android.R.layout.simple_spinner_item);
            adapter1CF2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            feeEarner.setAdapter(adapter1CF2);
            feeEarner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    //set value for pin
                    pin.setText(String.valueOf(feeEarner.getSelectedItemPosition()));
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }
        if(formSelected.equals("Crim.CF003 Fee Earner Update")) {
            clientNameCF01 = (EditText) view.findViewById(R.id.clientNameView);
            caseNumber = (EditText) view.findViewById(R.id.caseNoView);
            caseNumber.setEnabled(false);
            clientNameCF01.setEnabled(false);
            dob = (EditText) view.findViewById(R.id.dobView);
            dob.setEnabled(false);
            addressCF01 = (EditText) view.findViewById(R.id.addressView);
            addressCF01.setEnabled(false);
            feeEarner = (Spinner)view.findViewById(R.id.feeEarnerView);
            feeEarner.setEnabled(false);
            dispatchDateFE = (EditText) view.findViewById(R.id.dispatchDateFE);
            dispatchDateFE.setEnabled(false);
            dispatchTimeFE = (EditText) view.findViewById(R.id.dispatchTimeFE);
            dispatchTimeFE.setEnabled(false);
            arrivalTimePS = (EditText)view.findViewById(R.id.arrivalTimePS);
            timeEngaged = (EditText)view.findViewById(R.id.timeEngView);
            custodyRecordNo = (EditText) view.findViewById(R.id.crNoView);
            custodyRecordNo.setEnabled(false);
            attestingOfficer = (EditText)view.findViewById(R.id.attestingOfficer);
            arrestTime = (EditText)view.findViewById(R.id.arrestTime);
            arrestPlace = (EditText)view.findViewById(R.id.arrestPlace);
            arrivalTimeP_S = (EditText)view.findViewById(R.id.arrivalTimeP_S);
            detentionTime = (EditText)view.findViewById(R.id.detentionTime);
            officerInCase = (EditText)view.findViewById(R.id.officerInCase);
            disclosureDetails = (EditText)view.findViewById(R.id.disclosureDetails);
            sample1 = (Spinner)view.findViewById(R.id.sample1);
            sample1.setFocusable(true);
            sample2 = (Spinner)view.findViewById(R.id.sample2);
            sample2.setFocusable(true);
            sample3 = (Spinner)view.findViewById(R.id.sample3);
            sample3.setFocusable(true);
            sample4 = (Spinner)view.findViewById(R.id.sample4);
            sample4.setFocusable(true);
            sample5 = (EditText)view.findViewById(R.id.sample5);
            search1 = (EditText)view.findViewById(R.id.search1);
            search2 = (Spinner)view.findViewById(R.id.search2);
            search2.setFocusable(true);
            search3 = (EditText)view.findViewById(R.id.search3);
            search4 = (EditText)view.findViewById(R.id.search4);
            coAccused1 = (Spinner)view.findViewById(R.id.coac1);
            coAccused1.setFocusable(true);
            coAccused2 = (Spinner)view.findViewById(R.id.coac2);
            coAccused2.setFocusable(true);
            coAccused3 = (EditText)view.findViewById(R.id.coac3);
            coAccused4 = (Spinner)view.findViewById(R.id.coac4);
            coAccused4.setFocusable(true);
            medicalCondition1 = (EditText)view.findViewById(R.id.medcon1);
            medicalCondition2 = (Spinner)view.findViewById(R.id.medcon2);
            medicalCondition2.setFocusable(true);
            medicalCondition3 = (Spinner)view.findViewById(R.id.medcon3);
            medicalCondition3.setFocusable(true);
            medicalCondition4 = (EditText)view.findViewById(R.id.medcon4);
            medicalCondition5 = (Spinner)view.findViewById(R.id.medcon5);
            medicalCondition5.setFocusable(true);
            medicalCondition6 = (EditText)view.findViewById(R.id.medcon6);
            medicalCondition7 = (Spinner)view.findViewById(R.id.medcon7);
            medicalCondition7.setFocusable(true);
            medicalCondition8 = (Spinner)view.findViewById(R.id.medcon8);
            medicalCondition8.setFocusable(true);
            medicalCondition8Detail = (EditText)view.findViewById(R.id.medcon8Detail);
            medicalCondition9 = (Spinner)view.findViewById(R.id.medcon9);
            medicalCondition9.setFocusable(true);
            medicalCondition9Detail = (EditText)view.findViewById(R.id.medcon9Detail);
            medicalCondition10 = (EditText)view.findViewById(R.id.medcon10);
            medicalCondition11 = (Spinner)view.findViewById(R.id.medcon11);
            medicalCondition11.setFocusable(true);
            medicalCondition12 = (Spinner)view.findViewById(R.id.medcon12);
            medicalCondition12.setFocusable(true);
            clientInstruction = (EditText)view.findViewById(R.id.clientInstruction);
            question1 = (Spinner)view.findViewById(R.id.question1);
            question1.setFocusable(true);
            question2 = (Spinner)view.findViewById(R.id.question2);
            question2.setFocusable(true);
            question3 = (Spinner)view.findViewById(R.id.question3);
            question3.setFocusable(true);
            question4 = (Spinner)view.findViewById(R.id.question4);
            question4.setFocusable(true);
            question5 = (Spinner)view.findViewById(R.id.question5);
            question5.setFocusable(true);
            question6 = (Spinner)view.findViewById(R.id.question6);
            question6.setFocusable(true);
            question7 = (Spinner)view.findViewById(R.id.question7);
            question7.setFocusable(true);
            question8 = (Spinner)view.findViewById(R.id.question8);
            question8.setFocusable(true);
            question9 = (Spinner)view.findViewById(R.id.question9);
            question9.setFocusable(true);
            question10 = (Spinner)view.findViewById(R.id.question10);
            question10.setFocusable(true);
            question11 = (Spinner)view.findViewById(R.id.question11);
            question11.setFocusable(true);
            question12 = (Spinner)view.findViewById(R.id.question12);
            question12.setFocusable(true);
            question13 = (Spinner)view.findViewById(R.id.question13);
            question13.setFocusable(true);
            question14 = (Spinner)view.findViewById(R.id.question14);
            question14.setFocusable(true);
            question15 = (Spinner)view.findViewById(R.id.question15);
            question15.setFocusable(true);
            question16 = (Spinner)view.findViewById(R.id.question16);
            question16.setFocusable(true);
            question17 = (Spinner)view.findViewById(R.id.question17);
            question17.setFocusable(true);
            question18 = (Spinner)view.findViewById(R.id.question18);
            question18.setFocusable(true);
            question19 = (Spinner)view.findViewById(R.id.question19);
            question19.setFocusable(true);
            question20 = (Spinner)view.findViewById(R.id.question20);
            question20.setFocusable(true);
            question21 = (Spinner)view.findViewById(R.id.question21);
            question21.setFocusable(true);
            question22 = (Spinner)view.findViewById(R.id.question22);
            question22.setFocusable(true);
            notesOfAdvice = (EditText)view.findViewById(R.id.notesOfAdvice);
            signRep = (EditText)view.findViewById(R.id.signRep);
            signRep.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    showPanel("signRep");
                }
            });
            signRep.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean hasFocus) {
                    if (hasFocus) {
                        showPanel("signRep");
                    }
                }
            });
            signClient = (EditText)view.findViewById(R.id.signClient);
            signClient.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                   showPanel("signClient");
                }
            });
            signClient.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean hasFocus) {
                    if (hasFocus) {
                        showPanel("signClient");
                    }
                }
            });
            signInterpreter = (EditText)view.findViewById(R.id.signInterpreter);
            signInterpreter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    showPanel("signInterpreter");
                }
            });
            signInterpreter.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean hasFocus) {
                    if (hasFocus) {
                        showPanel("signInterpreter");
                    }
                }
            });
            signRepImage = (ImageView)view.findViewById(R.id.signRepImage);
            signClientImage = (ImageView)view.findViewById(R.id.signClientImage);
            signInterpreterImage = (ImageView)view.findViewById(R.id.signInterpreterImage);
            printInterpreter = (EditText)view.findViewById(R.id.printInterpreter);
            interview1Date = (EditText)view.findViewById(R.id.interview1Date);
            interview1StartTime = (EditText)view.findViewById(R.id.interview1StartTime);
            interview1EndTime = (EditText)view.findViewById(R.id.interview1EndTime);
            interview1Officer = (EditText)view.findViewById(R.id.interview1Officer);
            interview1Others = (EditText)view.findViewById(R.id.interview1Others);
            interview1Tape = (EditText)view.findViewById(R.id.interview1Tape);
            interview1Caution = (Spinner)view.findViewById(R.id.interview1Caution);
            interview1Caution.setFocusable(true);
            interview2Date = (EditText)view.findViewById(R.id.interview2Date);
            interview2StartTime = (EditText)view.findViewById(R.id.interview2StartTime);
            interview2EndTime = (EditText)view.findViewById(R.id.interview2EndTime);
            interview2Officer = (EditText)view.findViewById(R.id.interview2Officer);
            interview2Others = (EditText)view.findViewById(R.id.interview2Others);
            interview2Tape = (EditText)view.findViewById(R.id.interview2Tape);
            interview2Caution = (Spinner)view.findViewById(R.id.interview2Caution);
            interview2Caution.setFocusable(true);
            interview3Date = (EditText)view.findViewById(R.id.interview3Date);
            interview3StartTime = (EditText)view.findViewById(R.id.interview3StartTime);
            interview3EndTime = (EditText)view.findViewById(R.id.interview3EndTime);
            interview3Officer = (EditText)view.findViewById(R.id.interview3Officer);
            interview3Others = (EditText)view.findViewById(R.id.interview3Others);
            interview3Tape = (EditText)view.findViewById(R.id.interview3Tape);
            interview3Caution = (Spinner)view.findViewById(R.id.interview3Caution);
            interview3Caution.setFocusable(true);
            interview4Date = (EditText)view.findViewById(R.id.interview4Date);
            interview4StartTime = (EditText)view.findViewById(R.id.interview4StartTime);
            interview4EndTime = (EditText)view.findViewById(R.id.interview4EndTime);
            interview4Officer = (EditText)view.findViewById(R.id.interview4Officer);
            interview4Others = (EditText)view.findViewById(R.id.interview4Others);
            interview4Tape = (EditText)view.findViewById(R.id.interview4Tape);
            interview4Caution = (Spinner)view.findViewById(R.id.interview4Caution);
            interview4Caution.setFocusable(true);

            noteInterview = (EditText)view.findViewById(R.id.noteInterview);
            nfa = (EditText)view.findViewById(R.id.nfaView);
            cautionOffence = (Spinner)view.findViewById(R.id.cautionOffence);
            cautionOffence.setFocusable(true);
            bailDate = (EditText)view.findViewById(R.id.bailDate);
            bailTime = (EditText)view.findViewById(R.id.bailTime);
            bailLocation = (Spinner)view.findViewById(R.id.bailLocation);
            bailLocation.setFocusable(true);
            bailType = (Spinner)view.findViewById(R.id.bail);
            bailType.setFocusable(true);
            bailCondition = (EditText)view.findViewById(R.id.bailCondition);
            bailConditionLayout = (LinearLayout)view.findViewById(R.id.bailConditionLayout);
            bailReason = (EditText)view.findViewById(R.id.bailReason);
            remandDate = (EditText)view.findViewById(R.id.remandDate);
            remandTime = (EditText)view.findViewById(R.id.remandTime);
            remandLocation = (Spinner)view.findViewById(R.id.remandLocation);
            remandLocation.setFocusable(true);
            chargedOffence = (Spinner)view.findViewById(R.id.chargedOffence);
            chargedOffence.setFocusable(true);
            chargedBailType = (Spinner)view.findViewById(R.id.chargedBail);
            chargedBailType.setFocusable(true);
            chargedCondition = (EditText)view.findViewById(R.id.chargedConditions);
            chargedConditionLayout = (LinearLayout)view.findViewById(R.id.chargedConditionLayout);
            chargedDate = (EditText)view.findViewById(R.id.chargedDate);
            chargedTime = (EditText)view.findViewById(R.id.chargedTime);
            chargedLocation = (Spinner)view.findViewById(R.id.chargedLocation);
            chargedLocation.setFocusable(true);
            custodyOffence = (Spinner)view.findViewById(R.id.custodyOffence);
            custodyOffence.setFocusable(true);
            custodyDate = (EditText)view.findViewById(R.id.custodyDate);
            custodyTime = (EditText)view.findViewById(R.id.custodyTime);
            custodyLocation = (Spinner)view.findViewById(R.id.custodyLocation);
            custodyLocation.setFocusable(true);

            hubDate = (EditText)view.findViewById(R.id.hubDate);
            hubDate.setEnabled(false);
            hubTime = (EditText)view.findViewById(R.id.hubTime);
            hubTime.setEnabled(false);
            clientCare = (Spinner)view.findViewById(R.id.clientCare);
            clientCare.setFocusable(true);
            outcomeLetter = (Spinner)view.findViewById(R.id.outcomeLetter);
            outcomeLetter.setFocusable(true);

            adapter1CF2 = ArrayAdapter.createFromResource(getActivity(), R.array.feeEarnerArray, android.R.layout.simple_spinner_item);
            adapter1CF2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            feeEarner.setAdapter(adapter1CF2);

            adapter3CF1 = ArrayAdapter.createFromResource(getActivity(), R.array.noYesArray, android.R.layout.simple_spinner_item);
            adapter3CF1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sample1.setAdapter(adapter3CF1);
            sample2.setAdapter(adapter3CF1);
            sample4.setAdapter(adapter3CF1);
            coAccused1.setAdapter(adapter3CF1);
            coAccused4.setAdapter(adapter3CF1);
            medicalCondition2.setAdapter(adapter3CF1);
            medicalCondition7.setAdapter(adapter3CF1);
            medicalCondition8.setAdapter(adapter3CF1);
            medicalCondition8.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if(medicalCondition8.getSelectedItem().toString().equals("Yes"))
                    medicalCondition8Detail.setVisibility(View.VISIBLE);
                    if(medicalCondition8.getSelectedItem().toString().equals("No"))
                        medicalCondition8Detail.setVisibility(View.GONE);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            medicalCondition9.setAdapter(adapter3CF1);
            medicalCondition9.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if(medicalCondition9.getSelectedItem().toString().equals("Yes"))
                    medicalCondition9Detail.setVisibility(View.VISIBLE);
                    if(medicalCondition9.getSelectedItem().toString().equals("No"))
                        medicalCondition9Detail.setVisibility(View.GONE);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            medicalCondition12.setAdapter(adapter3CF1);
            question1.setAdapter(adapter3CF1);
            question2.setAdapter(adapter3CF1);
            question3.setAdapter(adapter3CF1);
            question4.setAdapter(adapter3CF1);
            question5.setAdapter(adapter3CF1);
            question6.setAdapter(adapter3CF1);
            question7.setAdapter(adapter3CF1);
            question8.setAdapter(adapter3CF1);
            question11.setAdapter(adapter3CF1);
            question12.setAdapter(adapter3CF1);
            question14.setAdapter(adapter3CF1);
            question15.setAdapter(adapter3CF1);
            question18.setAdapter(adapter3CF1);
            interview1Caution.setAdapter(adapter3CF1);
            interview2Caution.setAdapter(adapter3CF1);
            interview3Caution.setAdapter(adapter3CF1);
            interview4Caution.setAdapter(adapter3CF1);
            clientCare.setAdapter(adapter3CF1);
            outcomeLetter.setAdapter(adapter3CF1);


            adapter1CF3 = ArrayAdapter.createFromResource(getActivity(), R.array.sampleArray, android.R.layout.simple_spinner_item);
            adapter1CF3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sample3.setAdapter(adapter1CF3);

            adapter2CF3 = ArrayAdapter.createFromResource(getActivity(), R.array.searchArray, android.R.layout.simple_spinner_item);
            adapter2CF3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            search2.setAdapter(adapter2CF3);


            adapter3CF3 = ArrayAdapter.createFromResource(getActivity(), R.array.coAccusedArray, android.R.layout.simple_spinner_item);
            adapter3CF3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            coAccused2.setAdapter(adapter3CF3);


            adapter4CF3 = ArrayAdapter.createFromResource(getActivity(), R.array.noYesNAArray, android.R.layout.simple_spinner_item);
            adapter4CF3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            medicalCondition3.setAdapter(adapter4CF3);
            medicalCondition5.setAdapter(adapter4CF3);
            medicalCondition11.setAdapter(adapter4CF3);
            question9.setAdapter(adapter4CF3);
            question10.setAdapter(adapter4CF3);
            question13.setAdapter(adapter4CF3);
            question16.setAdapter(adapter4CF3);
            question17.setAdapter(adapter4CF3);
            question19.setAdapter(adapter4CF3);
            question20.setAdapter(adapter4CF3);
            question21.setAdapter(adapter4CF3);
            question22.setAdapter(adapter4CF3);


            adapter2CF1 = ArrayAdapter.createFromResource(getActivity(), R.array.allegationArray, android.R.layout.simple_spinner_item);
            adapter2CF1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            cautionOffence.setAdapter(adapter2CF1);
            chargedOffence.setAdapter(adapter2CF1);
            custodyOffence.setAdapter(adapter2CF1);

            adapter1CF1 = ArrayAdapter.createFromResource(getActivity(), R.array.policeStationArray, android.R.layout.simple_spinner_item);
            adapter1CF1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            bailLocation.setAdapter(adapter1CF1);
            remandLocation.setAdapter(adapter1CF1);
            chargedLocation.setAdapter(adapter1CF1);
            custodyLocation.setAdapter(adapter1CF1);

            adapter5CF3 = ArrayAdapter.createFromResource(getActivity(), R.array.bailArray, android.R.layout.simple_spinner_item);
            adapter5CF3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            bailType.setAdapter(adapter5CF3);
            bailType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if(bailType.getSelectedItem().toString().equals("Conditional"))
                        bailConditionLayout.setVisibility(View.VISIBLE);
                    if(bailType.getSelectedItem().toString().equals("Unconditional"))
                        bailConditionLayout.setVisibility(View.GONE);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            chargedBailType.setAdapter(adapter5CF3);
            chargedBailType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if(chargedBailType.getSelectedItem().toString().equals("Conditional"))
                        chargedConditionLayout.setVisibility(View.VISIBLE);
                    if(chargedBailType.getSelectedItem().toString().equals("Unconditional"))
                        chargedConditionLayout.setVisibility(View.GONE);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });


        }
        if (formSelected.equals("Crim.CF004 Fee Earner Claim for Costs at Police Station Form")) {
            caseNumber = (EditText) view.findViewById(R.id.caseNoView);
            caseNumber.setEnabled(false);
            autoCalCF04 = (EditText) view.findViewById(R.id.autoCalView04);
            autoSolCF04 = (EditText) view.findViewById(R.id.autoSolView04);
        }
        if (formSelected.equals("Crim.CF005 Case Dispatch Form Plea Hearing")) {
            dateCF05 = (EditText) view.findViewById(R.id.dateCF05);
            dateCF05.setText(new StringBuilder().append(todaysDay).append("-")
                                                .append(todaysMonth + 1).append("-")
                                                .append(todaysYear).append(" "));
            timeCF05 = (EditText) view.findViewById(R.id.timeCF05);
            timeCF05.setText(new StringBuilder().append(todaysHour)
                                                .append(":").append(todaysMinute)
                                                .append(" "));
            caseNumber = (EditText) view.findViewById(R.id.caseNoView);
            clientNameCF01 = (EditText) view.findViewById(R.id.clientNameView);
            /*court need to be resolved*/
            hubDate = (EditText) view.findViewById(R.id.hubDate);
            hubTime = (EditText) view.findViewById(R.id.hubTime);
            feeEarnerCF05 = (Spinner) view.findViewById(R.id.feeEarnerCF05);
            feeEarnerCF05.setFocusable(true);
            pinCF05 = (EditText) view.findViewById(R.id.pinCF05);
            solicitorCF05 = (EditText) view.findViewById(R.id.solicitorCF05);
            legalAid = (EditText) view.findViewById(R.id.legalAid);
            acceptDateFECF05 = (EditText) view.findViewById(R.id.acceptDateFECF05);
            acceptTimeFECF05 = (EditText) view.findViewById(R.id.acceptTimeFECF05);

            adapter1CF2 = ArrayAdapter.createFromResource(getActivity(), R.array.feeEarnerArray, android.R.layout.simple_spinner_item);
            adapter1CF2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            feeEarnerCF05.setAdapter(adapter1CF2);
            feeEarnerCF05.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    //set value for pinCF05
                    pinCF05.setText(String.valueOf(feeEarnerCF05.getSelectedItemPosition()));
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }
        if (formSelected.equals("Crim.CF006 Fee Earner Update Plea Hearing")) {
            clientNameCF01 = (EditText) view.findViewById(R.id.clientNameView);
            caseNumber = (EditText) view.findViewById(R.id.caseNoView);
            dob = (EditText) view.findViewById(R.id.dobView);
            addressCF01 = (EditText) view.findViewById(R.id.addressView);
            feeEarnerCF05 = (Spinner) view.findViewById(R.id.feeEarnerCF05);
            feeEarnerCF05.setEnabled(false);
            acceptDateFECF05 = (EditText) view.findViewById(R.id.acceptDateFECF05);
            acceptTimeFECF05 = (EditText) view.findViewById(R.id.acceptTimeFECF05);
            arrivalCourt = (EditText) view.findViewById(R.id.arrivalCourt);
            timeEngCF06 = (EditText) view.findViewById(R.id.timeEngCF06);
            clientInstCF06 = (EditText) view.findViewById(R.id.clientInstructionCF06);
            investigation1 = (Spinner) view.findViewById(R.id.investigation1);
            investigation2 = (Spinner) view.findViewById(R.id.investigation2);
            investigation3 = (Spinner) view.findViewById(R.id.investigation3);
            investigation4 = (EditText) view.findViewById(R.id.investigation4);
            investigation5 = (Spinner) view.findViewById(R.id.investigation5);
            investigation6 = (Spinner) view.findViewById(R.id.investigation6);
            investigation7 = (Spinner) view.findViewById(R.id.investigation7);
            investigation8 = (Spinner) view.findViewById(R.id.investigation8);
            investigation9 = (Spinner) view.findViewById(R.id.investigation9);
            investigation10 = (Spinner) view.findViewById(R.id.investigation10);
            investigation11 = (Spinner) view.findViewById(R.id.investigation11);
            investigation12 = (Spinner) view.findViewById(R.id.investigation12);
            investigation13 = (Spinner) view.findViewById(R.id.investigation13);
            investigation14 = (EditText) view.findViewById(R.id.investigation14);
            adjournedReason = (Spinner) view.findViewById(R.id.adjournedReason);
            adjournedDate = (EditText) view.findViewById(R.id.adjournedDate);
            adjournedTime = (EditText) view.findViewById(R.id.adjournedTime);
            adjournedLocation = (Spinner) view.findViewById(R.id.adjournedLocation);
            adjournedBail = (Spinner) view.findViewById(R.id.adjournedBail);
            adjournedBailType = (Spinner) view.findViewById(R.id.adjournedBailType);
            adjournedCondition = (EditText) view.findViewById(R.id.adjournedCondition);
            adjournedConditionLayout = (LinearLayout) view.findViewById(R.id.adjournedConditionLayout);
            guiltyAwaitingCharges = (EditText) view.findViewById(R.id.guiltyAwaitingCharges);
            guiltyAwaitingDate = (EditText) view.findViewById(R.id.guiltyAwaitingDate);
            guiltyAwaitingTime = (EditText) view.findViewById(R.id.guiltyAwaitingTime);
            guiltyAwaitingLocation = (Spinner) view.findViewById(R.id.guiltyAwaitingLocation);
            guiltySentencedCharges = (EditText) view.findViewById(R.id.guiltySentencedCharges);
            guiltySentencedTerms = (EditText) view.findViewById(R.id.guiltySentencedTerms);
            notGuiltyCustodyDetail1 = (Spinner) view.findViewById(R.id.notGuiltyCustodyDetail1);
            notGuiltyCustodyDetail2 = (Spinner) view.findViewById(R.id.notGuiltyCustodyDetail2);
            notGuiltyCustodyDate = (EditText) view.findViewById(R.id.notGuiltyCustodyDate);
            notGuiltyCustodyTime = (EditText) view.findViewById(R.id.notGuiltyCustodyTime);
            notGuiltyCustodyLocation = (Spinner) view.findViewById(R.id.notGuiltyCustodyLocation);
            notGuiltyDetail1 = (Spinner) view.findViewById(R.id.notGuiltyDetail1);
            notGuiltyDetail2 = (Spinner) view.findViewById(R.id.notGuiltyDetail2);
            notGuiltyDate = (EditText) view.findViewById(R.id.notGuiltyDate);
            notGuiltyTime = (EditText) view.findViewById(R.id.notGuiltyTime);
            notGuiltyLocation = (Spinner) view.findViewById(R.id.notGuiltyLocation);
            notGuiltyBailType = (Spinner) view.findViewById(R.id.notGuiltyBailType);
            notGuiltyConditions = (EditText) view.findViewById(R.id.notGuiltyConditions);
            notGuiltyConditionLayout = (LinearLayout) view.findViewById(R.id.notGuiltyConditionLayout);
            hubDateCF06 = (EditText) view.findViewById(R.id.hubDateCF06);
            hubTimeCF06 = (EditText) view.findViewById(R.id.hubTimeCF06);
            outcomeLetterCF06 = (Spinner) view.findViewById(R.id.outcomeLetterCF06);

            adapter1CF2 = ArrayAdapter.createFromResource(getActivity(), R.array.feeEarnerArray, android.R.layout.simple_spinner_item);
            adapter1CF2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            feeEarnerCF05.setAdapter(adapter1CF2);

            adapter3CF1 = ArrayAdapter.createFromResource(getActivity(), R.array.noYesArray, android.R.layout.simple_spinner_item);
            adapter3CF1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            investigation1.setAdapter(adapter3CF1);
            investigation2.setAdapter(adapter3CF1);
            investigation3.setAdapter(adapter3CF1);
            investigation5.setAdapter(adapter3CF1);
            investigation6.setAdapter(adapter3CF1);
            investigation7.setAdapter(adapter3CF1);
            investigation8.setAdapter(adapter3CF1);
            investigation10.setAdapter(adapter3CF1);
            investigation11.setAdapter(adapter3CF1);
            investigation12.setAdapter(adapter3CF1);
            investigation13.setAdapter(adapter3CF1);
            adjournedBail.setAdapter(adapter3CF1);
            adjournedBail.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if(adjournedBail.getSelectedItem().toString().equals("Yes"))
                        adjournedBailType.setVisibility(View.VISIBLE);
                    if(adjournedBail.getSelectedItem().toString().equals("No"))
                        adjournedBailType.setVisibility(View.GONE);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            outcomeLetterCF06.setAdapter(adapter3CF1);

            adapter4CF3 = ArrayAdapter.createFromResource(getActivity(), R.array.noYesNAArray, android.R.layout.simple_spinner_item);
            adapter4CF3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            investigation9.setAdapter(adapter4CF3);

            adapter1CF6 = ArrayAdapter.createFromResource(getActivity(), R.array.reasonArray, android.R.layout.simple_spinner_item);
            adapter1CF6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            adjournedReason.setAdapter(adapter1CF6);

            adapter1CF1 = ArrayAdapter.createFromResource(getActivity(), R.array.policeStationArray, android.R.layout.simple_spinner_item);
            adapter1CF1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            adjournedLocation.setAdapter(adapter1CF1);
            guiltyAwaitingLocation.setAdapter(adapter1CF1);
            notGuiltyCustodyLocation.setAdapter(adapter1CF1);
            notGuiltyLocation.setAdapter(adapter1CF1);

            adapter2CF1 = ArrayAdapter.createFromResource(getActivity(), R.array.allegationArray, android.R.layout.simple_spinner_item);
            adapter2CF1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            notGuiltyCustodyDetail1.setAdapter(adapter2CF1);
            notGuiltyCustodyDetail2.setAdapter(adapter2CF1);
            notGuiltyDetail1.setAdapter(adapter2CF1);
            notGuiltyDetail2.setAdapter(adapter2CF1);

            adapter5CF3 = ArrayAdapter.createFromResource(getActivity(), R.array.bailArray, android.R.layout.simple_spinner_item);
            adapter5CF3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            adjournedBailType.setAdapter(adapter5CF3);
            adjournedBailType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if(adjournedBailType.getSelectedItem().toString().equals("Conditional"))
                        adjournedConditionLayout.setVisibility(View.VISIBLE);
                    if(adjournedBailType.getSelectedItem().toString().equals("Unconditional"))
                        adjournedConditionLayout.setVisibility(View.GONE);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            notGuiltyBailType.setAdapter(adapter5CF3);
            notGuiltyBailType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if(notGuiltyBailType.getSelectedItem().toString().equals("Conditional"))
                        notGuiltyConditionLayout.setVisibility(View.VISIBLE);
                    if(notGuiltyBailType.getSelectedItem().toString().equals("Unconditional"))
                        notGuiltyConditionLayout.setVisibility(View.GONE);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });


        }
        if (formSelected.equals("Crim.CF007 Fee Earner Claim for Costs at Plea Hearing")) {
            caseNumber = (EditText)view.findViewById(R.id.caseNoView);
            caseNumber.setEnabled(false);
            autoCalCF07 = (EditText)view.findViewById(R.id.autoCalView07);
            autoSolCF07 = (EditText)view.findViewById(R.id.autoSolView07);
        }
        if (formSelected.equals("Crim.CF008 Crown Court Case Prep Form")) {
            caseNumber = (EditText)view.findViewById(R.id.caseNoView);
            clientNameCF01 = (EditText)view.findViewById(R.id.clientNameView);
            hubDateCF06 = (EditText)view.findViewById(R.id.hubDateCF06);
            convictionDateCF08 = (EditText)view.findViewById(R.id.convictionDateCF08);
            casePreparedDateCF08 = (EditText)view.findViewById(R.id.casePreparedDateCF08);
            listingOfficerDateCF08 = (EditText)view.findViewById(R.id.listingOfficerDateCF08);
            numberOfPagesCF08 = (EditText)view.findViewById(R.id.numberOfPagesCF08);
            ongoingMatterNumberCF08 = (EditText)view.findViewById(R.id.ongoingMatterNumberCF08);

        }
        if (formSelected.equals("Crim.CF009 Magistrates Court Case Prep Form")) {
            caseNumber = (EditText)view.findViewById(R.id.caseNoView);
            clientNameCF01 = (EditText)view.findViewById(R.id.clientNameView);
            hubDateCF06 = (EditText)view.findViewById(R.id.hubDateCF06);
            convictionDateCF09 = (EditText)view.findViewById(R.id.convictionDateCF09);
            casePreparedDateCF09 = (EditText)view.findViewById(R.id.casePreparedDateCF09);
            listingOfficerDateCF09 = (EditText)view.findViewById(R.id.listingOfficerDateCF09);
            numberOfPagesCF09 = (EditText)view.findViewById(R.id.numberOfPagesCF09);
            ongoingMatterNumberCF09 = (EditText)view.findViewById(R.id.ongoingMatterNumberCF09);
        }
        if (formSelected.equals("Crim.CF010 Case Dispatch For Trial")) {
            dateCF10 = (EditText)view.findViewById(R.id.dateCF10);
            dateCF10.setText(new StringBuilder().append(todaysDay).append("-")
                    .append(todaysMonth + 1).append("-")
                    .append(todaysYear).append(" "));
            timeCF10 = (EditText)view.findViewById(R.id.timeCF10);
            timeCF10.setText(new StringBuilder().append(todaysHour)
                    .append(":").append(todaysMinute)
                    .append(" "));
            caseNumber = (EditText)view.findViewById(R.id.caseNoView);
            clientNameCF01 = (EditText)view.findViewById(R.id.clientNameView);
             /*court need to be resolved*/
            hubDateCF06 = (EditText) view.findViewById(R.id.hubDateCF06);
            hubTimeCF06 = (EditText) view.findViewById(R.id.hubTimeCF06);
            feeEarnerCF10 = (Spinner) view.findViewById(R.id.feeEarnerCF10);
            feeEarnerCF10.setFocusable(true);
            pinCF10 = (EditText) view.findViewById(R.id.pinCF10);
            solicitorCF10 = (EditText) view.findViewById(R.id.solicitorCF10);
            legalAidCF10 = (EditText) view.findViewById(R.id.legalAidCF10);
            acceptDateFECF10 = (EditText) view.findViewById(R.id.acceptDateFECF10);
            acceptTimeFECF10 = (EditText) view.findViewById(R.id.acceptTimeFECF10);
            courtBundleDate = (EditText) view.findViewById(R.id.courtBundleDate);
            courtBundleTime = (EditText) view.findViewById(R.id.courtBundleTime);
            courtBundleAcceptDate = (EditText) view.findViewById(R.id.courtBundleAcceptDate);
            courtBundleAcceptTime = (EditText) view.findViewById(R.id.courtBundleAcceptTime);

            adapter1CF2 = ArrayAdapter.createFromResource(getActivity(), R.array.feeEarnerArray, android.R.layout.simple_spinner_item);
            adapter1CF2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            feeEarnerCF10.setAdapter(adapter1CF2);
            feeEarnerCF10.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    //set value for pinCF10
                    pinCF10.setText(String.valueOf(feeEarnerCF10.getSelectedItemPosition()));
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }
        if (formSelected.equals("Crim.CF011 Fee Earner Update Trial")) {
            clientNameCF01 = (EditText) view.findViewById(R.id.clientNameView);
            caseNumber = (EditText) view.findViewById(R.id.caseNoView);
            dob = (EditText) view.findViewById(R.id.dobView);
            addressCF01 = (EditText) view.findViewById(R.id.addressView);
            acceptDateFECF10 = (EditText) view.findViewById(R.id.acceptDateFECF10);
            arrivalCourtCF11 = (EditText) view.findViewById(R.id.arrivalCourtCF11);
            timeEngCF11 = (EditText) view.findViewById(R.id.timeEngCF11);
            clientCommentCF11 = (EditText) view.findViewById(R.id.clientCommentCF11);
            adviceViewCF11 = (EditText) view.findViewById(R.id.adviceViewCF11);

            adjournedReasonCF11 = (Spinner) view.findViewById(R.id.adjournedReasonCF11);
            adjournedDateCF11 = (EditText) view.findViewById(R.id.adjournedDateCF11);
            adjournedTimeCF11 = (EditText) view.findViewById(R.id.adjournedTimeCF11);
            adjournedLocationCF11 = (Spinner) view.findViewById(R.id.adjournedLocationCF11);
            adjournedBailCF11 = (Spinner) view.findViewById(R.id.adjournedBailCF11);
            adjournedBailTypeCF11 = (Spinner) view.findViewById(R.id.adjournedBailTypeCF11);
            adjournedConditionLayoutCF11 = (LinearLayout) view.findViewById(R.id.adjournedConditionLayoutCF11);
            adjournedConditionCF11 = (EditText) view.findViewById(R.id.adjournedConditionCF11);

            awaitingIssuedCharges = (Spinner) view.findViewById(R.id.awaitingIssuedCharges);
            awaitingUnfoundCharges = (Spinner) view.findViewById(R.id.awaitingUnfoundCharges);
            awaitingBailCF11 = (Spinner) view.findViewById(R.id.awaitingBailCF11);
            awaitingBailTypeCF11 = (Spinner) view.findViewById(R.id.awaitingBailTypeCF11);
            awaitingConditionLayoutCF11 = (LinearLayout) view.findViewById(R.id.awaitingConditionLayoutCF11);
            awaitingConditionCF11 = (EditText) view.findViewById(R.id.awaitingConditionCF11);
            guiltyAwaitingDateCF11 = (EditText) view.findViewById(R.id.guiltyAwaitingDateCF11);
            guiltyAwaitingTimeCF11 = (EditText) view.findViewById(R.id.guiltyAwaitingTimeCF11);
            guiltyAwaitingLocationCF11 = (Spinner) view.findViewById(R.id.guiltyAwaitingLocationCF11);

            sentencedIssuedCharges = (Spinner) view.findViewById(R.id.sentencedIssuedCharges);
            sentencedUnfoundCharges = (Spinner) view.findViewById(R.id.sentencedUnfoundCharges);
            sentenceReceived = (EditText) view.findViewById(R.id.sentenceReceived);
            notGuiltyVerdict = (EditText) view.findViewById(R.id.notGuiltyVerdict);

            hubDateCF11 = (EditText) view.findViewById(R.id.hubDateCF11);
            hubTimeCF11 = (EditText) view.findViewById(R.id.hubTimeCF11);
            outcomeLetterCF11 = (Spinner) view.findViewById(R.id.outcomeLetterCF11);

            adapter3CF1 = ArrayAdapter.createFromResource(getActivity(), R.array.noYesArray, android.R.layout.simple_spinner_item);
            adapter3CF1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            outcomeLetterCF11.setAdapter(adapter3CF1);
            adjournedBailCF11.setAdapter(adapter3CF1);
            adjournedBailCF11.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if(adjournedBailCF11.getSelectedItem().toString().equals("Yes"))
                        adjournedBailTypeCF11.setVisibility(View.VISIBLE);
                    if(adjournedBailCF11.getSelectedItem().toString().equals("No"))
                        adjournedBailTypeCF11.setVisibility(View.GONE);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            awaitingBailCF11.setAdapter(adapter3CF1);
            awaitingBailCF11.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if(awaitingBailCF11.getSelectedItem().toString().equals("Yes"))
                        awaitingBailTypeCF11.setVisibility(View.VISIBLE);
                    if(awaitingBailCF11.getSelectedItem().toString().equals("No"))
                        awaitingBailTypeCF11.setVisibility(View.GONE);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            adapter1CF6 = ArrayAdapter.createFromResource(getActivity(), R.array.reasonArray, android.R.layout.simple_spinner_item);
            adapter1CF6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            adjournedReasonCF11.setAdapter(adapter1CF6);

            adapter1CF1 = ArrayAdapter.createFromResource(getActivity(), R.array.policeStationArray, android.R.layout.simple_spinner_item);
            adapter1CF1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            adjournedLocationCF11.setAdapter(adapter1CF1);
            guiltyAwaitingLocationCF11.setAdapter(adapter1CF1);

            adapter2CF1 = ArrayAdapter.createFromResource(getActivity(), R.array.allegationArray, android.R.layout.simple_spinner_item);
            adapter2CF1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            awaitingIssuedCharges.setAdapter(adapter2CF1);
            awaitingUnfoundCharges.setAdapter(adapter2CF1);
            sentencedIssuedCharges.setAdapter(adapter2CF1);
            sentencedUnfoundCharges.setAdapter(adapter2CF1);

            adapter5CF3 = ArrayAdapter.createFromResource(getActivity(), R.array.bailArray, android.R.layout.simple_spinner_item);
            adapter5CF3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            adjournedBailTypeCF11.setAdapter(adapter5CF3);
            adjournedBailTypeCF11.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if(adjournedBailTypeCF11.getSelectedItem().toString().equals("Conditional"))
                        adjournedConditionLayoutCF11.setVisibility(View.VISIBLE);
                    if(adjournedBailTypeCF11.getSelectedItem().toString().equals("Unconditional"))
                        adjournedConditionLayoutCF11.setVisibility(View.GONE);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            awaitingBailTypeCF11.setAdapter(adapter5CF3);
            awaitingBailTypeCF11.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if(awaitingBailTypeCF11.getSelectedItem().toString().equals("Conditional"))
                        awaitingConditionLayoutCF11.setVisibility(View.VISIBLE);
                    if(awaitingBailTypeCF11.getSelectedItem().toString().equals("Unconditional"))
                        awaitingConditionLayoutCF11.setVisibility(View.GONE);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

        }
        if (formSelected.equals("Crim.CF012 Fee Earner Claim for Costs at Trial")) {
            caseNumber = (EditText)view.findViewById(R.id.caseNoView);
            autoCalViewCF12 = (EditText)view.findViewById(R.id.autoCalViewCF12);
            autoSolViewCF12 = (EditText)view.findViewById(R.id.autoSolViewCF12);
        }
        if (formSelected.equals("Crim.MISC001 Client Care Letter Criminal Legal Aid")) {
            caseNumber = (EditText)view.findViewById(R.id.caseNoView);
            dateMisc01 = (EditText)view.findViewById(R.id.dateMisc01);
            clientNameCF01 = (EditText)view.findViewById(R.id.clientNameView);
            addressCF01 = (EditText)view.findViewById(R.id.addressView);
            policeStation = (Spinner) view.findViewById(R.id.psView);
            policeStation.setEnabled(false);
            dispatchDate = (EditText)view.findViewById(R.id.dispatchDateView);
            dispatchTime = (EditText)view.findViewById(R.id.dispatchTimeView);
            caseHandler = (EditText)view.findViewById(R.id.caseHandler);
            fe = (EditText)view.findViewById(R.id.fe);
            supervisor = (EditText)view.findViewById(R.id.supervisor);
            emailCH = (EditText)view.findViewById(R.id.emailCH);
            telephone01 = (EditText)view.findViewById(R.id.telephone01);
            emailSuper = (EditText)view.findViewById(R.id.emailSuper);

            adapter1CF1 = ArrayAdapter.createFromResource(getActivity(), R.array.policeStationArray, android.R.layout.simple_spinner_item);
            adapter1CF1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            policeStation.setAdapter(adapter1CF1);
        }


            if(formOption.equalsIgnoreCase("View")) {

                // Check if Internet present
                isInternetPresent = cd.isConnectingToInternet(getActivity());
                if (!isInternetPresent) {
                    // Internet Connection is not present
                    Toast.makeText(getActivity(),"Please check internet connection",Toast.LENGTH_LONG).show();
                } else {
                    // Internet Connection is present
                    // Getting complete client details in background thread
                    new GetClientDetails().execute();
                }

        if (userType.equalsIgnoreCase("admin") || userType.equalsIgnoreCase("user") || userType.equalsIgnoreCase("consultant")) {

            // Save button click event
            saveButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    // starting background task to update client
                    if (formSelected.equals("Crim.CF001 Client Matter Form")) {
                        dispatchDate.setText(new StringBuilder().append(todaysDay).append("-")
                                                                .append(todaysMonth + 1).append("-")
                                                                .append(todaysYear).append(" "));
                        dispatchTime.setText(new StringBuilder().append(todaysHour)
                                                                .append(":").append(todaysMinute)
                                                                .append(" "));
                        new SaveClientDetails().execute();
                    }
                    else if (formSelected.equals("Crim.CF002 Case Dispatch Form")) {
                        dispatchDateFE.setText(new StringBuilder().append(todaysDay).append("-")
                                                                  .append(todaysMonth + 1).append("-")
                                                                  .append(todaysYear).append(" "));
                        dispatchTimeFE.setText(new StringBuilder().append(todaysHour)
                                                                  .append(":").append(todaysMinute)
                                                                  .append(" "));
                        new SaveFormCF02Details().execute();
                    }
                    else if (formSelected.equals("Crim.CF003 Fee Earner Update")) {
                        hubDate.setText(new StringBuilder().append(todaysDay).append("-")
                                                           .append(todaysMonth + 1).append("-")
                                                           .append(todaysYear).append(" "));
                        hubTime.setText(new StringBuilder().append(todaysHour)
                                                           .append(":").append(todaysMinute)
                                                           .append(" "));
                        new SaveFormCF03Details().execute();
                    }
                    else if (formSelected.equals("Crim.CF004 Fee Earner Claim for Costs at Police Station Form")) {

                        new SaveFormCF04().execute();
                    }
                    else if (formSelected.equals("Crim.CF005 Case Dispatch Form Plea Hearing")) {

                        new SaveFormCF05().execute();
                    }
                    else if (formSelected.equals("Crim.CF006 Fee Earner Update Plea Hearing")) {
                        hubDateCF06.setText(new StringBuilder().append(todaysDay).append("-")
                                .append(todaysMonth + 1).append("-")
                                .append(todaysYear).append(" "));
                        hubTimeCF06.setText(new StringBuilder().append(todaysHour)
                                .append(":").append(todaysMinute)
                                .append(" "));
                        new SaveFormCF06().execute();
                    }
                    else if (formSelected.equals("Crim.CF007 Fee Earner Claim for Costs at Plea Hearing")) {

                        new SaveFormCF07().execute();
                    }
                    else if (formSelected.equals("Crim.CF008 Crown Court Case Prep Form")) {

                        new SaveFormCF08().execute();
                    }
                    else if (formSelected.equals("Crim.CF009 Magistrates Court Case Prep Form")) {

                        new SaveFormCF09().execute();
                    }
                    else if (formSelected.equals("Crim.CF010 Case Dispatch For Trial")) {

                        new SaveFormCF10().execute();
                    }
                    else if (formSelected.equals("Crim.CF011 Fee Earner Update Trial")) {
                        hubDateCF11.setText(new StringBuilder().append(todaysDay).append("-")
                                .append(todaysMonth + 1).append("-")
                                .append(todaysYear).append(" "));
                        hubTimeCF11.setText(new StringBuilder().append(todaysHour)
                                .append(":").append(todaysMinute)
                                .append(" "));
                        new SaveFormCF11().execute();
                    }
                    else if (formSelected.equals("Crim.CF012 Fee Earner Claim for Costs at Trial")) {

                        new SaveFormCF12().execute();
                    }
                    else if (formSelected.equals("Crim.MISC001 Client Care Letter Criminal Legal Aid")) {

//                        Intent intent = new Intent(Intent.ACTION_SEND);
//                        intent.setType("message/rfc822");
//                        intent.putExtra(Intent.EXTRA_SUBJECT, "Client Care Letter");
//                        intent.putExtra(Intent.EXTRA_TEXT, "Hi, This is FYI.");
//                        Intent mailer = Intent.createChooser(intent, null);
//                        startActivity(mailer);
                      //  new SaveFormCF12().execute();
                    }
                }
            });
        }
        if (userType.equalsIgnoreCase("admin")) {
            if (formSelected.equals("Crim.CF001 Client Matter Form")) {
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
                                        new DeleteClient().execute();
                                        break;

                                    case DialogInterface.BUTTON_NEGATIVE:
                                        //No button clicked
                                        break;
                                }
                            }
                        };

                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setTitle("Delete Client")
                                .setMessage("Are you sure?")
                                .setPositiveButton("Yes", dialogClickListener)
                                .setNegativeButton("No", dialogClickListener).show();

                    }
                });
            }
        }
        if (userType.equalsIgnoreCase("consultant")) {

            if(formSelected.equals("Crim.CF001 Client Matter Form")) {
                saveButton.setVisibility(View.GONE);
                deleteButton.setVisibility(View.GONE);
                clientNameCF01.setEnabled(false);
                dob.setEnabled(false);
                addressCF01.setEnabled(false);
                policeStation.setEnabled(false);
                custodyRecordNo.setEnabled(false);
                allegation.setEnabled(false);
                incidentDate.setEnabled(false);
                incidentTime.setEnabled(false);
                coAccused.setEnabled(false);
                callTime.setEnabled(false);
                interviewTime.setEnabled(false);
                officerView.setEnabled(false);
                previousClient.setEnabled(false);
                dispatchDate.setEnabled(false);
                dispatchTime.setEnabled(false);

            }
            if(formSelected.equals("Crim.CF002 Case Dispatch Form")) {
                saveButton.setVisibility(View.GONE);
                feeEarner.setEnabled(false);
                distancePS.setEnabled(false);
                eta.setEnabled(false);
            }

        }
    }

        if(formOption.equalsIgnoreCase("Create")) {
            presentDate.setText(new StringBuilder().append(todaysDay).append("-")
                                                   .append(todaysMonth + 1).append("-")
                                                   .append(todaysYear).append(" "));
            //Generate case number
            int range = 10;
            String one = String.valueOf((int)(Math.random() * range));
            String ten = String.valueOf((int)(Math.random() * range));
            String hundred = String.valueOf((int)(Math.random() * range));
            String seq = hundred+ten+one;
            String date = String.valueOf(new StringBuilder().append(todaysDay).append(todaysMonth + 1).append(todaysYear));
            caseNumber.setText(date+seq);
            deleteButton.setVisibility(View.GONE);
            saveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dispatchDate.setText(new StringBuilder().append(todaysDay).append("-")
                                .append(todaysMonth + 1).append("-")
                                .append(todaysYear).append(" "));
                        dispatchTime.setText(new StringBuilder().append(todaysHour)
                                .append(":").append(todaysMinute)
                                .append(" "));
                        new CreateNewClient().execute();
                    }
                });
        }

            View.OnClickListener showDatePickerClick = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final View finalview = v;

                    final Calendar c = Calendar.getInstance();
                    int year = c.get(Calendar.YEAR);
                    int month = c.get(Calendar.MONTH);
                    int day = c.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int day) {
                             if(finalview.getId() == R.id.dobView){
                                dob.setText(new StringBuilder().append(day)
                                        .append("-").append(month + 1).append("-").append(year)
                                        .append(" "));
                            } else if(finalview.getId() == R.id.dispatchDateView){
                                dispatchDate.setText(new StringBuilder().append(day)
                                        .append("-").append(month + 1).append("-").append(year)
                                        .append(" "));
                            } else if(finalview.getId() == R.id.interview1Date){
                                interview1Date.setText(new StringBuilder().append(day)
                                        .append("-").append(month + 1).append("-").append(year)
                                        .append(" "));
                            } else if(finalview.getId() == R.id.interview2Date){
                                 interview2Date.setText(new StringBuilder().append(day)
                                        .append("-").append(month + 1).append("-").append(year)
                                        .append(" "));
                             } else if(finalview.getId() == R.id.interview3Date){
                                 interview3Date.setText(new StringBuilder().append(day)
                                         .append("-").append(month + 1).append("-").append(year)
                                         .append(" "));
                             } else if(finalview.getId() == R.id.interview4Date){
                                 interview4Date.setText(new StringBuilder().append(day)
                                         .append("-").append(month + 1).append("-").append(year)
                                         .append(" "));
                             } else if(finalview.getId() == R.id.bailDate){
                                 bailDate.setText(new StringBuilder().append(day)
                                         .append("-").append(month + 1).append("-").append(year)
                                         .append(" "));
                             } else if(finalview.getId() == R.id.remandDate){
                                 remandDate.setText(new StringBuilder().append(day)
                                         .append("-").append(month + 1).append("-").append(year)
                                         .append(" "));
                            } else if(finalview.getId() == R.id.chargedDate){
                                chargedDate.setText(new StringBuilder().append(day)
                                        .append("-").append(month + 1).append("-").append(year)
                                        .append(" "));
                            } else if(finalview.getId() == R.id.custodyDate){
                                custodyDate.setText(new StringBuilder().append(day)
                                        .append("-").append(month + 1).append("-").append(year)
                                        .append(" "));
                            } else if(finalview.getId() == R.id.adjournedDate){
                                 adjournedDate.setText(new StringBuilder().append(day)
                                         .append("-").append(month + 1).append("-").append(year)
                                         .append(" "));
                             } else if(finalview.getId() == R.id.guiltyAwaitingDate){
                                 guiltyAwaitingDate.setText(new StringBuilder().append(day)
                                         .append("-").append(month + 1).append("-").append(year)
                                         .append(" "));
                             } else if(finalview.getId() == R.id.notGuiltyCustodyDate){
                                 notGuiltyCustodyDate.setText(new StringBuilder().append(day)
                                         .append("-").append(month + 1).append("-").append(year)
                                         .append(" "));
                             } else if(finalview.getId() == R.id.notGuiltyDate){
                                 notGuiltyDate.setText(new StringBuilder().append(day)
                                         .append("-").append(month + 1).append("-").append(year)
                                         .append(" "));
                             } else if(finalview.getId() == R.id.convictionDateCF08){
                                convictionDateCF08.setText(new StringBuilder().append(day)
                                        .append("-").append(month + 1).append("-").append(year)
                                        .append(" "));
                            } else if(finalview.getId() == R.id.casePreparedDateCF08){
                                casePreparedDateCF08.setText(new StringBuilder().append(day)
                                        .append("-").append(month + 1).append("-").append(year)
                                        .append(" "));
                            } else if(finalview.getId() == R.id.listingOfficerDateCF08){
                                listingOfficerDateCF08.setText(new StringBuilder().append(day)
                                        .append("-").append(month + 1).append("-").append(year)
                                        .append(" "));
                            } else if(finalview.getId() == R.id.convictionDateCF09){
                                convictionDateCF09.setText(new StringBuilder().append(day)
                                        .append("-").append(month + 1).append("-").append(year)
                                        .append(" "));
                            } else if(finalview.getId() == R.id.casePreparedDateCF09){
                                casePreparedDateCF09.setText(new StringBuilder().append(day)
                                        .append("-").append(month + 1).append("-").append(year)
                                        .append(" "));
                            } else if(finalview.getId() == R.id.listingOfficerDateCF09){
                                listingOfficerDateCF09.setText(new StringBuilder().append(day)
                                        .append("-").append(month + 1).append("-").append(year)
                                        .append(" "));
                            } else if(finalview.getId() == R.id.adjournedDateCF11){
                                 adjournedDateCF11.setText(new StringBuilder().append(day)
                                         .append("-").append(month + 1).append("-").append(year)
                                         .append(" "));
                             } else if(finalview.getId() == R.id.guiltyAwaitingDateCF11){
                                 guiltyAwaitingDateCF11.setText(new StringBuilder().append(day)
                                         .append("-").append(month + 1).append("-").append(year)
                                         .append(" "));
                             }
                        }
                    }, year, month, day);
                    datePickerDialog.show();



                }
            };

            View.OnFocusChangeListener showDatePickerFocus = new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {

                    if (hasFocus) {
                        final View finalview = v;
                        final Calendar c = Calendar.getInstance();
                        int year = c.get(Calendar.YEAR);
                        int month = c.get(Calendar.MONTH);
                        int day = c.get(Calendar.DAY_OF_MONTH);

                        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int day) {
                                 if(finalview.getId() == R.id.dobView){
                                    dob.setText(new StringBuilder().append(day)
                                            .append("-").append(month + 1).append("-").append(year)
                                            .append(" "));
                                } else if(finalview.getId() == R.id.dispatchDateView){
                                    dispatchDate.setText(new StringBuilder().append(day)
                                            .append("-").append(month + 1).append("-").append(year)
                                            .append(" "));
                                 } else if(finalview.getId() == R.id.interview1Date){
                                     interview1Date.setText(new StringBuilder().append(day)
                                             .append("-").append(month + 1).append("-").append(year)
                                             .append(" "));
                                 } else if(finalview.getId() == R.id.interview2Date){
                                     interview2Date.setText(new StringBuilder().append(day)
                                             .append("-").append(month + 1).append("-").append(year)
                                             .append(" "));
                                 } else if(finalview.getId() == R.id.interview3Date){
                                     interview3Date.setText(new StringBuilder().append(day)
                                             .append("-").append(month + 1).append("-").append(year)
                                             .append(" "));
                                 } else if(finalview.getId() == R.id.interview4Date){
                                     interview4Date.setText(new StringBuilder().append(day)
                                             .append("-").append(month + 1).append("-").append(year)
                                             .append(" "));
                                 } else if(finalview.getId() == R.id.bailDate){
                                     bailDate.setText(new StringBuilder().append(day)
                                             .append("-").append(month + 1).append("-").append(year)
                                             .append(" "));
                                 } else if(finalview.getId() == R.id.remandDate){
                                     remandDate.setText(new StringBuilder().append(day)
                                             .append("-").append(month + 1).append("-").append(year)
                                             .append(" "));
                                 } else if(finalview.getId() == R.id.chargedDate){
                                     chargedDate.setText(new StringBuilder().append(day)
                                             .append("-").append(month + 1).append("-").append(year)
                                             .append(" "));
                                 } else if(finalview.getId() == R.id.custodyDate){
                                     custodyDate.setText(new StringBuilder().append(day)
                                             .append("-").append(month + 1).append("-").append(year)
                                             .append(" "));
                                 } else if(finalview.getId() == R.id.adjournedDate){
                                     adjournedDate.setText(new StringBuilder().append(day)
                                             .append("-").append(month + 1).append("-").append(year)
                                             .append(" "));
                                 } else if(finalview.getId() == R.id.guiltyAwaitingDate){
                                     guiltyAwaitingDate.setText(new StringBuilder().append(day)
                                             .append("-").append(month + 1).append("-").append(year)
                                             .append(" "));
                                 } else if(finalview.getId() == R.id.notGuiltyCustodyDate){
                                     notGuiltyCustodyDate.setText(new StringBuilder().append(day)
                                             .append("-").append(month + 1).append("-").append(year)
                                             .append(" "));
                                 } else if(finalview.getId() == R.id.notGuiltyDate){
                                     notGuiltyDate.setText(new StringBuilder().append(day)
                                             .append("-").append(month + 1).append("-").append(year)
                                             .append(" "));
                                 } else if(finalview.getId() == R.id.convictionDateCF08){
                                     convictionDateCF08.setText(new StringBuilder().append(day)
                                             .append("-").append(month + 1).append("-").append(year)
                                             .append(" "));
                                 } else if(finalview.getId() == R.id.casePreparedDateCF08){
                                     casePreparedDateCF08.setText(new StringBuilder().append(day)
                                             .append("-").append(month + 1).append("-").append(year)
                                             .append(" "));
                                 } else if(finalview.getId() == R.id.listingOfficerDateCF08){
                                     listingOfficerDateCF08.setText(new StringBuilder().append(day)
                                             .append("-").append(month + 1).append("-").append(year)
                                             .append(" "));
                                 } else if(finalview.getId() == R.id.convictionDateCF09){
                                     convictionDateCF09.setText(new StringBuilder().append(day)
                                             .append("-").append(month + 1).append("-").append(year)
                                             .append(" "));
                                 } else if(finalview.getId() == R.id.casePreparedDateCF09){
                                     casePreparedDateCF09.setText(new StringBuilder().append(day)
                                             .append("-").append(month + 1).append("-").append(year)
                                             .append(" "));
                                 } else if(finalview.getId() == R.id.listingOfficerDateCF09){
                                     listingOfficerDateCF09.setText(new StringBuilder().append(day)
                                             .append("-").append(month + 1).append("-").append(year)
                                             .append(" "));
                                 } else if(finalview.getId() == R.id.adjournedDateCF11){
                                     adjournedDateCF11.setText(new StringBuilder().append(day)
                                             .append("-").append(month + 1).append("-").append(year)
                                             .append(" "));
                                 } else if(finalview.getId() == R.id.guiltyAwaitingDateCF11){
                                     guiltyAwaitingDateCF11.setText(new StringBuilder().append(day)
                                             .append("-").append(month + 1).append("-").append(year)
                                             .append(" "));
                                 }
                            }
                        }, year, month, day);
                        datePickerDialog.show();


                    }
                }
            };


            View.OnClickListener showTimePickerClick = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final View finalview = v;

                    final Calendar c = Calendar.getInstance();
                    int hour = c.get(Calendar.HOUR_OF_DAY);
                    int minute = c.get(Calendar.MINUTE);

                    TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                            String am_pm = "";
//                            if (c.get(Calendar.AM_PM) == Calendar.AM)
//                                am_pm = "AM";
//                            else if (c.get(Calendar.AM_PM) == Calendar.PM)
//                                am_pm = "PM";

                            if(finalview.getId() == R.id.callTimeView) {
                                callTime.setText(new StringBuilder().append(hourOfDay)
                                        .append(":").append(minute)
                                        .append(" "));
                            } else if(finalview.getId() == R.id.interviewTimeView){
                                interviewTime.setText(new StringBuilder().append(hourOfDay)
                                        .append(":").append(minute)
                                        .append(" "));
                            } else if(finalview.getId() == R.id.dispatchTimeView){
                                dispatchTime.setText(new StringBuilder().append(hourOfDay)
                                        .append(":").append(minute)
                                        .append(" "));
                            } else if(finalview.getId() == R.id.etaView){
                                eta.setText(new StringBuilder().append(hourOfDay)
                                        .append(":").append(minute)
                                        .append(" "));
                            } else if(finalview.getId() == R.id.arrivalTimePS){
                                arrivalTimePS.setText(new StringBuilder().append(hourOfDay)
                                        .append(":").append(minute)
                                        .append(" "));
                            } else if(finalview.getId() == R.id.arrestTime){
                                arrestTime.setText(new StringBuilder().append(hourOfDay)
                                        .append(":").append(minute)
                                        .append(" "));
                            } else if(finalview.getId() == R.id.arrivalTimeP_S){
                                arrivalTimeP_S.setText(new StringBuilder().append(hourOfDay)
                                        .append(":").append(minute)
                                        .append(" "));
                            } else if(finalview.getId() == R.id.detentionTime){
                                detentionTime.setText(new StringBuilder().append(hourOfDay)
                                        .append(":").append(minute)
                                        .append(" "));

                            } else if(finalview.getId() == R.id.interview1StartTime){
                                interview1StartTime.setText(new StringBuilder().append(hourOfDay)
                                        .append(":").append(minute)
                                        .append(" "));
                            } else if(finalview.getId() == R.id.interview2StartTime){
                                interview2StartTime.setText(new StringBuilder().append(hourOfDay)
                                        .append(":").append(minute)
                                        .append(" "));
                            } else if(finalview.getId() == R.id.interview3StartTime){
                                interview3StartTime.setText(new StringBuilder().append(hourOfDay)
                                        .append(":").append(minute)
                                        .append(" "));
                            } else if(finalview.getId() == R.id.interview4StartTime){
                                interview4StartTime.setText(new StringBuilder().append(hourOfDay)
                                        .append(":").append(minute)
                                        .append(" "));
                            } else if(finalview.getId() == R.id.interview1EndTime){
                                interview1EndTime.setText(new StringBuilder().append(hourOfDay)
                                        .append(":").append(minute)
                                        .append(" "));
                            } else if(finalview.getId() == R.id.interview2EndTime){
                                interview2EndTime.setText(new StringBuilder().append(hourOfDay)
                                        .append(":").append(minute)
                                        .append(" "));
                            } else if(finalview.getId() == R.id.interview3EndTime){
                                interview3EndTime.setText(new StringBuilder().append(hourOfDay)
                                        .append(":").append(minute)
                                        .append(" "));
                            } else if(finalview.getId() == R.id.interview4EndTime){
                                interview4EndTime.setText(new StringBuilder().append(hourOfDay)
                                        .append(":").append(minute)
                                        .append(" "));
                            } else if(finalview.getId() == R.id.bailTime){
                                bailTime.setText(new StringBuilder().append(hourOfDay)
                                        .append(":").append(minute)
                                        .append(" "));
                            } else if(finalview.getId() == R.id.remandTime){
                                remandTime.setText(new StringBuilder().append(hourOfDay)
                                        .append(":").append(minute)
                                        .append(" "));
                            } else if(finalview.getId() == R.id.chargedTime){
                                chargedTime.setText(new StringBuilder().append(hourOfDay)
                                        .append(":").append(minute)
                                        .append(" "));
                            } else if(finalview.getId() == R.id.custodyTime){
                                custodyTime.setText(new StringBuilder().append(hourOfDay)
                                        .append(":").append(minute)
                                        .append(" "));
                            } else if(finalview.getId() == R.id.arrivalCourt){
                                arrivalCourt.setText(new StringBuilder().append(hourOfDay)
                                        .append(":").append(minute)
                                        .append(" "));
                            } else if(finalview.getId() == R.id.adjournedTime){
                                adjournedTime.setText(new StringBuilder().append(hourOfDay)
                                        .append(":").append(minute)
                                        .append(" "));
                            } else if(finalview.getId() == R.id.guiltyAwaitingTime){
                                guiltyAwaitingTime.setText(new StringBuilder().append(hourOfDay)
                                        .append(":").append(minute)
                                        .append(" "));
                            } else if(finalview.getId() == R.id.notGuiltyCustodyTime){
                                notGuiltyCustodyTime.setText(new StringBuilder().append(hourOfDay)
                                        .append(":").append(minute)
                                        .append(" "));
                            } else if(finalview.getId() == R.id.notGuiltyTime){
                                notGuiltyTime.setText(new StringBuilder().append(hourOfDay)
                                        .append(":").append(minute)
                                        .append(" "));
                            } else if(finalview.getId() == R.id.arrivalCourtCF11){
                                arrivalCourtCF11.setText(new StringBuilder().append(hourOfDay)
                                        .append(":").append(minute)
                                        .append(" "));
                            } else if(finalview.getId() == R.id.adjournedTimeCF11){
                                adjournedTimeCF11.setText(new StringBuilder().append(hourOfDay)
                                        .append(":").append(minute)
                                        .append(" "));
                            } else if(finalview.getId() == R.id.guiltyAwaitingTimeCF11){
                                guiltyAwaitingTimeCF11.setText(new StringBuilder().append(hourOfDay)
                                        .append(":").append(minute)
                                        .append(" "));
                            }
                        }
                    }, hour, minute, DateFormat.is24HourFormat(getActivity()));
                    timePickerDialog.show();


                }
            };

            View.OnFocusChangeListener showTimePickerFocus = new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {

                    if (hasFocus) {
                        final View finalview = v;

                        final Calendar c = Calendar.getInstance();
                        int hour = c.get(Calendar.HOUR_OF_DAY);
                        int minute = c.get(Calendar.MINUTE);

                        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                if (finalview.getId() == R.id.callTimeView) {
                                    callTime.setText(new StringBuilder().append(hourOfDay)
                                            .append(":").append(minute)
                                            .append(" "));
                                } else if(finalview.getId() == R.id.interviewTimeView){
                                    interviewTime.setText(new StringBuilder().append(hourOfDay)
                                            .append(":").append(minute)
                                            .append(" "));
                                } else if(finalview.getId() == R.id.dispatchTimeView){
                                    dispatchTime.setText(new StringBuilder().append(hourOfDay)
                                            .append(":").append(minute)
                                            .append(" "));
                                } else if(finalview.getId() == R.id.etaView){
                                    eta.setText(new StringBuilder().append(hourOfDay)
                                            .append(":").append(minute)
                                            .append(" "));
                                } else if(finalview.getId() == R.id.arrivalTimePS){
                                    arrivalTimePS.setText(new StringBuilder().append(hourOfDay)
                                            .append(":").append(minute)
                                            .append(" "));
                                } else if(finalview.getId() == R.id.arrestTime){
                                    arrestTime.setText(new StringBuilder().append(hourOfDay)
                                            .append(":").append(minute)
                                            .append(" "));
                                } else if(finalview.getId() == R.id.arrivalTimeP_S){
                                    arrivalTimeP_S.setText(new StringBuilder().append(hourOfDay)
                                            .append(":").append(minute)
                                            .append(" "));
                                } else if(finalview.getId() == R.id.detentionTime){
                                    detentionTime.setText(new StringBuilder().append(hourOfDay)
                                            .append(":").append(minute)
                                            .append(" "));

                                } else if(finalview.getId() == R.id.interview1StartTime){
                                    interview1StartTime.setText(new StringBuilder().append(hourOfDay)
                                            .append(":").append(minute)
                                            .append(" "));
                                } else if(finalview.getId() == R.id.interview2StartTime){
                                    interview2StartTime.setText(new StringBuilder().append(hourOfDay)
                                            .append(":").append(minute)
                                            .append(" "));
                                } else if(finalview.getId() == R.id.interview3StartTime){
                                    interview3StartTime.setText(new StringBuilder().append(hourOfDay)
                                            .append(":").append(minute)
                                            .append(" "));
                                } else if(finalview.getId() == R.id.interview4StartTime){
                                    interview4StartTime.setText(new StringBuilder().append(hourOfDay)
                                            .append(":").append(minute)
                                            .append(" "));
                                } else if(finalview.getId() == R.id.interview1EndTime){
                                    interview1EndTime.setText(new StringBuilder().append(hourOfDay)
                                            .append(":").append(minute)
                                            .append(" "));
                                } else if(finalview.getId() == R.id.interview2EndTime){
                                    interview2EndTime.setText(new StringBuilder().append(hourOfDay)
                                            .append(":").append(minute)
                                            .append(" "));
                                } else if(finalview.getId() == R.id.interview3EndTime){
                                    interview3EndTime.setText(new StringBuilder().append(hourOfDay)
                                            .append(":").append(minute)
                                            .append(" "));
                                } else if(finalview.getId() == R.id.interview4EndTime){
                                    interview4EndTime.setText(new StringBuilder().append(hourOfDay)
                                            .append(":").append(minute)
                                            .append(" "));
                                } else if(finalview.getId() == R.id.bailTime){
                                    bailTime.setText(new StringBuilder().append(hourOfDay)
                                            .append(":").append(minute)
                                            .append(" "));
                                } else if(finalview.getId() == R.id.remandTime){
                                    remandTime.setText(new StringBuilder().append(hourOfDay)
                                            .append(":").append(minute)
                                            .append(" "));
                                } else if(finalview.getId() == R.id.chargedTime){
                                    chargedTime.setText(new StringBuilder().append(hourOfDay)
                                            .append(":").append(minute)
                                            .append(" "));
                                } else if(finalview.getId() == R.id.custodyTime){
                                    custodyTime.setText(new StringBuilder().append(hourOfDay)
                                            .append(":").append(minute)
                                            .append(" "));
                                } else if(finalview.getId() == R.id.arrivalCourt){
                                    arrivalCourt.setText(new StringBuilder().append(hourOfDay)
                                            .append(":").append(minute)
                                            .append(" "));
                                } else if(finalview.getId() == R.id.adjournedTime){
                                    adjournedTime.setText(new StringBuilder().append(hourOfDay)
                                            .append(":").append(minute)
                                            .append(" "));
                                } else if(finalview.getId() == R.id.guiltyAwaitingTime){
                                    guiltyAwaitingTime.setText(new StringBuilder().append(hourOfDay)
                                            .append(":").append(minute)
                                            .append(" "));
                                } else if(finalview.getId() == R.id.notGuiltyCustodyTime){
                                    notGuiltyCustodyTime.setText(new StringBuilder().append(hourOfDay)
                                            .append(":").append(minute)
                                            .append(" "));
                                } else if(finalview.getId() == R.id.notGuiltyTime){
                                    notGuiltyTime.setText(new StringBuilder().append(hourOfDay)
                                            .append(":").append(minute)
                                            .append(" "));
                                } else if(finalview.getId() == R.id.arrivalCourtCF11){
                                    arrivalCourtCF11.setText(new StringBuilder().append(hourOfDay)
                                            .append(":").append(minute)
                                            .append(" "));
                                } else if(finalview.getId() == R.id.adjournedTimeCF11){
                                    adjournedTimeCF11.setText(new StringBuilder().append(hourOfDay)
                                            .append(":").append(minute)
                                            .append(" "));
                                } else if(finalview.getId() == R.id.guiltyAwaitingTimeCF11){
                                    guiltyAwaitingTimeCF11.setText(new StringBuilder().append(hourOfDay)
                                            .append(":").append(minute)
                                            .append(" "));
                                }
                            }
                        }, hour, minute, DateFormat.is24HourFormat(getActivity()));
                        timePickerDialog.show();


                    }
                }
            };

        View.OnClickListener showDateandTimeClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        incidentDate.setText(new StringBuilder().append(day)
                                .append("-").append(month + 1).append("-").append(year)
                                .append(" "));
                    }
                }, year, month, day);
                    datePickerDialog.show();

                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        incidentTime.setText(new StringBuilder().append(hourOfDay)
                                .append(":").append(minute)
                                .append(" "));
                    }
                }, hour, minute, DateFormat.is24HourFormat(getActivity()));
                timePickerDialog.show();
            }
        };

        View.OnFocusChangeListener showDateandTimeFocus = new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (hasFocus) {
                    final Calendar c = Calendar.getInstance();
                    int year = c.get(Calendar.YEAR);
                    int month = c.get(Calendar.MONTH);
                    int day = c.get(Calendar.DAY_OF_MONTH);
                    int hour = c.get(Calendar.HOUR_OF_DAY);
                    int minute = c.get(Calendar.MINUTE);

                    final DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int day) {
                            incidentDate.setText(new StringBuilder().append(day)
                                    .append("-").append(month + 1).append("-").append(year)
                                    .append(" "));
                        }
                    }, year, month, day);
                    datePickerDialog.show();

                    TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            incidentTime.setText(new StringBuilder().append(hourOfDay)
                                    .append(":").append(minute)
                                    .append(" "));
                        }
                    }, hour, minute, DateFormat.is24HourFormat(getActivity()));
                    timePickerDialog.show();
                }
            }
        };

        if(userType.equalsIgnoreCase("admin") || userType.equalsIgnoreCase("user")) {
            if (formSelected.equals("Crim.CF001 Client Matter Form")) {
                dob.setOnClickListener(showDatePickerClick);
                dob.setOnFocusChangeListener(showDatePickerFocus);
                dispatchDate.setOnClickListener(showDatePickerClick);
                dispatchDate.setOnFocusChangeListener(showDatePickerFocus);

                dispatchTime.setOnClickListener(showTimePickerClick);
                dispatchTime.setOnFocusChangeListener(showTimePickerFocus);
                callTime.setOnClickListener(showTimePickerClick);
                callTime.setOnFocusChangeListener(showTimePickerFocus);
                interviewTime.setOnClickListener(showTimePickerClick);
                interviewTime.setOnFocusChangeListener(showTimePickerFocus);

                incidentDate.setOnClickListener(showDateandTimeClick);
                incidentDate.setOnFocusChangeListener(showDateandTimeFocus);
                incidentTime.setOnClickListener(showDateandTimeClick);
                incidentTime.setOnFocusChangeListener(showDateandTimeFocus);

            }

            if (formSelected.equals("Crim.CF002 Case Dispatch Form")) {

                eta.setOnClickListener(showTimePickerClick);
                eta.setOnFocusChangeListener(showTimePickerFocus);
            }

        }

        if (formSelected.equals("Crim.CF003 Fee Earner Update")) {
            interview1Date.setOnClickListener(showDatePickerClick);
            interview1Date.setOnFocusChangeListener(showDatePickerFocus);
            interview2Date.setOnClickListener(showDatePickerClick);
            interview2Date.setOnFocusChangeListener(showDatePickerFocus);
            interview3Date.setOnClickListener(showDatePickerClick);
            interview3Date.setOnFocusChangeListener(showDatePickerFocus);
            interview4Date.setOnClickListener(showDatePickerClick);
            interview4Date.setOnFocusChangeListener(showDatePickerFocus);
            bailDate.setOnClickListener(showDatePickerClick);
            bailDate.setOnFocusChangeListener(showDatePickerFocus);
            remandDate.setOnClickListener(showDatePickerClick);
            remandDate.setOnFocusChangeListener(showDatePickerFocus);
            chargedDate.setOnClickListener(showDatePickerClick);
            chargedDate.setOnFocusChangeListener(showDatePickerFocus);
            custodyDate.setOnClickListener(showDatePickerClick);
            custodyDate.setOnFocusChangeListener(showDatePickerFocus);

            arrivalTimePS.setOnClickListener(showTimePickerClick);
            arrivalTimePS.setOnFocusChangeListener(showTimePickerFocus);
            arrestTime.setOnClickListener(showTimePickerClick);
            arrestTime.setOnFocusChangeListener(showTimePickerFocus);
            arrivalTimeP_S.setOnClickListener(showTimePickerClick);
            arrivalTimeP_S.setOnFocusChangeListener(showTimePickerFocus);
            detentionTime.setOnClickListener(showTimePickerClick);
            detentionTime.setOnFocusChangeListener(showTimePickerFocus);

            interview1StartTime.setOnClickListener(showTimePickerClick);
            interview1StartTime.setOnFocusChangeListener(showTimePickerFocus);
            interview2StartTime.setOnClickListener(showTimePickerClick);
            interview2StartTime.setOnFocusChangeListener(showTimePickerFocus);
            interview3StartTime.setOnClickListener(showTimePickerClick);
            interview3StartTime.setOnFocusChangeListener(showTimePickerFocus);
            interview4StartTime.setOnClickListener(showTimePickerClick);
            interview4StartTime.setOnFocusChangeListener(showTimePickerFocus);

            interview1EndTime.setOnClickListener(showTimePickerClick);
            interview1EndTime.setOnFocusChangeListener(showTimePickerFocus);
            interview2EndTime.setOnClickListener(showTimePickerClick);
            interview2EndTime.setOnFocusChangeListener(showTimePickerFocus);
            interview3EndTime.setOnClickListener(showTimePickerClick);
            interview3EndTime.setOnFocusChangeListener(showTimePickerFocus);
            interview4EndTime.setOnClickListener(showTimePickerClick);
            interview4EndTime.setOnFocusChangeListener(showTimePickerFocus);

            bailTime.setOnClickListener(showTimePickerClick);
            bailTime.setOnFocusChangeListener(showTimePickerFocus);
            remandTime.setOnClickListener(showTimePickerClick);
            remandTime.setOnFocusChangeListener(showTimePickerFocus);
            chargedTime.setOnClickListener(showTimePickerClick);
            chargedTime.setOnFocusChangeListener(showTimePickerFocus);
            custodyTime.setOnClickListener(showTimePickerClick);
            custodyTime.setOnFocusChangeListener(showTimePickerFocus);


        }
        if (formSelected.equals("Crim.CF006 Fee Earner Update Plea Hearing")) {
            adjournedDate.setOnClickListener(showDatePickerClick);
            adjournedDate.setOnFocusChangeListener(showDatePickerFocus);
            guiltyAwaitingDate.setOnClickListener(showDatePickerClick);
            guiltyAwaitingDate.setOnFocusChangeListener(showDatePickerFocus);
            notGuiltyCustodyDate.setOnClickListener(showDatePickerClick);
            notGuiltyCustodyDate.setOnFocusChangeListener(showDatePickerFocus);
            notGuiltyDate.setOnClickListener(showDatePickerClick);
            notGuiltyDate.setOnFocusChangeListener(showDatePickerFocus);

            arrivalCourt.setOnClickListener(showTimePickerClick);
            arrivalCourt.setOnFocusChangeListener(showTimePickerFocus);
            adjournedTime.setOnClickListener(showTimePickerClick);
            adjournedTime.setOnFocusChangeListener(showTimePickerFocus);
            guiltyAwaitingTime.setOnClickListener(showTimePickerClick);
            guiltyAwaitingTime.setOnFocusChangeListener(showTimePickerFocus);
            notGuiltyCustodyTime.setOnClickListener(showTimePickerClick);
            notGuiltyCustodyTime.setOnFocusChangeListener(showTimePickerFocus);
            notGuiltyTime.setOnClickListener(showTimePickerClick);
            notGuiltyTime.setOnFocusChangeListener(showTimePickerFocus);
        }
        if (formSelected.equals("Crim.CF008 Crown Court Case Prep Form")) {
            convictionDateCF08.setOnClickListener(showDatePickerClick);
            convictionDateCF08.setOnFocusChangeListener(showDatePickerFocus);
            casePreparedDateCF08.setOnClickListener(showDatePickerClick);
            casePreparedDateCF08.setOnFocusChangeListener(showDatePickerFocus);
            listingOfficerDateCF08.setOnClickListener(showDatePickerClick);
            listingOfficerDateCF08.setOnFocusChangeListener(showDatePickerFocus);
        }
        if (formSelected.equals("Crim.CF009 Magistrates Court Case Prep Form")) {
            convictionDateCF09.setOnClickListener(showDatePickerClick);
            convictionDateCF09.setOnFocusChangeListener(showDatePickerFocus);
            casePreparedDateCF09.setOnClickListener(showDatePickerClick);
            casePreparedDateCF09.setOnFocusChangeListener(showDatePickerFocus);
            listingOfficerDateCF09.setOnClickListener(showDatePickerClick);
            listingOfficerDateCF09.setOnFocusChangeListener(showDatePickerFocus);
        }
        if (formSelected.equals("Crim.CF011 Fee Earner Update Trial")) {
            adjournedDateCF11.setOnClickListener(showDatePickerClick);
            adjournedDateCF11.setOnFocusChangeListener(showDatePickerFocus);
            guiltyAwaitingDateCF11.setOnClickListener(showDatePickerClick);
            guiltyAwaitingDateCF11.setOnFocusChangeListener(showDatePickerFocus);

            arrivalCourtCF11.setOnClickListener(showTimePickerClick);
            arrivalCourtCF11.setOnFocusChangeListener(showTimePickerFocus);
            adjournedTimeCF11.setOnClickListener(showTimePickerClick);
            adjournedTimeCF11.setOnFocusChangeListener(showTimePickerFocus);
            guiltyAwaitingTimeCF11.setOnClickListener(showTimePickerClick);
            guiltyAwaitingTimeCF11.setOnFocusChangeListener(showTimePickerFocus);
        }

    }

    public void prepareForms(String form, String caseNo, String toDo) {

        formSelected = form;
        caseNoID = caseNo;
        formOption = toDo;
    }

    public void prepareForms(String form, String toDo) {

        formSelected = form;
        formOption = toDo;
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
     * Code for signature capture
     */

    @Override
    public void onDestroy() {
        Log.w("GetSignature", "onDestory");
        super.onDestroy();
    }


    private String getTodaysDate() {

        final Calendar c = Calendar.getInstance();
        int todaysDate =     (c.get(Calendar.YEAR) * 10000) +
                ((c.get(Calendar.MONTH) + 1) * 100) +
                (c.get(Calendar.DAY_OF_MONTH));
        Log.w("DATE:",String.valueOf(todaysDate));
        return(String.valueOf(todaysDate));

    }

    private String getCurrentTime() {

        final Calendar c = Calendar.getInstance();
        int currentTime =     (c.get(Calendar.HOUR_OF_DAY) * 10000) +
                (c.get(Calendar.MINUTE) * 100) +
                (c.get(Calendar.SECOND));
        Log.w("TIME:",String.valueOf(currentTime));
        return(String.valueOf(currentTime));

    }


    private boolean prepareDirectory()
    {
        try
        {
            if (makedirs())
            {
                return true;
            } else {
                return false;
            }
        } catch (Exception e)
        {
            e.printStackTrace();
            Toast.makeText(getActivity(), "Could not initiate File System.. Internal storage not found", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    private boolean makedirs()
    {
        File tempdir = new File(tempDir);
        if (!tempdir.exists())
            tempdir.mkdirs();

        if (tempdir.isDirectory())
        {
            File[] files = tempdir.listFiles();
            for (File file : files)
            {
                if (!file.delete())
                {
                    System.out.println("Failed to delete " + file);
                }
            }
        }
        return (tempdir.isDirectory());
    }

    public class signature extends View
    {
        private static final float STROKE_WIDTH = 5f;
        private static final float HALF_STROKE_WIDTH = STROKE_WIDTH / 2;
        private Paint paint = new Paint();
        private Path path = new Path();

        private float lastTouchX;
        private float lastTouchY;
        private final RectF dirtyRect = new RectF();

        public signature(Context context, AttributeSet attrs)
        {
            super(context, attrs);
            paint.setAntiAlias(true);
            paint.setColor(Color.BLACK);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeJoin(Paint.Join.ROUND);
            paint.setStrokeWidth(STROKE_WIDTH);
        }

        public void save(View v)
        {
            Log.v("log_tag", "Width: " + v.getWidth());
            Log.v("log_tag", "Height: " + v.getHeight());
            if(mBitmap == null)
            {
                mBitmap =  Bitmap.createBitmap(mContent.getWidth(), mContent.getHeight(), Bitmap.Config.RGB_565);;
            }
            Canvas canvas = new Canvas(mBitmap);
            try
            {
                FileOutputStream mFileOutStream = new FileOutputStream(mypath);

                v.draw(canvas);
                mBitmap.compress(Bitmap.CompressFormat.PNG, 90, mFileOutStream);
                mFileOutStream.flush();
                mFileOutStream.close();
                //    String url = MediaStore.Images.Media.insertImage(getContentResolver(), mBitmap, "title", null);  // Stores signature in Gallery in .jpg format
                //    Log.v("log_tag","url: " + url);
                //In case you want to delete the file
                //boolean deleted = mypath.delete();
                //Log.v("log_tag","deleted: " + mypath.toString() + deleted);
                //If you want to convert the image to string use base64 converter
                /** Various conversion methods using the Base64 class */
                //Convert binary image file to String data
                //    String encoded = Base64.encodeFromFile(tempDir+"/"+current);   //data/inputImage.png = tempDir+"/"+current

                //Convert String data to binary image file
                //    Base64.decodeToFile(encoded, "data/outputImage.png");

                //Convert binary image file to byte array to base64 encoded string
                //    FileInputStream mFileInputStream = new FileInputStream("data/inputImage.png");
                //    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                //    byte[] b = new byte[1024];
                //    int bytesRead = 0;
                //    while ((bytesRead = mFileInputStream.read(b)) != -1) {
                //        bos.write(b, 0, bytesRead);
                //    }
                //    byte[] ba = bos.toByteArray();
                //    encoded = Base64.encodeBytes(ba);

                //Convert String data to binary image file
                //    Base64.decodeToFile(encoded, "data/outputImage.png");

                //Convert binary image file to base64 encoded String data file
                Base64.encodeFileToFile(tempDir+current,tempDir+"encoded"+uniqueId+".txt");

                //Convert base64 encoded String data file to binary image file
                //    Base64.decodeFileToFile("data/encodedImage.txt","data/outputImage.png");

                /**End */
            }
            catch(Exception e)
            {
                Log.v("log_tag", e.toString());
            }
        }

        public void clear()
        {
            path.reset();
            invalidate();
        }

        @Override
        protected void onDraw(Canvas canvas)
        {
            canvas.drawPath(path, paint);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event)
        {
            float eventX = event.getX();
            float eventY = event.getY();
            mGetSign.setEnabled(true);

            switch (event.getAction())
            {
                case MotionEvent.ACTION_DOWN:
                    path.moveTo(eventX, eventY);
                    lastTouchX = eventX;
                    lastTouchY = eventY;
                    return true;

                case MotionEvent.ACTION_MOVE:

                case MotionEvent.ACTION_UP:

                    if(lastTouchX==eventX && lastTouchY==eventY && event.getAction()==MotionEvent.ACTION_UP) {     //case dot point
                        path.addCircle(eventX, eventY, 1, Path.Direction.CW);
                    }

                    resetDirtyRect(eventX, eventY);
                    int historySize = event.getHistorySize();
                    for (int i = 0; i < historySize; i++)
                    {
                        float historicalX = event.getHistoricalX(i);
                        float historicalY = event.getHistoricalY(i);
                        expandDirtyRect(historicalX, historicalY);
                        path.lineTo(historicalX, historicalY);
                    }
                    path.lineTo(eventX, eventY);
                    break;

                default:
                    debug("Ignored touch event: " + event.toString());
                    return false;
            }

            invalidate((int) (dirtyRect.left - HALF_STROKE_WIDTH),
                    (int) (dirtyRect.top - HALF_STROKE_WIDTH),
                    (int) (dirtyRect.right + HALF_STROKE_WIDTH),
                    (int) (dirtyRect.bottom + HALF_STROKE_WIDTH));

            lastTouchX = eventX;
            lastTouchY = eventY;

            return true;
        }

        private void debug(String string){
        }

        private void expandDirtyRect(float historicalX, float historicalY)
        {
            if (historicalX < dirtyRect.left)
            {
                dirtyRect.left = historicalX;
            }
            else if (historicalX > dirtyRect.right)
            {
                dirtyRect.right = historicalX;
            }

            if (historicalY < dirtyRect.top)
            {
                dirtyRect.top = historicalY;
            }
            else if (historicalY > dirtyRect.bottom)
            {
                dirtyRect.bottom = historicalY;
            }
        }

        private void resetDirtyRect(float eventX, float eventY)
        {
            dirtyRect.left = Math.min(lastTouchX, eventX);
            dirtyRect.right = Math.max(lastTouchX, eventX);
            dirtyRect.top = Math.min(lastTouchY, eventY);
            dirtyRect.bottom = Math.max(lastTouchY, eventY);
        }
    }

    public void showPanel(final String editText){

        // custom dialog
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.fragment_signature_canvas);
        dialog.setTitle("Signature");

        tempDir = Environment.getExternalStorageDirectory() + "/" + getResources().getString(R.string.external_dir) + "/";
        ContextWrapper cw = new ContextWrapper(getActivity().getApplicationContext());
        File directory = cw.getDir(getResources().getString(R.string.external_dir), Context.MODE_PRIVATE);

        prepareDirectory();
        uniqueId = getTodaysDate() + "_" + getCurrentTime() + "_" + Math.random();
        current = uniqueId + ".png";
        mypath = new File(tempDir, current);


        mContent = (LinearLayout) dialog.findViewById(R.id.linearLayout);
        mSignature = new signature(getActivity(), null);
        mSignature.setBackgroundColor(Color.WHITE);
        mContent.addView(mSignature, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mClear = (Button) dialog.findViewById(R.id.clear);
        mGetSign = (Button) dialog.findViewById(R.id.getsign);
        mCancel = (Button) dialog.findViewById(R.id.cancel);
        mView = mContent;

        mClear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.v("log_tag", "Panel Cleared");
                mSignature.clear();
                mGetSign.setEnabled(false);
            }
        });

        mGetSign.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Log.v("log_tag", "Panel Saved");
                mView.setDrawingCacheEnabled(true);
                mSignature.save(mView);

                showSignature(editText,uniqueId);
                dialog.dismiss();

            }
        });

        mCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.v("log_tag", "Panel Canceled");
                dialog.dismiss();
            }
        });
        dialog.show();


    }

    public void showSignature(String imageView ,String ID){

        String path = ClientFormsFragment.tempDir+"encoded"+ID+".txt";

        BufferedReader br = null;
        String sCurrentLine;
        byte[] imageAsBytes;

        try {


            br = new BufferedReader(new FileReader(path));

            while ((sCurrentLine = br.readLine()) != null) {

                //convert the string to byte array
                imageAsBytes = Base64.decode(sCurrentLine.getBytes());

                if(imageView.equalsIgnoreCase("signRep")) {
                    //set the image by decoding the byte array to bitmap
                    signRepImage.setImageBitmap(
                            BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length)
                    );
                    signRepContent = sCurrentLine;
                }
                else if (imageView.equalsIgnoreCase("signClient")){
                    //set the image by decoding the byte array to bitmap
                    signClientImage.setImageBitmap(
                            BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length)
                    );
                    signClientContent = sCurrentLine;
                }
                else if (imageView.equalsIgnoreCase("signInterpreter")){
                    //set the image by decoding the byte array to bitmap
                    signInterpreterImage.setImageBitmap(
                            BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length)
                    );
                    signInterpreterContent = sCurrentLine;
                }


            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }


    }


    /***********************************************************************/

    /**
     * Background Async Task to Create new client
     * */
    class CreateNewClient extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Saving Form..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Creating client
         * */
        protected String doInBackground(String... args) {
            String clientName = clientNameCF01.getText().toString();
            String caseNo = caseNumber.getText().toString();
            String date = presentDate.getText().toString();
            String dateOfBirth = dob.getText().toString();
            String address = addressCF01.getText().toString();
            String ps = policeStation.getSelectedItem().toString();
            String crNo = custodyRecordNo.getText().toString();
            String allegation_offence = allegation.getSelectedItem().toString();
            String incidentDateTime = incidentDate.getText().toString()+";"+incidentTime.getText().toString();
            String coAcc = coAccused.getText().toString();
            String iTime = interviewTime.getText().toString();
            String cTime = callTime.getText().toString();
            String officer = officerView.getText().toString();
            String prevClient = previousClient.getSelectedItem().toString();
            String dispatchDt = dispatchDate.getText().toString();
            String dispatchTm = dispatchTime.getText().toString();

            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("ClientName", clientName));
            params.add(new BasicNameValuePair("CaseNo", caseNo));
            params.add(new BasicNameValuePair("Date", date));
            params.add(new BasicNameValuePair("DOB", dateOfBirth));
            params.add(new BasicNameValuePair("Address", address));
            params.add(new BasicNameValuePair("PoliceStation", ps));
            params.add(new BasicNameValuePair("CustodyRecordNo", crNo));
            params.add(new BasicNameValuePair("Allegation", allegation_offence));
            params.add(new BasicNameValuePair("IncidentDateTime", incidentDateTime));
            params.add(new BasicNameValuePair("CoAccused", coAcc));
            params.add(new BasicNameValuePair("InterviewTime", iTime));
            params.add(new BasicNameValuePair("CallTime", cTime));
            params.add(new BasicNameValuePair("OfficerInCharge", officer));
            params.add(new BasicNameValuePair("PreviousClient", prevClient));
            params.add(new BasicNameValuePair("DispatchDate", dispatchDt));
            params.add(new BasicNameValuePair("DispatchTime", dispatchTm));

            // getting JSON Object
            // Note that create form url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(url_create_client,"POST", params);

            // check log cat fro response
            Log.d("Create Response", json.toString());

            // check for success tag
            try {
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // successfully created client
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            listener.showClients();
                        }
                    });

                } else {
                    // failed to create client
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
            // dismiss the dialog once done
            if(pDialog != null)
                pDialog.dismiss();
        }

    }


    /*****************************************************************
     * Background Async Task to Get complete client details
     * */
    class GetClientDetails extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Loading client details. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Getting client details in background thread
         * */
        protected String doInBackground(String... params) {

            // updating UI from Background Thread
            getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    // Check for success tag
                    int success;
                    try {
                        // Building Parameters
                        List<NameValuePair> params = new ArrayList<NameValuePair>();
                        params.add(new BasicNameValuePair("CaseNo", caseNoID));

                        // getting client details by making HTTP request
                        // Note that client details url will use GET request
                        JSONObject json = jsonParser.makeHttpRequest(
                                url_client_details, "GET", params);

                        // check your log for json response
                        Log.d("Single Client Details", json.toString());

                        // json success tag
                        success = json.getInt(TAG_SUCCESS);
                        if (success == 1) {
                            // successfully received client details
                            JSONArray clientObj = json
                                    .getJSONArray(TAG_CLIENT); // JSON Array

                            // get first client object from JSON Array
                            JSONObject client = clientObj.getJSONObject(0);

                            // client with this case no found
                            // display client data in EditText




                        if(formSelected.equals("Crim.CF001 Client Matter Form")){
                            String[] dateTimeparts = client.getString(TAG_INCIDENT_DATE_TIME).split("\\;",-1);
                            clientNameCF01.setText(client.getString(TAG_CLIENT_NAME));
                            caseNumber.setText(client.getString(TAG_CASE_NO));
                            presentDate.setText(client.getString(TAG_DATE));
                            dob.setText(client.getString(TAG_DOB));
                            addressCF01.setText(client.getString(TAG_ADDRESS));
                            policeStation.setSelection(adapter1CF1.getPosition(client.getString(TAG_POLICE_STATION)));
                            custodyRecordNo.setText(client.getString(TAG_CUSTODY_RECORD_NO));
                            allegation.setSelection(adapter2CF1.getPosition(client.getString(TAG_ALLEGATION)));
                            incidentDate.setText(dateTimeparts[0]);
                            incidentTime.setText(dateTimeparts[1]);
                            coAccused.setText(client.getString(TAG_CO_ACCUSED));
                            interviewTime.setText(client.getString(TAG_INTERVIEW_TIME));
                            callTime.setText(client.getString(TAG_CALL_TIME));
                            officerView.setText(client.getString(TAG_OFFICER_IN_CHARGE));
                            previousClient.setSelection(adapter3CF1.getPosition(client.getString(TAG_PREVIOUS_CLIENT)));
                            dispatchDate.setText(client.getString(TAG_DISPATCH_DATE));
                            dispatchTime.setText(client.getString(TAG_DISPATCH_TIME));
                        }
                        if(formSelected.equals("Crim.CF002 Case Dispatch Form")){
                            clientNameCF01.setText(client.getString(TAG_CLIENT_NAME));
                            caseNumber.setText(client.getString(TAG_CASE_NO));
                            policeStation.setSelection(adapter1CF1.getPosition(client.getString(TAG_POLICE_STATION)));
                            dispatchDate.setText(client.getString(TAG_DISPATCH_DATE));
                            dispatchTime.setText(client.getString(TAG_DISPATCH_TIME));
                            feeEarner.setSelection(adapter1CF2.getPosition(client.getString(TAG_FEE_EARNER)));
                            pin.setText(client.getString(TAG_PIN));
                            solicitor.setText(client.getString(TAG_SOLICITOR));
                            distancePS.setText(client.getString(TAG_DISTANCE_PS));
                            eta.setText(client.getString(TAG_ETA));
                            dispatchDateFE.setText(client.getString(TAG_DISPATCH_DATE_FE));
                            dispatchTimeFE.setText(client.getString(TAG_DISPATCH_TIME_FE));
                            acceptDateFE.setText(client.getString(TAG_ACCEPT_DATE_FE));
                            acceptTimeFE.setText(client.getString(TAG_ACCEPT_TIME_FE));
                            allocateFE.setText(client.getString(TAG_ALLOCATE_FE));
                        }
                        if(formSelected.equals("Crim.CF003 Fee Earner Update")){
                            String[] cf03Data = client.getString(TAG_CF03_DATA).split("\\<>",-1);
                            clientNameCF01.setText(client.getString(TAG_CLIENT_NAME));
                            caseNumber.setText(client.getString(TAG_CASE_NO));
                            dob.setText(client.getString(TAG_DOB));
                            addressCF01.setText(client.getString(TAG_ADDRESS));
                            feeEarner.setSelection(adapter1CF2.getPosition(client.getString(TAG_FEE_EARNER)));
                            dispatchDateFE.setText(client.getString(TAG_DISPATCH_DATE_FE));
                            dispatchTimeFE.setText(client.getString(TAG_DISPATCH_TIME_FE));
                            custodyRecordNo.setText(client.getString(TAG_CUSTODY_RECORD_NO));
                            arrivalTimePS.setText(cf03Data[0]);
                            timeEngaged.setText(cf03Data[1]);
                            attestingOfficer.setText(cf03Data[2]);
                            arrestTime.setText(cf03Data[3]);
                            arrestPlace.setText(cf03Data[4]);
                            arrivalTimeP_S.setText(cf03Data[5]);
                            detentionTime.setText(cf03Data[6]);
                            officerInCase.setText(cf03Data[7]);
                            disclosureDetails.setText(cf03Data[8]);
                            sample1.setSelection(adapter3CF1.getPosition(cf03Data[9]));
                            sample2.setSelection(adapter3CF1.getPosition(cf03Data[10]));
                            sample3.setSelection(adapter1CF3.getPosition(cf03Data[11]));
                            sample4.setSelection(adapter3CF1.getPosition(cf03Data[12]));
                            sample5.setText(cf03Data[13]);
                            search1.setText(cf03Data[14]);
                            search2.setSelection(adapter2CF3.getPosition(cf03Data[15]));
                            search3.setText(cf03Data[16]);
                            search4.setText(cf03Data[17]);
                            coAccused1.setSelection(adapter3CF1.getPosition(cf03Data[18]));
                            coAccused2.setSelection(adapter3CF3.getPosition(cf03Data[19]));
                            coAccused3.setText(cf03Data[20]);
                            coAccused4.setSelection(adapter3CF1.getPosition(cf03Data[21]));
                            medicalCondition1.setText(cf03Data[22]);
                            medicalCondition2.setSelection(adapter3CF1.getPosition(cf03Data[23]));
                            medicalCondition3.setSelection(adapter4CF3.getPosition(cf03Data[24]));
                            medicalCondition4.setText(cf03Data[25]);
                            medicalCondition5.setSelection(adapter4CF3.getPosition(cf03Data[26]));
                            medicalCondition6.setText(cf03Data[27]);
                            medicalCondition7.setSelection(adapter3CF1.getPosition(cf03Data[28]));
                            medicalCondition8.setSelection(adapter3CF1.getPosition(cf03Data[29]));
                            medicalCondition8Detail.setText(cf03Data[30]);
                            medicalCondition9.setSelection(adapter3CF1.getPosition(cf03Data[31]));
                            medicalCondition9Detail.setText(cf03Data[32]);
                            medicalCondition10.setText(cf03Data[33]);
                            medicalCondition11.setSelection(adapter4CF3.getPosition(cf03Data[34]));
                            medicalCondition12.setSelection(adapter3CF1.getPosition(cf03Data[35]));
                            clientInstruction.setText(cf03Data[36]);
                            question1.setSelection(adapter3CF1.getPosition(cf03Data[37]));
                            question2.setSelection(adapter3CF1.getPosition(cf03Data[38]));
                            question3.setSelection(adapter3CF1.getPosition(cf03Data[39]));
                            question4.setSelection(adapter3CF1.getPosition(cf03Data[40]));
                            question5.setSelection(adapter3CF1.getPosition(cf03Data[41]));
                            question6.setSelection(adapter3CF1.getPosition(cf03Data[42]));
                            question7.setSelection(adapter3CF1.getPosition(cf03Data[43]));
                            question8.setSelection(adapter3CF1.getPosition(cf03Data[44]));
                            question9.setSelection(adapter4CF3.getPosition(cf03Data[45]));
                            question10.setSelection(adapter4CF3.getPosition(cf03Data[46]));
                            question11.setSelection(adapter3CF1.getPosition(cf03Data[47]));
                            question12.setSelection(adapter3CF1.getPosition(cf03Data[48]));
                            question13.setSelection(adapter4CF3.getPosition(cf03Data[49]));
                            question14.setSelection(adapter3CF1.getPosition(cf03Data[50]));
                            question15.setSelection(adapter3CF1.getPosition(cf03Data[51]));
                            question16.setSelection(adapter4CF3.getPosition(cf03Data[52]));
                            question17.setSelection(adapter4CF3.getPosition(cf03Data[53]));
                            question18.setSelection(adapter3CF1.getPosition(cf03Data[54]));
                            question19.setSelection(adapter4CF3.getPosition(cf03Data[55]));
                            question20.setSelection(adapter4CF3.getPosition(cf03Data[56]));
                            question21.setSelection(adapter4CF3.getPosition(cf03Data[57]));
                            question22.setSelection(adapter4CF3.getPosition(cf03Data[58]));
                            notesOfAdvice.setText(cf03Data[59]);
                            printInterpreter.setText(cf03Data[60]);
                            interview1Date.setText(cf03Data[61]);
                            interview1StartTime.setText(cf03Data[62]);
                            interview1EndTime.setText(cf03Data[63]);
                            interview1Officer.setText(cf03Data[64]);
                            interview1Others.setText(cf03Data[65]);
                            interview1Tape.setText(cf03Data[66]);
                            interview1Caution.setSelection(adapter3CF1.getPosition(cf03Data[67]));
                            interview2Date.setText(cf03Data[68]);
                            interview2StartTime.setText(cf03Data[69]);
                            interview2EndTime.setText(cf03Data[70]);
                            interview2Officer.setText(cf03Data[71]);
                            interview2Others.setText(cf03Data[72]);
                            interview2Tape.setText(cf03Data[73]);
                            interview2Caution.setSelection(adapter3CF1.getPosition(cf03Data[74]));
                            interview3Date.setText(cf03Data[75]);
                            interview3StartTime.setText(cf03Data[76]);
                            interview3EndTime.setText(cf03Data[77]);
                            interview3Officer.setText(cf03Data[78]);
                            interview3Others.setText(cf03Data[79]);
                            interview3Tape.setText(cf03Data[80]);
                            interview3Caution.setSelection(adapter3CF1.getPosition(cf03Data[81]));
                            interview4Date.setText(cf03Data[82]);
                            interview4StartTime.setText(cf03Data[83]);
                            interview4EndTime.setText(cf03Data[84]);
                            interview4Officer.setText(cf03Data[85]);
                            interview4Others.setText(cf03Data[86]);
                            interview4Tape.setText(cf03Data[87]);
                            interview4Caution.setSelection(adapter3CF1.getPosition(cf03Data[88]));
                            noteInterview.setText(cf03Data[89]);
                            nfa.setText(cf03Data[90]);
                            cautionOffence.setSelection(adapter2CF1.getPosition(cf03Data[91]));
                            bailDate.setText(cf03Data[92]);
                            bailTime.setText(cf03Data[93]);
                            bailLocation.setSelection(adapter1CF1.getPosition(cf03Data[94]));
                            bailType.setSelection(adapter5CF3.getPosition(cf03Data[95]));
                            bailCondition.setText(cf03Data[96]);
                            bailReason.setText(cf03Data[97]);
                            remandDate.setText(cf03Data[98]);
                            remandTime.setText(cf03Data[99]);
                            remandLocation.setSelection(adapter1CF1.getPosition(cf03Data[100]));
                            chargedOffence.setSelection(adapter2CF1.getPosition(cf03Data[101]));
                            chargedBailType.setSelection(adapter5CF3.getPosition(cf03Data[102]));
                            chargedCondition.setText(cf03Data[103]);
                            chargedDate.setText(cf03Data[104]);
                            chargedTime.setText(cf03Data[105]);
                            chargedLocation.setSelection(adapter1CF1.getPosition(cf03Data[106]));
                            custodyOffence.setSelection(adapter2CF1.getPosition(cf03Data[107]));
                            custodyDate.setText(cf03Data[108]);
                            custodyTime.setText(cf03Data[109]);
                            custodyLocation.setSelection(adapter1CF1.getPosition(cf03Data[110]));
                            hubDate.setText(cf03Data[111]);
                            hubTime.setText(cf03Data[112]);
                            clientCare.setSelection(adapter3CF1.getPosition(cf03Data[113]));
                            outcomeLetter.setSelection(adapter3CF1.getPosition(cf03Data[114]));
                            try {

                                byte[] imageAsBytes1 = Base64.decode((client.getString(TAG_SIGN_REP)).getBytes());
                                signRepImage.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes1, 0, imageAsBytes1.length));
                                byte[] imageAsBytes2 = Base64.decode((client.getString(TAG_SIGN_CLIENT)).getBytes());
                                signClientImage.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes2, 0, imageAsBytes2.length));
                                byte[] imageAsBytes3 = Base64.decode((client.getString(TAG_SIGN_INTER)).getBytes());
                                signInterpreterImage.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes3, 0, imageAsBytes3.length));


                            }catch (IOException e) {
                                e.printStackTrace();
                            }

                            }
                            if(formSelected.equals("Crim.CF004 Fee Earner Claim for Costs at Police Station Form")) {
                                String[] cf04Data = client.getString(TAG_CF04_DATA).split("\\;", -1);
                                caseNumber.setText(client.getString(TAG_CASE_NO));
                                autoCalCF04.setText(cf04Data[0]);
                                autoSolCF04.setText(cf04Data[1]);
                            }
                            if(formSelected.equals("Crim.CF005 Case Dispatch Form Plea Hearing")) {
                                String[] cf05Data = client.getString(TAG_CF05_DATA).split("\\;", -1);
                                String[] cf03Data = client.getString(TAG_CF03_DATA).split("\\<>",-1);
                            //    dateCF05.setText(cf05Data[0]);
                            //    timeCF05.setText(cf05Data[1]);
                                caseNumber.setText(client.getString(TAG_CASE_NO));
                                clientNameCF01.setText(client.getString(TAG_CLIENT_NAME));
                                hubDate.setText(client.getString(cf03Data[111]));
                                hubTime.setText(client.getString(cf03Data[112]));
                                feeEarnerCF05.setSelection(adapter1CF2.getPosition(cf05Data[2]));
                                pinCF05.setText(cf05Data[3]);
                                solicitorCF05.setText(cf05Data[4]);
                                legalAid.setText(cf05Data[5]);
                                acceptDateFECF05.setText(cf05Data[6]);
                                acceptTimeFECF05.setText(cf05Data[7]);
                            }
                            if(formSelected.equals("Crim.CF006 Fee Earner Update Plea Hearing")) {
                                String[] cf05Data = client.getString(TAG_CF05_DATA).split("\\;", -1);
                                String[] cf06Data = client.getString(TAG_CF06_DATA).split("\\;", -1);
                                clientNameCF01.setText(client.getString(TAG_CLIENT_NAME));
                                caseNumber.setText(client.getString(TAG_CASE_NO));
                                dob.setText(client.getString(TAG_DOB));
                                addressCF01.setText(client.getString(TAG_ADDRESS));
                                feeEarnerCF05.setSelection(adapter1CF2.getPosition(cf05Data[2]));
                                acceptDateFECF05.setText(cf05Data[6]);
                                acceptTimeFECF05.setText(cf05Data[7]);
                                arrivalCourt.setText(cf06Data[0]);
                                timeEngCF06.setText(cf06Data[1]);
                                clientInstCF06.setText(cf06Data[2]);
                                investigation1.setSelection(adapter3CF1.getPosition(cf06Data[3]));
                                investigation2.setSelection(adapter3CF1.getPosition(cf06Data[4]));
                                investigation3.setSelection(adapter3CF1.getPosition(cf06Data[5]));
                                investigation4.setText(cf06Data[6]);
                                investigation5.setSelection(adapter3CF1.getPosition(cf06Data[7]));
                                investigation6.setSelection(adapter3CF1.getPosition(cf06Data[8]));
                                investigation7.setSelection(adapter3CF1.getPosition(cf06Data[9]));
                                investigation8.setSelection(adapter3CF1.getPosition(cf06Data[10]));
                                investigation9.setSelection(adapter4CF3.getPosition(cf06Data[11]));
                                investigation10.setSelection(adapter3CF1.getPosition(cf06Data[12]));
                                investigation11.setSelection(adapter3CF1.getPosition(cf06Data[13]));
                                investigation12.setSelection(adapter3CF1.getPosition(cf06Data[14]));
                                investigation13.setSelection(adapter3CF1.getPosition(cf06Data[15]));
                                investigation14.setText(cf06Data[16]);
                                adjournedReason.setSelection(adapter1CF6.getPosition(cf06Data[17]));
                                adjournedDate.setText(cf06Data[18]);
                                adjournedTime.setText(cf06Data[19]);
                                adjournedLocation.setSelection(adapter1CF1.getPosition(cf06Data[20]));
                                adjournedBail.setSelection(adapter3CF1.getPosition(cf06Data[21]));
                                adjournedBailType.setSelection(adapter5CF3.getPosition(cf06Data[22]));
                                adjournedCondition.setText(cf06Data[23]);
                                guiltyAwaitingCharges.setText(cf06Data[24]);
                                guiltyAwaitingDate.setText(cf06Data[25]);
                                guiltyAwaitingTime.setText(cf06Data[26]);
                                guiltyAwaitingLocation.setSelection(adapter1CF1.getPosition(cf06Data[27]));
                                guiltySentencedCharges.setText(cf06Data[28]);
                                guiltySentencedTerms.setText(cf06Data[29]);
                                notGuiltyCustodyDetail1.setSelection(adapter2CF1.getPosition(cf06Data[30]));
                                notGuiltyCustodyDetail2.setSelection(adapter2CF1.getPosition(cf06Data[31]));
                                notGuiltyCustodyDate.setText(cf06Data[32]);
                                notGuiltyCustodyTime.setText(cf06Data[33]);
                                notGuiltyCustodyLocation.setSelection(adapter1CF1.getPosition(cf06Data[34]));
                                notGuiltyDetail1.setSelection(adapter2CF1.getPosition(cf06Data[35]));
                                notGuiltyDetail2.setSelection(adapter2CF1.getPosition(cf06Data[36]));
                                notGuiltyDate.setText(cf06Data[37]);
                                notGuiltyTime.setText(cf06Data[38]);
                                notGuiltyLocation.setSelection(adapter1CF1.getPosition(cf06Data[39]));
                                notGuiltyBailType.setSelection(adapter5CF3.getPosition(cf06Data[40]));
                                notGuiltyConditions.setText(cf06Data[41]);
                                hubDateCF06.setText(cf06Data[42]);
                                hubTimeCF06.setText(cf06Data[43]);
                                outcomeLetterCF06.setSelection(adapter3CF1.getPosition(cf06Data[44]));

                            }
                            if(formSelected.equals("Crim.CF007 Fee Earner Claim for Costs at Plea Hearing")) {
                                String[] cf07Data = client.getString(TAG_CF07_DATA).split("\\;", -1);
                                caseNumber.setText(client.getString(TAG_CASE_NO));
                                autoCalCF07.setText(cf07Data[0]);
                                autoSolCF07.setText(cf07Data[1]);
                            }
                            if(formSelected.equals("Crim.CF008 Crown Court Case Prep Form")) {
                                String[] cf06Data = client.getString(TAG_CF06_DATA).split("\\;", -1);
                                String[] cf08Data = client.getString(TAG_CF08_DATA).split("\\;", -1);
                                caseNumber.setText(client.getString(TAG_CASE_NO));
                                clientNameCF01.setText(client.getString(TAG_CLIENT_NAME));
                                hubDateCF06.setText(cf06Data[42]);
                                convictionDateCF08.setText(cf08Data[0]);
                                casePreparedDateCF08.setText(cf08Data[1]);
                                listingOfficerDateCF08.setText(cf08Data[2]);
                                numberOfPagesCF08.setText(cf08Data[3]);
                                ongoingMatterNumberCF08.setText(cf08Data[4]);
                            }
                            if(formSelected.equals("Crim.CF009 Magistrates Court Case Prep Form")) {
                                String[] cf06Data = client.getString(TAG_CF06_DATA).split("\\;", -1);
                                String[] cf09Data = client.getString(TAG_CF09_DATA).split("\\;", -1);
                                caseNumber.setText(client.getString(TAG_CASE_NO));
                                clientNameCF01.setText(client.getString(TAG_CLIENT_NAME));
                                hubDateCF06.setText(cf06Data[42]);
                                convictionDateCF09.setText(cf09Data[0]);
                                casePreparedDateCF09.setText(cf09Data[1]);
                                listingOfficerDateCF09.setText(cf09Data[2]);
                                numberOfPagesCF09.setText(cf09Data[3]);
                                ongoingMatterNumberCF09.setText(cf09Data[4]);
                            }
                            if(formSelected.equals("Crim.CF010 Case Dispatch For Trial")) {
                                String[] cf10Data = client.getString(TAG_CF10_DATA).split("\\;", -1);
                                String[] cf06Data = client.getString(TAG_CF06_DATA).split("\\;",-1);
                        //        dateCF10.setText(cf10Data[0]);
                        //        timeCF10.setText(cf10Data[1]);
                                caseNumber.setText(client.getString(TAG_CASE_NO));
                                clientNameCF01.setText(client.getString(TAG_CLIENT_NAME));
                                hubDateCF06.setText(client.getString(cf06Data[42]));
                                hubTimeCF06.setText(client.getString(cf06Data[43]));
                                feeEarnerCF10.setSelection(adapter1CF2.getPosition(cf10Data[2]));
                                pinCF10.setText(cf10Data[3]);
                                solicitorCF10.setText(cf10Data[4]);
                                legalAidCF10.setText(cf10Data[5]);
                                acceptDateFECF10.setText(cf10Data[6]);
                                acceptTimeFECF10.setText(cf10Data[7]);
                                courtBundleDate.setText(cf10Data[8]);
                                courtBundleTime.setText(cf10Data[9]);
                                courtBundleAcceptDate.setText(cf10Data[10]);
                                courtBundleAcceptTime.setText(cf10Data[11]);
                            }
                            if(formSelected.equals("Crim.CF011 Fee Earner Update Trial")) {
                                String[] cf10Data = client.getString(TAG_CF10_DATA).split("\\;", -1);
                                String[] cf11Data = client.getString(TAG_CF11_DATA).split("\\;", -1);
                                clientNameCF01.setText(client.getString(TAG_CLIENT_NAME));
                                caseNumber.setText(client.getString(TAG_CASE_NO));
                                dob.setText(client.getString(TAG_DOB));
                                addressCF01.setText(client.getString(TAG_ADDRESS));
                                acceptDateFECF10.setText(cf10Data[6]);
                                arrivalCourtCF11.setText(cf11Data[0]);
                                timeEngCF11.setText(cf11Data[1]);
                                clientCommentCF11.setText(cf11Data[2]);
                                adviceViewCF11.setText(cf11Data[3]);
                                adjournedReasonCF11.setSelection(adapter1CF6.getPosition(cf11Data[4]));
                                adjournedDateCF11.setText(cf11Data[5]);
                                adjournedTimeCF11.setText(cf11Data[6]);
                                adjournedLocationCF11.setSelection(adapter1CF1.getPosition(cf11Data[7]));
                                adjournedBailCF11.setSelection(adapter3CF1.getPosition(cf11Data[8]));
                                adjournedBailTypeCF11.setSelection(adapter5CF3.getPosition(cf11Data[9]));
                                adjournedConditionCF11.setText(cf11Data[10]);
                                awaitingIssuedCharges.setSelection(adapter2CF1.getPosition(cf11Data[11]));
                                awaitingUnfoundCharges.setSelection(adapter2CF1.getPosition(cf11Data[12]));
                                awaitingBailCF11.setSelection(adapter3CF1.getPosition(cf11Data[13]));
                                awaitingBailTypeCF11.setSelection(adapter5CF3.getPosition(cf11Data[14]));
                                awaitingConditionCF11.setText(cf11Data[15]);
                                guiltyAwaitingDateCF11.setText(cf11Data[16]);
                                guiltyAwaitingTimeCF11.setText(cf11Data[17]);
                                guiltyAwaitingLocationCF11.setSelection(adapter1CF1.getPosition(cf11Data[18]));
                                sentencedIssuedCharges.setSelection(adapter2CF1.getPosition(cf11Data[19]));
                                sentencedUnfoundCharges.setSelection(adapter2CF1.getPosition(cf11Data[20]));
                                sentenceReceived.setText(cf11Data[21]);
                                notGuiltyVerdict.setText(cf11Data[22]);
                                hubDateCF11.setText(cf11Data[23]);
                                hubTimeCF11.setText(cf11Data[24]);
                                outcomeLetterCF11.setSelection(adapter3CF1.getPosition(cf11Data[25]));
                            }
                            if(formSelected.equals("Crim.CF012 Fee Earner Claim for Costs at Trial")) {
                                String[] cf12Data = client.getString(TAG_CF12_DATA).split("\\;", -1);
                                caseNumber.setText(client.getString(TAG_CASE_NO));
                                autoCalViewCF12.setText(cf12Data[0]);
                                autoSolViewCF12.setText(cf12Data[1]);
                            }

                            } else {
                                // client with case no not found
                            }
                        }catch(JSONException e){
                            e.printStackTrace();
                        }
                    }
                }

                );

                return null;
            }


                    /**
                     * After completing background task Dismiss the progress dialog
                     * **/

        protected void onPostExecute(String file_url) {
            // dismiss the dialog once got all details
            pDialog.dismiss();
        }
    }

    /*************************************************************************
     * Background Async Task to  Save client Details
     * */
    class SaveClientDetails extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Saving form ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Saving client
         * */
        protected String doInBackground(String... args) {

            // getting updated data from EditTexts
            String clientName = clientNameCF01.getText().toString();
            String date = presentDate.getText().toString();
            String dateOfBirth = dob.getText().toString();
            String address = addressCF01.getText().toString();
            String ps = policeStation.getSelectedItem().toString();
            String crNo = custodyRecordNo.getText().toString();
            String allegation_offence = allegation.getSelectedItem().toString();
            String incidentDateTime = incidentDate.getText().toString()+";"+incidentTime.getText().toString();
            String coAcc = coAccused.getText().toString();
            String iTime = interviewTime.getText().toString();
            String cTime = callTime.getText().toString();
            String officer = officerView.getText().toString();
            String prevClient = previousClient.getSelectedItem().toString();
            String dispatchDt = dispatchDate.getText().toString();
            String dispatchTm = dispatchTime.getText().toString();


                // Building Parameters
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair(TAG_CASE_NO, caseNoID));
                params.add(new BasicNameValuePair(TAG_CLIENT_NAME, clientName));
                params.add(new BasicNameValuePair(TAG_DATE, date));
                params.add(new BasicNameValuePair(TAG_DOB, dateOfBirth));
                params.add(new BasicNameValuePair(TAG_ADDRESS, address));
                params.add(new BasicNameValuePair(TAG_POLICE_STATION, ps));
                params.add(new BasicNameValuePair(TAG_CUSTODY_RECORD_NO, crNo));
                params.add(new BasicNameValuePair(TAG_ALLEGATION, allegation_offence));
                params.add(new BasicNameValuePair(TAG_INCIDENT_DATE_TIME, incidentDateTime));
                params.add(new BasicNameValuePair(TAG_CO_ACCUSED, coAcc));
                params.add(new BasicNameValuePair(TAG_INTERVIEW_TIME, iTime));
                params.add(new BasicNameValuePair(TAG_CALL_TIME, cTime));
                params.add(new BasicNameValuePair(TAG_OFFICER_IN_CHARGE, officer));
                params.add(new BasicNameValuePair(TAG_PREVIOUS_CLIENT, prevClient));
                params.add(new BasicNameValuePair(TAG_DISPATCH_DATE, dispatchDt));
                params.add(new BasicNameValuePair(TAG_DISPATCH_TIME, dispatchTm));

                // sending modified data through http request
                // Notice that update client url accepts POST method
                JSONObject json = jsonParser.makeHttpRequest(url_update_client,
                        "POST", params);

            // check json success tag
            try {
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // successfully updated
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            listener.showClients();
                        }
                    });
                } else {
                    // failed to update client
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
            // dismiss the dialog once client updated
            pDialog.dismiss();
        }
    }

    /*************************************************************************
     * Background Async Task to  Save form CF02 Details
     * */
    class SaveFormCF02Details extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Saving form ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Saving form CF02
         * */
        protected String doInBackground(String... args) {

            // getting updated data from EditTexts
            String stFeeEarner = feeEarner.getSelectedItem().toString();
            String stPin = pin.getText().toString();
            String stSolicitor = solicitor.getText().toString();
            String stDistancePS = distancePS.getText().toString();
            String stEta = eta.getText().toString();
            String stDispatchDateFE = dispatchDateFE.getText().toString();
            String stDispatchTimeFE = dispatchTimeFE.getText().toString();
            String stAcceptDateFE = acceptDateFE.getText().toString();
            String stAcceptTimeFE = acceptTimeFE.getText().toString();
            String stAllocateFE = allocateFE.getText().toString();

            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair(TAG_CASE_NO, caseNoID));
            params.add(new BasicNameValuePair(TAG_FEE_EARNER, stFeeEarner));
            params.add(new BasicNameValuePair(TAG_PIN, stPin));
            params.add(new BasicNameValuePair(TAG_SOLICITOR, stSolicitor));
            params.add(new BasicNameValuePair(TAG_DISTANCE_PS, stDistancePS));
            params.add(new BasicNameValuePair(TAG_ETA, stEta));
            params.add(new BasicNameValuePair(TAG_DISPATCH_DATE_FE, stDispatchDateFE));
            params.add(new BasicNameValuePair(TAG_DISPATCH_TIME_FE, stDispatchTimeFE));
            params.add(new BasicNameValuePair(TAG_ACCEPT_DATE_FE, stAcceptDateFE));
            params.add(new BasicNameValuePair(TAG_ACCEPT_TIME_FE, stAcceptTimeFE));
            params.add(new BasicNameValuePair(TAG_ALLOCATE_FE, stAllocateFE));

            // sending modified data through http request
            // Notice that update client url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(url_update_formCF02,
                    "POST", params);

            // check json success tag
            try {
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // successfully updated
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            listener.showClients();
                        }
                    });
                } else {
                    // failed to update client
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
            // dismiss the dialog once client updated
            pDialog.dismiss();
        }
    }

    /*************************************************************************
     * Background Async Task to  Save form CF03 Details
     * */
    class SaveFormCF03Details extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Saving form ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Saving form CF03
         * */
        protected String doInBackground(String... args) {

            // getting updated data from EditTexts
            String stArrivalTimePS = arrivalTimePS.getText().toString();
            String stTimeEngaged = timeEngaged.getText().toString();
            String stAttestingOfficer = attestingOfficer.getText().toString();
            String stArrestTime = arrestTime.getText().toString();
            String stArrestPlace = arrestPlace.getText().toString();
            String stArrivalTimeP_S = arrivalTimeP_S.getText().toString();
            String stDetentionTime = detentionTime.getText().toString();
            String stOfficerInCase = officerInCase.getText().toString();
            String stDisclosureDetails = disclosureDetails.getText().toString();
            String stSample1 = sample1.getSelectedItem().toString();
            String stSample2 = sample2.getSelectedItem().toString();
            String stSample3 = sample3.getSelectedItem().toString();
            String stSample4 = sample4.getSelectedItem().toString();
            String stSample5 = sample5.getText().toString();
            String stSearch1 = search1.getText().toString();
            String stSearch2 = search2.getSelectedItem().toString();
            String stSearch3 = search3.getText().toString();
            String stSearch4 = search4.getText().toString();
            String stCoAccused1 = coAccused1.getSelectedItem().toString();
            String stCoAccused2 = coAccused2.getSelectedItem().toString();
            String stCoAccused3 = coAccused3.getText().toString();
            String stCoAccused4 = coAccused4.getSelectedItem().toString();
            String stMedicalCondition1 = medicalCondition1.getText().toString();
            String stMedicalCondition2 = medicalCondition2.getSelectedItem().toString();
            String stMedicalCondition3 = medicalCondition3.getSelectedItem().toString();
            String stMedicalCondition4 = medicalCondition4.getText().toString();
            String stMedicalCondition5 = medicalCondition5.getSelectedItem().toString();
            String stMedicalCondition6 = medicalCondition6.getText().toString();
            String stMedicalCondition7 = medicalCondition7.getSelectedItem().toString();
            String stMedicalCondition8 = medicalCondition8.getSelectedItem().toString();
            String stMedicalCondition8Detail = "";
            if(stMedicalCondition8.equals("Yes")) {
                stMedicalCondition8Detail = medicalCondition8Detail.getText().toString();
            }
            String stMedicalCondition9 = medicalCondition9.getSelectedItem().toString();
            String stMedicalCondition9Detail = "";
            if(stMedicalCondition9.equals("Yes")) {
                stMedicalCondition9Detail = medicalCondition9Detail.getText().toString();
            }
            String stMedicalCondition10 = medicalCondition10.getText().toString();
            String stMedicalCondition11 = medicalCondition11.getSelectedItem().toString();
            String stMedicalCondition12 = medicalCondition12.getSelectedItem().toString();
            String stClientInstruction = clientInstruction.getText().toString();
            String stQuestion1 = question1.getSelectedItem().toString();
            String stQuestion2 = question2.getSelectedItem().toString();
            String stQuestion3 = question3.getSelectedItem().toString();
            String stQuestion4 = question4.getSelectedItem().toString();
            String stQuestion5 = question5.getSelectedItem().toString();
            String stQuestion6 = question6.getSelectedItem().toString();
            String stQuestion7 = question7.getSelectedItem().toString();
            String stQuestion8 = question8.getSelectedItem().toString();
            String stQuestion9 = question9.getSelectedItem().toString();
            String stQuestion10 = question10.getSelectedItem().toString();
            String stQuestion11 = question11.getSelectedItem().toString();
            String stQuestion12 = question12.getSelectedItem().toString();
            String stQuestion13 = question13.getSelectedItem().toString();
            String stQuestion14 = question14.getSelectedItem().toString();
            String stQuestion15 = question15.getSelectedItem().toString();
            String stQuestion16 = question16.getSelectedItem().toString();
            String stQuestion17 = question17.getSelectedItem().toString();
            String stQuestion18 = question18.getSelectedItem().toString();
            String stQuestion19 = question19.getSelectedItem().toString();
            String stQuestion20 = question20.getSelectedItem().toString();
            String stQuestion21 = question21.getSelectedItem().toString();
            String stQuestion22 = question22.getSelectedItem().toString();
            String stNotesOfAdvice = notesOfAdvice.getText().toString();
            String stPrintInterpreter = printInterpreter.getText().toString();
            String stInterview1Date = interview1Date.getText().toString();
            String stInterview1StartTime = interview1StartTime.getText().toString();
            String stInterview1EndTime = interview1EndTime.getText().toString();
            String stInterview1Officer = interview1Officer.getText().toString();
            String stInterview1Others = interview1Others.getText().toString();
            String stInterview1Tape = interview1Tape.getText().toString();
            String stInterview1Caution = interview1Caution.getSelectedItem().toString();
            String stInterview2Date = interview2Date.getText().toString();
            String stInterview2StartTime = interview2StartTime.getText().toString();
            String stInterview2EndTime = interview2EndTime.getText().toString();
            String stInterview2Officer = interview2Officer.getText().toString();
            String stInterview2Others = interview2Others.getText().toString();
            String stInterview2Tape = interview2Tape.getText().toString();
            String stInterview2Caution = interview2Caution.getSelectedItem().toString();
            String stInterview3Date = interview3Date.getText().toString();
            String stInterview3StartTime = interview3StartTime.getText().toString();
            String stInterview3EndTime = interview3EndTime.getText().toString();
            String stInterview3Officer = interview3Officer.getText().toString();
            String stInterview3Others = interview3Others.getText().toString();
            String stInterview3Tape = interview3Tape.getText().toString();
            String stInterview3Caution = interview3Caution.getSelectedItem().toString();
            String stInterview4Date = interview4Date.getText().toString();
            String stInterview4StartTime = interview4StartTime.getText().toString();
            String stInterview4EndTime = interview4EndTime.getText().toString();
            String stInterview4Officer = interview4Officer.getText().toString();
            String stInterview4Others = interview4Others.getText().toString();
            String stInterview4Tape = interview4Tape.getText().toString();
            String stInterview4Caution = interview4Caution.getSelectedItem().toString();
            String stNoteInterview = noteInterview.getText().toString();
            String stNfa = nfa.getText().toString();
            String stCautionOffence = cautionOffence.getSelectedItem().toString();
            String stBailDate = bailDate.getText().toString();
            String stBailTime = bailTime.getText().toString();
            String stBailLocation = bailLocation.getSelectedItem().toString();
            String stBailType = bailType.getSelectedItem().toString();
            String stBailCondition = "";
            if(stBailType.equals("Conditional")) {
                stBailCondition = bailCondition.getText().toString();
            }
            String stBailReason = bailReason.getText().toString();
            String stRemandDate = remandDate.getText().toString();
            String stRemandTime = remandTime.getText().toString();
            String stRemandLocation = remandLocation.getSelectedItem().toString();
            String stChargedOffence = chargedOffence.getSelectedItem().toString();
            String stChargedBailType = chargedBailType.getSelectedItem().toString();
            String stChargedCondition = "";
            if(stChargedBailType.equals("Conditional")) {
                stChargedCondition = chargedCondition.getText().toString();
            }
            String stChargedDate = chargedDate.getText().toString();
            String stChargedTime = chargedTime.getText().toString();
            String stChargedLocation = chargedLocation.getSelectedItem().toString();
            String stCustodyOffence = custodyOffence.getSelectedItem().toString();
            String stCustodyDate = custodyDate.getText().toString();
            String stCustodyTime = custodyTime.getText().toString();
            String stCustodyLocation = custodyLocation.getSelectedItem().toString();
            String stHubDate = hubDate.getText().toString();
            String stHubTime = hubTime.getText().toString();
            String stClientCare = clientCare.getSelectedItem().toString();
            String stOutcomeLetter = outcomeLetter.getSelectedItem().toString();

            //Building the save data format
            String cf03Data = stArrivalTimePS+"<>"+stTimeEngaged+"<>"+stAttestingOfficer+"<>"+stArrestTime+"<>"+stArrestPlace+"<>"+stArrivalTimeP_S+"<>"+
                              stDetentionTime+"<>"+stOfficerInCase+"<>"+stDisclosureDetails+"<>"+stSample1+"<>"+stSample2+"<>"+stSample3+"<>"+stSample4+"<>"+
                              stSample5+"<>"+stSearch1+"<>"+stSearch2+"<>"+stSearch3+"<>"+stSearch4+"<>"+stCoAccused1+"<>"+stCoAccused2+"<>"+stCoAccused3+"<>"+
                              stCoAccused4+"<>"+stMedicalCondition1+"<>"+stMedicalCondition2+"<>"+stMedicalCondition3+"<>"+stMedicalCondition4+"<>"+
                              stMedicalCondition5+"<>"+stMedicalCondition6+"<>"+stMedicalCondition7+"<>"+stMedicalCondition8+"<>"+stMedicalCondition8Detail+"<>"+
                              stMedicalCondition9+"<>"+stMedicalCondition9Detail+"<>"+stMedicalCondition10+"<>"+stMedicalCondition11+"<>"+stMedicalCondition12+"<>"+
                              stClientInstruction+"<>"+stQuestion1+"<>"+stQuestion2+"<>"+stQuestion3+"<>"+stQuestion4+"<>"+stQuestion5+"<>"+stQuestion6+"<>"+
                              stQuestion7+"<>"+stQuestion8+"<>"+stQuestion9+"<>"+stQuestion10+"<>"+stQuestion11+"<>"+stQuestion12+"<>"+stQuestion13+"<>"+
                              stQuestion14+"<>"+stQuestion15+"<>"+stQuestion16+"<>"+stQuestion17+"<>"+stQuestion18+"<>"+stQuestion19+"<>"+stQuestion20+"<>"+
                              stQuestion21+"<>"+stQuestion22+"<>"+stNotesOfAdvice+"<>"+stPrintInterpreter+"<>"+stInterview1Date+"<>"+stInterview1StartTime+"<>"+
                              stInterview1EndTime+"<>"+stInterview1Officer+"<>"+stInterview1Others+"<>"+stInterview1Tape+"<>"+stInterview1Caution+"<>"+
                              stInterview2Date+"<>"+stInterview2StartTime+"<>"+stInterview2EndTime+"<>"+stInterview2Officer+"<>"+stInterview2Others+"<>"+
                              stInterview2Tape+"<>"+stInterview2Caution+"<>"+stInterview3Date+"<>"+stInterview3StartTime+"<>"+stInterview3EndTime+"<>"+
                              stInterview3Officer+"<>"+stInterview3Others+"<>"+stInterview3Tape+"<>"+stInterview3Caution+"<>"+stInterview4Date+"<>"+
                              stInterview4StartTime+"<>"+stInterview4EndTime+"<>"+stInterview4Officer+"<>"+stInterview4Others+"<>"+stInterview4Tape+"<>"+
                              stInterview4Caution+"<>"+stNoteInterview+"<>"+stNfa+"<>"+stCautionOffence+"<>"+stBailDate+"<>"+stBailTime+"<>"+stBailLocation+"<>"+
                              stBailType+"<>"+stBailCondition+"<>"+stBailReason+"<>"+stRemandDate+"<>"+stRemandTime+"<>"+stRemandLocation+"<>"+stChargedOffence+"<>"+
                              stChargedBailType+"<>"+stChargedCondition+"<>"+stChargedDate+"<>"+stChargedTime+"<>"+stChargedLocation+"<>"+stCustodyOffence+"<>"+
                              stCustodyDate+"<>"+stCustodyTime+"<>"+stCustodyLocation+"<>"+stHubDate+"<>"+stHubTime+"<>"+stClientCare+"<>"+stOutcomeLetter;
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair(TAG_CASE_NO, caseNoID));
            params.add(new BasicNameValuePair(TAG_CF03_DATA, cf03Data));
            params.add(new BasicNameValuePair(TAG_SIGN_REP, signRepContent));
            params.add(new BasicNameValuePair(TAG_SIGN_CLIENT, signClientContent));
            params.add(new BasicNameValuePair(TAG_SIGN_INTER, signInterpreterContent));


            // sending modified data through http request
            // Notice that update client url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(url_update_formCF03,
                    "POST", params);

            // check json success tag
            try {
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // successfully updated
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            listener.showClients();
                        }
                    });
                } else {
                    // failed to update client
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
            // dismiss the dialog once client updated
            pDialog.dismiss();
        }
    }

    /*************************************************************************
     * Background Async Task to  Save form CF04 Details
     * */
    class SaveFormCF04 extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Saving form ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Saving form CF04
         * */
        protected String doInBackground(String... args) {

            // getting updated data from EditTexts
            String stAutoCalCF04 = autoCalCF04.getText().toString();
            String stAutoSolCF04 = autoSolCF04.getText().toString();
            String cf04Data = stAutoCalCF04+";"+stAutoSolCF04;
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair(TAG_CASE_NO, caseNoID));
            params.add(new BasicNameValuePair(TAG_CF04_DATA, cf04Data));

            // sending modified data through http request
            // Notice that update client url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(url_update_formCF04,
                    "POST", params);

            // check json success tag
            try {
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // successfully updated
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            listener.showClients();
                        }
                    });
                } else {
                    // failed to update client
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
            // dismiss the dialog once client updated
            pDialog.dismiss();
        }
    }

    /*************************************************************************
     * Background Async Task to  Save form CF05 Details
     * */
    class SaveFormCF05 extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Saving form ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Saving form CF05
         * */
        protected String doInBackground(String... args) {

            // getting updated data from EditTexts
            String stDateCF05 = dateCF05.getText().toString();
            String stTimeCF05 = timeCF05.getText().toString();
            String stFeeEarnerCF05 = feeEarnerCF05.getSelectedItem().toString();
            String stPinCF05 = pinCF05.getText().toString();
            String stSolicitorCF05 = solicitorCF05.getText().toString();
            String stLegalAid = legalAid.getText().toString();
            String stAcceptDateFECF05 = acceptDateFECF05.getText().toString();
            String stAcceptTimeFECF05 = acceptTimeFECF05.getText().toString();
            String cf05Data = stDateCF05+";"+stTimeCF05+";"+stFeeEarnerCF05+";"+stPinCF05+";"+stSolicitorCF05+";"+
                              stLegalAid+";"+stAcceptDateFECF05+";"+stAcceptTimeFECF05;
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair(TAG_CASE_NO, caseNoID));
            params.add(new BasicNameValuePair(TAG_CF05_DATA, cf05Data));

            // sending modified data through http request
            // Notice that update client url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(url_update_formCF05,
                    "POST", params);

            // check json success tag
            try {
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // successfully updated
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            listener.showClients();
                        }
                    });
                } else {
                    // failed to update client
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
            // dismiss the dialog once client updated
            pDialog.dismiss();
        }
    }

    /*************************************************************************
     * Background Async Task to  Save form CF06 Details
     * */
    class SaveFormCF06 extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Saving form ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Saving form CF06
         * */
        protected String doInBackground(String... args) {

            // getting updated data from EditTexts
            String stArrivalCourt = arrivalCourt.getText().toString();
            String stTimeEngCF06 = timeEngCF06.getText().toString();
            String stClientInstCF06 = clientInstCF06.getText().toString();
            String stInvestigation1 = investigation1.getSelectedItem().toString();
            String stInvestigation2 = investigation2.getSelectedItem().toString();
            String stInvestigation3 = investigation3.getSelectedItem().toString();
            String stInvestigation4 = investigation4.getText().toString();
            String stInvestigation5 = investigation5.getSelectedItem().toString();
            String stInvestigation6 = investigation6.getSelectedItem().toString();
            String stInvestigation7 = investigation7.getSelectedItem().toString();
            String stInvestigation8 = investigation8.getSelectedItem().toString();
            String stInvestigation9 = investigation9.getSelectedItem().toString();
            String stInvestigation10 = investigation10.getSelectedItem().toString();
            String stInvestigation11 = investigation11.getSelectedItem().toString();
            String stInvestigation12 = investigation12.getSelectedItem().toString();
            String stInvestigation13 = investigation13.getSelectedItem().toString();
            String stInvestigation14 = investigation14.getText().toString();
            String stAdjournedReason = adjournedReason.getSelectedItem().toString();
            String stAdjournedDate = adjournedDate.getText().toString();
            String stAdjournedTime = adjournedTime.getText().toString();
            String stAdjournedLocation = adjournedLocation.getSelectedItem().toString();
            String stAdjournedBail = adjournedBail.getSelectedItem().toString();
            String stAdjournedBailType = "";
            if(stAdjournedBail.equals("Yes"))
                stAdjournedBailType = adjournedBailType.getSelectedItem().toString();
            String stAdjournedCondition = "";
            if(stAdjournedBailType.equals("Conditional"))
                stAdjournedCondition = adjournedCondition.getText().toString();
            String stGuiltyAwaitingCharges = guiltyAwaitingCharges.getText().toString();
            String stGuiltyAwaitingDate = guiltyAwaitingDate.getText().toString();
            String stGuiltyAwaitingTime = guiltyAwaitingTime.getText().toString();
            String stGuiltyAwaitingLocation = guiltyAwaitingLocation.getSelectedItem().toString();
            String stGuiltySentencedCharges = guiltySentencedCharges.getText().toString();
            String stGuiltySentencedTerms = guiltySentencedTerms.getText().toString();
            String stNotGuiltyCustodyDetail1 = notGuiltyCustodyDetail1.getSelectedItem().toString();
            String stNotGuiltyCustodyDetail2 = notGuiltyCustodyDetail2.getSelectedItem().toString();
            String stNotGuiltyCustodyDate = notGuiltyCustodyDate.getText().toString();
            String stNotGuiltyCustodyTime = notGuiltyCustodyTime.getText().toString();
            String stNotGuiltyCustodyLocation = notGuiltyCustodyLocation.getSelectedItem().toString();
            String stNotGuiltyDetail1 = notGuiltyDetail1.getSelectedItem().toString();
            String stNotGuiltyDetail2 = notGuiltyDetail2.getSelectedItem().toString();
            String stNotGuiltyDate = notGuiltyDate.getText().toString();
            String stNotGuiltyTime = notGuiltyTime.getText().toString();
            String stNotGuiltyLocation = notGuiltyLocation.getSelectedItem().toString();
            String stNotGuiltyBailType = notGuiltyBailType.getSelectedItem().toString();
            String stNotGuiltyConditions = "";
            if(stNotGuiltyBailType.equals("Conditional"))
                stNotGuiltyConditions = notGuiltyConditions.getText().toString();
            String stHubDateCF06 = hubDateCF06.getText().toString();
            String stHubTimeCF06 = hubTimeCF06.getText().toString();
            String stOutcomeLetterCF06 = outcomeLetterCF06.getSelectedItem().toString();

            String cf06Data = stArrivalCourt+";"+stTimeEngCF06+";"+stClientInstCF06+";"+stInvestigation1+";"+stInvestigation2+";"+stInvestigation3
                    +";"+stInvestigation4+";"+stInvestigation5+";"+stInvestigation6+";"+stInvestigation7+";"+stInvestigation8+";"+stInvestigation9
                    +";"+stInvestigation10+";"+stInvestigation11+";"+stInvestigation12+";"+stInvestigation13+";"+stInvestigation14+";"+stAdjournedReason
                    +";"+stAdjournedDate+";"+stAdjournedTime+";"+stAdjournedLocation+";"+stAdjournedBail+";"+stAdjournedBailType+";"+stAdjournedCondition
                    +";"+stGuiltyAwaitingCharges+";"+stGuiltyAwaitingDate+";"+stGuiltyAwaitingTime+";"+stGuiltyAwaitingLocation+";"+stGuiltySentencedCharges
                    +";"+stGuiltySentencedTerms+";"+stNotGuiltyCustodyDetail1+";"+stNotGuiltyCustodyDetail2+";"+stNotGuiltyCustodyDate+";"+stNotGuiltyCustodyTime
                    +";"+stNotGuiltyCustodyLocation+";"+stNotGuiltyDetail1+";"+stNotGuiltyDetail2+";"+stNotGuiltyDate+";"+stNotGuiltyTime+";"+stNotGuiltyLocation
                    +";"+stNotGuiltyBailType+";"+stNotGuiltyConditions+";"+stHubDateCF06+";"+stHubTimeCF06+";"+stOutcomeLetterCF06;
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair(TAG_CASE_NO, caseNoID));
            params.add(new BasicNameValuePair(TAG_CF06_DATA, cf06Data));

            // sending modified data through http request
            // Notice that update client url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(url_update_formCF06,
                    "POST", params);

            // check json success tag
            try {
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // successfully updated
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            listener.showClients();
                        }
                    });
                } else {
                    // failed to update client
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
            // dismiss the dialog once client updated
            pDialog.dismiss();
        }
    }

    /*************************************************************************
     * Background Async Task to  Save form CF07 Details
     * */
    class SaveFormCF07 extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Saving form ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Saving form CF07
         * */
        protected String doInBackground(String... args) {

            // getting updated data from EditTexts
            String stAutoCalCF07 = autoCalCF07.getText().toString();
            String stAutoSolCF07 = autoSolCF07.getText().toString();
            String cf07Data = stAutoCalCF07+";"+stAutoSolCF07;
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair(TAG_CASE_NO, caseNoID));
            params.add(new BasicNameValuePair(TAG_CF07_DATA, cf07Data));

            // sending modified data through http request
            // Notice that update client url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(url_update_formCF07,
                    "POST", params);

            // check json success tag
            try {
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // successfully updated
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            listener.showClients();
                        }
                    });
                } else {
                    // failed to update client
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
            // dismiss the dialog once client updated
            pDialog.dismiss();
        }
    }

    /*************************************************************************
     * Background Async Task to  Save form CF08 Details
     * */
    class SaveFormCF08 extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Saving form ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Saving form CF08
         * */
        protected String doInBackground(String... args) {

            // getting updated data from EditTexts
            String stConvictionDateCF08 = convictionDateCF08.getText().toString();
            String stCasePreparedDateCF08 = casePreparedDateCF08.getText().toString();
            String stListingOfficerDateCF08 = listingOfficerDateCF08.getText().toString();
            String stNumberOfPagesCF08 = numberOfPagesCF08.getText().toString();
            String stOngoingMatterNumberCF08 = ongoingMatterNumberCF08.getText().toString();
            String cf08Data = stConvictionDateCF08+";"+stCasePreparedDateCF08+";"+stListingOfficerDateCF08+";"+stNumberOfPagesCF08+";"+stOngoingMatterNumberCF08;
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair(TAG_CASE_NO, caseNoID));
            params.add(new BasicNameValuePair(TAG_CF08_DATA, cf08Data));

            // sending modified data through http request
            // Notice that update client url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(url_update_formCF08,
                    "POST", params);

            // check json success tag
            try {
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // successfully updated
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            listener.showClients();
                        }
                    });
                } else {
                    // failed to update client
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
            // dismiss the dialog once client updated
            pDialog.dismiss();
        }
    }

    /*************************************************************************
     * Background Async Task to  Save form CF09 Details
     * */
    class SaveFormCF09 extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Saving form ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Saving form CF09
         * */
        protected String doInBackground(String... args) {

            // getting updated data from EditTexts
            String stConvictionDateCF09 = convictionDateCF09.getText().toString();
            String stCasePreparedDateCF09 = casePreparedDateCF09.getText().toString();
            String stListingOfficerDateCF09 = listingOfficerDateCF09.getText().toString();
            String stNumberOfPagesCF09 = numberOfPagesCF09.getText().toString();
            String stOngoingMatterNumberCF09 = ongoingMatterNumberCF09.getText().toString();
            String cf09Data = stConvictionDateCF09+";"+stCasePreparedDateCF09+";"+stListingOfficerDateCF09+";"+stNumberOfPagesCF09+";"+stOngoingMatterNumberCF09;
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair(TAG_CASE_NO, caseNoID));
            params.add(new BasicNameValuePair(TAG_CF09_DATA, cf09Data));

            // sending modified data through http request
            // Notice that update client url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(url_update_formCF09,
                    "POST", params);

            // check json success tag
            try {
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // successfully updated
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            listener.showClients();
                        }
                    });
                } else {
                    // failed to update client
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
            // dismiss the dialog once client updated
            pDialog.dismiss();
        }
    }

    /*************************************************************************
     * Background Async Task to  Save form CF10 Details
     * */
    class SaveFormCF10 extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Saving form ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Saving form CF10
         * */
        protected String doInBackground(String... args) {

            // getting updated data from EditTexts
            String stDateCF10 = dateCF10.getText().toString();
            String stTimeCF10 = timeCF10.getText().toString();
            String stFeeEarnerCF10 = feeEarnerCF10.getSelectedItem().toString();
            String stPinCF10 = pinCF10.getText().toString();
            String stSolicitorCF10 = solicitorCF10.getText().toString();
            String stLegalAidCF10 = legalAidCF10.getText().toString();
            String stAcceptDateFECF10 = acceptDateFECF10.getText().toString();
            String stAcceptTimeFECF10 = acceptTimeFECF10.getText().toString();
            String stCourtBundleDate = courtBundleDate.getText().toString();
            String stCourtBundleTime = courtBundleTime.getText().toString();
            String stCourtBundleAcceptDate = courtBundleAcceptDate.getText().toString();
            String stCourtBundleAcceptTime = courtBundleAcceptTime.getText().toString();
            String cf10Data = stDateCF10+";"+stTimeCF10+";"+stFeeEarnerCF10+";"+stPinCF10+";"+stSolicitorCF10+";"+
                    stLegalAidCF10+";"+stAcceptDateFECF10+";"+stAcceptTimeFECF10+";"+stCourtBundleDate+";"+stCourtBundleTime+";"+
                    stCourtBundleAcceptDate+";"+stCourtBundleAcceptTime;
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair(TAG_CASE_NO, caseNoID));
            params.add(new BasicNameValuePair(TAG_CF10_DATA, cf10Data));

            // sending modified data through http request
            // Notice that update client url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(url_update_formCF10,
                    "POST", params);

            // check json success tag
            try {
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // successfully updated
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            listener.showClients();
                        }
                    });
                } else {
                    // failed to update client
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
            // dismiss the dialog once client updated
            pDialog.dismiss();
        }
    }

    /*************************************************************************
     * Background Async Task to  Save form CF11 Details
     * */
    class SaveFormCF11 extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Saving form ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Saving form CF11
         * */
        protected String doInBackground(String... args) {

            // getting updated data from EditTexts
            String stArrivalCourtCF11 = arrivalCourtCF11.getText().toString();
            String stTimeEngCF11 = timeEngCF11.getText().toString();
            String stClientCommentCF11 = clientCommentCF11.getText().toString();
            String stAdviceViewCF11 = adviceViewCF11.getText().toString();
            String stAdjournedReasonCF11 = adjournedReasonCF11.getSelectedItem().toString();
            String stAdjournedDateCF11 = adjournedDateCF11.getText().toString();
            String stAdjournedTimeCF11 = adjournedTimeCF11.getText().toString();
            String stAdjournedLocationCF11 = adjournedLocationCF11.getSelectedItem().toString();
            String stAdjournedBailCF11 = adjournedBailCF11.getSelectedItem().toString();
            String stAdjournedBailTypeCF11 = "";
            if(stAdjournedBailCF11.equals("Yes"))
                stAdjournedBailTypeCF11 = adjournedBailTypeCF11.getSelectedItem().toString();
            String stAdjournedConditionCF11 = "";
            if(stAdjournedBailTypeCF11.equals("Conditional"))
                stAdjournedConditionCF11 = adjournedConditionCF11.getText().toString();
            String stAwaitingIssuedCharges = awaitingIssuedCharges.getSelectedItem().toString();
            String stAwaitingUnfoundCharges = awaitingUnfoundCharges.getSelectedItem().toString();
            String stAwaitingBailCF11 = awaitingBailCF11.getSelectedItem().toString();
            String stAwaitingBailTypeCF11 = "";
            if(stAwaitingBailCF11.equals("Yes"))
                stAwaitingBailTypeCF11 = awaitingBailTypeCF11.getSelectedItem().toString();
            String stAwaitingConditionCF11 = "";
            if(stAwaitingBailTypeCF11.equals("Conditional"))
                stAwaitingConditionCF11 = awaitingConditionCF11.getText().toString();
            String stGuiltyAwaitingDateCF11 = guiltyAwaitingDateCF11.getText().toString();
            String stGuiltyAwaitingTimeCF11 = guiltyAwaitingTimeCF11.getText().toString();
            String stGuiltyAwaitingLocationCF11 = guiltyAwaitingLocationCF11.getSelectedItem().toString();
            String stSentencedIssuedCharges = sentencedIssuedCharges.getSelectedItem().toString();
            String stSentencedUnfoundCharges = sentencedUnfoundCharges.getSelectedItem().toString();
            String stSentenceReceived = sentenceReceived.getText().toString();
            String stNotGuiltyVerdict = notGuiltyVerdict.getText().toString();
            String stHubDateCF11 = hubDateCF11.getText().toString();
            String stHubTimeCF11 = hubTimeCF11.getText().toString();
            String stOutcomeLetterCF11 = outcomeLetterCF11.getSelectedItem().toString();

            String cf11Data = stArrivalCourtCF11+";"+stTimeEngCF11+";"+stClientCommentCF11+";"+stAdviceViewCF11+";"+stAdjournedReasonCF11+";"+stAdjournedDateCF11
                    +";"+stAdjournedTimeCF11+";"+stAdjournedLocationCF11+";"+stAdjournedBailCF11+";"+stAdjournedBailTypeCF11+";"+stAdjournedConditionCF11+";"+stAwaitingIssuedCharges
                    +";"+stAwaitingUnfoundCharges+";"+stAwaitingBailCF11+";"+stAwaitingBailTypeCF11+";"+stAwaitingConditionCF11+";"+stGuiltyAwaitingDateCF11+";"+stGuiltyAwaitingTimeCF11
                    +";"+stGuiltyAwaitingLocationCF11+";"+stSentencedIssuedCharges+";"+stSentencedUnfoundCharges+";"+stSentenceReceived+";"+stNotGuiltyVerdict+";"+stHubDateCF11
                    +";"+stHubTimeCF11+";"+stOutcomeLetterCF11;
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair(TAG_CASE_NO, caseNoID));
            params.add(new BasicNameValuePair(TAG_CF11_DATA, cf11Data));

            // sending modified data through http request
            // Notice that update client url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(url_update_formCF11,
                    "POST", params);

            // check json success tag
            try {
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // successfully updated
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            listener.showClients();
                        }
                    });
                } else {
                    // failed to update client
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
            // dismiss the dialog once client updated
            pDialog.dismiss();
        }
    }

    /*************************************************************************
     * Background Async Task to  Save form CF12 Details
     * */
    class SaveFormCF12 extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Saving form ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Saving form CF12
         * */
        protected String doInBackground(String... args) {

            // getting updated data from EditTexts
            String stAutoCalViewCF12 = autoCalViewCF12.getText().toString();
            String stAutoSolViewCF12 = autoSolViewCF12.getText().toString();
            String cf12Data = stAutoCalViewCF12+";"+stAutoSolViewCF12;
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair(TAG_CASE_NO, caseNoID));
            params.add(new BasicNameValuePair(TAG_CF12_DATA, cf12Data));

            // sending modified data through http request
            // Notice that update client url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(url_update_formCF12,
                    "POST", params);

            // check json success tag
            try {
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // successfully updated
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            listener.showClients();
                        }
                    });
                } else {
                    // failed to update client
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
            // dismiss the dialog once client updated
            pDialog.dismiss();
        }
    }


    /**************************************************************************
     * Background Async Task to Delete Client
     * */
    class DeleteClient extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Deleting Client...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Deleting client
         * */
        protected String doInBackground(String... args) {

            // Check for success tag
            int success;
            try {
                // Building Parameters
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("CaseNo", caseNoID));

                // getting client details by making HTTP request
                JSONObject json = jsonParser.makeHttpRequest(
                        url_delete_client, "POST", params);

                // check your log for json response
                Log.d("Delete Client", json.toString());

                // json success tag
                success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                    // client successfully deleted
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            listener.showClients();
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
            // dismiss the dialog once client deleted
            pDialog.dismiss();

        }

    }

}
