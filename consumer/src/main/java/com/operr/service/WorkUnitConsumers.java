package com.operr.service;

import com.operr.entity.Driver;
import com.operr.entity.DriverList;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


@Service
public class WorkUnitConsumers {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(WorkUnitConsumers.class);

    @KafkaListener(topics = "${request.topic.name}")
    @SendTo("replybooking")
    public Driver listen(Driver driver) {
        log.info("Received Messasge in group groud-id1: " + driver.toString());
        DriverList driverList= DriverList.getDriverList();
        driver=driverList.bookDriver(driver);
        return driver;
    }
}
