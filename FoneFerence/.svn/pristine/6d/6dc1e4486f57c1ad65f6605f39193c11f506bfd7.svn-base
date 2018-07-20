package com.example.training.myapplication;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Slidenote_list extends AppCompatActivity implements View.OnClickListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slidenote_list);

        final RecyclerView recyclerView=findViewById(R.id.slidenote_recycle);
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference slidenoteDbRef=database.getReference("/files/slide note");

        FloatingActionButton addslideNoteBtn=findViewById(R.id.addSlideNotebtn);

        addslideNoteBtn.setOnClickListener(this);



        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        slidenoteDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                HashMap hashMap = (HashMap) dataSnapshot.getValue();
                Iterator iterator = null;

                ArrayList<SlideNoteBean> slidesNoteArrayList = new ArrayList<>();


                if (hashMap != null) {
                    iterator = hashMap.keySet().iterator();

                    while (iterator.hasNext()) {
                        SlideNoteBean slidesNote = new SlideNoteBean();
                        String key = (String) iterator.next();
                        HashMap hashMap1 = (HashMap) hashMap.get(key);
                        slidesNote.setUrl((String) hashMap1.get("Url"));
                        slidesNote.setDesc((String) hashMap1.get("Description"));
                        slidesNote.setTitle((String) hashMap1.get("Title"));
                        slidesNoteArrayList.add(slidesNote);
                    }
                }
                recyclerView.setAdapter(new SlidenoteAdapter(slidesNoteArrayList, Slidenote_list.this));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(this,UploadSlideNote.class);
        startActivity(intent);
    }
}
