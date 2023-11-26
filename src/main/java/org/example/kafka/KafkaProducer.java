package org.example.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {

        @Autowired
        KafkaTemplate<String, String> kafkaTemplate;
        private static final String TOPIC = "NewTopic";

        public String publishMessage(final String message)
        {
            // Sending the message
            kafkaTemplate.send(TOPIC, message);
            return "Published Successfully";
        }
}
