package com.operr.entity;

public class Driver {
    
    String name;
    Boolean isHired;

    public Driver(String name, Boolean isHired) {
        this.name = name;
        this.isHired = isHired;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                "name='" + name + '\'' +
                ", isHired=" + isHired +
                '}';
    }
}
