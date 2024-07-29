package com.example.demo.domain.attempt.kafka.publisher;

import lombok.RequiredArgsConstructor;
import temp.infra.avrobuild.math.ai.my.kafka.infra.avrobuild.UserUpdateEvent;
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
