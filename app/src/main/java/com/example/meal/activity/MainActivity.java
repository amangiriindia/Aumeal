package com.example.meal.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.meal.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create an Intent to start the HomeActivity
        Intent intent = new Intent(MainActivity.this, HomeActivity.class);

        // Create a Handler to post a delayed action
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start the HomeActivity after a 5-second delay
                startActivity(intent);

                // Close the splash activity
                finish();
            }
        }, 5000); // 5000 milliseconds = 5 seconds
    }
}