package com.example.training.myapplication;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    public BottomNavigationView bottomNavigationView;
    private FirebaseDatabase mDatabase;
    private FirebaseAuth mAuth;
    public String Uid, Eventid;

    private DatabaseReference myRef;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.EventDetails:
                    mAuth = FirebaseAuth.getInstance();
                    Uid = mAuth.getCurrentUser().getUid().toString();
                    mDatabase = FirebaseDatabase.getInstance();

                    myRef = mDatabase.getReference("Users/" + Uid + "/Eventid");
                    myRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.getValue() == null) {
                                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new EventListFragmentNull()).commit();

                            } else {
                                Eventid=dataSnapshot.getValue(String.class);
                                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new EventListFragment()).commit();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                    return true;

                case R.id.Attendees:
//                    mTextMessage.setText("Attendees");
                    Bundle bundle=new Bundle();
                    bundle.putString("eventid",Eventid);
                    AudianceListFragment audianceListFragment=new AudianceListFragment();
                    audianceListFragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container, audianceListFragment).commit();
                    return true;
                case R.id.Goods:
//                    mTextMessage.setText("Goods");
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container, new GoodsListFragment()).commit();
                    return true;
                case R.id.Profile:
//                    mTextMessage.setText("Profile");
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileFragment()).commit();
                    return true;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.navigation);

        //        getFragmentManager().beginTransaction().replace(R.id.fragment_container, new EventListFragmentNull()).commit();
        mAuth = FirebaseAuth.getInstance();
        Uid = mAuth.getCurrentUser().getUid();
        mDatabase = FirebaseDatabase.getInstance();
        myRef = mDatabase.getReference("Users/" + Uid + "/Eventid");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               try {
                   if (dataSnapshot.getValue() == null) {
                       getFragmentManager().beginTransaction().add(R.id.fragment_container, new EventListFragmentNull()).commit();

                   } else {
                       Eventid = dataSnapshot.getValue(String.class);
                       getFragmentManager().beginTransaction().add(R.id.fragment_container, new EventListFragment()).commit();
                   }
               }catch (Exception ex){
                        ex.printStackTrace();
               }
          }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Log.d("MainActivity", "onCreate");
    }

    public void Slides(View v) {
        startActivity(new Intent(getApplicationContext(), Slide_list.class));
    }

    public void Notes(View view) {
        startActivity(new Intent(getApplicationContext(), Slidenote_list.class));
    }

    public void addetails(View view) {
        startActivity(new Intent(getApplicationContext(), EventRegistrationForm.class));
    }

    public void Certificates(View view) {
        startActivity(new Intent(getApplicationContext(), Certificates_List.class));
    }

    public void foodcoupons(View v) {
        startActivity(new Intent(getApplicationContext(), Coupon_List.class));
    }

    public void EditEventClick(View v) {
        startActivity(new Intent(getApplicationContext(), Editevent.class));
    }
    public void qrCode(View view){
        Intent i=new Intent(this,Generateqrcode.class);
        i.putExtra("eventId",Eventid);
        startActivity(i);
    }

}


