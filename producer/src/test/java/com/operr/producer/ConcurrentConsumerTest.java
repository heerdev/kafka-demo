package com.operr.producer;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import com.operr.entity.Driver;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;


public class ConcurrentConsumerTest implements Runnable {

    private Thread t;
    private String consumerName;

    private final KafkaProducer<String, Driver> producer;
    private final String topic;

    public ConcurrentConsumerTest(String brokers, String topic, String consumerName) {
        Properties prop = createProducerConfig("localhost:7092");
        this.producer = new KafkaProducer<String, Driver>(prop);
        this.topic = topic;
        this.consumerName=consumerName;

    }

    private static Properties createProducerConfig(String brokers) {
        Properties props = new Properties();
        props.put("bootstrap.servers", brokers);
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.springframework.kafka.support.serializer.JsonSerializer");
        return props;
    }

    @Override
    public void run() {

            producer.send(new ProducerRecord<String, Driver>("reqbooking", getCustomerReq() ), new Callback() {
                public void onCompletion(RecordMetadata metadata, Exception e) {
                    if (e != null) {
                        e.printStackTrace();
                    }
                    System.out.println("Sent:" + getCustomerReq().toString() + ", Offset: " + metadata.offset());
                }
            });
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        // closes producer
        producer.close();

    }

    private Driver getCustomerReq() {
        List<Driver> customerRequestList= new ArrayList<>();
        Random rn = new Random();
        int diverNumber= rn.nextInt(5) + 1;
        Driver driver1=new Driver("driver1",null,"chicago",false);
        Driver driver2=new Driver("driver1",null,"tampa",false);
        Driver driver3=new Driver("driver1",null,"chicago",false);
        Driver driver4=new Driver("driver1",null,"california",false);
        Driver driver5=new Driver("driver1",null,"chicago",false);

        customerRequestList.add(driver1);
        customerRequestList.add(driver2);
        customerRequestList.add(driver3);
        customerRequestList.add(driver4);
        customerRequestList.add(driver5);
        return customerRequestList.get(diverNumber);
    }

    public void start () {

        System.out.println("Starting " +  consumerName );
        if (t == null) {
            t = new Thread (this, consumerName);
            t.start ();
        }
    }
    public static void main(String[] args) {

        ConcurrentConsumerTest c1= new ConcurrentConsumerTest("localhost:7092","reqbooking","customer1");
        ConcurrentConsumerTest c2= new ConcurrentConsumerTest("localhost:7092","reqbooking","customer2");
        ConcurrentConsumerTest c3= new ConcurrentConsumerTest("localhost:7092","reqbooking","customer3");
        ConcurrentConsumerTest c4= new ConcurrentConsumerTest("localhost:7092","reqbooking","customer4");
        ConcurrentConsumerTest c5= new ConcurrentConsumerTest("localhost:7092","reqbooking","customer5");


        c1.start();
        c2.start();
        c3.start();
        c4.start();
        c5.start();

    }


}
