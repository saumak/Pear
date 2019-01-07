package com.pervasive.pear.pear;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.pervasive.pear.pear.features.Connect;
import com.pervasive.pear.pear.features.Events;
import com.pervasive.pear.pear.features.Groups;


public class DashBoard extends AppCompatActivity  implements View.OnClickListener {

    private CardView connect;
    private CardView groups;
    private CardView events;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
        connect = findViewById(R.id.connect);
        connect.setOnClickListener(this);
        groups = findViewById(R.id.groups);
        groups.setOnClickListener(this);
        events = findViewById(R.id.Event);
        events.setOnClickListener(this);
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
            case R.id.Event:

                Intent intent2 = new Intent(DashBoard.this, Events.class);
                startActivity(intent2);
                break;

            default:
                break;
        }

    }
}
