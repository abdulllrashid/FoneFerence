package com.example.training.myapplication;


import android.os.Bundle;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.widget.TextView;

public class Main2Activity extends Activity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.EventDetails:
                    mTextMessage.setText("Event Details");

                    return true;
                case R.id.Attendees:
                    mTextMessage.setText("Attendees");
                    return true;
                case R.id.Goods:
                    mTextMessage.setText("Goods");
                    return true;
                case R.id.Profile:
                    mTextMessage.setText("Profile");
                    return true;
            }
            return false;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

}
