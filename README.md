# kafka-demo
This repository contains the demo code for kafka consumer and producer model

# Steps to run
1. have the kafka and zookeeper running on local(or on server) an specify the bootstrap server property in properties file.

# Producer is Consumer module and Consumer is Driver module
1. Run Consumer module as Spring app, it will  run on port 9091
2. Run Producer module as Spring app ,it will run on port 9090

#To book as via web
http://localhost:9090/swagger-ui.html#!/book-driver-controller/requestDriverUsingPOST

# Run 5 concurrent consumer
run ConcurrentConsumerTest class in package com.operr.producer

# Please refer to application properties file for request and reply queues

