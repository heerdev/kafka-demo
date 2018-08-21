package com.operr.producer;
import java.util.Properties;

import com.operr.entity.Driver;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;


public class ConcurrentConsumerTest implements Runnable {

    private final KafkaProducer<String, Driver> producer;
    private final String topic;

    public ConcurrentConsumerTest(String brokers, String topic) {
        Properties prop = createProducerConfig("localhost:7092");
        this.producer = new KafkaProducer<String, Driver>(prop);
        this.topic = topic;
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
        System.out.println("Produces 5 messages");
        for (int i = 0; i < 5; i++) {
            String msg = "Message " + i;
            producer.send(new ProducerRecord<String, Driver>("reqbooking", new Driver("driver1",null,"tampa",false)), new Callback() {
                public void onCompletion(RecordMetadata metadata, Exception e) {
                    if (e != null) {
                        e.printStackTrace();
                    }
                    System.out.println("Sent:" + msg + ", Offset: " + metadata.offset());
                }
            });
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        // closes producer
        producer.close();

    }

    public static void main(String[] args) {

        ConcurrentConsumerTest concurrentConsumerTest= new ConcurrentConsumerTest("localhost:7092","reqbooking");
        concurrentConsumerTest.run();
    }


}
