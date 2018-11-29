package com.pervasive.pear.pear;

import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

/**
 * Created by shubh on 10/23/2018.
 */

public class EditProfile extends AppCompatActivity {

    private TextView name;
    private TextView branch;
    private TextView university;
    private TextView location;
    private TextView email;
    private ImageView img;
    private FirebaseAuth mAuth;

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);
        name = (TextView) findViewById(R.id.name);
        branch = (TextView) findViewById(R.id.branch);
        location = (TextView) findViewById(R.id.location);
        university = (TextView) findViewById(R.id.university);
        email = (TextView) findViewById(R.id.email);
        img = (ImageView) findViewById(R.id.img);
        mAuth = FirebaseAuth.getInstance();
        // Create a storage reference from our app

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        myRef.child("Users").child(mAuth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                name.setText(snapshot.child("name").getValue().toString());
                branch.setText(snapshot.child("branch").getValue().toString());
                location.setText(snapshot.child("location").getValue().toString());
                email.setText(snapshot.child("email").getValue().toString());
                university.setText(snapshot.child("university").getValue().toString());
                FirebaseStorage storage = FirebaseStorage.getInstance();
                StorageReference storageRef = storage.getReference();
                StorageReference pathReference = storageRef.child(snapshot.child("name").getValue().toString());
                pathReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        // Got the download URL for 'users/me/profile.png'
                        Picasso.get().load(uri.toString()).into(img);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Toast.makeText(EditProfile.this,exception.toString(),Toast.LENGTH_LONG).show();
                    }
                });

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });



    }
}
