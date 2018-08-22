package com.operr.producer;
import java.util.Properties;

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

           Driver driver=new Driver("driver1",null,"chicago",false);
            producer.send(new ProducerRecord<String, Driver>("reqbooking",driver ), new Callback() {
                public void onCompletion(RecordMetadata metadata, Exception e) {
                    if (e != null) {
                        e.printStackTrace();
                    }
                    System.out.println("Sent:" + driver.toString() + ", Offset: " + metadata.offset());
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
      /*  c3.start();
        c4.start();
        c5.start();*/

    }


}
