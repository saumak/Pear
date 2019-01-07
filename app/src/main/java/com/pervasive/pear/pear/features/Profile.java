package com.pervasive.pear.pear.features;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pervasive.pear.pear.R;
import com.pervasive.pear.pear.User;

public class Profile extends AppCompatActivity {
    private TextView name;
    private TextView branch;
    private TextView university;
    private TextView location;
    private TextView email;
    private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);
        Intent i = getIntent();

        User user = (User) i.getSerializableExtra("User");
        Toast.makeText(Profile.this,user.getName(),Toast.LENGTH_LONG);

        name = (TextView) findViewById(R.id.profile_name);
        branch = (TextView) findViewById(R.id.profile_branch);
        location = (TextView) findViewById(R.id.profile_location);
        university = (TextView) findViewById(R.id.profile_university);
        email = (TextView) findViewById(R.id.profile_email);
        img = (ImageView) findViewById(R.id.profile_img);
        name.setText(user.getName());
        branch.setText(user.getBranch());
        location.setText(user.getLocation());
        email.setText(user.getEmail());
        university.setText(user.getUniversity());


    }


}
