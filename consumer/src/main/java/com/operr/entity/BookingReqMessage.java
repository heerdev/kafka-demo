package com.operr.entity;

public class BookingReqMessage {

    String locationName;
    String customerName;



    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public String toString() {
        return "BookingReqMessage{" +
                "locationName='" + locationName + '\'' +
                ", customerName='" + customerName + '\'' +
                '}';
    }
}
