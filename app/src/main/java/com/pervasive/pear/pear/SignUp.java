package com.pervasive.pear.pear;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

/**
 * Created by shubh on 10/24/2018.
 */

public class SignUp extends AppCompatActivity {
    private Button mSign_up;
    private EditText name;
    private EditText branch;
    private EditText university;
    private EditText location;
    private EditText email;
    private EditText password;
    private Button mUpload;
    private Task<Uri> profile_pic;
    private FirebaseAuth mAuth;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        name = (EditText)findViewById(R.id.reg_name);
        name = (EditText)findViewById(R.id.reg_name);
        branch = (EditText)findViewById(R.id.reg_branch);
        university = (EditText)findViewById(R.id.reg_university);
        location = (EditText)findViewById(R.id.reg_location);
        email = (EditText)findViewById(R.id.reg_email);
        password = (EditText)findViewById(R.id.reg_password);
        mSign_up = (Button)findViewById(R.id.sign_up);
        mUpload = (Button)findViewById(R.id.upload_pic);
        mAuth = FirebaseAuth.getInstance();

        mSign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String strEmail = email.getText().toString();
                final String strPass = password.getText().toString();
                final String strBranch = branch.getText().toString();
                final String strName = name.getText().toString();
                final String strUniv = university.getText().toString();
                final String strLocation = location.getText().toString();
                signUp(strEmail,strPass,strBranch,strName,strUniv,strLocation);
            }
        });
        mUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });
    }
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] img = baos.toByteArray();
            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storageRef = storage.getReference();
            final StorageReference imagesRef = storageRef.child(name.getText().toString());
            final UploadTask uploadTask = imagesRef.putBytes(img);
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle unsuccessful uploads
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                    // ...
                    Toast.makeText(SignUp.this,"image uploaded",Toast.LENGTH_LONG);
                    profile_pic=imagesRef.getDownloadUrl();

                }
            });


        }
    }
    private void signUp(final String email, String password, final String branch, final String strName, final String univ, final String strLocation){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference myRef = database.getReference();
                            FirebaseUser user = mAuth.getCurrentUser();
                            myRef.child("Users").child(user.getUid()).child("branch").setValue(branch);
                            myRef.child("Users").child(user.getUid()).child("name").setValue(strName);
                            myRef.child("Users").child(user.getUid()).child("location").setValue(strLocation);
                            myRef.child("Users").child(user.getUid()).child("email").setValue(email);
                            myRef.child("Users").child(user.getUid()).child("university").setValue(univ);
                            myRef.child("Users").child(user.getUid()).child("pic").setValue(profile_pic.toString());
                            Toast.makeText(SignUp.this, user.getUid(),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(SignUp.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }
}
