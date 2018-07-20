package com.example.training.myapplication;


import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.os.Bundle;
import android.app.Fragment;
import android.content.Intent;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.support.annotation.Nullable;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import android.support.design.widget.FloatingActionButton;

public class EventListFragment extends Fragment  {

    private FirebaseDatabase mDatabase;
    private FirebaseAuth mAuth;
    public String UID;
    public String ob;
    private DatabaseReference myRef,eRef,tRef;
    private TextView eventnamE,datE, day, month,timE, venuE, speakerdetailS, descriptioN;
    FloatingActionButton fab;
    private LinearLayout mProgressLayout;
    public static final String PREFS_NAME = "usertype";
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_event_list, null);
mProgressLayout = (LinearLayout)view.findViewById(R.id.loading_layout);
        fab = (FloatingActionButton) view.findViewById(R.id.floatingActionEdit);
        SharedPreferences preferences = getActivity().getSharedPreferences(PREFS_NAME,0);
        String value = preferences.getString("usertype", "");
        if (value.equals("Audiance")) {
            fab.setVisibility(View.GONE);
        }
        else if(value.equals("Speaker")) {
            fab.setVisibility(View.GONE);
        }
        else if (value.equals("Event Host")){
            fab.setVisibility(View.VISIBLE);
        }


        mAuth = FirebaseAuth.getInstance();
        UID=mAuth.getCurrentUser().getUid().toString();
        mDatabase = FirebaseDatabase.getInstance();
        myRef = mDatabase.getReference("Users/"+UID+"/Eventid");

        eventnamE = (TextView) view.findViewById(R.id.txtEventname);
        day = (TextView) view.findViewById(R.id.txtDay);
        month = (TextView) view.findViewById(R.id.txtMonth);
        timE = (TextView) view.findViewById(R.id.txtTime);
        venuE = (TextView) view.findViewById(R.id.txtVenue);
        speakerdetailS = (TextView) view.findViewById(R.id.txtSpeaker);
        descriptioN = (TextView) view.findViewById(R.id.txtDescription);




        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() == null) {
                    try {
                        getFragmentManager().beginTransaction().replace(R.id.fragment_container, new EventListFragmentNull()).commit();
                    }
                    catch (Exception e)
                    {
                        Log.d("TAG",e.getMessage());
                    }


                }
                else {

                    ob = dataSnapshot.getValue().toString();
                    eRef = mDatabase.getReference("events/" + ob + "/details");
                    eRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            mProgressLayout.setVisibility(View.GONE);
                            if (dataSnapshot.getValue() == null) {
                                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new EventListFragmentNull()).commit();

                            }
                            else {
                                AddEvents events = dataSnapshot.getValue(AddEvents.class);


                                eventnamE.setText(events.getEventname());
                                day.setText(events.getDay());
                                month.setText(events.getMonth());
                                timE.setText(events.getTime());
                                venuE.setText(events.getVenue());
                                speakerdetailS.setText(events.getSpeakerdetails());
                                descriptioN.setText(events.getDescription());


                            }
                        }


                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }                }

                @Override
                public void onCancelled (DatabaseError databaseError){

                }

        });

       return view;

    }



}
