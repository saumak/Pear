package com.pervasive.pear.pear.features;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pervasive.pear.pear.Group;
import com.pervasive.pear.pear.R;
import com.pervasive.pear.pear.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shubh on 11/28/2018.
 */

public class Groups  extends AppCompatActivity implements AdapterView.OnItemClickListener {


    private DatabaseReference grouplistReference;

    ArrayList<Group> groupList = new ArrayList<>();
    EditText search;

    private ListView groupListView;

    private GroupAdapter groupAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.groups);
        grouplistReference = FirebaseDatabase.getInstance().getReference().child("Groups");
        groupListView = (ListView) findViewById(R.id.group_list);


        final DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();

        DatabaseReference groupsdRef = rootRef.child("Groups");

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()) {



                    String tittle = ds.child("tittle").getValue(String.class);
                    String desc = ds.child("desc").getValue(String.class);
                    String groupImg = ds.child("groupImg").getValue(String.class);
                    ArrayList<String> memebers = new ArrayList<>();


                    for (DataSnapshot ds1 : ds.child("members").getChildren()){
                        //Toast.makeText(Groups.this,ds1.getValue().toString(),Toast.LENGTH_LONG).show();
                        memebers.add(ds1.getValue().toString());
                    }
                    Group group = new Group(tittle,desc,groupImg,memebers);

                    groupList.add(group);

                }
                groupListView.invalidateViews();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        groupsdRef.addListenerForSingleValueEvent(eventListener);

        groupAdapter = new GroupAdapter(this,groupList);
        groupListView.setAdapter(groupAdapter);
        groupListView.setOnItemClickListener(this);

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(Groups.this, GroupActivity.class);
        intent.putExtra("Group", (Serializable)groupList.get(position));
        startActivity(intent);

    }
}



