package com.example.okabe.nexttry.settings_Page;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.okabe.nexttry.R;
import com.example.okabe.nexttry.data_base.db_classes.Deliver;
import com.example.okabe.nexttry.main_views.MainView;

import java.util.ArrayList;

public class AddDeliver extends AppCompatActivity {
    private Button deliverConfirm;
    private String deliverName;
    private long deliverPhone;
    private TextView textViewDeliverName;
    private TextView textViewDeliverPhone;
    private ArrayList<Deliver> deliverArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_deliver);

        deliverArrayList = MainView.baseHub.getDeliverArrayList();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Añadir Proveedor");

        textViewDeliverName = (TextView) findViewById(R.id.deliverName);
        textViewDeliverPhone = (TextView) findViewById(R.id.deliverPhone);

        textViewDeliverName.requestFocus();

        deliverConfirm = (Button) findViewById(R.id.deliverConfirm);

        deliverConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (verify()) {
                    addNewDeliver();
                    Toast.makeText(getApplicationContext(), "¡Registro exitoso! :D", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Faltan campos por llenar", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private boolean verify() {
        if ((!textViewDeliverName.getText().toString().equals("")) && (!textViewDeliverPhone.getText().toString().equals(""))) {
            deliverName = textViewDeliverName.getText().toString();
            deliverPhone = Long.parseLong(textViewDeliverPhone.getText().toString());
            return true;
        }
        return false;
    }

    private void clearScreen() {
        textViewDeliverName.setText("");
        textViewDeliverPhone.setText("");
        textViewDeliverName.requestFocus();
    }

    private void addNewDeliver() {
        Deliver deliver = new Deliver(deliverName, deliverPhone);
        MainView.dbHandler.addDeliver(deliver);
        deliver.setDeliverId(MainView.dbHandler.idLastDeliver());
        deliverArrayList.add(deliver);
        clearScreen();
    }
}