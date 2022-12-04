package io.trackflight_app.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;

import com.blongho.country_data.World;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import io.trackflight_app.R;
import io.trackflight_app.adapter.AirportAdapter;
import io.trackflight_app.adapter.SecondAirportAdapter;
import io.trackflight_app.utils.RecyclerViewInterface;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;



public class AirportsFragment extends Fragment implements RecyclerViewInterface {
;
    private static final String CITY = "CITY";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
   // final int flag=World.getFlagOf("sweden")
    String myResponse;
    ListView lv;
    ArrayList<String> arrayList;
    ArrayList<String> name=new ArrayList<>();
    ArrayList<String> country=new ArrayList<>();
    ArrayList<String> city=new ArrayList<>();
    ArrayList<String> longitude=new ArrayList<>();
    ArrayList<String> latitude=new ArrayList<>();
    ArrayList<String> icao=new ArrayList<>();
    ArrayList<ImageView> flag_image=new ArrayList<>();
    SecondAirportAdapter adapter;




    // use country name

    // Set the image of an imageView

    public AirportsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AirportsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AirportsFragment newInstance(String param1, String param2) {
        AirportsFragment fragment = new AirportsFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_airports, container, false);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       World.init(getContext());
        RecyclerView recyclerView=view.findViewById(R.id.recycleView2);
        final   int flag= World.getFlagOf("sweden");
        //swedishFlag.setImageResource(flag);








        LinearLayoutManager linearLayoutManager1=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager1);

        try {
            JSONObject jsonObject=new JSONObject(JsonDtaFromAsset());
            JSONArray jsonArray= jsonObject.getJSONArray("airports");
            for(int i = 0;i<jsonArray.length();i++)
            {

                JSONObject airportData = jsonArray.getJSONObject(i);
                country.add(airportData.getString(("country")));
                name.add( airportData.getString("name"));
                city.add(airportData.getString("city"));
                longitude.add(airportData.getString("lon"));
                latitude.add(airportData.getString("lat"));
                icao.add(airportData.getString("icao"));



            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for (int i=0;i<name.size();i++){
            if (name.get(i)!=null){
                    World.getFlagOf(name.get(i));
            }
            else {
               World.getFlagOf("china");
            }

        }
        //swedishFlag.setImageResource(2131165686);


        EditText text;

        adapter=new SecondAirportAdapter(name,country,city,latitude,longitude,getContext(),this,flag_image,icao);
        recyclerView.setAdapter(adapter);


    }
    //    private boolean isNetworkAvailable() {
//        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo coninfo = manager.getActiveNetworkInfo();
//        boolean isAvailable = false;
//        if(coninfo!=null && coninfo.isConnected()){
//            isAvailable = true;
//            return isAvailable;
//        }
//
//        return false;
//    }


    private String JsonDtaFromAsset() {
        String json=null;
        try {
            InputStream inputStream=getContext().getAssets().open("airports.json");
            int sizeOfFiles=inputStream.available();
            byte []  bufferData=new  byte[sizeOfFiles];
            inputStream.read(bufferData);
            inputStream.close();
            json=new String(bufferData,"UTF-8");
        }catch (IOException e){
            e.printStackTrace();
            return  null;
        }
        return  json;

    }

    @Override
    public void onItemClick(int position) {
        Intent intent=new Intent(getContext(),AirportsDetails.class);
        intent.putExtra("CITY",city.get(position).toString());
        intent.putExtra("NAME",name.get(position).toString());
        intent.putExtra("COUNTRY",country.get(position).toString());
        intent.putExtra("LAT",longitude.get(position).toString());
        intent.putExtra("LON",latitude.get(position).toString());
        intent.putExtra("ICAO",icao.get(position).toString());

        startActivity(intent);
        System.out.println(city.get(position));
    }
}
