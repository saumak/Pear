package com.pervasive.pear.pear.features;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.pervasive.pear.pear.EditProfile;
import com.pervasive.pear.pear.Group;
import com.pervasive.pear.pear.LoginActivity;
import com.pervasive.pear.pear.R;
import com.pervasive.pear.pear.User;
import com.squareup.picasso.Picasso;

/**
 * Created by shubh on 11/28/2018.
 */

public class GroupActivity extends AppCompatActivity {
     ImageView img;
     TextView tittle;
     TextView desc;
     ListView ls;
     Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group_activity);
        Intent i = getIntent();

        final Group group = (Group) i.getSerializableExtra("Group");
        Toast.makeText(GroupActivity.this,group.getTittle(),Toast.LENGTH_LONG).show();

//        Group group = (Group) i.getSerializableExtra("Group");
           img = (ImageView) findViewById(R.id.group_img);
         tittle = (TextView) findViewById(R.id.group_title);
          desc = (TextView)findViewById(R.id.g_desc);
          ls=(ListView)findViewById(R.id.group_list_member);
          button= (Button)findViewById(R.id.join);
           Picasso.get().load(group.getGroupImg()).into(img);

        tittle.setText(group.getTittle());
        desc.setText(group.getDesc());
        final ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, group.getMemebrs());
        ls.setAdapter(itemsAdapter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference myRef = database.getReference();
                myRef.child("Users").child(LoginActivity.mAuth.getUid()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {


                        if(group.getMemebrs().contains(snapshot.child("name").getValue().toString())){
                            Toast.makeText(GroupActivity.this,"you are already a member!",Toast.LENGTH_LONG).show();
                        }else{
                            myRef.child("Groups").child("group1").child("members").child(LoginActivity.mAuth.getUid()).setValue(snapshot.child("name").getValue().toString());
                            group.getMemebrs().add(snapshot.child("name").getValue().toString());
                            itemsAdapter.notifyDataSetChanged();
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });


            }
        });

    }


}
