package com.example.okabe.nexttry.settings_Page;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.okabe.nexttry.R;
import com.example.okabe.nexttry.data_base.db_classes.DataBaseHub;
import com.example.okabe.nexttry.data_base.db_classes.Product;
import com.example.okabe.nexttry.main_views.MainView;

import static com.example.okabe.nexttry.main_views.MainView.dbHandler;

public class AddStock extends AppCompatActivity {
    EditText stockName;
    EditText stockAmount;
    Button stockConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_stock);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Crear Producto");

        stockName = (EditText) findViewById(R.id.stockName);
        stockAmount = (EditText) findViewById(R.id.stockAmount);
        stockConfirm = (Button) findViewById(R.id.stockConfirm);

        stockName.requestFocus();

        stockConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (add()) {
                    Toast.makeText(getApplicationContext(), "¡Registro exitoso! :D", Toast.LENGTH_SHORT).show();
                    clearScreen();
                } else {
                    Toast.makeText(getApplicationContext(), "Faltan campos por llenar", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    private void clearScreen() {
        stockAmount.setText("");
        stockName.setText("");
        stockName.requestFocus();
    }

    private boolean add() {
        String productName = stockName.getText().toString();
        String productAmount = stockAmount.getText().toString();
        int productAmountInt;
        if ((!productName.equals("")) && (!productAmount.equals(""))) {
            productAmountInt = Integer.parseInt(productAmount);
            Product product = new Product(productName, productAmountInt);
            dbHandler.addProduct(product);
            product.setProductId(MainView.dbHandler.idLastProduct());
            DataBaseHub.productArrayList.add(product);
            return true;
        }
        return false;
    }
}
