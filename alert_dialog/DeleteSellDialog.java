package com.example.okabe.nexttry.alert_dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.example.okabe.nexttry.R;
import com.example.okabe.nexttry.adapter.SellArrayAdapter;
import com.example.okabe.nexttry.data_base.db_classes.DataBaseHub;
import com.example.okabe.nexttry.data_base.db_classes.Product;
import com.example.okabe.nexttry.data_base.db_classes.Sell;
import com.example.okabe.nexttry.main_views.MainView;

import java.util.ArrayList;

public class DeleteSellDialog extends DialogFragment {
    int selected;
    SellArrayAdapter arrayAdapter;
    ArrayList<Product> productArrayList = DataBaseHub.getProductArrayList();
    ArrayList<Sell> sellArrayList = DataBaseHub.getSellArrayList();

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
        Sell sell = sellArrayList.get(selected);
        int father = sell.getProductId();
        Product product = getFather(productArrayList,father);
        product.setProductAmount(product.getProductAmount() + sell.getProductAmount());
        MainView.dbHandler.updateProduct(product.getProductId(), product.getProductAmount());
        MainView.dbHandler.deleteSell(sell.getSellId());
        sellArrayList.remove(sell);
    }

    public void setSelected(int i) {
        this.selected = i;
    }

    public void setArrayAdapter(SellArrayAdapter adapter) {
        this.arrayAdapter = adapter;
    }

    private Product getFather(ArrayList<Product> products, int id) {
        Product product = null;
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductId() == id) {
                product = products.get(i);
            }
        }
        return product;
    }

}