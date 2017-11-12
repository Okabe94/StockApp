package com.example.okabe.nexttry.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.okabe.nexttry.R;
import com.example.okabe.nexttry.data_base.db_classes.Product;

import java.util.ArrayList;

public class StockArrayAdapter extends ArrayAdapter<Product> {

    private ArrayList<Product> productsList = new ArrayList<>();

    public StockArrayAdapter(Context context, ArrayList<Product> products) {
        super(context, 0, products);
        productsList = products;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.stock_list_view, parent, false);
        }

        // Get the data item for this position
        Product currentProduct = productsList.get(position);

        // Lookup view for data population
        TextView productName = convertView.findViewById(R.id.productName);
        TextView productAmount = convertView.findViewById(R.id.productAmount);

        // Populate the data into the template view using the data object
        productName.setText(currentProduct.getProductName());
        productAmount.setText(String.valueOf(currentProduct.getProductAmount()));
        // Return the completed view to render on screen
        return convertView;
    }

}