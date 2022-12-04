package io.trackflight_app.activities;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


import io.trackflight_app.R;

public class SplashscreenActivity extends AppCompatActivity {
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        //actionBar.hide();


        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(getApplicationContext(),DashboardActivity.class);
                startActivity(intent);
                finish();
            }

        };

        new  Handler().postDelayed(runnable,3000);
    }
}