package com.example.okabe.nexttry.main_views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.okabe.nexttry.R;
import com.example.okabe.nexttry.adapter.StockArrayAdapter;
import com.example.okabe.nexttry.alert_dialog.AddStockDialog;
import com.example.okabe.nexttry.alert_dialog.StockDeleteDialog;
import com.example.okabe.nexttry.data_base.db_classes.Product;

import java.util.ArrayList;

public class StockPage extends Fragment {

    private StockArrayAdapter adapter;
    private ArrayList<Product> arrayProduct;
    private ListView listView;
    private AddStockDialog addStockDialog;
    private StockDeleteDialog stockDeleteDialog;
    private int layout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        arrayProduct = MainView.baseHub.getProductArrayList();

        layout = getCorrectLayout();

        View view = inflater.inflate(layout, container, false);
        setCorrectView(view);

        return view;
    }

    private void dialogOnClick() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                addStockDialog = new AddStockDialog();
                addStockDialog.setAddSelected(position);
                addStockDialog.show(getFragmentManager(), "Añadir");
                //add to sell or stock
            }
        });
    }

    private void dialogOnLongClick() {
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                stockDeleteDialog = new StockDeleteDialog();
                stockDeleteDialog.setDeleteSelected(position);
                stockDeleteDialog.setArrayAdapter(adapter);
                stockDeleteDialog.show(getFragmentManager(), "Eliminar");
                return true;
            }
        });
    }

    private int getCorrectLayout() {
        if (arrayProduct.isEmpty()) {
            return R.layout.empty_view;
        } else {
            return R.layout.stock_fragment;
        }
    }

    private void setCorrectView(View view) {
        if (layout == R.layout.stock_fragment) {
            //not empty
            listView = view.findViewById(R.id.listView_Stock);
            adapter = new StockArrayAdapter(getContext(), arrayProduct);
            listView.setAdapter(adapter);
            dialogOnClick();
            dialogOnLongClick();
        }
    }

}