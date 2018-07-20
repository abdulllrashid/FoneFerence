package com.example.training.myapplication;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Slide;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Slide_list extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_list);
        final RecyclerView recyclerView = findViewById(R.id.slide_recycle);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference slideDbRef = database.getReference("/files/slides");


        FloatingActionButton slideUplaod=findViewById(R.id.addSlidebtn);
        slideUplaod.setOnClickListener(this);



        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        slideDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                HashMap hashMap = (HashMap) dataSnapshot.getValue();
                Iterator iterator = null;

                ArrayList<Slides> slidesArrayList = new ArrayList<>();


                if (hashMap != null) {
                    iterator = hashMap.keySet().iterator();

                    while (iterator.hasNext()) {
                        Slides slides = new Slides();
                        String key = (String) iterator.next();
                        HashMap hashMap1 = (HashMap) hashMap.get(key);
                        slides.setSlideurl((String) hashMap1.get("Url"));
                        slides.setSlidedesc((String) hashMap1.get("Description"));
                        slides.setSlideTitle((String) hashMap1.get("Title"));
                        slidesArrayList.add(slides);
                    }
                }
                recyclerView.setAdapter(new SlideAdapter(slidesArrayList, Slide_list.this));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(View v) {

        Intent intent=new Intent(this,Upload_File.class);
        startActivity(intent);


    }
}
