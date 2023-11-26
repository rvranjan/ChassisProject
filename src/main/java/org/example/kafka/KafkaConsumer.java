package org.example.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = "NewTopic", groupId = "group_id")
    public void consume(String message)
    {
        // Print statement
        System.out.println("message = " + message);
    }
}
