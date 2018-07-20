package com.example.training.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

//import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class User_Registration extends Activity implements AdapterView.OnItemSelectedListener {
    FirebaseAuth mAuth=FirebaseAuth.getInstance();
    EditText email,password,ConfirmPassword,PName,PLocation,PJobDescrption,PPhoneNumber,PMailId;;
    private Uri filePath;
    TextView profileText;
    BaseActivity baseActivity;
    ImageView imageView;
    private final int PICK_IMAGE_REQUEST = 71;
    private StorageReference storage;
    private String UID;
    DatabaseReference profileRef;

    Profile profile;
    FirebaseDatabase database= FirebaseDatabase.getInstance();
    private Spinner PType;
    String Type,Name,Emailid,JobTitle,Location,MobileNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__registration);
        imageView=(ImageView)findViewById(R.id.profile_img_post);
        profileText=(TextView)findViewById(R.id.ProfileImageText);
        email=(EditText)findViewById(R.id.RegistrationScreen_Emailid);
        password=(EditText)findViewById(R.id.RegistrationScreen_Password);
        ConfirmPassword=(EditText)findViewById(R.id.RegistrationScreen_Confirm_Password);
        PName=(EditText)findViewById(R.id.RegistrationScreen_Fullname);
        PLocation=(EditText)findViewById(R.id.RegistrationScreen_Location);
        PJobDescrption=(EditText)findViewById(R.id.RegistrationScreen_JobTitle);
        PPhoneNumber=(EditText)findViewById(R.id.RegistrationScreen_PhoneNumber);
        PMailId=email;
        PType=(Spinner)findViewById(R.id.RegistrationScreen_TypeSpinner);
        profileRef=database.getReference();
        UID="OGwaw6n81teojZLzzsYMvXY7BNo1";

        storage = FirebaseStorage.getInstance().getReference();




        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.UserTypeArray, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        PType.setAdapter(adapter);
        PType.setOnItemSelectedListener(this);


    }

    private void createAccount(String email, String password) {
//        Log.d(TAG, "createAccount:" + email);
        if (!validateForm()) {
            return;
        }



        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    public static final String TAG = "uid error";

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
//                             Sign in success, update UI with the signed-in user's information

                            Toast.makeText(getApplicationContext(), "Authentication Success.",
                                    Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            UID=user.getUid();
                            uploadimage();
                            writeProfileDetails(Emailid, MobileNumber,Type,Location,Name, JobTitle);


//                            updateUI(user);

                        } else {
                            // If sign in fails, display a message to the user.
//                           Log.w(TAG, "createUserWithEmail:failure", task.getException());

                            Toast.makeText(getApplicationContext(), task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
//                            updateUI(null);
                        }

                        // [START_EXCLUDE]
//                        hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
        // [END create_user_with_email]
    }

    private boolean validateForm() {
        boolean valid = true;

        String Email = email.getText().toString();
        if (TextUtils.isEmpty(Email)) {
            email.setError("Required.");
            valid = false;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
            email.setError("Not a Valid EMail id");
        }




        String Password = password.getText().toString();
        String CPassword=ConfirmPassword.getText().toString();
        if (TextUtils.isEmpty(Password)) {
            password.setError("Required.");
            valid = false;
        }else if(Password.length()<8)
        {
            password.setError("Minimum 8 Charecters");
        }

        else if (!Password.equals(CPassword)) {
            ConfirmPassword.setError("Password Not Matching");

        }

        return valid;
    }

    public void Reg_Submit(View view) {

        Name = PName.getText().toString();
        Emailid = PMailId.getText().toString();
        JobTitle = PJobDescrption.getText().toString();
        Location = PLocation.getText().toString();
        MobileNumber = PPhoneNumber.getText().toString();
        createAccount(email.getText().toString(),password.getText().toString());


    }

    private void uploadimage() {

        if(filePath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            StorageReference ref = storage.child("images/"+ UID);
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Uploaded", Toast.LENGTH_SHORT).show();
                            mAuth.signOut();
                            startActivity(new Intent(getApplicationContext(),SigninScreen.class));

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
        }
    }

    public void ProfileImageClick(View view) {
            pickimage();
    }

    private void pickimage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"),PICK_IMAGE_REQUEST );
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);
                profileText.setText("");

            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    private void writeProfileDetails(String Emailid, String MobileNumber,String Type,String Location,String Name, String JobTitle) {

        Profile profile = new Profile(Emailid,MobileNumber,Type,Location,Name,JobTitle);
        profileRef.child("Users").child(UID).setValue(profile);
    }


    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        if(parent.getSelectedItemPosition()==0)
        {
            Toast.makeText(getApplicationContext(),"Enter a userType",Toast.LENGTH_LONG);
        }
       Type=parent.getSelectedItem().toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
