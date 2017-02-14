package tabletlawyer.uk.tabletlawyer;

import android.app.Activity;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MapsFragment extends Fragment {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private SupportMapFragment mMapFragment;
    private static final LatLng UK = new LatLng(53.420441,-2.031040);
    ArrayList<Marker> markers;
    TextView distance;
    TextView time;
    TextView cost;
    Polyline polyline;
    private OnItemSelectedListener listener;
    private View view;
    private static int ps;
    private static int mc;
    private static int cc;
    private static int fe;
    private static int spinner;


    //  private FragmentActivity myContext;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MapFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MapsFragment newInstance(String param1, String param2) {
        MapsFragment fragment = new MapsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public MapsFragment() {
        // Required empty public constructor
    }

//    public void recLifeCycle(){
//
//        String className = getClass().getSimpleName();
//        StackTraceElement[] s = Thread.currentThread().getStackTrace();
//        String methodName = s[3].getMethodName();
//
//        String msg = className + "." + methodName;
//
//       // Toast.makeText(getActivity(),msg, Toast.LENGTH_SHORT).show();
//        Log.i("MYTAG", msg);
//    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
     //   recLifeCycle();
        setRetainInstance(true);

    }


    public interface OnItemSelectedListener {
        public void mapsMethod(int[] marker);
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

      //  recLifeCycle();
    }


    public void setMarkers(int[] marker)
    {
        spinner = marker[0];
        ps = marker[1];
        mc = marker[2];
        cc = marker[3];
        fe = marker[4];

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
          super.onCreateView(inflater,container,savedInstanceState);
      //  recLifeCycle();
        if(view==null)
        //    recLifeCycle();
        view = inflater.inflate(R.layout.fragment_map, container, false);
       // Toast.makeText(getActivity(),"in createview",Toast.LENGTH_LONG).show();

     //   MapsInitializer.initialize(getActivity());


        // setUpMapIfNeeded();
     //   if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
       //     mMap = ((SupportMapFragment)  getActivity().getSupportFragmentManager().findFragmentById(R.id.map))
         //           .getMap();
           // }

        /////////////////////////////////////


//               this.tvDistanceDuration = (TextView) view.findViewById(R.id.tv_distance_time);
//
//                this.markers = new ArrayList<Marker>();
//
//                this.mMap.setMyLocationEnabled(true);
//
//                this.mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
//                    @Override
//                    public boolean onMarkerClick(Marker marker) {
//
//                        if (MapsFragment.this.markers.size() > 1) {
//                            MapsFragment.this.markers.clear();
//                            polyline.remove();
//                        }
//
//                        MapsFragment.this.markers.add(marker);
//
//                        if (MapsFragment.this.markers.size() >= 2) {
//                            LatLng origin = MapsFragment.this.markers.get(0).getPosition();
//                            LatLng dest = MapsFragment.this.markers.get(1).getPosition();
//
//                            String url = MapsFragment.this.getDirectionsUrl(origin, dest);
//
//                            DownloadTask downloadTask = new DownloadTask();
//                            downloadTask.execute(url);
//
//                        }
//                        return true;
//                    }
//                });

                ///////////////////////////////////////////////



        return view;
    }

    

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        getView().setFocusableInTouchMode(true);
//        getView().requestFocus();
//
//        getView().setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                if (event.getAction() == KeyEvent.ACTION_DOWN) {
//                    if (keyCode == KeyEvent.KEYCODE_BACK) {
//                        getActivity().getSupportFragmentManager().popBackStack();
//                        return true;
//                    }
//                }
//                return false;
//            }
//        });

      //  recLifeCycle();
        FragmentManager fm = getChildFragmentManager();
    //    mMapFragment = (SupportMapFragment) fm.findFragmentById(R.id.map);
        if (mMapFragment == null) {
      //      recLifeCycle();
            mMapFragment = SupportMapFragment.newInstance();
            fm.beginTransaction().replace(R.id.map, mMapFragment).commit();

         //   this.tvDistanceDuration = (TextView) view.findViewById(R.id.tv_distance_time);

         //   this.markers = new ArrayList<Marker>();



           // Toast.makeText(getActivity(),"in activitycreated",Toast.LENGTH_LONG).show();

        }


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
     //   recLifeCycle();

       if (view != null) {
       //    recLifeCycle();

          // //getChildFragmentManager().beginTransaction().remove(mMapFragment).commit();
            ViewGroup parentViewGroup = (ViewGroup) view.getParent();
            if (parentViewGroup != null) {
                parentViewGroup.removeAllViews();

            }

          //  SupportMapFragment f = (SupportMapFragment) getActivity().getSupportFragmentManager()
            //        .findFragmentById(R.id.map);
          //  if (mMapFragment.isResumed()) {
            //    recLifeCycle();

            }

       //    Toast.makeText(getActivity(),"in destroyview",Toast.LENGTH_LONG).show();
       }







    @Override
    public void onResume() {
        super.onResume();
      //  recLifeCycle();
        if (mMap == null) {
     //       recLifeCycle();
            mMap = mMapFragment.getMap();

        }
      //  Toast.makeText(getActivity(),"resume"+mMap,Toast.LENGTH_LONG).show();
        Log.i("MYTAG","val"+mMap);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(UK,7));


            this.distance = (TextView) view.findViewById(R.id.distanceView);
            this.time = (TextView) view.findViewById(R.id.timeView);
            this.cost = (TextView) view.findViewById(R.id.costView);

            this.markers = new ArrayList<Marker>();

            if (mMap != null) {
                setUpMap();

                this.mMap.setMyLocationEnabled(true);

                this.mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {

                        if (MapsFragment.this.markers.size() > 1) {
                            MapsFragment.this.markers.clear();
                            polyline.remove();

                        }

                        MapsFragment.this.markers.add(marker);

                        if (MapsFragment.this.markers.size() >= 2) {
                            LatLng origin = MapsFragment.this.markers.get(0).getPosition();
                            LatLng dest = MapsFragment.this.markers.get(1).getPosition();

                            String url = MapsFragment.this.getDirectionsUrl(origin, dest);

                            DownloadTask downloadTask = new DownloadTask();
                            downloadTask.execute(url);

                        }
                        return false;
                    }
                });
            }

    }

