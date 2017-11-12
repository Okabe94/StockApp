package com.example.okabe.nexttry.alert_dialog;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.example.okabe.nexttry.R;
import com.example.okabe.nexttry.adapter.SellArrayAdapter;
import com.example.okabe.nexttry.data_base.db_classes.Sell;
import com.example.okabe.nexttry.main_views.MainView;

import java.util.ArrayList;

public class SellConfirmDialog extends DialogFragment {
    private int sellSelected;
    private ArrayList<Sell> sellArrayList = MainView.baseHub.getSellArrayList();
    private SellArrayAdapter arrayAdapter;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Venta")
                .setMessage("¿Confirmar la venta?")
                .setIcon(R.drawable.sell_dialog_image)
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sellItem();
                        arrayAdapter.notifyDataSetChanged();
                        Toast.makeText(getActivity(), "Venta realizada :D", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Canceler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getActivity(), "Cancelado", Toast.LENGTH_SHORT).show();
                        dismiss();
                    }
                });
        return builder.create();
    }

    public void setDeleteSelected(int i) {
        sellSelected = i;
    }

    public void setArrayAdapter(SellArrayAdapter adapter) {
        arrayAdapter = adapter;
    }

    public void sellItem() {
        Sell currentSell = sellArrayList.get(sellSelected);
        MainView.dbHandler.deleteSell(currentSell.getSellId());
        sellArrayList.remove(sellSelected);
    }

}

