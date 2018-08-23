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

# start with Landoop docker images for kafka
docker run --rm -it \
           -p 3181:3181 -p 3040:3040 -p 7081:7081 \
           -p 7082:7082 -p 7083:7083 -p 7092:7092 \
           -e ZK_PORT=3181 -e WEB_PORT=3040 -e REGISTRY_PORT=8081 \
           -e REST_PORT=7082 -e CONNECT_PORT=7083 -e BROKER_PORT=7092 \
           -e ADV_HOST=127.0.0.1 \
           landoop/fast-data-dev