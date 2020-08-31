package com.example.workassist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class CustomSiteList extends ArrayAdapter {

    private Activity context;
    private ArrayList<String> siteOwners;
    private ArrayList<String> siteLocations;

    public CustomSiteList(Activity context, ArrayList<String> siteOwners, ArrayList<String> siteLocations) {
        super(context, R.layout.row_item, siteOwners);
        this.context=context;
        this.siteOwners=siteOwners;
        this.siteLocations=siteLocations;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;
        LayoutInflater inflater = context.getLayoutInflater();
        if(convertView==null)
            row = inflater.inflate(R.layout.row_item, null, true);
        TextView siteOwner = (TextView) row.findViewById(R.id.siteOwner);
        TextView siteLocation = (TextView) row.findViewById(R.id.siteLocation);
        ImageView details = (ImageView) row.findViewById(R.id.details);

        siteOwner.setText(siteOwners.get(position));
        siteLocation.setText(siteLocations.get(position));
        details.setImageResource(R.drawable.info);
        details.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                context.startActivity(new Intent(context, DetailsDialog.class));
            }
        });
        return  row;
    }
}
