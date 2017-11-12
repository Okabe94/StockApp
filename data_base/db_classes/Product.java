package com.example.okabe.nexttry.data_base.db_classes;


public class Product {
    private int productId =0;
    private String productName;
    private int productAmount;

    public Product(String productName, int productAmount, int productId) {
        this.productName = productName;
        this.productAmount = productAmount;
        this.productId = productId;
    }

    public Product(String productName, int productAmount) {
        this.productName = productName;
        this.productAmount = productAmount;
    }

    public void setProductAmount(int productAmount) {
        this.productAmount = productAmount;
    }

    public String getProductName() {
        return productName;
    }

    public int getProductAmount() {
        return productAmount;
    }

    public int getProductId(){
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
