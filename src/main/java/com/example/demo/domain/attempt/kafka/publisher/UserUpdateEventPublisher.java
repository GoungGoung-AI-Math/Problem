package com.example.demo.domain.attempt.kafka.publisher;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserUpdateEventPublisher {
    private final KafkaTemplate<String, com.example.demo.avro.UserUpdateEvent> kafkaTemplate;

    public void publish(String topic, com.example.demo.avro.UserUpdateEvent event) {
        kafkaTemplate.send(topic, event);
    }
}
