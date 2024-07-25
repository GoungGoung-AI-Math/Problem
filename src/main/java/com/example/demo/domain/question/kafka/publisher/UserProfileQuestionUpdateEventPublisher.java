package com.example.demo.domain.question.kafka.publisher;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserProfileQuestionUpdateEventPublisher {
    private final KafkaTemplate<String,
            com.example.demo.avro.UserProfileQuestionUpdateEvent> kafkaTemplate;

    public void publish(String topic, com.example.demo.avro.UserProfileQuestionUpdateEvent event) {
        kafkaTemplate.send(topic, event);
    }
}
