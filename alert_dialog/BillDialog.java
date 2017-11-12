package com.example.okabe.nexttry.alert_dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.example.okabe.nexttry.R;
import com.example.okabe.nexttry.adapter.BillArrayAdapter;
import com.example.okabe.nexttry.data_base.db_classes.Bills;
import com.example.okabe.nexttry.data_base.db_classes.DataBaseHub;
import com.example.okabe.nexttry.main_views.MainView;

import java.util.ArrayList;

public class BillDialog extends DialogFragment {

    private int position;
    private ArrayList<Bills> billsArrayList = DataBaseHub.getBillsArrayList();
    private String title = "";
    private String message = "";
    private BillArrayAdapter adapter;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title)
                .setMessage(message)
                .setIcon(R.drawable.warning_image)
                .setNegativeButton("Cancelar", null)
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int i) {
                        deleteBill(position);
                        adapter.notifyDataSetChanged();
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

    public void setPosition(int i) {
        this.position = i;
    }

    public void setAdapter(BillArrayAdapter a) {
        this.adapter = a;
    }

    public void setTitle(String t) {
        this.title = t;
    }

    public void setMessage(String t) {
        this.message = t;
    }

    private void deleteBill(int position) {
        Bills bill = billsArrayList.get(position);
        MainView.dbHandler.deleteBill(bill.getBillId());
        billsArrayList.remove(bill);
    }
}