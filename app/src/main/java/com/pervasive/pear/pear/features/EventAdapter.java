package com.pervasive.pear.pear.features;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.pervasive.pear.pear.Event;
import com.pervasive.pear.pear.R;

import java.util.ArrayList;
import java.util.List;

public class EventAdapter extends ArrayAdapter<Event> {

private Context mContext;
private List<Event> currentList = new ArrayList<>();
public EventAdapter(@NonNull Context context, @NonNull ArrayList<Event> list) {
        super(context, 0, list);
        mContext = context;
        currentList = list;
        }

@NonNull
@Override
public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;

        if(listItem == null)
        listItem = LayoutInflater.from(mContext).inflate(R.layout.event_card,parent,false);

        Event currentEvent = currentList.get(position);




        TextView tittle = (TextView) listItem.findViewById(R.id.event_tittle);
        TextView desc = (TextView) listItem.findViewById(R.id.event_desc);
        TextView date = (TextView) listItem.findViewById(R.id.event_date);
        TextView location = (TextView) listItem.findViewById(R.id.event_location);

        tittle.setText(currentEvent.getTittle());
        desc.setText(currentEvent.getDescription());
        date.setText(currentEvent.getDate());
        location.setText(currentEvent.getLocation());






        return  listItem;
        }
        }

