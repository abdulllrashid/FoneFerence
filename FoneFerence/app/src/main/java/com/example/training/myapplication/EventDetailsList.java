package com.example.training.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EventDetailsList extends AppCompatActivity  {

    private FirebaseDatabase mDatabase;
    private FirebaseAuth mAuth;
    public String UID,ob;
    private DatabaseReference myRef,eRef;
    private TextView eventnamE, datE, timE, venuE, speakerdetailS, descriptioN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event__details__list);
        mDatabase = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        UID=mAuth.getCurrentUser().getUid().toString();
        myRef = mDatabase.getReference("Users/"+UID+"/Eventid");
//        eventnamE = (TextView) findViewById(R.id.eventname);
//        datE = (TextView) findViewById(R.id.Date);
//        timE = (TextView) findViewById(R.id.Time);
//        venuE = (TextView) findViewById(R.id.venue);
//        speakerdetailS = (TextView) findViewById(R.id.speakerdetails);
        descriptioN = (TextView) findViewById(R.id.description);


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                ob = dataSnapshot.getValue().toString();
                Toast.makeText(getApplicationContext(),ob,Toast.LENGTH_LONG).show();

                eRef = mDatabase.getReference("events/"+ob+"/details");
                eRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        AddEvents events = dataSnapshot.getValue(AddEvents.class);


                  eventnamE.setText(events.getEventname());
                  datE.setText(events.getDate());
                  timE.setText(events.getTime());
                  venuE.setText(events.getVenue());
                  speakerdetailS.setText(events.getSpeakerdetails());
                  descriptioN.setText(events.getDescription());

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
//
    }

}
