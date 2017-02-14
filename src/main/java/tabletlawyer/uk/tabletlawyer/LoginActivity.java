package tabletlawyer.uk.tabletlawyer;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class LoginActivity extends Activity {

    TextView support;
    private static EditText username;
    private static EditText password;
    private static String user;
    private static String pass;
    private static Button signin;

    private static String userId ;
    private static String userType;
    private static String userName;

    // Connection detector class
    ConnectionDetector cd;
    // flag for Internet connection status
    Boolean isInternetPresent = false;


      JSONObject json = new JSONObject();


    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_UID = "uid";
    private static final String TAG_USER_TYPE = "userType";
    private static final String TAG_NAME = "name";
    private static final String TAG_EMAIL = "email";
    private static final String TAG_PASSWORD = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        cd = new ConnectionDetector();

        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        signin = (Button)findViewById(R.id.signin);
        support = (TextView) findViewById(R.id.support);

        support.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                support.setText(Html.fromHtml(getString(R.string.support)));
                Linkify.addLinks(support, Linkify.ALL);
                support.setMovementMethod(LinkMovementMethod.getInstance());
            }
        });

        ///Avoid android.os.NetworkOnMainThreadException
        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

    }

    /** This method is called when user clicks the Login button */
    public void signIn(View view){

        user = username.getText().toString();
        pass = password.getText().toString();

        // Check if Internet present
        isInternetPresent = cd.isConnectingToInternet();
        if (!isInternetPresent) {
            // Internet Connection is not present
            Toast.makeText(this,"Please check internet connection",Toast.LENGTH_LONG).show();
        } else {
            // Internet Connection is present

            UserFunctions userFunction = new UserFunctions();
            Log.d("Button", "Login");
            json = userFunction.loginUser(user, pass);

        }
            /***************************************************/
        // check for login response
        try {
            if (json.getString(TAG_SUCCESS) != null) {
                String res = json.getString(TAG_SUCCESS);
                if(Integer.parseInt(res) == 1){
                    // user successfully logged in
                    // Store user details
                    JSONObject json_user = json.getJSONObject("user");

                    // Save values in strings
                    userId = json.getString(TAG_UID);
                    userType = json_user.getString(TAG_USER_TYPE);
                    userName = json_user.getString(TAG_NAME);

                    // Change Sign in button design
                    signin.setBackgroundResource(R.drawable.button_style_pressed);
                    signin.setTextColor(Color.parseColor("#003B50"));

                    // Launch MainActivity Screen
                    Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);

                    // Close all views before launching MainActivity
                    mainActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    mainActivity.putExtra("uid",userId);
                    mainActivity.putExtra("userType",userType);
                    mainActivity.putExtra("name",userName);
                    startActivity(mainActivity);

                    // Close Login Screen
                    finish();
                }else{
                    // Error in login
                    Toast.makeText(LoginActivity.this, "Please enter correct Email and Password and try again", Toast.LENGTH_LONG).show();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        /**************************************************************/


    }

    /** This method is called when user clicks Forgot Password */
    public void resetPassword(View view){

        Intent i = new Intent(LoginActivity.this, Register_ResetPasswordActivity.class);
        i.putExtra("LAYOUT","resetPassword");
        startActivity(i);

    }

    /** This method is called when user clicks the Register button */
    public void newUser(View view){

        Intent i = new Intent(LoginActivity.this, Register_ResetPasswordActivity.class);
        i.putExtra("LAYOUT","register");
        startActivity(i);
    }

    /** This method is called when user clicks the Support button */
    public void openSite(View view){

      //  "http://support.tabletlawyer.uk"
    }



}
