package org.newit.microservice.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    @KafkaListener(topics = "topic-name-newit", groupId = "defaultGroup")
    public void consumeMessage(ConsumerRecord<Integer, String> record){
        System.out.println("get record, value=" + record.value());
    }
}
