package com.operr.controller;


import com.operr.entity.BookingReqMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookDriverController {

    @Autowired
    private KafkaTemplate<String, BookingReqMessage> kafkaTemplate;

    @Value("${request.topic.name}")
    String bookinReqTopic;

    @RequestMapping(value="/request-driver", method=RequestMethod.POST)
    public void requestDriver(BookingReqMessage reqMessage){
        String location=reqMessage.getLocationName();
        String custName=reqMessage.getCustomerName();
        kafkaTemplate.send(bookinReqTopic, new BookingReqMessage(location,custName));
    }


}
