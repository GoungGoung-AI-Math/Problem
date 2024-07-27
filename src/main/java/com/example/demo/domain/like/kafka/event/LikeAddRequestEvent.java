package com.example.demo.domain.like.kafka.event;

import com.example.demo.my.kafka.infra.kafka.dtos.like.add.LikeAddRequestDto;
import com.example.demo.my.kafka.infra.kafka.publisher.kafka.DomainEvent;
import com.example.demo.my.kafka.infra.kafka.publisher.kafka.DomainEventPublisher;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class LikeAddRequestEvent implements DomainEvent<LikeAddRequestDto> {
    private final LikeAddRequestDto likeAddRequestDto;
    private final List<String> failureMessages;
    private final ZonedDateTime createdAt;
    private final DomainEventPublisher<LikeAddRequestEvent> likeAddRequestEventDomainEventPublisher;

    @Override
    public void fire() {
        likeAddRequestEventDomainEventPublisher.publish(this);
    }
}