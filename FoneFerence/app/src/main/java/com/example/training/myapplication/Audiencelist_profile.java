package com.example.training.myapplication;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.firebase.client.DataSnapshot;
import static android.provider.Telephony.Carriers.NAME;

public class Audiencelist_profile extends AppCompatActivity {
    FloatingActionButton floatingActionButton1;
    TextView PhoneNumber, fullname, emailid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audiencelist_profile);
        PhoneNumber=findViewById(R.id.PhoneNumber_TextField);
        fullname=findViewById(R.id.fullNameEdit);
        emailid=findViewById(R.id.emaild_textField_CardView);

    }

    public void FAB_AddtoContacts_Click(View v)
    {
        Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
        intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
        intent.putExtra(ContactsContract.Intents.Insert.NAME,fullname.getText().toString())
                .putExtra(ContactsContract.Intents.Insert.PHONETIC_NAME,fullname.getText())
                .putExtra(ContactsContract.Intents.Insert.EMAIL, emailid.getText())
//                .putExtra(ContactsContract.Intents.Insert.EMAIL_TYPE, )
                .putExtra(ContactsContract.Intents.Insert.PHONE, PhoneNumber.getText())
                .putExtra(ContactsContract.Intents.Insert.PHONE_TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE);
        startActivity(intent);

    }
}


