package com.operr.service;

import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

import org.springframework.stereotype.Service;


@Service
public class WorkUnitConsumers {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(WorkUnitConsumers.class);

    @KafkaListener(topics = "test")
    public void onReceiving(String topic) {
        log.info("Processing topic" + topic);
    }
}
