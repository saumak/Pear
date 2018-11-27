package com.pervasive.pear.pear;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.pervasive.pear.pear.features.Connect;

/**
 * Created by shubh on 11/12/2018.
 */

public class DashBoard extends AppCompatActivity  implements View.OnClickListener {

    private CardView connect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
        connect = findViewById(R.id.connect);
        connect.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.connect:
                Toast.makeText(DashBoard.this,"now what?!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(DashBoard.this, Connect.class);
                startActivity(intent);
                break;



            default:
                break;
        }

    }
}
