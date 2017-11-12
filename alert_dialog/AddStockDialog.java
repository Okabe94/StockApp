package com.example.okabe.nexttry.alert_dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.EditText;
import android.widget.Toast;

import com.example.okabe.nexttry.R;
import com.example.okabe.nexttry.data_base.db_classes.DataBaseHub;
import com.example.okabe.nexttry.data_base.db_classes.Product;
import com.example.okabe.nexttry.data_base.db_classes.Sell;
import com.example.okabe.nexttry.main_views.MainView;

import java.util.ArrayList;

public class AddStockDialog extends DialogFragment {
    int addSelected;
    ArrayList<Product> arrayProduct = DataBaseHub.getProductArrayList();
    ArrayList<Sell> arraySell = DataBaseHub.getSellArrayList();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Adicionar Producto a...")
                .setIcon(R.drawable.dialog_add_image)
                .setView(R.layout.add_option_dialog)
                .setNeutralButton("Cancelar", null)

                .setNegativeButton("Inventario", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Dialog d = (Dialog) dialog;
                        EditText dialogAmount = d.findViewById(R.id.dialog_amount);

                        if (addStockDialog(dialogAmount)) {
                            Toast.makeText(getActivity(), "Modificación completada", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "Cantidad invalida", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setPositiveButton("Ventas", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int i) {
                        Dialog d = (Dialog) dialog;
                        EditText dialogAmount = d.findViewById(R.id.dialog_amount);
                        if (addToSellDialog(dialogAmount)) {
                            Toast.makeText(getContext(), "Llevado a ventas", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Cantidad invalida", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

    public boolean addStockDialog(EditText amount) {
        String number = amount.getText().toString();
        Product product = arrayProduct.get(addSelected);
        if (number.equals("")) {
            return false;
        }
        int newAmount = Integer.parseInt(number);
        product.setProductAmount(newAmount);
        arrayProduct.set(addSelected, product);
        MainView.dbHandler.updateProduct(product.getProductId(), newAmount);
        return true;
    }

    public boolean addToSellDialog(EditText dialogAmount) {
        String text = dialogAmount.getText().toString();
        if (!text.equals("")) {
            int amount = Integer.parseInt(text);
            Product selected = arrayProduct.get(addSelected);
            if (amount <= selected.getProductAmount() && amount > 0) {
                selected.setProductAmount(selected.getProductAmount() - amount);
                MainView.dbHandler.updateProduct(selected.getProductId(), selected.getProductAmount());
                Sell sell = new Sell(selected.getProductId(), selected.getProductName(), amount);
                MainView.dbHandler.addSell(sell);
                sell.setSellId(MainView.dbHandler.idLastSell());
                arraySell.add(sell);
                return true;
            }
        }
        return false;
    }

    public void setAddSelected(int i) {
        addSelected = i;
    }
}
