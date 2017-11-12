package com.example.okabe.nexttry.alert_dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.example.okabe.nexttry.R;
import com.example.okabe.nexttry.adapter.DeliverArrayAdapter;
import com.example.okabe.nexttry.data_base.db_classes.DataBaseHub;
import com.example.okabe.nexttry.data_base.db_classes.Deliver;
import com.example.okabe.nexttry.main_views.MainView;

import java.util.ArrayList;

public class DeliverDeleteDialog extends DialogFragment {
    int deleteSelected;
    DeliverArrayAdapter arrayAdapter;
    ArrayList<Deliver> arrayDeliver = DataBaseHub.getDeliverArrayList();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setIcon(R.drawable.warning_image)
                .setTitle("Eliminar")
                .setMessage("¿Confirmar eliminación?")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        deleteItem();
                        arrayAdapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dismiss();
                    }
                });
        return builder.create();
    }

    private void deleteItem() {
        MainView.dbHandler.deleteDeliver(arrayDeliver.get(deleteSelected).getDeliverId());
        arrayDeliver.remove(deleteSelected);
    }

    public void setDeleteSelected(int i) {
        deleteSelected = i;
    }

    public void setArrayAdapter(DeliverArrayAdapter adapter) {
        arrayAdapter = adapter;
    }

}

