package com.operr.service;

import com.operr.entity.BookingReqMessage;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;

import org.springframework.stereotype.Service;


@Service
public class WorkUnitConsumers {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(WorkUnitConsumers.class);


    /* @KafkaListener(topics = "test")
    public void onReceiving(String topic) {
        log.info("Processing topic" + topic);
    }
*/
    @KafkaListener(topics = "reqbooking")
    public void listen(BookingReqMessage reqMessage) {
        System.out.println("Received Messasge in group foo: " + reqMessage.toString());
    }
}
