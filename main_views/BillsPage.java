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
import com.example.okabe.nexttry.adapter.BillArrayAdapter;
import com.example.okabe.nexttry.alert_dialog.BillDialog;
import com.example.okabe.nexttry.data_base.db_classes.Bills;

import java.util.ArrayList;

public class BillsPage extends Fragment {

    private ListView listView;
    private ArrayList<Bills> billsArrayList;
    private int intLayout;
    private BillArrayAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        billsArrayList = MainView.baseHub.getBillsArrayList();

        intLayout = getCorrectLayout();
        View view = inflater.inflate(intLayout, container, false);
        setCorrectView(view);

        return view;
    }

    private int getCorrectLayout() {
        if (billsArrayList.isEmpty()) {
            return R.layout.empty_view;
        } else {
            return R.layout.bills_fragment;
        }
    }

    private void setCorrectView(View view) {
        if (intLayout == R.layout.bills_fragment) {
            //not empty
            listView = view.findViewById(R.id.listView_bill);
            adapter = new BillArrayAdapter(getContext(), billsArrayList);
            listView.setAdapter(adapter);
            dialogOnClick();
            dialogOnLongClick();
        }
    }

    private void dialogOnClick() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                BillDialog billDialog = new BillDialog();
                billDialog.setTitle("Cobro");
                billDialog.setMessage("¿Confirmar cobro de cuenta?");
                billDialog.setAdapter(adapter);
                billDialog.setPosition(position);
                billDialog.show(getFragmentManager(), "Cobro");
            }
        });
    }

    private void dialogOnLongClick() {
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                BillDialog billDialog = new BillDialog();
                billDialog.setTitle("Eliminar");
                billDialog.setMessage("¿Confirmar eliminación?");
                billDialog.setPosition(position);
                billDialog.setAdapter(adapter);
                billDialog.show(getFragmentManager(), "Eliminar");
                return true;
            }
        });
    }

}