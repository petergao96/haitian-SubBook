package com.example.peter.subbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by peter on 2018-02-03.
 */

public class MyAdapter extends ArrayAdapter<Subscription>{
    public MyAdapter(Context context, ArrayList<Subscription> subs){
        super(context, 0, subs);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        // Get the data item for this position
        Subscription subs = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        // Lookup view for data population
        TextView textName = (TextView) convertView.findViewById(R.id.TextName);
        TextView textCharge = (TextView) convertView.findViewById(R.id.TextCharge);
        TextView textComment = (TextView) convertView.findViewById(R.id.TextComment);
        TextView textDate = (TextView) convertView.findViewById(R.id.TextDate);
        // Populate the data into the template view using the data object
        textName.setText(subs.getName());
        textCharge.setText(subs.getCharge());
        textDate.setText(subs.getDate().toString());
        textComment.setText(subs.getComment());

        // Return the completed view to render on screen
        return convertView;
    }


}
