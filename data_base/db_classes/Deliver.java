package com.example.okabe.nexttry.data_base.db_classes;

public class Deliver {
    private String deliverName = "";
    private long deliverPhone = 0L;
    private int deliverId = 0;

    public Deliver(String deliverName, long deliverPhone) {
        this.deliverName = deliverName;
        this.deliverPhone = deliverPhone;
    }

    public Deliver(String deliverName, long deliverPhone, int deliverId) {
        this.deliverName = deliverName;
        this.deliverPhone = deliverPhone;
        this.deliverId = deliverId;
    }

    public int getDeliverId() {
        return deliverId;
    }

    public void setDeliverId(int deliverId) {
        this.deliverId = deliverId;
    }

    public String getDeliverName() {
        return deliverName;
    }

    public long getDeliverPhone() {
        return deliverPhone;
    }

}
