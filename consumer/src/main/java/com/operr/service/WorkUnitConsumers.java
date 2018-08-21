package com.operr.service;

import com.operr.entity.BookingReqMessage;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

import org.springframework.stereotype.Service;


@Service
public class WorkUnitConsumers {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(WorkUnitConsumers.class);

    @KafkaListener(topics = "${request.topic.name}")
    public void listen(BookingReqMessage reqMessage) {
        log.info("Received Messasge in group foo: " + reqMessage.toString());
    }
}
