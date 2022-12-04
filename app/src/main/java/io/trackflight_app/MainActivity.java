package io.trackflight_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.rilixtech.widget.countrycodepicker.Country;
import com.rilixtech.widget.countrycodepicker.CountryCodePicker;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    private  GoogleMap mMap;
    ArrayList<LatLng>arrayList=new ArrayList<LatLng>();
    LatLng point1=new LatLng(12,56);
    LatLng point2=new LatLng(-24.8,31.5333);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton imageButton=findViewById(R.id.view_more);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialog bottomSheetDialog= new BottomSheetDialog(MainActivity.this);
                bottomSheetDialog.setContentView(R.layout.bottomsheet_dialog);
                bottomSheetDialog.setCanceledOnTouchOutside(false);
                bottomSheetDialog.show();
                System.out.println(12);
            }
        });
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_id);
        mapFragment.getMapAsync(this);
        arrayList.add(point1);
        arrayList.add(point2);




    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        for (int i=0;i<arrayList.size();i++){
            mMap.addMarker(new MarkerOptions().position(arrayList.get(i)).title("Map"));
            mMap.moveCamera(CameraUpdateFactory.zoomTo(0.0f));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(arrayList.get(i)));


        }



    }
}