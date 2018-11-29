package com.pervasive.pear.pear;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.pervasive.pear.pear.features.Connect;
import com.pervasive.pear.pear.features.Groups;

/**
 * Created by shubh on 11/12/2018.
 */

public class DashBoard extends AppCompatActivity  implements View.OnClickListener {

    private CardView connect;
    private CardView groups;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
        connect = findViewById(R.id.connect);
        connect.setOnClickListener(this);
        groups = findViewById(R.id.groups);
        groups.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.connect:

                Intent intent = new Intent(DashBoard.this, Connect.class);
                startActivity(intent);
                break;

            case R.id.groups:

                Intent intent1 = new Intent(DashBoard.this, Groups.class);
                startActivity(intent1);
                break;


            default:
                break;
        }

    }
}
