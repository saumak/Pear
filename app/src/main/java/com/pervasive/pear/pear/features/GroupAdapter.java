package com.pervasive.pear.pear.features;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pervasive.pear.pear.Group;
import com.pervasive.pear.pear.R;
import com.pervasive.pear.pear.User;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shubh on 11/28/2018.
 */

public class GroupAdapter extends ArrayAdapter<Group> {

    private Context mContext;
    private List<Group> groupList = new ArrayList<>();
    public GroupAdapter(@NonNull Context context, @NonNull ArrayList<Group> list) {
        super(context, 0, list);
        mContext = context;
        groupList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;

        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.group_card,parent,false);

        Group currentGroup = groupList.get(position);




        TextView tittle = (TextView) listItem.findViewById(R.id.groups_title);
        TextView desc = (TextView) listItem.findViewById(R.id.groups_desc);

        tittle.setText(currentGroup.getTittle());
        desc.setText(currentGroup.getDesc());

        ImageView img = (ImageView) listItem.findViewById(R.id.group_image);
        Picasso.get().load(currentGroup.getGroupImg()).into(img);





        return  listItem;
    }
}
