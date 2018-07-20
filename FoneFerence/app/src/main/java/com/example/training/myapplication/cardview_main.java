package com.example.training.myapplication;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class cardview_main extends AppCompatActivity implements View.OnClickListener {
    private TextView textView;
    private ImageButton imageButton1,imageButton2,imageButton3,imageButton4;
    private RecyclerView.ViewHolder ViewHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardview_main);
        imageButton1 = (ImageButton) findViewById(R.id.certificates);
        imageButton2 = (ImageButton) findViewById(R.id.coupons);
        imageButton3 = (ImageButton) findViewById(R.id.notes);
        imageButton4 = (ImageButton) findViewById(R.id.slides);
        //RecyclerView rec = (RecyclerView) findViewById(R.id.mainrecycle);
        imageButton1.setOnClickListener(this);
        imageButton2.setOnClickListener(this);
        imageButton3.setOnClickListener(this);
        imageButton4.setOnClickListener(this);
    }


   @Override
   public void onClick(View v) {
//        if (v.getId() == R.id.certificates) {
//            Intent intent1 = new Intent(this,Certificates_List.class);
//            Toast.makeText(getApplicationContext(), "certificate list selected ", Toast.LENGTH_SHORT).show();
//            startActivity(intent1);
//        }
//        else if (v.getId() == R.id.coupons) {
//            Intent intent2 = new Intent(this,Coupon_List.class);
//            Toast.makeText(getApplicationContext(), "coupon list selected", Toast.LENGTH_SHORT).show();
//            startActivity(intent2);
//        }
//        else if (v.getId() == R.id.notes) {
//            Intent intent3 = new Intent(this, Slidenote_list.class);
//            Toast.makeText(getApplicationContext(), "slide note selected", Toast.LENGTH_SHORT).show();
//            startActivity(intent3);
//        }
//        else if (v.getId() == R.id.slides) {
//            Intent intent4 = new Intent(this, Slide_list.class);
//            Toast.makeText(getApplicationContext(), "slide list selected", Toast.LENGTH_SHORT).show();
//            startActivity(intent4);
//        }
//
//
//
   }
}














