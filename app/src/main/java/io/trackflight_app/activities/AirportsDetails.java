package io.trackflight_app.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.SimpleTimeZone;

import io.trackflight_app.R;
import io.trackflight_app.services.BasicAuthInterceptor;
import io.trackflight_app.utils.RecyclerViewInterface;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttp;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AirportsDetails extends AppCompatActivity implements OnMapReadyCallback {

    private static final String USERNAME ="sprodeg77" ;
    private static final String PASSWORD = "77Evil#2";
    static  String icao;
    private static final String API_ROOT = "http://opensky-network.org/api";
    private static String DEPARTURES_URI = null;
    private static String ARRIVALS_URI = null;
    ActionBar actionBar;
    private GoogleMap mMap;
    double num_lon;
    double num_lat;
    private long  begin_time;
    private long end_time;
    Calendar calendar;
    SimpleDateFormat simpleDateFormat;
    String time;
    String name;
    TabLayout tabLayout;
    ViewPager viewPager;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airports_details);
        TextView details_city =findViewById(R.id.details_city);
        TextView details =findViewById(R.id.details);
//        TextView details_name =findViewById(R.id.details_name);
        TextView lon =findViewById(R.id.longitude);
        TextView lat =findViewById(R.id.latitude);
        ImageView back =findViewById(R.id.backAF);
        tabLayout=findViewById(R.id.tab_layout);
        viewPager=findViewById(R.id.view_pager);
         ListView lv = (ListView)findViewById(R.id.lv);

        //TABS
        ArrayList<String> arrayList=new ArrayList<>(0);

        // Add title in array list
        arrayList.add("DEPARTURES");
        arrayList.add("ARRIVALS");

        // Setup tab layout
        tabLayout.setupWithViewPager(viewPager);

        // Prepare view pager
        prepareViewPager(viewPager,arrayList);
        //Back
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        String city=getIntent().getStringExtra("CITY");
         name=getIntent().getStringExtra("NAME");
        String country=getIntent().getStringExtra("COUNTRY");
        String longitude=getIntent().getStringExtra("LON");
        String latitude=getIntent().getStringExtra("LAT");
        String icao=getIntent().getStringExtra("ICAO");
        begin_time=(long)(System.currentTimeMillis()/1000);
        end_time=(long)(begin_time+86400);
        DEPARTURES_URI="https://opensky-network.org/api/flights/departure?airport=EDDF&begin=1517227200&end=1517230800";
        // STATES_URI = "https://opensky-network.org/api/flights/departure?airport="+icao+"&begin="+begin_time+"&end="+end_time+"";


        num_lat= Double.valueOf(latitude);
        num_lon= Double.valueOf(longitude);
        System.out.println(num_lat+"\n"+num_lon);

        details_city.setText(city+", "+country);
       lon.setText( latitude);
        lat.setText( longitude);
        System.out.println(city);



        details.setText(name);



        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        //
        OkHttpClient client=new OkHttpClient.Builder()
                .addInterceptor(new BasicAuthInterceptor(USERNAME, PASSWORD))
                .build();
        String url=DEPARTURES_URI;
        Request request=new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()){
                    String myResponse=response.body().string();
                    AirportsDetails.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println(myResponse);
                            try {
                                JSONObject reader = new JSONObject(myResponse);
                                JSONArray flights_array = reader.getJSONArray("superheros"); // get the whole json array list
                                System.out.println("json size is : "+flights_array.length());
                                for(int i = 0;i<flights_array.length();i++)
                                {
                                    JSONObject hero = flights_array.getJSONObject(i);
                                    String name = hero.getString("icao24");


                                    ArrayList<String> icao=new ArrayList<>();
                                    icao.add(name);
                                    System.out.println(icao);


//                                    ListAdapter adapter = new SimpleAdapter(AirportsDetails.this,icao,R.layout.arrivals
//                                            ,new String[]{"name"},new int[]{R.id.icao});
//                                    lv.setAdapter(adapter);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    });
                }
            }
        });


    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(num_lon,num_lat );
        mMap.addMarker(new MarkerOptions().position(sydney).title(name));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(num_lon,num_lat),8.0f));
    }
    private void prepareViewPager(ViewPager viewPager, ArrayList<String> arrayList) {
        // Initialize main adapter
        MainAdapter adapter=new MainAdapter(getSupportFragmentManager());

        // Initialize main fragment
        MainFragment mainFragment=new MainFragment();

        // Use for loop
        for(int i=0;i<arrayList.size();i++)
        {
            // Initialize bundle
            Bundle bundle=new Bundle();

            // Put title
            bundle.putString("title",arrayList.get(i));

            // set argument
            mainFragment.setArguments(bundle);

            // Add fragment
            adapter.addFragment(mainFragment,arrayList.get(i));
            mainFragment=new MainFragment();
        }
        // set adapter
        viewPager.setAdapter(adapter);
    }
    private class MainAdapter extends FragmentPagerAdapter implements io.trackflight_app.activities.MainAdapter {
        // Initialize arrayList
        ArrayList<Fragment> fragmentArrayList= new ArrayList<>();
        ArrayList<String> stringArrayList=new ArrayList<>();

        int[] imageList={R.drawable.ic_baseline_airplanemode_active_24,R.drawable.ic_baseline_airplanemode_active_24};

        // Create constructor
        public void addFragment(Fragment fragment,String s)
        {
            // Add fragment
            fragmentArrayList.add(fragment);
            // Add title
            stringArrayList.add(s);
        }

        public MainAdapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            // return fragment position
            return fragmentArrayList.get(position);
        }

        @Override
        public int getCount() {
            // Return fragment array list size
            return fragmentArrayList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {

            // Initialize drawable
            Drawable drawable= ContextCompat.getDrawable(getApplicationContext()
                    ,imageList[position]);

            // set bound
            drawable.setBounds(0,0,0,
                    0);

            // Initialize spannable image
            SpannableString spannableString=new SpannableString(""+stringArrayList.get(position));

            // Initialize image span
            ImageSpan imageSpan=new ImageSpan(drawable,ImageSpan.ALIGN_BOTTOM);

            // Set span
            spannableString.setSpan(imageSpan,0,0, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            // return spannable string
            return spannableString;
        }
    }
}