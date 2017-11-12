package com.example.okabe.nexttry.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.okabe.nexttry.R;
import com.example.okabe.nexttry.data_base.db_classes.Bills;

import java.util.ArrayList;

public class BillArrayAdapter extends ArrayAdapter<Bills> {

    private ArrayList<Bills> billsArrayList = new ArrayList<>();

    public BillArrayAdapter(Context context, ArrayList<Bills> billses) {
        super(context, 0, billses);
        billsArrayList = billses;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.bill_list_view, parent, false);
        }

        // Get the data item for this position
        Bills currentBill = billsArrayList.get(position);

        // Lookup view for data population
        TextView bName = convertView.findViewById(R.id.BillName);
        TextView bAmount = convertView.findViewById(R.id.BillAmount);
        TextView bDate = convertView.findViewById(R.id.BillDate);

        // Populate the data into the template view using the data object
        bName.setText(currentBill.getBillName());
        bAmount.setText(String.valueOf(currentBill.getDebtAmount()));
        bDate.setText(currentBill.getDebtDate());

        // Return the completed view to render on screen
        return convertView;
    }

}