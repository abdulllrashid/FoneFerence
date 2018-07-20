package com.example.training.myapplication;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Coupon_List extends AppCompatActivity implements View.OnClickListener {

    ArrayList<Coupon> couponArrayList = new ArrayList<>();
    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon__list);


        final RecyclerView couponrecycler = (RecyclerView) findViewById(R.id.coupon_recycle);


        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference CouponDbRef = database.getReference("/files/Coupon");

        FloatingActionButton couponUpload=findViewById(R.id.couponUpload);
        couponUpload.setOnClickListener(this);



        couponrecycler.setLayoutManager(new LinearLayoutManager(this));

        CouponDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    for (DataSnapshot snap : dataSnapshot.getChildren()) {
                        HashMap<String, String> map = (HashMap<String, String>)snap.getValue();
                        Coupon coupon = new Coupon();
                        coupon.setCouponurl((String) map.get("Url"));
                        coupon.setCoupondesc((String) map.get("Desc"));
                        coupon.setCouponTitle((String) map.get("Title"));
                        couponArrayList.add(coupon);
                    }
                    couponrecycler.setAdapter(new CouponAdapter(couponArrayList, Coupon_List.this));
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        /*CouponDbRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                HashMap hashMap = (HashMap) dataSnapshot.getValue();
                Iterator iterator = null;

                ArrayList<Coupon> couponArrayList = new ArrayList<>();


                if (hashMap != null) {
                    iterator = hashMap.keySet().iterator();

    while (iterator.hasNext()) {
        Coupon coupon = new Coupon();
        String key = (String) iterator.next();
        *//*HashMap hashMap1 = (HashMap) hashMap.get(key);*//*
        coupon.setcouponurl((String) hashMap.get("Url"));
        coupon.setcoupondesc((String) hashMap.get("Desc"));
        coupon.setcouponTitle((String) hashMap.get("Title"));
        couponArrayList.add(coupon);
    }

                }
                couponrecycler.setAdapter(new CouponAdapter(couponArrayList, Coupon_List.this));
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

            }
        });*/

    }

    @Override
    public void onClick(View v) {

        Intent intent=new Intent(this,UploadCoupon.class);
        startActivity(intent);

    }
}
