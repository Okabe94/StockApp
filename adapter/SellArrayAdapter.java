package com.example.okabe.nexttry.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.okabe.nexttry.R;
import com.example.okabe.nexttry.data_base.db_classes.Sell;

import java.util.ArrayList;

public class SellArrayAdapter extends ArrayAdapter<Sell> {

    private ArrayList<Sell> sellList = new ArrayList<>();

    public SellArrayAdapter(Context context, ArrayList<Sell> sell) {
        super(context, 0, sell);
        sellList = sell;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.sell_list_view, parent, false);
        }

        // Get the data item for this position
        Sell currentSell = sellList.get(position);

        // Lookup view for data population
        TextView productName = convertView.findViewById(R.id.sellName);
        TextView productAmount = convertView.findViewById(R.id.sellAmount);

        // Populate the data into the template view using the data object
        productName.setText(currentSell.getProductName());
        productAmount.setText(String.valueOf(currentSell.getProductAmount()));

        // Return the completed view to render on screen
        return convertView;
    }

}