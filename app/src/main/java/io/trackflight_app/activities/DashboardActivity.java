package io.trackflight_app.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Calendar;

import io.trackflight_app.R;



public class DashboardActivity extends AppCompatActivity {

    public static final String CITY = "city";

    String myuid;
    ActionBar actionBar;
    BottomNavigationView navigationView;
    TextView arr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        navigationView = findViewById(R.id.navigation);
       arr = findViewById(R.id.arrival);

        navigationView.setOnNavigationItemSelectedListener(selectedListener);


        // When we open the application first
        // time the fragment should be shown to the user
        // in this case it is home fragment
        HomeFragment fragment = new HomeFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content, fragment, "");
        fragmentTransaction.commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener selectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {

                case R.id.home_item:

                    HomeFragment fragment = new HomeFragment();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.content, fragment, "");
                    fragmentTransaction.commit();
                    return true;



                case R.id.airport_item:

                    AirportsFragment fragment2 = new AirportsFragment();
                    FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction2.replace(R.id.content, fragment2, "");
                    fragmentTransaction2.commit();
                    return true;

                case R.id.settings_item:

                    SettingsFragment listFragment = new SettingsFragment();
                    FragmentTransaction fragmentTransaction3 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction3.replace(R.id.content, listFragment, "");
                    fragmentTransaction3.commit();
                    return true;


            }
            return false;
        }
    };
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        switch (requestCode) {
//            case .REQUEST_CODE:
//
//
//                if (resultCode == Activity.RESULT_OK && data != null) {
//                    String name = (String) data.getSerializableExtra(CITY);
//                    System.out.println(name+"ffffffffffffffffffffffffffff");
//                    arr.setText(name);
//
//
//                    System.out.println(name);
//                }
//                break;
//        }
//    }
}
