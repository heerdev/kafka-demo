package com.operr.entity;

public class Driver {
    
    String driverName;
    String customerName;
    Boolean isHired;

    public Driver(String driverName, String customerName, Boolean isHired) {
        this.driverName = driverName;
        this.customerName = customerName;
        this.isHired = isHired;
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
                ", isHired=" + isHired +
                '}';
    }
}
