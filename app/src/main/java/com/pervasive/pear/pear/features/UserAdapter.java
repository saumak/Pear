package com.pervasive.pear.pear.features;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pervasive.pear.pear.R;
import com.pervasive.pear.pear.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shubh on 11/26/2018.
 */

public class UserAdapter extends ArrayAdapter<User> implements AdapterView.OnItemClickListener{
    private Context mContext;
    private List<User> userList = new ArrayList<>();
    public UserAdapter(@NonNull Context context,@NonNull ArrayList<User> list) {
        super(context, 0, list);
        mContext = context;
        userList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;

        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.user_card,parent,false);

        User currentUser = userList.get(position);




        TextView name = (TextView) listItem.findViewById(R.id.textView_name);
        name.setText(currentUser.getName());






        return  listItem;
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(parent.getContext(),"were now?",Toast.LENGTH_LONG).show();
    }
}
