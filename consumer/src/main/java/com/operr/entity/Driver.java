package com.operr.entity;

public class Driver {
    
    String driverName;
    String customerName;
    String location;
    Boolean isHired;

    public Driver(String driverName, String customerName,String location, Boolean isHired) {
        this.driverName = driverName;
        this.location=location;
        this.customerName = customerName;
        this.isHired = isHired;
    }
    public Driver(){

    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Boolean getHired() {
        return isHired;
    }

    public void setHired(Boolean hired) {
        isHired = hired;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "driverName='" + driverName + '\'' +
                ", customerName='" + customerName + '\'' +
                ", location='" + location + '\'' +
                ", isHired=" + isHired +
                '}';
    }
}
