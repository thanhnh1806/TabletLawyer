package tabletlawyer.uk.tabletlawyer;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Register_ResetPasswordActivity extends Activity {

    private static EditText name;
    private static EditText email;
    private static EditText newPassword;
    private static EditText confirmPassword;
    private static EditText mobile;
    private static Button register;


    JSONObject json = new JSONObject();
    // Connection detector class
    ConnectionDetector cd;
    // flag for Internet connection status
    Boolean isInternetPresent = false;


    private static String KEY_SUCCESS = "success";
    private static String KEY_ERROR_MSG = "error_msg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String layout = String.valueOf(getIntent().getCharSequenceExtra("LAYOUT"));
        cd = new ConnectionDetector(getApplicationContext());

        if(layout.equals("register")) {
            setContentView(R.layout.activity_register);

            register = (Button)findViewById(R.id.register);
            name = (EditText)findViewById(R.id.name);
            email = (EditText)findViewById(R.id.email);
            newPassword = (EditText)findViewById(R.id.newPassword);
            confirmPassword = (EditText)findViewById(R.id.confirmPass);
            mobile = (EditText)findViewById(R.id.mobile);
        }
        else if(layout.equals("resetPassword"))
            setContentView(R.layout.activity_reset_password);
        else if(layout.equals("passcode"))
            setContentView(R.layout.activity_passcode);
        else
            setContentView(R.layout.activity_new_password);

          ///Avoid android.os.NetworkOnMainThreadException
        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        /********************************************************************/
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String stName = name.getText().toString();
                String stEmail = email.getText().toString();
                String stNewPassword = newPassword.getText().toString();
                String stConfirmPassword = confirmPassword.getText().toString();
                String stMobile = mobile.getText().toString();

                /**********************************************************************/
                if(!stName.equals("") && !stEmail.equals("") && !stNewPassword.equals("") && !stConfirmPassword.equals("") && !stMobile.equals("") && stNewPassword.equals(stConfirmPassword)) {
                    register.setBackgroundResource(R.drawable.button_style_pressed);
                    register.setTextColor(Color.parseColor("#003B50"));

                    // Check if Internet present
                    isInternetPresent = cd.isConnectingToInternet();
                    if (!isInternetPresent) {
                        // Internet Connection is not present
                        Toast.makeText(getApplicationContext(),"Please check internet connection",Toast.LENGTH_LONG).show();
                    } else {
                        // Internet Connection is present
                        UserFunctions userFunction = new UserFunctions();
                        json = userFunction.registerUser(stName, stEmail, stNewPassword, stMobile);
                    }
                    // check for login response
                    try {
                        if (json.getString(KEY_SUCCESS) != null) {
                            String res = json.getString(KEY_SUCCESS);
                            if(Integer.parseInt(res) == 1){
                                // user successfully registred
                                // Store user details in SQLite Database
                                JSONObject json_user = json.getJSONObject("user");

                                // Clear all previous data in database
                               // (json_user.getString(KEY_NAME), json_user.getString(KEY_EMAIL), json.getString(KEY_UID));
                                // Launch LoginActivity Screen
                                Intent loginActivity = new Intent(getApplicationContext(), LoginActivity.class);
                                // Close all views before launching LoginActivity
                                loginActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(loginActivity);
                                // Close Registration Screen
                                finish();
                            }else{
                                // Error in registration
                                Toast.makeText(getApplicationContext(),json.getString(KEY_ERROR_MSG),Toast.LENGTH_SHORT).show();
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else
                    Toast.makeText(getApplicationContext(),"Please enter the details",Toast.LENGTH_SHORT).show();

            }
        });
        /********************************************************************************/
    }



    /** This method is called when user clicks the Cancel button */
    public void cancelReset(View view){

        Button cancel = (Button)findViewById(R.id.cancel);
        cancel.setBackgroundResource(R.drawable.button_style_pressed);
        cancel.setTextColor(Color.parseColor("#003B50"));
        Intent i = new Intent(Register_ResetPasswordActivity.this, LoginActivity.class);
        startActivity(i);
    }

    /** This method is called when user clicks the Confirm Email button */
    public void confirmReset(View view){

        Button confirm = (Button)findViewById(R.id.confirmReset);
        confirm.setBackgroundResource(R.drawable.button_style_pressed);
        confirm.setTextColor(Color.parseColor("#003B50"));
        Intent i = new Intent(Register_ResetPasswordActivity.this, Register_ResetPasswordActivity.class);
        i.putExtra("LAYOUT","passcode");
        startActivity(i);

    }

    /** This method is called when user clicks the Resend button */
    public void resend(View view){

        Button resend = (Button)findViewById(R.id.resend);
        resend.setBackgroundResource(R.drawable.button_style_pressed);
        resend.setTextColor(Color.parseColor("#003B50"));


    }

    /** This method is called when user clicks the Confirm Passcode button */
    public void confirmCode(View view){

        Button confirm = (Button)findViewById(R.id.confirmCode);
        confirm.setBackgroundResource(R.drawable.button_style_pressed);
        confirm.setTextColor(Color.parseColor("#003B50"));
        Intent i = new Intent(Register_ResetPasswordActivity.this, Register_ResetPasswordActivity.class);
        i.putExtra("LAYOUT","newPassword");
        startActivity(i);

    }

    /** This method is called when user clicks the Confirm Password button */
    public void confirmPassword(View view){

        Button confirm = (Button)findViewById(R.id.confirmPassword);
        confirm.setBackgroundResource(R.drawable.button_style_pressed);
        confirm.setTextColor(Color.parseColor("#003B50"));
        Intent i = new Intent(Register_ResetPasswordActivity.this, LoginActivity.class);
        startActivity(i);

    }




}
