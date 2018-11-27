package com.pervasive.pear.pear.features;

import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.pervasive.pear.pear.R;

/**
 * Created by shubh on 11/27/2018.
 */

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
        name = (TextView) findViewById(R.id.profile_name);
        branch = (TextView) findViewById(R.id.profile_branch);
        location = (TextView) findViewById(R.id.profile_location);
        university = (TextView) findViewById(R.id.profile_university);
        email = (TextView) findViewById(R.id.profile_email);
        img = (ImageView) findViewById(R.id.profile_img);




    }


}
