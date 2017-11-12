package com.example.okabe.nexttry.data_base.db_classes;


import com.example.okabe.nexttry.main_views.MainView;

import java.util.ArrayList;

public class DataBaseHub {

    public static ArrayList<Product> productArrayList = new ArrayList<>();
    public static ArrayList<Sell> sellArrayList = new ArrayList<>();
    public static ArrayList<Deliver> deliverArrayList = new ArrayList<>();
    public static ArrayList<Bills> billsArrayList = new ArrayList<>();

    public DataBaseHub() {
        ArrayList<Product> arrayListProduct = MainView.dbHandler.productDBToArray();
        if (arrayListProduct != null) {
            productArrayList = arrayListProduct;
        }
        ArrayList<Sell> arrayListSell = MainView.dbHandler.sellDBToArray();
        if (arrayListSell != null) {
            sellArrayList = arrayListSell;
        }
        ArrayList<Deliver> arrayListDeliver = MainView.dbHandler.deliverDBToArray();
        if (arrayListDeliver != null) {
            deliverArrayList = arrayListDeliver;
        }
        ArrayList<Bills> arrayListBill = MainView.dbHandler.billDBToArray();
        if (arrayListBill != null) {
            billsArrayList = arrayListBill;
        }
    }

    public static ArrayList<Product> getProductArrayList() {
        return productArrayList;
    }

    public static ArrayList<Sell> getSellArrayList() {
        return sellArrayList;
    }

    public static ArrayList<Deliver> getDeliverArrayList() {
        return deliverArrayList;
    }

    public static ArrayList<Bills> getBillsArrayList() {
        return billsArrayList;
    }

}
