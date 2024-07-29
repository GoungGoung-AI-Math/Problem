package com.example.demo.domain.attempt.kafka.event;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import math.ai.my.kafka.infra.kafka.dtos.attempt.analysis.AttemptAnalysisResponseDto;
import math.ai.my.kafka.infra.listener.DomainEvent;
import math.ai.my.kafka.infra.listener.DomainEventPublisher;

import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AttemptAnalysisResponseEvent implements DomainEvent<AttemptAnalysisResponseDto> {
    private final AttemptAnalysisResponseDto attemptAnalysisDto;
    private final List<String> failureMessages;
    private final ZonedDateTime createdAt;
    private final DomainEventPublisher<AttemptAnalysisResponseEvent> attemptAnalysisEventDomainEventPublisher;

    @Override
    public void fire() {
        attemptAnalysisEventDomainEventPublisher.publish(this);
    }
}
