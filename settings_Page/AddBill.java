package com.example.okabe.nexttry.settings_Page;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.okabe.nexttry.R;
import com.example.okabe.nexttry.data_base.db_classes.Bills;
import com.example.okabe.nexttry.main_views.MainView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class AddBill extends AppCompatActivity {
    private EditText billDate;
    private EditText billName;
    private EditText billAmount;
    private Calendar myCalendar;
    private ArrayList<Bills> billsArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bill);

        billsArrayList = MainView.baseHub.getBillsArrayList();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Crear Cuenta de Cobro");

        myCalendar = Calendar.getInstance();
        billDate = (EditText) findViewById(R.id.billDate);

        billName = (EditText) findViewById(R.id.billName);
        billAmount = (EditText) findViewById(R.id.billAmount);
        Button billConfirm = (Button) findViewById(R.id.billConfirm);

        billName.requestFocus();

        billConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (readyToSend(billName, billAmount, billDate)) {
                    addNewBill();
                    Toast.makeText(getApplicationContext(), "¡Registro exitoso! :D", Toast.LENGTH_LONG).show();
                    clearScreen(billName, billAmount, billDate);
                } else {
                    Toast.makeText(getApplicationContext(), "Faltan campos necesarios", Toast.LENGTH_LONG).show();
                }
            }
        });

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel(myCalendar, billDate);
            }
        };

        billDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                DatePickerDialog pickerDialog = new DatePickerDialog(AddBill.this, date, myCalendar.get(Calendar.DAY_OF_MONTH), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.YEAR));
                pickerDialog.getDatePicker().setMinDate(myCalendar.getTimeInMillis() - 1000);
                pickerDialog.show();
            }
        });
    }

    private void updateLabel(Calendar myCalendar, EditText billDate) {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        billDate.setText(sdf.format(myCalendar.getTime()));
    }

    private boolean isNotEmpty(EditText editText) {
        return !editText.getText().toString().isEmpty();
    }

    private boolean readyToSend(EditText billName, EditText billAmount, EditText billDate) {
        if ((isNotEmpty(billAmount) == isNotEmpty(billDate)) && (isNotEmpty(billDate) == isNotEmpty(billName))) {
            if (isNotEmpty(billAmount)) {
                return true;
            }
        }
        return false;
    }

    private void clearScreen(EditText billName, EditText billAmount, EditText billDate) {
        billAmount.setText("");
        billDate.setText("");
        billName.setText("");
        billName.requestFocus();
    }

    private void addNewBill() {
        String bName = billName.getText().toString();
        int bAmount = Integer.parseInt(billAmount.getText().toString());
        String bDate = billDate.getText().toString();
        Bills bill = new Bills(bName, bAmount, bDate);
        MainView.dbHandler.addBill(bill);
        bill.setBillId(MainView.dbHandler.idLastBill());
        billsArrayList.add(bill);
    }
}
