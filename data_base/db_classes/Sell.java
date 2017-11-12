package com.example.okabe.nexttry.data_base.db_classes;

public class Sell {
    private int productId;
    private String productName;
    private int productAmount;
    private int sellId = 0;

    public Sell(int productId, String productName, int productAmount) {
        this.productName = productName;
        this.productAmount = productAmount;
        this.productId = productId;
    }

    public Sell(int productId, String productName, int productAmount, int sellId) {
        this.productId = productId;
        this.productName = productName;
        this.productAmount = productAmount;
        this.sellId = sellId;
    }

    public String getProductName() {
        return productName;
    }

    public int getProductAmount() {
        return productAmount;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getSellId() {
        return sellId;
    }

    public void setSellId(int sellId) {
        this.sellId = sellId;
    }
}
