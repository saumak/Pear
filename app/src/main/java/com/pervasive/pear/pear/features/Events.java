package com.pervasive.pear.pear.features;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pervasive.pear.pear.Event;
import com.pervasive.pear.pear.Group;
import com.pervasive.pear.pear.R;

import java.io.Serializable;
import java.util.ArrayList;


public class Events extends AppCompatActivity {


    private DatabaseReference eventlistReference;

    ArrayList<Event> eventList = new ArrayList<>();

    private ListView eventListView;

    private EventAdapter eventAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.events);
        eventlistReference = FirebaseDatabase.getInstance().getReference().child("Events");
        eventListView = (ListView) findViewById(R.id.event_list);


        final DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();

        DatabaseReference eventsRef = rootRef.child("Events");

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()) {



                    String tittle = ds.child("tittle").getValue(String.class);
                    String desc = ds.child("description").getValue(String.class);
                    String date = ds.child("date").getValue(String.class);
                    String location = ds.child("location").getValue(String.class);



                    Event event = new Event(date,desc,location,tittle);

                    eventList.add(event);

                }
                eventListView.invalidateViews();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        eventsRef.addListenerForSingleValueEvent(eventListener);

        eventAdapter = new EventAdapter(this,eventList);
        eventListView.setAdapter(eventAdapter);


    }



}




