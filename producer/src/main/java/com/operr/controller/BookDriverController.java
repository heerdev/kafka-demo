package com.operr.controller;


import com.operr.entity.Driver;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
public class BookDriverController {

    @Autowired
    private ReplyingKafkaTemplate<String, Driver, Driver> kafkaTemplate;

    @Value("${request.topic.name}")
    String bookinReqTopic;


    @Value("${reply.topic.name}")
    String requestReplyTopic;

    @RequestMapping(value="/request-driver", method=RequestMethod.POST)
    public void requestDriver(@RequestParam String location,@RequestParam String customerName) throws ExecutionException, InterruptedException {
        Driver driver= new Driver();
        driver.setCustomerName(customerName);
        driver.setLocation(location);
        ProducerRecord<String, Driver> bookingReq=new ProducerRecord<>(bookinReqTopic,driver);
        bookingReq.headers().add(new RecordHeader(KafkaHeaders.REPLY_TOPIC,requestReplyTopic.getBytes()));


        RequestReplyFuture<String, Driver, Driver > sendAndReceive=kafkaTemplate.sendAndReceive(bookingReq);

        SendResult<String, Driver> sendResult = sendAndReceive.getSendFuture().get();

        //print all headers
        sendResult.getProducerRecord().headers().forEach(header -> System.out.println(header.key() + ":" + header.value().toString()));

        // get consumer record
       // ConsumerRecord<String, Driver> consumerRecord = sendAndReceive.get();

    }


}
