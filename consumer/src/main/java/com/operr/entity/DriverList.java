package com.operr.entity;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class DriverList {

    private static DriverList driverList= new DriverList();
    List<Driver> drivers = new CopyOnWriteArrayList<>();
    private DriverList() {
        drivers.add(new Driver("driver1",null,"california" ,false));
        drivers.add(new Driver("driver2",null,"chicago" ,false));
        drivers.add(new Driver("driver3",null,"tampa" ,false));
        drivers.add(new Driver("driver4",null,"california" ,false));
        drivers.add(new Driver("driver5",null,"california" ,false));
        drivers.add(new Driver("driver6",null,"tampa" ,false));
        drivers.add(new Driver("driver6",null,"chicago" ,false));
        drivers.add(new Driver("driver8",null,"chicago" ,false));
        drivers.add(new Driver("driver9",null,"chicago" ,false));
        drivers.add(new Driver("driver10",null,"chicago" ,false));


    }
    public static DriverList getDriverList() {
        return driverList;
    }

    public Driver bookDriver(Driver driver){
        boolean driverPresent= drivers.removeIf(s -> s.getLocation().equalsIgnoreCase(driver.getLocation()));
        driver.setHired(driverPresent ? true:false);
        System.out.println("DRIVERS REMAINING :" +drivers.size());
        return driver;
    }

}
