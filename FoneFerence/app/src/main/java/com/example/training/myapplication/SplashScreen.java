package com.example.training.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends Activity {
    // Set Duration of the Splash Screen
    long Delay = 2000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Remove the Title Bar
        setContentView(R.layout.activity_splash_screen);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        // Get the view from splash_screen.xml
        // Create a Timer
        Timer RunSplash = new Timer();
        // Task to do when the timer ends
        TimerTask ShowSplash = new TimerTask() {
            @Override
            public void run() {
                // Close SplashScreenActivity.class
                finish();
                // Start MainActivity.class
                Intent myIntent = new Intent(getApplicationContext(), SigninScreen.class);
                startActivity(myIntent);
            }
        };
        // Start the timer
        RunSplash.schedule(ShowSplash, Delay);
    }
}


