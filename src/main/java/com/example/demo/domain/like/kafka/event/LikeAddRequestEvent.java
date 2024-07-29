package com.example.demo.domain.like.kafka.event;

import com.example.demo.domain.like.mvc.dto.LikeAddRequest;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import math.ai.my.kafka.infra.kafka.publisher.kafka.DomainEvent;
import math.ai.my.kafka.infra.kafka.publisher.kafka.DomainEventPublisher;

import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class LikeAddRequestEvent implements DomainEvent<LikeAddRequest> {
    private final LikeAddRequest likeAddRequest;
    private final List<String> failureMessages;
    private final ZonedDateTime createdAt;
    private final DomainEventPublisher<LikeAddRequestEvent> likeAddRequestEventDomainEventPublisher;

    @Override
    public void fire() {
        likeAddRequestEventDomainEventPublisher.publish(this);
    }
}