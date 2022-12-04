package io.trackflight_app.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import io.trackflight_app.MainActivity;
import io.trackflight_app.R;
import io.trackflight_app.adapter.FlightAdapter;
import io.trackflight_app.utils.RecyclerViewInterface;

public class FlightsActivity extends AppCompatActivity  implements RecyclerViewInterface{
    FlightAdapter flightAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flights);
        ImageView back=findViewById(R.id.backSF);
        RecyclerView recyclerView=findViewById(R.id.flights_list);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        LinearLayoutManager linearLayoutManager1=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager1);
        flightAdapter=new FlightAdapter( this,getApplicationContext());
        recyclerView.setAdapter(flightAdapter);

    }

    @Override
    public void onItemClick(int position) {

        Intent intent=new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);

    }
}