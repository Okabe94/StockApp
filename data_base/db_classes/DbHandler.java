package com.example.okabe.nexttry.data_base.db_classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DbHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Inventario.db";

    private static final String TABLE_PRODUCT = "PRODUCT";
    private static final String COLUMN_PRODUCT_ID = "PRODUCT_ID";
    private static final String COLUMN_PRODUCT_NAME = "PRODUCT_NAME";
    private static final String COLUMN_PRODUCT_AMOUNT = "PRODUCT_AMOUNT";

    private static final String TABLE_SELL = "SELL";
    private static final String COLUMN_SELL_ID = "SELL_ID";
    private static final String COLUMN_SELL_NAME = "SELL_NAME";
    private static final String COLUMN_SELL_AMOUNT = "SELL_AMOUNT";
    private static final String COLUMN_SELL_PRODUCT_ID = "SELL_PRODUCT_ID";

    private static final String TABLE_DELIVER = "DELIVER";
    private static final String COLUMN_DELIVER_ID = "DELIVER_ID";
    private static final String COLUMN_DELIVER_NAME = "DELIVER_NAME";
    private static final String COLUMN_DELIVER_PHONE = "DELIVER_PHONE";

    private static final String TABLE_BILL = "BILL";
    private static final String COLUMN_BILL_ID = "BILL_ID";
    private static final String COLUMN_BILL_NAME = "BILL_NAME";
    private static final String COLUMN_BILL_AMOUNT = "BILL_AMOUNT";
    private static final String COLUMN_BILL_DATE = "BILL_DATE";


    public DbHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String productQuery = "CREATE TABLE " + TABLE_PRODUCT + "(" +
                COLUMN_PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PRODUCT_NAME + " TEXT NOT NULL, " +
                COLUMN_PRODUCT_AMOUNT + " INTEGER NOT NULL);";

        String sellQuery = "CREATE TABLE " + TABLE_SELL + "(" +
                COLUMN_SELL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_SELL_NAME + " TEXT NOT NULL, " +
                COLUMN_SELL_AMOUNT + " INTEGER NOT NULL," +
                COLUMN_SELL_PRODUCT_ID + " INT NOT NULL);";

        String deliverQuery = "CREATE TABLE " + TABLE_DELIVER + "(" +
                COLUMN_DELIVER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_DELIVER_NAME + " TEXT NOT NULL, " +
                COLUMN_DELIVER_PHONE + " INTEGER UNSIGNED NOT NULL);";

        String billQuery = "CREATE TABLE " + TABLE_BILL + "(" +
                COLUMN_BILL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_BILL_NAME + " TEXT NOT NULL, " +
                COLUMN_BILL_AMOUNT + " INTEGER NOT NULL, " +
                COLUMN_BILL_DATE + " TEXT NOT NULL);";

        db.execSQL(productQuery);
        db.execSQL(sellQuery);
        db.execSQL(deliverQuery);
        db.execSQL(billQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SELL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DELIVER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BILL);
        onCreate(db);
    }

//    ADD ROWS

    public void addProduct(Product product) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCT_NAME, product.getProductName());
        db.insert(TABLE_PRODUCT, null, values);
        values.put(COLUMN_PRODUCT_AMOUNT, product.getProductAmount());
        db.insert(TABLE_PRODUCT, null, values);
        db.close();
    }

    public void addSell(Sell sell) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SELL_NAME, sell.getProductName());
        db.insert(TABLE_SELL, null, values);
        values.put(COLUMN_SELL_AMOUNT, sell.getProductAmount());
        db.insert(TABLE_SELL, null, values);
        values.put(COLUMN_SELL_PRODUCT_ID, sell.getProductId());
        db.insert(TABLE_SELL, null, values);
        db.close();
    }

    public void addDeliver(Deliver deliver) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DELIVER_NAME, deliver.getDeliverName());
        db.insert(TABLE_DELIVER, null, values);
        values.put(COLUMN_DELIVER_PHONE, deliver.getDeliverPhone());
        db.insert(TABLE_DELIVER, null, values);
        db.close();
    }

    public void addBill(Bills bills) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_BILL_NAME, bills.getBillName());
        db.insert(TABLE_BILL, null, values);
        values.put(COLUMN_BILL_AMOUNT, bills.getDebtAmount());
        db.insert(TABLE_BILL, null, values);
        values.put(COLUMN_BILL_DATE, bills.getDebtDate());
        db.insert(TABLE_BILL, null, values);
        db.close();
    }

//    DELETE ROWS

    public void deleteProduct(int productId) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_SELL + " WHERE " + COLUMN_SELL_PRODUCT_ID + " = " + productId + ";");
        db.execSQL("DELETE FROM " + TABLE_PRODUCT + " WHERE " + COLUMN_PRODUCT_ID + " = " + productId + ";");
    }

    public void deleteSell(int sellId) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_SELL + " WHERE " + COLUMN_SELL_ID + " = " + sellId + ";");
    }

    public void deleteDeliver(int deliverId) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_DELIVER + " WHERE " + COLUMN_DELIVER_ID + " = " + deliverId + ";");
    }

    public void deleteBill(int billId) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_BILL + " WHERE " + COLUMN_BILL_ID + " = " + billId + ";");
    }

