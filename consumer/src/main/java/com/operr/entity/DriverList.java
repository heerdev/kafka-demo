package com.operr.entity;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;


public class DriverList {

    private static DriverList driverList= new DriverList();

    //List<Driver> drivers = new CopyOnWriteArrayList<>();
    private Queue<Driver> drivers = new ConcurrentLinkedQueue<>();
    private DriverList() {
        drivers.add(new Driver("driver1",null,"california" ,false));
        drivers.add(new Driver("driver2",null,"california" ,false));
        drivers.add(new Driver("driver3",null,"chicago" ,false));
        drivers.add(new Driver("driver4",null,"chicago" ,false));
        drivers.add(new Driver("driver5",null,"chicago" ,false));
        drivers.add(new Driver("driver6",null,"tampa" ,false));
        drivers.add(new Driver("driver7",null,"tampa" ,false));
        drivers.add(new Driver("driver8",null,"california" ,false));
        drivers.add(new Driver("driver10",null,"california" ,false));
    }
    public static DriverList getDriverList() {
        return driverList;
    }

    public Driver bookDriver(Driver driver){

        for(Driver removeDriver:drivers){
            if(removeDriver.getLocation().equalsIgnoreCase(driver.getLocation())){
                driver.setCustomerName(removeDriver.getCustomerName());
                driver.setHired(true);
                driver.setDriverName(removeDriver.getDriverName());
                drivers.remove();
                break;
            }
        }
        System.out.println("DRIVERS REMAINING :  " +drivers.size() + " Current driver list :" + drivers.toString());
        return driver;
    }

}
