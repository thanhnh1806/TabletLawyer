package tabletlawyer.uk.tabletlawyer;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


//public class SplashActivity extends Activity {
//
//
//    // Splash screen timer
//    private static int SPLASH_TIME_OUT = 3000;
//    TextView versionView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_splash);
//
//        versionView = (TextView)findViewById(R.id.versionView);
//        versionView.setText("Version: " + getVersion());
//
//        new Handler().postDelayed(new Runnable() {
//
//            /*
//             * Showing splash screen with a timer. This will be useful when you
//             * want to show case your app logo / company
//             */
//
//            @Override
//            public void run() {
//                // This method will be executed once the timer is over
//                // Start your app main activity
//                 Intent i = new Intent(SplashActivity.this, LoginActivity.class);
//                 startActivity(i);
//
//
//                // close this activity
//                 finish();
//            }
//        }, SPLASH_TIME_OUT);
//
//
//    }
//
//    public String getVersion() {
//        String v = "";
//        try {
//            v = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
//        } catch (PackageManager.NameNotFoundException e) {
//
//        }
//        return v;
//    }
//
//
//
//}
/*********************************************/
public class SplashActivity extends Activity {

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        StartAnimations();

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                 Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                 startActivity(i);


                // close this activity
                 finish();
            }
        }, 3000);
    }
    private void StartAnimations() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        LinearLayout l=(LinearLayout) findViewById(R.id.splashLayout);
        l.clearAnimation();
        l.startAnimation(anim);

        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        ImageView iv1 = (ImageView) findViewById(R.id.imageView1);
        iv1.clearAnimation();
        iv1.startAnimation(anim);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ImageView iv2 = (ImageView) findViewById(R.id.imageView2);
                iv2.setVisibility(View.VISIBLE);
                TextView versionView = (TextView)findViewById(R.id.versionView);
                versionView.setText("Version: " + getVersion());
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    public String getVersion() {
        String v = "";
        try {
            v = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {

        }
        return v;
    }

}