//    DB to arrayList

    public ArrayList<Product> productDBToArray() {

        ArrayList<Product> arrayProduct = new ArrayList<>();
        Product product;
        String productName = "";
        int productAmount = 0;
        int productId;

        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_PRODUCT + " WHERE 1";

        //cursor
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {

            productId = Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_ID)));

            if (cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_NAME)) != null) {
                productName = cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_NAME));
            }
            if (cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_AMOUNT)) != null) {
                productAmount = Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_AMOUNT)));
            }
            product = new Product(productName, productAmount, productId);
            arrayProduct.add(product);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return arrayProduct;
    }

    public ArrayList<Sell> sellDBToArray() {
        ArrayList<Sell> arraySell = new ArrayList<>();
        Sell sell;
        String productName = "";
        int productAmount = 0;
        int sellId, productId;

        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_SELL + " WHERE 1";

        //cursor
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            sellId = Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_SELL_ID)));
            productId = Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_SELL_PRODUCT_ID)));

            if (cursor.getString(cursor.getColumnIndex(COLUMN_SELL_NAME)) != null) {
                productName = cursor.getString(cursor.getColumnIndex(COLUMN_SELL_NAME));
            }
            if (cursor.getString(cursor.getColumnIndex(COLUMN_SELL_AMOUNT)) != null) {
                productAmount = Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_SELL_AMOUNT)));
            }
            sell = new Sell(productId, productName, productAmount, sellId);
            arraySell.add(sell);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();

        return arraySell;
    }

    public ArrayList<Deliver> deliverDBToArray() {

        ArrayList<Deliver> arrayDeliver = new ArrayList<>();
        Deliver deliver;
        String deliverName = "";
        int deliverId;
        long deliverPhone = 0;

        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_DELIVER + " WHERE 1";

        //cursor
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            deliverId = Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_DELIVER_ID)));

            if (cursor.getString(cursor.getColumnIndex(COLUMN_DELIVER_NAME)) != null) {
                deliverName = cursor.getString(cursor.getColumnIndex(COLUMN_DELIVER_NAME));
            }
            if (cursor.getString(cursor.getColumnIndex(COLUMN_DELIVER_PHONE)) != null) {
                deliverPhone = Long.parseLong(cursor.getString(cursor.getColumnIndex(COLUMN_DELIVER_PHONE)));
            }
            deliver = new Deliver(deliverName, deliverPhone, deliverId);
            arrayDeliver.add(deliver);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return arrayDeliver;
    }

    public ArrayList<Bills> billDBToArray() {

        ArrayList<Bills> arrayBills = new ArrayList<>();
        Bills bill;
        String billName = "";
        int billAmount = 0;
        int billId;
        String billDate = "";

        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_BILL + " WHERE 1";

        //cursor
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            billId = Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_BILL_ID)));

            if (cursor.getString(cursor.getColumnIndex(COLUMN_BILL_NAME)) != null) {
                billName = cursor.getString(cursor.getColumnIndex(COLUMN_BILL_NAME));
            }
            if (cursor.getString(cursor.getColumnIndex(COLUMN_BILL_AMOUNT)) != null) {
                billAmount = Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_BILL_AMOUNT)));
            }
            if (cursor.getString(cursor.getColumnIndex(COLUMN_BILL_DATE)) != null) {
                billDate = cursor.getString(cursor.getColumnIndex(COLUMN_BILL_DATE));
            }
            bill = new Bills(billName, billAmount, billDate, billId);
            arrayBills.add(bill);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return arrayBills;
    }

//    UPDATES

    public void updateProduct(int productId, int productAmount) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE " + TABLE_PRODUCT + " SET " + COLUMN_PRODUCT_AMOUNT + " = " + productAmount +
                " WHERE " + COLUMN_PRODUCT_ID + " = " + productId + ";");
    }

    public int idLastProduct() {
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_PRODUCT + " WHERE 1";
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToLast();
        int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_ID)));
        cursor.close();
        db.close();
        return id;
    }

    public int idLastSell() {
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_SELL + " WHERE 1";
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToLast();
        int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_SELL_ID)));
        cursor.close();
        db.close();
        return id;
    }

    public int idLastDeliver() {
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_DELIVER + " WHERE 1";
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToLast();
        int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_DELIVER_ID)));
        cursor.close();
        db.close();
        return id;
    }

    public int idLastBill() {
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_BILL + " WHERE 1";
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToLast();
        int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_BILL_ID)));
        cursor.close();
        db.close();
        return id;
    }
}
