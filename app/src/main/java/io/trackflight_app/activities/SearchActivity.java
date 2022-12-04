package io.trackflight_app.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import io.trackflight_app.R;
import io.trackflight_app.adapter.AirportAdapter;
import io.trackflight_app.models.Airports;
import io.trackflight_app.utils.RecyclerViewInterface;

public class SearchActivity extends AppCompatActivity implements RecyclerViewInterface {
    public static final int REQUEST_CODE =3002 ;
    ArrayList<String> name=new ArrayList<>();
    ArrayList<String> code=new ArrayList<>();
    ArrayList<String> city=new ArrayList<>();
    public  static final  String CITY="CITY";
    ArrayList<String> airportArrayList=new ArrayList<>();
    AirportAdapter adapter;
    SearchActivity searchActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        RecyclerView recyclerView = findViewById(R.id.recycleView);
        ImageView back_to_search = findViewById(R.id.search_back);
        EditText searchView = (EditText) findViewById(R.id.searchView);

        searchView.setFocusable(true);

        searchView.requestFocusFromTouch();

        //Back
        back_to_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        try {
            JSONObject jsonObject = new JSONObject(JsonDtaFromAsset());
            JSONArray jsonArray = jsonObject.getJSONArray("airports");
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject airportData = jsonArray.getJSONObject(i);
                code.add(airportData.getString(("code")));
                name.add(airportData.getString("name"));
                city.add(airportData.getString("city"));
                airportArrayList.add(airportData.getString("name"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        EditText text;

        adapter = new AirportAdapter(name, code, city, airportArrayList, this);
        recyclerView.setAdapter(adapter);


    }

    private String JsonDtaFromAsset() {
        String json=null;
        try {
            InputStream inputStream=getAssets().open("airports.json");
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

    @SuppressLint("ResourceType")
    @Override
    public void onItemClick(int position) {

        Intent intent =new Intent();
        intent.putExtra(CITY,city.get(position));
       // System.out.println(city.get(position));
       intent.putExtra(CITY,code.get(position).toString());
        setResult(Activity.RESULT_OK, intent);
        finish();
//        //
//        MyFlightFragment myFlightFragment= new MyFlightFragment();
//        FragmentManager fragmentManager= getSupportFragmentManager() ;
//        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
//        Bundle bundle= new Bundle();
//        bundle.putString("city",city.get(position));
//        myFlightFragment.setArguments(bundle);
//        fragmentTransaction.replace(R.id.content,myFlightFragment);
//        fragmentTransaction.commit();








        //onBackPressed();

        //startActivity(intent);

    }
    ///


}