package com.example.training.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

public class UploadCoupon extends Activity {
    private Uri filePath;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    StorageReference Storage;
    DatabaseReference ref = database.getReference("files/Coupon");


    private final int PICK_IMAGE_REQUEST = 71;
    Button bt1;
    EditText mTitle_edittext, mDescription_edittext;
    String mTitle, mDescrption, fileUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_file);
        bt1 = (Button) findViewById(R.id.browse_button);

        Storage = FirebaseStorage.getInstance().getReference();
        mTitle_edittext = findViewById(R.id.Title_text);
        mDescription_edittext = findViewById(R.id.Description_text);

    }


    public void browseClick(View view) {
        {
            Intent intent = new Intent();
            intent.setType("file/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select File"), PICK_IMAGE_REQUEST);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && intent != null && intent.getData() != null) {
            filePath = intent.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void uploadClick(View view) {
        uploadfile();
        mTitle = mTitle_edittext.getText().toString();
        mDescrption = mDescription_edittext.getText().toString();
    }

    private void uploadDetails(String Title, String Descrption, String fileUrl) {

        // FileDetails fileDetails = new FileDetails(Title, Descrption, fileUrl);
        String slideId=ref.push().getKey();

        ref.child(slideId).child("Title").setValue(Title);
        ref.child(slideId).child("Desc").setValue(Descrption);
        ref.child(slideId).child("Url").setValue(fileUrl);

        Toast.makeText(getApplicationContext(), "File uploading..", Toast.LENGTH_LONG).show();

    }


    public void uploadfile() {

        if (filePath != null) {

            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            final StorageReference ref = Storage.child("files/" + UUID.randomUUID().toString());
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {

                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            fileUrl = taskSnapshot.getMetadata().getDownloadUrl().toString();
                            uploadDetails(mTitle, mDescrption, fileUrl);
                            Toast.makeText(getApplicationContext(), "Uploaded", Toast.LENGTH_LONG).show();

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploading" + (int) progress + "%");
                        }
                    });
        }

    }
}
