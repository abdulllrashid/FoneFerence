package com.example.training.myapplication;

import android.nfc.Tag;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Certificates_List extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certificates__list_recycle);


        final RecyclerView recyclerView =  findViewById(R.id.cer_recyle);

        final ArrayList<String> cer_array = new ArrayList<String>();


        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference CertDbRef = database.getReference("files/Certificates");

        //Button bi=(Button)findViewById(R.id.btn_cer_delete);


      //  recyclerView.setLayoutManager(new LinearLayoutManager(this));

      /*  CertDbRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    cer_array.add(ds.getValue(String.class));

                }
                recyclerView.setAdapter(new CertificateAdapter(cer_array));

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {


            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(getApplicationContext(), "error cant fetch data" + databaseError, Toast.LENGTH_LONG).show();

            }
        });

*/
    }

}