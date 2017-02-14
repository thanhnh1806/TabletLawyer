package tabletlawyer.uk.tabletlawyer;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.internal.view.menu.MenuBuilder;
import android.support.v7.widget.PopupMenu;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.internal.zzau;


public class MainActivity extends ActionBarActivity {

    private static final String TAB_KEY_INDEX = "tab_key";
    Boolean fullScreen = false;
    int volumestatus = 1;
    AudioManager audioManager;
    static int i=0;
    private static String userType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set the Action Bar to use tabs for navigation
        ActionBar ab = getSupportActionBar();
        ab.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ab.setDisplayShowHomeEnabled(false);
        ab.setDisplayShowTitleEnabled(false);


        Fragment mSettingsFragment = new SettingsFragment();
        Fragment mFormsFragment = new FormsFragment();
        Fragment mMailFragment = new MailFragment();
        Fragment mDownloadFragment = new DownloadFragment();
        Fragment mAgendaFragment = new AgendaFragment();
        Fragment mMapSetupFragment = new MapSetupFragment();


            // Add these tabs to the Action Bar for display
            ActionBar.Tab tab1 = ab.newTab().setText("Settings").setIcon(R.drawable.settings).setTabListener(new MyTabsListener(mSettingsFragment, getApplicationContext()));

            ActionBar.Tab tab2 = ab.newTab().setText("Forms").setIcon(R.drawable.forms).setTabListener(new MyTabsListener(mFormsFragment, getApplicationContext()));

            ActionBar.Tab tab4 = ab.newTab().setText("Mail").setIcon(R.drawable.mail).setTabListener(new MyTabsListener(mMailFragment, getApplicationContext()));

            ActionBar.Tab tab5 = ab.newTab().setText("Download").setIcon(R.drawable.download).setTabListener(new MyTabsListener(mDownloadFragment, getApplicationContext()));

            ActionBar.Tab tab6 = ab.newTab().setText("Agenda").setIcon(R.drawable.agenda).setTabListener(new MyTabsListener(mAgendaFragment, getApplicationContext()));

            ActionBar.Tab tab7 = ab.newTab().setText("Map").setIcon(R.drawable.map).setTabListener(new MyTabsListener(mMapSetupFragment, getApplicationContext()));

            ab.addTab(tab1);
            ab.addTab(tab2);

        userType = (String)getIntent().getCharSequenceExtra("userType");
        if(userType.equals("admin")) {
            Fragment mReportingFragment = new ReportingFragment();
            ActionBar.Tab tab3 = ab.newTab().setText("Reporting").setIcon(R.drawable.reporting).setTabListener(new MyTabsListener(mReportingFragment, getApplicationContext()));
            ab.addTab(tab3);
        }
            ab.addTab(tab4);
            ab.addTab(tab5);
            ab.addTab(tab6);
            ab.addTab(tab7);


        // restore to navigation
        if (savedInstanceState != null) {
            ab.setSelectedNavigationItem(savedInstanceState.getInt(
                    TAB_KEY_INDEX, 0));
        }


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(TAB_KEY_INDEX, getActionBar()
                .getSelectedNavigationIndex());

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        final MenuItem item = menu.findItem(R.id.action_volume);
        audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        int volume = audioManager.getRingerMode();
        if(volume==0)
            item.setIcon(R.drawable.ic_action_silent);
        if(volume==1)
            item.setIcon(R.drawable.ic_action_vibrate);
        if(volume==2)
            item.setIcon(R.drawable.ic_action_volume);

        new Handler().post(new Runnable() {
            @Override
            public void run() {
                final View v = findViewById(R.id.action_volume);

                if (v != null) {
                    v.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
                            audioManager.adjustStreamVolume(audioManager.STREAM_RING,audioManager.ADJUST_SAME,audioManager.FLAG_SHOW_UI);
                            item.setIcon(R.drawable.ic_action_volume);
                            volumestatus = 3;
                            return false;
                        }
                    });
                }
            }
        });


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_fullScreen) {

            if(fullScreen==false) {
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                item.setIcon(R.drawable.ic_action_normalscreen);
                fullScreen = true;
            }else
            {
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                item.setIcon(R.drawable.ic_action_fullscreen);
                fullScreen = false;
            }
            return true;
        }

        if (id == R.id.action_refresh){


            return true;
        }

        if (id == R.id.action_volume){
            audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);

            switch (volumestatus){

                case 1:{

                    audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                    item.setIcon(R.drawable.ic_action_silent);
                    volumestatus = 2;
                    break;
                }
                case 2:{

                    audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                    item.setIcon(R.drawable.ic_action_vibrate);
                    volumestatus = 3;
                    break;
                }
                case 3:{

                    audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                    item.setIcon(R.drawable.ic_action_volume);
                    volumestatus = 1;
                    break;
                }
            }


//            if(volumestatus.equals("General")) {
//                audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
//                volumestatus = "Silent";
//
//            }
//            else if(volumestatus.equals("Silent")) {
//                audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
//                volumestatus = "Vibrate";
//            }
//            else if (volumestatus.equals("Vibrate")){
//                audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
//                volumestatus = "General";
//            }


            return true;
        }
        return super.onOptionsItemSelected(item);
    }




    class MyTabsListener implements ActionBar.TabListener {
        public Fragment fragment;
        public Context context;

        public MyTabsListener(Fragment fragment, Context context) {
            this.fragment = fragment;
            this.context = context;

        }

        @Override
        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {


        }

        @Override
        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {

            ft.replace(R.id.container, fragment);
            i=0;
        }

        @Override
        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

            ft.remove(fragment);
        }

    }
}
