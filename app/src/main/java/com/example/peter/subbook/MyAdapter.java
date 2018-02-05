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

        Subscription subs = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        TextView textName = (TextView) convertView.findViewById(R.id.TextName);
        TextView textCharge = (TextView) convertView.findViewById(R.id.TextCharge);
        TextView textComment = (TextView) convertView.findViewById(R.id.TextComment);
        TextView textDate = (TextView) convertView.findViewById(R.id.TextDate);

        textName.setText("Name: " +subs.getName());
        textCharge.setText("Charge: $"+subs.getCharge());
        textDate.setText("Date Started: "+subs.getFormDate());
        textComment.setText("Comments: "+subs.getComment());

        return convertView;
    }


}
