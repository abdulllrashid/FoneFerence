package com.example.training.myapplication;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class EventRegistrationForm extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    EditText  eventName, descriptioN, venuE, speakerDetails;
    private int mYear, mMonth, mDay, mHour, mMinute;
    ImageButton btnDatePicker, btnTimePicker;
    private DatabaseReference mDatabase;
    public String UID;
    TextView txtDate, txtTime;
    Button btnAddEvent;
    String format;
    public String montH,daY;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_registration_form);

        btnAddEvent = findViewById(R.id.btnAddEvent);
        btnDatePicker = findViewById(R.id.btnDate);
        btnTimePicker = findViewById(R.id.btnTime);
        txtDate = findViewById(R.id.set_date);
        txtTime = findViewById(R.id.set_time);
        eventName = findViewById(R.id.eventName);
        descriptioN = findViewById(R.id.description);
        venuE = findViewById(R.id.venue);
        speakerDetails = findViewById(R.id.speakerDetails);
        mAuth = FirebaseAuth.getInstance();
        UID=mAuth.getCurrentUser().getUid().toString();
        btnAddEvent.setOnClickListener(this);
        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);

        mDatabase = FirebaseDatabase.getInstance().getReference();

    }

    @Override
    public void onClick(View v) {
        if (v == btnDatePicker) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            daY=""+dayOfMonth;
                            if ((monthOfYear+1)==1) {
                                montH ="Jan";
                            }
                            else if((monthOfYear+1)==2) {
                                montH ="Feb";
                            }
                            else if((monthOfYear+1)==3) {
                                montH="Mar";
                            }
                            else if((monthOfYear+1)==4) {
                                montH="Apr";
                            }
                            else if((monthOfYear+1)==5) {
                                montH="May";
                            }
                            else if((monthOfYear+1)==6) {
                                montH="Jun";
                            }
                            else if((monthOfYear+1)==7) {
                                montH="Jul";
                            }
                            else if((monthOfYear+1)==8) {
                                montH="Aug";
                            }
                            else if((monthOfYear+1)==9) {
                                montH="Sep";
                            }
                            else if((monthOfYear+1)==10) {
                                montH="Oct";
                            }
                            else if((monthOfYear+1)==11) {
                                montH=" Nov";
                            }
                            else  {
                                montH="Dec";
                            };
                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == btnTimePicker) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            if (hourOfDay == 0) {

                                hourOfDay += 12;

                                format = "AM";
                            }
                            else if (hourOfDay == 12) {

                                format = "PM";

                            }
                            else if (hourOfDay > 12) {

                                hourOfDay -= 12;

                                format = "PM";

                            }
                            else {

                                format = "AM";
                            }

                            txtTime.setText(hourOfDay + ":" + minute+" "+ format);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }

        if (v == btnAddEvent) {

            final String eventname = eventName.getText().toString();
            final String description = descriptioN.getText().toString();
            final String time = txtTime.getText().toString();
            final String date = txtDate.getText().toString();
            final String venue = venuE.getText().toString();
            final String speakerderails = speakerDetails.getText().toString();
            writeEvents(eventname,description,time,date,venue,speakerderails,daY,montH);
            Toast.makeText(this, "adding events...", Toast.LENGTH_SHORT).show();
            try
            {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
            catch (Exception e)
            {
                Log.d("TAG",e.getMessage());
            }


        }

    }


    private void writeEvents( String eventname, String description, String time,String date, String venue, String speakerdetails,String day,String month) {
        String eventid=mDatabase.push().getKey();

        AddEvents events = new AddEvents(eventname,description,time,date,venue,speakerdetails,day,month);
        mDatabase.child("events").child(eventid).child("details").setValue(events);
        mDatabase.child("Users").child(UID).child("Eventid").setValue(eventid);
    }

}