package com.pervasive.pear.pear;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
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

/**
 * Created by shubh on 10/23/2018.
 */

public class EditProfile extends AppCompatActivity {

    private TextView name;
    private TextView branch;
    private TextView university;
    private TextView location;
    private TextView email;
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
        mAuth = FirebaseAuth.getInstance();


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
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });



    }
}
