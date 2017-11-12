package com.example.okabe.nexttry.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.okabe.nexttry.R;
import com.example.okabe.nexttry.data_base.db_classes.Deliver;

import java.util.ArrayList;

public class DeliverArrayAdapter extends ArrayAdapter<Deliver> {

    private ArrayList<Deliver> deliverList = new ArrayList<>();

    public DeliverArrayAdapter(Context context, ArrayList<Deliver> deliver) {
        super(context, 0, deliver);
        deliverList = deliver;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.deliver_list_view, parent, false);
        }

        // Get the data item for this position
        Deliver currentDeliver = deliverList.get(position);

        // Lookup view for data population
        TextView deliverName = convertView.findViewById(R.id.deliverName);
        TextView deliverPhone = convertView.findViewById(R.id.deliverPhone);

        // Populate the data into the template view using the data object
        deliverName.setText(currentDeliver.getDeliverName());
        deliverPhone.setText(String.valueOf(currentDeliver.getDeliverPhone()));

        // Return the completed view to render on screen
        return convertView;
    }

}