//    @Override
//    public void onPause() {
//        super.onPause();
//
//        SupportMapFragment supportMapFragment= (SupportMapFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.map);
//        getActivity().getSupportFragmentManager().beginTransaction().detach(supportMapFragment).commit();
//
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//
//        SupportMapFragment supportMapFragment= (SupportMapFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.map);
//        getActivity().getSupportFragmentManager().beginTransaction().attach(supportMapFragment).commit();
//        setUpMapIfNeeded();
//
//    }



    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
//            SupportMapFragment mMapFragment = SupportMapFragment.newInstance();
//            FragmentTransaction fragmentTransaction =
//                    getChildFragmentManager().beginTransaction();
//            fragmentTransaction.add(R.id.map, mMapFragment);
//            fragmentTransaction.commit();
//            mMap = mMapFragment.getMap();

            FragmentManager fm = getChildFragmentManager();
            mMapFragment = (SupportMapFragment) fm.findFragmentById(R.id.map);
            if (mMapFragment == null) {
                mMapFragment = SupportMapFragment.newInstance();
                fm.beginTransaction().replace(R.id.map, mMapFragment).commit();

                // Check if we were successful in obtaining the map.
                if (mMap != null) {
                    setUpMap();
                }
            }

        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {

         //    recLifeCycle();

        if(spinner==0) {
            if(ps==1){
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.328503, -2.696010)).title("Runcorn PS").snippet("101").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.206604, -2.909381)).title("Blacon PS (Western)").snippet("101").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.191061, -2.429976)).title("Middlewich (Eastern) Custody Centre").snippet("101").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
            }
            if(mc==1){
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.329627, -2.697873)).title("Runcorn Mags").snippet("01925 235250").snippet("Court No. 1722").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.387256, -2.600528)).title("Warrington Mags").snippet("01925 236 250").snippet("Court No. 1722").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.187525, -2.893494)).title("Chester Mags").snippet("01244 405790").snippet("Court No. 1729").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.097666, -2.438781)).title("Macclesfield Mags").snippet("01270 655920").snippet("Court No. 1178").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.097666, -2.438781)).title("Crewe Mags").snippet("01270 655 920").snippet("Court No. 1187").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
            }
            if(cc==1){
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.185985, -2.891805)).title("Chester Crown Court").snippet("01244 317606").snippet("Court No. 415").icon(BitmapDescriptorFactory.fromResource(R.drawable.crown_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.390610, -2.598750)).title("Warrington Crown Court").snippet("01244 317606").snippet("Court No. 462").icon(BitmapDescriptorFactory.fromResource(R.drawable.crown_court)));
            }
            if(fe==1){}
        }
        if(spinner==1) {
            if(ps==1){
                mMap.addMarker(new MarkerOptions().position(new LatLng(54.645081, -3.541576)).title("Workington PS").snippet("101").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(54.645139, -3.541321)).title("Whitehaven PS").snippet("101").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(54.110983, -3.227348)).title("Barrow PS").snippet("101").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(54.332399, -2.747752)).title("Kendal PS").snippet("101").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
            }
            if(mc==1){
                mMap.addMarker(new MarkerOptions().position(new LatLng(54.334695, -2.747980)).title("Kendal Mags").snippet("01229 820 161").snippet("Court No. 1323").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(54.116472, -3.227704)).title("Barrow Mags").snippet("01229 820 161").snippet("Court No. 1398").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(54.643978, -3.542377)).title("Workington Mags").snippet("01900 622 44").snippet("Court No. 1752").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
            }
            if(cc==1){
                mMap.addMarker(new MarkerOptions().position(new LatLng(54.893206, -2.931707)).title("Carlisle Crown Court").snippet("01228 882 120").snippet("Court No. 412").icon(BitmapDescriptorFactory.fromResource(R.drawable.crown_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.762343, -2.698873)).title("Preston Crown Court").snippet("01772 844 700").snippet("Court No. 448").icon(BitmapDescriptorFactory.fromResource(R.drawable.crown_court)));
            }
            if(fe==1){}
        }
        if(spinner==2) {
            if(ps==1){
                mMap.addMarker(new MarkerOptions().position(new LatLng(54.332468, -2.747785)).title("Kendal PS").snippet("101").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(54.883440, -2.909760)).title("Carlisle PS").snippet("101").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(54.666605, -2.752670)).title("Penrith PS").snippet("101").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
            }
            if(mc==1){
                mMap.addMarker(new MarkerOptions().position(new LatLng(54.897392, -2.935510)).title("Carlisle Mags").snippet("01228 518 800").snippet("Court No. 1727").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(54.334720, -2.747948)).title("Kendal Mags").snippet("01229 810 161").snippet("Court No. 1323").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
            }
            if(cc==1){
                mMap.addMarker(new MarkerOptions().position(new LatLng(54.893206, -2.931707)).title("Carlisle Crown Court").snippet("01228 882 120").snippet("Court No. 412").icon(BitmapDescriptorFactory.fromResource(R.drawable.crown_court)));
            }
            if(fe==1){}

        }
        if(spinner==3) {
            if(ps==1){
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.257195, -1.906870)).title("Buxton PS").snippet("0345 123 3333").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.234182, -1.425960)).title("Chesterfield PS").snippet("0345 123 3333").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(52.931755, -1.471714)).title("Derby PS").snippet("101").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
            }
            if(mc==1){
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.256801, -1.913103)).title("Northern Derbyshire Mags").snippet("Peak Buildings, Buxton").snippet("01298 23951").snippet("Court No. 1435").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.237021, -1.423744)).title("Northern Derbyshire Mags").snippet("Tapton Lane, Chesterfield M").snippet("01246 224040").snippet("Court No. 1425").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(52.924574, -1.480617)).title("Derby & South Derbyshire Mags").snippet("St Mary's Gate, Derby").snippet("01332 362000").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
            }
            if(cc==1){
                mMap.addMarker(new MarkerOptions().position(new LatLng(52.922247, -1.473432)).title("Derby Crown Court").snippet("01332 622 572").snippet("Court No. 419").icon(BitmapDescriptorFactory.fromResource(R.drawable.crown_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(52.948799, -1.146533)).title("Nottingham Crown Court").snippet("0115 910 3551").snippet("Court No. 444").icon(BitmapDescriptorFactory.fromResource(R.drawable.crown_court)));
            }
            if(fe==1){}

        }
        if(spinner==4) {
            if(ps==1){

                mMap.addMarker(new MarkerOptions().position(new LatLng(53.570189, -2.418216)).title("Bolton Central PS").snippet("0161 856 5629").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.528196, -2.161714)).title("Chadderton PS").snippet("0161 856 8828").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.485067, -2.105915)).title("Tameside PS").snippet("0161 872 5050").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.395699, -2.180860)).title("Cheadle Heath PS").snippet("0161 856 9807").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.502827, -2.196967)).title("Central Park PS").snippet("0161 872 5050").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.501570, -2.178991)).title("Newton Heath PS").snippet("0161 872 5050").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.461071, -2.208386)).title("Longsight PS").snippet("0161 856 4229").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.490035, -2.284801)).title("Pendleton PS").snippet("0161 856 5029").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.513137, -2.341648)).title("Swinton PS").snippet("0161 856 5229").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.543668, -2.655214)).title("Wigan PS").snippet("0161 856 7124").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.461071, -2.208386)).title("Bury PS").snippet("0161 872 5050").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
            }
            if(mc==1){

                mMap.addMarker(new MarkerOptions().position(new LatLng(53.500175, -2.381815)).title("Bolton Mags").snippet("01204 558 200").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.542851, -2.626648)).title("Wigan & Leigh Mags").snippet("01942 405 405").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.500165, -2.381803)).title("Bury Rochdale Mags").snippet("0161 447 8600").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.541893, -2.116853)).title("Oldham Mags").snippet("0161 620 2331").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.406585, -2.156198)).title("Stockport Mags").snippet("0161 477 2020").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.426341, -2.325267)).title("Trafford Mags").snippet("0161 976 3333").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.480121, -2.252169)).title("Manchester City Mags").snippet("0161 830 4200").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
            }
            if(cc==1){

                mMap.addMarker(new MarkerOptions().position(new LatLng(53.578274, -2.433345)).title("Bolton Crown Court").snippet("01204 392 881").icon(BitmapDescriptorFactory.fromResource(R.drawable.crown_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.480057, -2.252233)).title("Manchester Crown Square").snippet("0161 954 1800").icon(BitmapDescriptorFactory.fromResource(R.drawable.crown_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.476051, -2.232756)).title("Manchester Minshull St").snippet("0161 954 7500").icon(BitmapDescriptorFactory.fromResource(R.drawable.crown_court)));

            }
            if(fe==1){

                mMap.addMarker(new MarkerOptions().position(new LatLng(53.585519, -2.516283)).title("Ewan Laurence").icon(BitmapDescriptorFactory.fromResource(R.drawable.fee_earner)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.457038, -2.374131)).title("Adam Brown").icon(BitmapDescriptorFactory.fromResource(R.drawable.fee_earner)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.500395, -2.875298)).title("Laura Stewart").icon(BitmapDescriptorFactory.fromResource(R.drawable.fee_earner)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.582161, -2.444992)).title("Fred Close").icon(BitmapDescriptorFactory.fromResource(R.drawable.fee_earner)));

            }


        }
        if(spinner==5) {
            if(ps==1){

                mMap.addMarker(new MarkerOptions().position(new LatLng(53.812127, -3.053642)).title("Blackpool PS").snippet("0845 125 3545").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.746453, -2.479497)).title("Blackburn PS").snippet("01254 353576").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(54.046518, -2.798585)).title("Lancaster PS").snippet("01524 63333").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.787084, -2.242125)).title("Burnley PS").snippet("01282 472107").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.764528, -2.702490)).title("Preston PS").snippet("01772 209738").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.549651, -2.775261)).title("Skelmersdale PS").snippet("01695 724101").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
            }
            if(mc==1){

                mMap.addMarker(new MarkerOptions().position(new LatLng(53.811846, -3.052664)).title("Blackpool Mags").snippet("01253 757 000").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.749059, -2.359260)).title("Accrington Mags").snippet("01254 687500").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.749193, -2.485780)).title("Blackburn Mags").snippet("01254 687 500").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.697271, -2.666161)).title("South Ribble Leyland Mags").snippet("01772 844700").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.787384, -2.242339)).title("Burnley Mags").snippet("01282 800 100").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.762478, -2.702003)).title("Preston Mags").snippet("01772 208 000").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.927861, -3.010454)).title("Fleetwood Mags").snippet("01253 754 020").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
            }
            if(cc==1){

                mMap.addMarker(new MarkerOptions().position(new LatLng(53.787735, -2.247143)).title("Burnley Crown Court").snippet("01282 855 300").icon(BitmapDescriptorFactory.fromResource(R.drawable.crown_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.762394, -2.698972)).title("Preston Crown Court").snippet("01772 844 700").icon(BitmapDescriptorFactory.fromResource(R.drawable.crown_court)));
            }
            if(fe==1){

                mMap.addMarker(new MarkerOptions().position(new LatLng(53.585519, -2.516283)).title("Ewan Laurence").icon(BitmapDescriptorFactory.fromResource(R.drawable.fee_earner)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.684003, -2.459721)).title("Jane Dean").icon(BitmapDescriptorFactory.fromResource(R.drawable.fee_earner)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.582161, -2.444992)).title("Fred Close").icon(BitmapDescriptorFactory.fromResource(R.drawable.fee_earner)));
            }


        }
        if(spinner==6) {
            if(ps==1){
                mMap.addMarker(new MarkerOptions().position(new LatLng(52.667739, -1.169111)).title("Beaumont Leys PS").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(52.650176, -1.072395)).title("Keyham Lane PS").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(52.615247, -1.133429)).title("Leicester PS").snippet("aka Euston Lane").snippet("101").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(52.582810, -1.104153)).title("Wigston PS").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
            }
            if(mc==1){
                mMap.addMarker(new MarkerOptions().position(new LatLng(52.632426, -1.134288)).title("Leicester Mags").snippet("0116 255 3666").snippet("Court No. 2089").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
            }
            if(cc==1){
                mMap.addMarker(new MarkerOptions().position(new LatLng(52.629904, -1.127773)).title("Leicester Crown Court").snippet("0116 222 5700").snippet("Court No. 430").icon(BitmapDescriptorFactory.fromResource(R.drawable.crown_court)));
            }
            if(fe==1){}
        }
        if(spinner==7) {
            if(ps==1){
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.232641, -0.547653)).title("Lincoln City PS").snippet("101").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.148953, 0.344757)).title("Skegness PS").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(52.977859, -0.026714)).title("Boston PS").snippet("101").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(52.900871, -0.663958)).title("Grantham PS").snippet("101").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
            }
            if(mc==1){
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.224604, -0.541982)).title("Lincoln Mags").snippet("01522 528 218").snippet("Court No. 2083").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.148594, 0.344851)).title("Skegness Mags").snippet("01522 528218").snippet("Court No. 2051").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(52.982862, -0.025472)).title("Boston Mags").snippet("0175 489 8848").snippet("Court No. 2085").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(52.899380, -0.656287)).title("Grantham Mags").snippet("01476 563 438").snippet("01522 582 843").snippet("Court No. 2105").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
            }
            if(cc==1){
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.235215, -0.541585)).title("Lincoln Crown Court").snippet("01522 525 222").snippet("Court No. 432").icon(BitmapDescriptorFactory.fromResource(R.drawable.crown_court)));
            }
            if(fe==1){}
        }
        if(spinner==8) {
            if(ps==1){
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.488797, -2.957758)).title("Copy Lane PS").snippet("0151 709 6010").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.414738, -2.978625)).title("St Anne Street PS").snippet("0151 777 3040").snippet("0151 777 4851").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.456224, -2.737181)).title("St Helens PS").snippet("0151 777 6050").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.404105, -2.959980)).title("Wavertree PS").snippet("0151 709 6010").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.393436, -3.012859)).title("Wirral PS").snippet("0151 777 2228").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.652749, -2.997575)).title("Southport PS").snippet("0151 709 6010").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.481923, -2.883410)).title("Kirkby PS").snippet("0151  709 6010").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
            }
            if(mc==1){
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.449035, -2.989087)).title("South Sefton Mags").snippet("Youth & Mental Health").snippet("0151 933 6999").snippet("Court No. 1728").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.409125, -2.985950)).title("Liverpool & Knowsley Mags").snippet("0151 243 5500").snippet("Court No. 1730").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.392882, -3.013273)).title("Wirral Mags").snippet("0151 285 4100").snippet("Court No. 2271").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.404140, -2.989717)).title("Liverpool Youth Court").snippet("Liverpool Crown").snippet("0151 243 5626").snippet("Court No. 433").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.454941, -2.736502)).title("St Helens Mags").snippet("01744 620244").snippet("Court No. 2268").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
            }
            if(cc==1){
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.404140, -2.989717)).title("Liverpool Crown Court").snippet("0151 473 7373").snippet("Court No. 433").icon(BitmapDescriptorFactory.fromResource(R.drawable.crown_court)));
            }
            if(fe==1){}
        }
        if(spinner==9) {
            if(ps==1){
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.257831, -3.478232)).title("St Asaph PS").snippet("101").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.049099, -2.989836)).title("Wrexham PS").snippet("101").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
            }
            if(mc==1){
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.336384, -3.414245)).title("Prestatyn Mags").snippet("01492 871 333").snippet("Court No. 3061").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.047430, -2.991718)).title("Wrexham Mags").snippet("01352 707 330").snippet("Court No. 3058").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.177012, -3.137202)).title("Mold Mags").snippet("01352 707 330").snippet("Court No. 3056").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.320404, -3.823192)).title("Llandudno Mags").snippet("01492 871 333").snippet("Court No. 3062").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
            }
            if(cc==1){
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.177012, -3.137202)).title("Mold Crown Court").snippet("01352 707 340").snippet("Court No. 3056").icon(BitmapDescriptorFactory.fromResource(R.drawable.crown_court)));
            }
            if(fe==1){}
        }
        if(spinner==10) {
            if(ps==1){
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.142357, -4.263606)).title("Caernarfon PS").snippet("101").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.312511, -4.632987)).title("Holyead PS").snippet("101").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(52.745762, -3.888920)).title("Dolgellau PS").snippet("0845 607 1002").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
            }
            if(mc==1){
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.142027, -4.244581)).title("Caernarfon Mags").snippet("01286 669 700").snippet("Court No. 3244").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(52.741203, -3.953491)).title("Dolgellau Mags").snippet("01286 669 700").snippet("Court No. 3244").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.312210, -4.632864)).title("Holyead Mags").snippet("01286 669 700").snippet("Court No. 3238").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
            }
            if(cc==1){
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.142132, -4.244769)).title("Caernarfon Crown Court").snippet("01352 707 340").snippet("Court No. 755").icon(BitmapDescriptorFactory.fromResource(R.drawable.crown_court)));
            }
            if(fe==1){}
        }
        if(spinner==11) {
            if(ps==1){
                mMap.addMarker(new MarkerOptions().position(new LatLng(52.487934, -0.699071)).title("Corby PS").snippet("101").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(52.219240, -0.876968)).title("Criminal Justice Centre").snippet("101").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
            }
            if(mc==1){
                mMap.addMarker(new MarkerOptions().position(new LatLng(52.301300, -0.689100)).title("Wellingborough Mags").snippet("01604 497000").snippet("Court No. 2328").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(52.241843, -0.894772)).title("Northampton Mags").snippet("01604 497 000").snippet("Court No. 2325").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(52.397672, -0.725591)).title("Kettering Mags").snippet("Weds Only").snippet("01604 497 000").snippet("Court No. 2323").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(52.488268, -0.699109)).title("Corby Mags").snippet("Thurs/Fri Only").snippet("01604 497 000").snippet("Court No. 2321").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
            }
            if(cc==1){
                mMap.addMarker(new MarkerOptions().position(new LatLng(52.240781, -0.893550)).title("Northampton Crown Court").snippet("01604 470 400").snippet("Court No. 282").icon(BitmapDescriptorFactory.fromResource(R.drawable.crown_court)));
            }
            if(fe==1){}
        }
        if(spinner==12) {
            if(ps==1){
                mMap.addMarker(new MarkerOptions().position(new LatLng(52.948033, -1.147643)).title("Bridewell PS").snippet("101").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.141697, -1.190132)).title("Mansfield PS").snippet("101").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.079159, -0.804522)).title("Newark PS").snippet("101").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
            }
            if(mc==1){
                mMap.addMarker(new MarkerOptions().position(new LatLng(52.947451, -1.149423)).title("Nottingham Mags").snippet("0115 955 8111").snippet("Court No. 2086").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.148638, -1.205761)).title("Mansfield Mags").snippet("01623 451 500").snippet("Court No. 2087").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.302079, -1.122315)).title("Worksop Mags").snippet("01623 451 500").snippet("Court No. 2087").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
            }
            if(cc==1){
                mMap.addMarker(new MarkerOptions().position(new LatLng(52.948812, -1.146491)).title("Nottingham Crown Court").snippet("0115 910 3551").snippet("Court No. 444").icon(BitmapDescriptorFactory.fromResource(R.drawable.crown_court)));
            }
            if(fe==1){}
        }
        if(spinner==13) {
            if(ps==1){
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.022807, -2.179478)).title("Northern Area Custody Suite Stoke on Trent").snippet("0300 123 4455").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(52.807580, -1.630480)).title("Burton on Trent").snippet("0300 123 4455").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(52.692023, -2.121803)).title("Watling House Staffs").snippet("101").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(52.806189, -2.113448)).title("Stafford PS").snippet("0300 123 4455").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(52.635617, -1.691885)).title("Tamworth PS").snippet("0300 123 4455").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
            }
            if(mc==1){
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.013390, -2.230202)).title("Northern Staffs Mags").snippet("01782 741 641").snippet("Court No. 2786").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(52.807797, -1.630833)).title("Burton on Trent Mags").snippet("01785 223 144").snippet("Court No. 2780").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(52.684558, -2.036926)).title("Cannock Mags").snippet("01785 275 700").snippet("Court No. 2781").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(52.805674, -2.112833)).title("Stafford Mags").snippet("01785 223144").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
            }
            if(cc==1){
                mMap.addMarker(new MarkerOptions().position(new LatLng(52.805415, -2.119440)).title("Staffordshire Crown Court").snippet("01785 610 730").snippet("Court No. 455").icon(BitmapDescriptorFactory.fromResource(R.drawable.crown_court)));
            }
            if(fe==1){}
        }
        if(spinner==14) {
            if(ps==1){
                mMap.addMarker(new MarkerOptions().position(new LatLng(52.287976, -1.533396)).title("Leamington PS").snippet("01926 451 111").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(52.523005, -1.462701)).title("Nuneaton PS").snippet("024 7664 1111").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
            }
            if(mc==1){
                mMap.addMarker(new MarkerOptions().position(new LatLng(52.288139, -1.533236)).title("Leamington Mags").snippet("0192 642 9133").snippet("Court No. 2905").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(52.522935, -1.462632)).title("Nuneaton Mags").snippet("0192 642 9133").snippet("Court No. 2896").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
            }
            if(cc==1){
                mMap.addMarker(new MarkerOptions().position(new LatLng(52.288133, -1.533302)).title("Warwick Crown Court").snippet("01926 682 411").snippet("Court No. 361").icon(BitmapDescriptorFactory.fromResource(R.drawable.crown_court)));
            }
            if(fe==1){}
        }
        if(spinner==15) {
            if(ps==1){
                mMap.addMarker(new MarkerOptions().position(new LatLng(51.669640, -3.242653)).title("Ystrid Mynch PS").snippet("01443 865 595").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(51.580015, -2.994834)).title("Newport PS South").snippet("01633 838 111").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
            }
            if(mc==1){
                mMap.addMarker(new MarkerOptions().position(new LatLng(51.599874, -3.344247)).title("Caerphilly Mags").snippet("01633 261 300").snippet("Court No. 3211").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(51.654170, -3.020432)).title("Cwmbran Mags").snippet("01633 645000").snippet("Court No. 3211").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(51.588323, -3.004772)).title("Newport Mags Eskway").snippet("aka Gwent Mags").snippet("01633 261 300").snippet("Court No. 3211").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
            }
            if(cc==1){
                mMap.addMarker(new MarkerOptions().position(new LatLng(51.588336, -3.004772)).title("Newport South Wales Crown Court").snippet("029 2067 8730").snippet("Court No. 441").icon(BitmapDescriptorFactory.fromResource(R.drawable.crown_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(51.484606, -3.180213)).title("Cardiff Crown Court").snippet("029 2067 8730").snippet("Court No. 411").icon(BitmapDescriptorFactory.fromResource(R.drawable.crown_court)));
            }
            if(fe==1){}
        }
        if(spinner==16) {
            if(ps==1){
                mMap.addMarker(new MarkerOptions().position(new LatLng(51.464872, -3.170408)).title("Cardiff Bay PS").snippet("aka New Bridewell Suite").snippet("029 2022 2111").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(51.622059, -3.943169)).title("Swansea PS").snippet("101").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(51.744006, -3.379436)).title("Merthyr Tydfil PS").snippet("101").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(51.605313, -3.339207)).title("Bridgend New Bridewell Suite PS").snippet("101").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
            }
            if(mc==1){
                mMap.addMarker(new MarkerOptions().position(new LatLng(51.747402, -3.380586)).title("Merthyr Tydfil Mags").snippet("01685 721 731").snippet("Court No. 269").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(51.504404, -3.582695)).title("Bridgend Mags").snippet("001656 673 800").snippet("Court No. 146").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(51.598390, -3.340823)).title("Pontypridd Mags").snippet("01433 480 750").snippet("Court No. 3270").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(51.482076, -3.166114)).title("Cardiff Mags").snippet("029 2046 3040").snippet("Court No. 3251").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(51.622038, -3.945501)).title("Swansea Mags").snippet("01792 478 300").snippet("Court No. 3360").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
            }
            if(cc==1){
                mMap.addMarker(new MarkerOptions().position(new LatLng(51.484606, -3.180223)).title("Cardiff Crown Court").snippet("029 2067 8730").snippet("Court No. 411").icon(BitmapDescriptorFactory.fromResource(R.drawable.crown_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(51.614447, -3.957545)).title("Swansea Crown Court").snippet("01792 637 000").snippet("Court No. 457").icon(BitmapDescriptorFactory.fromResource(R.drawable.crown_court)));
            }
            if(fe==1){}
        }
        if(spinner==17) {
            if(ps==1){
                mMap.addMarker(new MarkerOptions().position(new LatLng(51.661838, -0.397700)).title("Watford PS").snippet("01707 354 000").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(51.754954, -0.013998)).title("Hoddesdon PS").snippet("01707 354 000").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(51.899527, -0.204765)).title("Stevenage PS").snippet("01707 354 000").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(51.765892, -0.237875)).title("Hatfield PS").snippet("01707 354 000").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
            }
            if(mc==1){
                mMap.addMarker(new MarkerOptions().position(new LatLng(51.661449, -0.397509)).title("Watford Mags").snippet("01923 281 300").snippet("Court No. 1886").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(51.752571, -0.336742)).title("St Albans Mags").snippet("01923 281 300").snippet("Court No. 1883").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(51.901165, -0.203981)).title("North Herts Mags").snippet("formerly Stevenage").snippet("01923 281 300").snippet("Court No. 1889").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(51.767067, -0.235450)).title("Hatfield Mags").snippet("01923 281 300").snippet("Court No. 1879").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
            }
            if(cc==1){
                mMap.addMarker(new MarkerOptions().position(new LatLng(51.751941, -0.335547)).title("St Albans Crown Court").snippet("01727 753 220").snippet("Court No. 220").icon(BitmapDescriptorFactory.fromResource(R.drawable.crown_court)));
            }
            if(fe==1){}
        }
        if(spinner==18) {
            if(ps==1){
                mMap.addMarker(new MarkerOptions().position(new LatLng(54.994657, -1.437281)).title("South Shields PS").snippet("101").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(54.918966, -1.405463)).title("Southwick PS").snippet("101").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
            }
            if(mc==1){
                mMap.addMarker(new MarkerOptions().position(new LatLng(54.958970, -1.600303)).title("Gateshead Mags").snippet("0191 477 5821").snippet("Court No. 2850").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(54.993044, -1.439088)).title("South Tyneside Mags").snippet("0191 455 8800").snippet("Court No. 2853").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(54.907201, -1.387869)).title("Sunderland Mags").snippet("0191 514 1621").snippet("Court No. 2855").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
            }
            if(cc==1){
                mMap.addMarker(new MarkerOptions().position(new LatLng(54.969893, -1.603390)).title("Newcastle Crown Court").snippet("0191 201 2000").snippet("Court No. 278").icon(BitmapDescriptorFactory.fromResource(R.drawable.crown_court)));
            }
            if(fe==1){}
        }
        if(spinner==19) {
            if(ps==1){
                mMap.addMarker(new MarkerOptions().position(new LatLng(55.133245, -1.593105)).title("Bedlington PS").snippet("101").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(55.010703, -1.502952)).title("Wallsend PS").snippet("101").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(54.966666, -1.618835)).title("Forthbanks PS").snippet("101").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
            }
            if(mc==1){
                mMap.addMarker(new MarkerOptions().position(new LatLng(55.133084, -1.594548)).title("Bedlington Mags").snippet("aka Mid South East Mags Court").snippet("01670 531 100").snippet("Court No. 2352").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(54.973185, -1.610481)).title("Newcastle Mags").snippet("0191 232 7326").snippet("Court No. 2351").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(55.011457, -1.443364)).title("North Tyneside Mags").snippet("0191 296 0099").snippet("Court No. 2852").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(55.770010, -2.001996)).title("Berwick Mags").snippet("01289 306885").snippet("Court No. 2348").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
            }
            if(cc==1){
                mMap.addMarker(new MarkerOptions().position(new LatLng(54.969893, -1.603390)).title("Newcastle Crown Court").snippet("0191 201 2000").snippet("Court No. 278").icon(BitmapDescriptorFactory.fromResource(R.drawable.crown_court)));
            }
            if(fe==1){}
        }
        if(spinner==20) {
            if(ps==1){
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.527447, -1.632152)).title("Bridge Street PS").snippet("101").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.442098, -1.458351)).title("Ecclesfield PS").snippet("101").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.518679, -1.130993)).title("Doncaster PS").snippet("101").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.429969, -1.361371)).title("Rotherham PS").snippet("101").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.554431, -1.485180)).title("Barnsley PS").snippet("101").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
            }
            if(mc==1){
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.384368, -1.465063)).title("Sheffield Mags").snippet("0114 276 0760").snippet("Court No. 320").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.519466, -1.130252)).title("Doncaster Mags").snippet("01302 366 711").snippet("Court No. 2771").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.430320, -1.360987)).title("Rotherham Mags").snippet("Court No. 2772").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.554820, -1.484480)).title("Barnsley Mags").snippet("01226 320 000").snippet("Court No. 2770").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
            }
            if(cc==1){
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.385364, -1.468799)).title("Sheffield Crown Court").snippet("0114 281 2400").snippet("Court No. 320").icon(BitmapDescriptorFactory.fromResource(R.drawable.crown_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.518656, -1.131477)).title("Doncaster Crown Court").snippet("0114 281 2400").snippet("Court No. 420").icon(BitmapDescriptorFactory.fromResource(R.drawable.crown_court)));
            }
            if(fe==1){}
        }
        if(spinner==21) {
            if(ps==1){
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.775128, -1.575228)).title("Elland Road PS").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.697863, -1.386253)).title("Normanton PS").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.643248, -1.784980)).title("Huddersfield PS").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.788099, -1.751520)).title("Bradford PS").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.726006, -1.864304)).title("Calderdale PS").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
            }
            if(mc==1){
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.800238, -1.551861)).title("Leeds Mags").snippet("0113 245 9653").snippet("Court No. 2992").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.683982, -1.502291)).title("Wakefield Mags & Pontefract Mags").snippet("01924 231 100").snippet("Court No. 2355").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.644313, -1.786151)).title("Kirklees Mags").snippet("01484 423 552").snippet("Court No. 2356").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.791889, -1.753910)).title("Bradford & Keighley Mags").snippet("01274 390 111").snippet("Court No. 2354").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.720210, -1.863360)).title("Calderdale Mags").snippet("01422 360 695").snippet("Court No. 2997").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
            }
            if(cc==1){
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.800315, -1.551897)).title("Leeds Crown Court").snippet("0113 306 2800").snippet("Court No. 243").icon(BitmapDescriptorFactory.fromResource(R.drawable.crown_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.792737, -1.748881)).title("Bradford Crown Court").snippet("01274 840 274").snippet("Court No. 402").icon(BitmapDescriptorFactory.fromResource(R.drawable.crown_court)));
            }
            if(fe==1){}
        }
        if(spinner==22) {
            if(ps==1){
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.976616, -1.570879)).title("Harrogate PS").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(54.341977, -1.435214)).title("Northallerton PS").snippet("101").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
            }
            if(mc==1){
                mMap.addMarker(new MarkerOptions().position(new LatLng(52.956314, -1.143912)).title("Harrogate Mags").snippet("3 days/week").snippet("01423 722 000").snippet("Court No. 2358").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(54.333487, -1.435900)).title("Northallerton Mags").snippet("4 days/week").snippet("01609 788 200").snippet("Court No. 2543").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.961324, -2.013198)).title("Skipton Mags").snippet("twice or once/week").snippet("01423 722 000").snippet("Court No. 2358").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
            }
            if(cc==1){
                mMap.addMarker(new MarkerOptions().position(new LatLng(54.575577, -1.231023)).title("Teeside Crown Court").snippet("Northallerton only").snippet("01642 340 000").snippet("Court No. 460").icon(BitmapDescriptorFactory.fromResource(R.drawable.crown_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.955039, -1.079516)).title("York Crown Court").snippet("01904 645 121").snippet("Court No. 467").icon(BitmapDescriptorFactory.fromResource(R.drawable.crown_court)));
            }
            if(fe==1){}
        }
        if(spinner==23) {
            if(ps==1){
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.946354, -1.074029)).title("York PS").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(54.281928, -0.408371)).title("Scarborough PS").snippet("0845 606 0247").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
            }
            if(mc==1){
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.956346, -1.081747)).title("York Mags").snippet("01904 818300").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(54.281489, -0.408011)).title("Scarborough Mags").snippet("01723 505000").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
            }
            if(cc==1){
                mMap.addMarker(new MarkerOptions().position(new LatLng(53.955033, -1.079538)).title("York Crown Court").snippet("01904 645 121").snippet("Court No. 467").icon(BitmapDescriptorFactory.fromResource(R.drawable.crown_court)));
            }
            if(fe==1){}
        }
        if(spinner==24) {
            if(ps==1){
                mMap.addMarker(new MarkerOptions().position(new LatLng(52.196494, -2.225267)).title("Worcester PS").snippet("0300 333 3000").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(52.390619, -2.265155)).title("Kidderminster PS").snippet("0300 333 3000").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(52.055640, -2.710257)).title("Hereford PS").snippet("0300 333 3000").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
            }
            if(mc==1){
                mMap.addMarker(new MarkerOptions().position(new LatLng(52.196848, -2.224045)).title("Worcester Mags").snippet("01905 743200").snippet("Court No. 1894").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(52.384180, -2.240911)).title("Kidderminster Mags").snippet("01562 514 000").snippet("Court No. 1894").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(52.057495, -2.711090)).title("Hereford Mags").snippet("01562 514 000").snippet("Court No. 1841").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(52.306317, -1.936972)).title("Redditch Mags").snippet("01562 514 000").snippet("Court No. 1894").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
            }
            if(cc==1){
                mMap.addMarker(new MarkerOptions().position(new LatLng(52.196808, -2.221861)).title("Worcester Crown Court").snippet("01905 730 800").snippet("Court No. 380").icon(BitmapDescriptorFactory.fromResource(R.drawable.crown_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(52.056138, -2.712988)).title("Hereford Crown Court").snippet("01432 276 118").snippet("Court No. 762").icon(BitmapDescriptorFactory.fromResource(R.drawable.crown_court)));
            }
            if(fe==1){}
        }
        if(spinner==25) {
            if(ps==1){
                mMap.addMarker(new MarkerOptions().position(new LatLng(52.714369, -2.724297)).title("Shrewsbury PS").snippet("0300 333 3000").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(52.677353, -2.452707)).title("Telford PS").snippet("0300 333 3000").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
            }
            if(mc==1){
                mMap.addMarker(new MarkerOptions().position(new LatLng(52.704991, -2.729754)).title("Shrewsbury Mags").snippet("01952 204 500").snippet("Court No. 3279").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(52.677737, -2.451509)).title("Telford Mags").snippet("for North Shropshire arrests").snippet("01952 204 500").snippet("Court No. 3282").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
            }
            if(cc==1){
                mMap.addMarker(new MarkerOptions().position(new LatLng(52.704846, -2.733926)).title("Shrewsbury Crown Court").snippet("01743 260 850/820").snippet("Court No. 452").icon(BitmapDescriptorFactory.fromResource(R.drawable.crown_court)));
            }
            if(fe==1){}
        }
        if(spinner==26) {
            if(ps==1){
                mMap.addMarker(new MarkerOptions().position(new LatLng(54.774428, -1.571553)).title("Durham PS").snippet("0345 606 0365").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(54.651183, -1.692342)).title("Bishop Aukland PS").snippet("101").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(54.524018, -1.549860)).title("Darlington PS").snippet("0345 606 0365").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(54.755699, -1.336163)).title("Peterlee PS").snippet("101").icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station)));
            }
            if(mc==1){
                mMap.addMarker(new MarkerOptions().position(new LatLng(54.620427, -1.569801)).title("Newton Aycliffe Mags").snippet("01325 318 114").snippet("Court No. 1584").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(54.755690, -1.336172)).title("Peterlee Mags").snippet("01429 230 600").snippet("Court No. 1583").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(54.524229, -1.549609)).title("Darlington Mags").snippet("01325 376 670").snippet("Court No. 1585").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(54.857500, -1.830217)).title("Consett Mags").icon(BitmapDescriptorFactory.fromResource(R.drawable.magistrate_court)));
            }
            if(cc==1){
                mMap.addMarker(new MarkerOptions().position(new LatLng(54.575568, -1.231040)).title("Teeside Crown Court").snippet("Darlington offences only").snippet("01642 340 000").snippet("Court No. 460").icon(BitmapDescriptorFactory.fromResource(R.drawable.crown_court)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(54.774091, -1.568852)).title("Durham Crown Court").snippet("0191 386 6714").snippet("Court No. 422").icon(BitmapDescriptorFactory.fromResource(R.drawable.crown_court)));
            }
            if(fe==1){}
        }

    }


    private String getDirectionsUrl(LatLng origin, LatLng dest)
    {
        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;

        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;

        // Sensor enabled
        String sensor = "sensor=false";

        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + sensor;

        // Output format
        String output = "json";

        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;

        return url;
    }

    /** A method to download json data from url */
    private String downloadUrl(String strUrl) throws IOException
    {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try
        {
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while ((line = br.readLine()) != null)
            {
                sb.append(line);
            }

            data = sb.toString();

            br.close();

        } catch (Exception e)
        {
            Log.d("Exception while downloading url", e.toString());
        } finally
        {
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }

    // Fetches data from url passed
    private class DownloadTask extends AsyncTask<String, Void, String>
    {
        // Downloading data in non-ui thread
        @Override
        protected String doInBackground(String... url)
        {

            // For storing data from web service
            String data = "";

            try
            {
                // Fetching the data from web service
                data = MapsFragment.this.downloadUrl(url[0]);
            } catch (Exception e)
            {
                Log.d("Background Task", e.toString());
            }
            return data;
        }

        // Executes in UI thread, after the execution of
        // doInBackground()
        @Override
        protected void onPostExecute(String result)
        {
            super.onPostExecute(result);

            ParserTask parserTask = new ParserTask();

            // Invokes the thread for parsing the JSON data
            parserTask.execute(result);

        }
    }

    /** A class to parse the Google Places in JSON format */
    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String, String>>>>
    {

        // Parsing the data in non-ui thread
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData)
        {
            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try
            {
                jObject = new JSONObject(jsonData[0]);
                DirectionsJSONParser parser = new DirectionsJSONParser();

                // Starts parsing data
                routes = parser.parse(jObject);
            } catch (Exception e)
            {
                e.printStackTrace();
            }
            return routes;
        }

        // Executes in UI thread, after the parsing process
        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result)
        {
            ArrayList<LatLng> points = null;
            PolylineOptions lineOptions = null;
            MarkerOptions markerOptions = new MarkerOptions();
            String distance = "";
            double distanceInMiles = 0;
            double cost = 0;
            String duration = "";

            if (result.size() < 1)
            {
                //     Toast.makeText(MapFragment.this.getBaseContext(), "No Points", Toast.LENGTH_SHORT).show();
                return;
            }

            // Traversing through all the routes
            for (int i = 0; i < result.size(); i++)
            {
                points = new ArrayList<LatLng>();
                lineOptions = new PolylineOptions();

                // Fetching i-th route
                List<HashMap<String, String>> path = result.get(i);

                // Fetching all the points in i-th route
                for (int j = 0; j < path.size(); j++)
                {
                    HashMap<String, String> point = path.get(j);

                    if (j == 0)
                    { // Get distance from the list
                        distance = point.get("distance");
                        int index = distance.indexOf(" ");
                        String distanceOnly= distance.substring(0,index);
                        distanceInMiles = Double.parseDouble(distanceOnly) * 0.621371192;
                        DecimalFormat df = new DecimalFormat("#.##");
                        distanceInMiles = Double.valueOf(df.format(distanceInMiles));
                        cost = Double.valueOf(df.format(distanceInMiles*0.25));

                        continue;
                    } else if (j == 1)
                    { // Get duration from the list
                        duration = point.get("duration");
                        continue;
                    }
                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);
                    points.add(position);
                }

                // Adding all the points in the route to LineOptions
                lineOptions.addAll(points);
                lineOptions.width(5);
                lineOptions.color(Color.BLUE);
            }

            MapsFragment.this.distance.setText(getString(R.string.distance)+ distanceInMiles + " miles");
            MapsFragment.this.time.setText(getString(R.string.time)+ duration);
            MapsFragment.this.cost.setText(getString(R.string.cost)+ cost);

        }
    }

}
