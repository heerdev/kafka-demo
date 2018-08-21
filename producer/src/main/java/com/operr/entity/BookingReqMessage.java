package com.operr.entity;

public class BookingReqMessage {

    String locationName;
    String customerName;

    public BookingReqMessage(String locationName, String customerName) {
        this.locationName = locationName;
        this.customerName = customerName;
    }

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
}
