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
import com.example.okabe.nexttry.adapter.SellArrayAdapter;
import com.example.okabe.nexttry.alert_dialog.DeleteSellDialog;
import com.example.okabe.nexttry.alert_dialog.SellConfirmDialog;
import com.example.okabe.nexttry.data_base.db_classes.Sell;

import java.util.ArrayList;

public class SellPage extends Fragment {
    private ArrayList<Sell> sellArrayList;
    private ListView listView;
    private SellArrayAdapter adapter;
    private SellConfirmDialog sellConfirmDialog;
    private int layout;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        sellArrayList = MainView.baseHub.getSellArrayList();
        layout = getCorrectLayout();

        View view = inflater.inflate(layout, container, false);
        setCorrectView(view);

        return view;
    }

    private int getCorrectLayout() {
        if (sellArrayList.isEmpty()) {
            return R.layout.empty_view;
        } else {
            return R.layout.sell_fragment;
        }
    }

    private void setCorrectView(View view) {
        if (layout == R.layout.sell_fragment) {
            //not empty
            listView = view.findViewById(R.id.listView_sell);
            adapter = new SellArrayAdapter(getContext(), sellArrayList);
            listView.setAdapter(adapter);
            sellProduct();
            returnProduct();
        }
    }

    private void sellProduct() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                sellConfirmDialog = new SellConfirmDialog();
                sellConfirmDialog.setDeleteSelected(position);
                sellConfirmDialog.setArrayAdapter(adapter);
                sellConfirmDialog.show(getFragmentManager(), "Venta");
                //add to sell or stock
            }
        });
    }

    private void returnProduct() {
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                DeleteSellDialog deleteSellDialog = new DeleteSellDialog();
                deleteSellDialog.setArrayAdapter(adapter);
                deleteSellDialog.setSelected(position);
                deleteSellDialog.show(getFragmentManager(), "Eliminar");
                return true;
            }
        });
    }
}

