package com.operr.controller;


import com.operr.entity.ReqMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookDriverController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    @RequestMapping(value="/request-driver", method=RequestMethod.POST)
    public void requestDriver(ReqMessage reqMessage){
        kafkaTemplate.send("test", reqMessage.toString());
    }


}
