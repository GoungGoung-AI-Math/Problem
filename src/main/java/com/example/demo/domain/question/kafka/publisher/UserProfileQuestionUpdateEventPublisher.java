package com.example.demo.domain.question.kafka.publisher;

import lombok.RequiredArgsConstructor;
import math.ai.my.kafka.infra.avrobuild.UserProfileQuestionUpdateEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserProfileQuestionUpdateEventPublisher {
    private final KafkaTemplate<String,
            UserProfileQuestionUpdateEvent> kafkaTemplate;

    public void publish(String topic, UserProfileQuestionUpdateEvent event) {
        kafkaTemplate.send(topic, event);
    }
}
