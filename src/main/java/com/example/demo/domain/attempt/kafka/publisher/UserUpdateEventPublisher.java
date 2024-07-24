package com.example.demo.domain.attempt.kafka.publisher;

import com.example.demo.domain.attempt.kafka.event.UserUpdateEvent;
import com.example.demo.my.kafka.infra.kafka.config.KafkaConfigData;
import com.example.demo.my.kafka.infra.kafka.producer.KafkaProducer;
import com.example.demo.my.kafka.infra.kafka.publisher.kafka.DomainEventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserUpdateEventPublisher implements DomainEventPublisher<UserUpdateEvent> {
    private final KafkaProducer<String, com.example.demo.avro.UserUpdateMessage> kafkaProducer;
    private final KafkaConfigData kafkaConfigData;

    @Override
    public void publish(UserUpdateEvent domainEvent) {
        Long userId = domainEvent.getUserUpdateMessage().getUserId();
        log.info("Received UserUpdateEvent for user id: {}", userId);
        try {
            kafkaProducer.send(kafkaConfigData.getUserUpdateTopicName(), String.valueOf(userId), domainEvent.getUserUpdateMessage());
            log.info("UserUpdateMessage sent to kafka for user id: {}", userId);
        } catch (Exception e) {
            log.error("Error while sending UserUpdateMessage to kafka for user id: {}, error: {}", userId, e.getMessage());
        }
    }
}
