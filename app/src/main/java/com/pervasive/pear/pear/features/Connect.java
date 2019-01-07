package com.pervasive.pear.pear.features;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pervasive.pear.pear.DashBoard;
import com.pervasive.pear.pear.EditProfile;
import com.pervasive.pear.pear.LoginActivity;
import com.pervasive.pear.pear.R;
import com.pervasive.pear.pear.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Connect extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private static final String TAG = "UserList" ;
    private DatabaseReference userlistReference;
    private ValueEventListener mUserListListener;
    ArrayList<User> userList = new ArrayList<>();
    EditText search;

   private ListView userListView;

    private UserAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connect);
        userlistReference = FirebaseDatabase.getInstance().getReference().child("Users");
        userListView = (ListView) findViewById(R.id.connect_list);
        search = (EditText) findViewById(R.id.search_connect);

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();

        DatabaseReference usersdRef = rootRef.child("Users");

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    String name = ds.child("name").getValue(String.class);
                    String branch = ds.child("branch").getValue(String.class);
                    String location = ds.child("location").getValue(String.class);
                    String pic = ds.child("pic").getValue(String.class);
                    String email = ds.child("email").getValue(String.class);
                    String university = ds.child("university").getValue(String.class);
                    User user = new User(branch,email,location,name, pic,university);

                    userList.add(user);


                }



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        usersdRef.addListenerForSingleValueEvent(eventListener);

        mAdapter = new UserAdapter(this,userList);
        userListView.setAdapter(mAdapter);
        userListView.setOnItemClickListener(this);

       }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


        Intent intent = new Intent(Connect.this, Profile.class);
        intent.putExtra("User", (Serializable)userList.get(position));
        startActivity(intent);

    }
}



