package com.example.demo.domain.attempt.kafka.publisher;

import Math.AI.my.kafka.infra.avrobuild.Math.AI.my.kafka.infra.avrobuild.UserUpdateEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserUpdateEventPublisher {
    private final KafkaTemplate<String, UserUpdateEvent> kafkaTemplate;

    public void publish(String topic, UserUpdateEvent event) {
        kafkaTemplate.send(topic, event);
    }
}
