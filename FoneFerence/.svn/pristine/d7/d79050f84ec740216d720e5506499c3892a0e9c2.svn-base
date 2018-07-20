package com.example.training.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


//import com.bumptech.glide.Glide;
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ProfileFragment extends Fragment {


    FirebaseDatabase database;
    FirebaseAuth mAuth;
    public String UID;
    DatabaseReference profileRef;
    TextView PName,PLocation,PJobDescrption,PPhoneNumber,PMailId;




    ImageView image;


    @SuppressLint("NewApi")
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_profile, null);
        database= FirebaseDatabase.getInstance();
        mAuth= FirebaseAuth.getInstance();
        UID=mAuth.getCurrentUser().getUid();
        profileRef=database.getReference("Users/"+UID);
        PName=(TextView) v.findViewById(R.id.fullNameEditText);
        PLocation=(TextView) v.findViewById(R.id.Location_TextField_CardView);
        PJobDescrption=(TextView) v.findViewById(R.id.JobTitleTextFieldCard);
        PPhoneNumber=(TextView) v.findViewById(R.id.PhoneNumber_TextField_CardView);
        PMailId=(TextView) v.findViewById(R.id.Maild_textField_CardView);
        image = (ImageView)v.findViewById(R.id.profile_img_post);






// Load the image using Glide


        profileRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

               Profile profile=dataSnapshot.getValue(Profile.class);
                PName.setText(profile.getName());
                PJobDescrption.setText(profile.getJobTitle());
                PLocation.setText(profile.getLocation());
                PPhoneNumber.setText(profile.getMobileNumber());
                PMailId.setText(profile.getEmailid());
                String userId=dataSnapshot.getKey();
                String imageURL="https://firebasestorage.googleapis.com/v0/b/foneferenca.appspot.com/o/images%2F"+userId+"?alt=media&token=30568345-5f2e-40eb-9edd-44cc42787204";
                Glide.with(getContext())
                        .load(imageURL)
                        .into(image);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
        return v;

    }
}
