package com.example.okabe.nexttry.data_base.db_classes;

public class Bills {

    private String billName;
    private int debtAmount;
    private String debtDate;
    private int billId;

    public Bills(String billName, int debtAmount, String debtDate) {
        this.billName = billName;
        this.debtAmount = debtAmount;
        this.debtDate = debtDate;
    }

    public Bills(String billName, int debtAmount, String debtDate, int billId) {
        this.billName = billName;
        this.debtAmount = debtAmount;
        this.debtDate = debtDate;
        this.billId = billId;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public String getBillName() {
        return billName;
    }

    public int getDebtAmount() {
        return debtAmount;
    }

    public String getDebtDate() {
        return debtDate;
    }

}
