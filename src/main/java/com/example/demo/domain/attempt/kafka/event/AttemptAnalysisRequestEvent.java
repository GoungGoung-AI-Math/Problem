package com.example.demo.domain.attempt.kafka.event;

import com.example.demo.my.kafka.infra.kafka.dtos.attempt.analysis.AttemptAnalysisRequestDto;
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
public class AttemptAnalysisRequestEvent implements DomainEvent<AttemptAnalysisRequestDto> {
    private final AttemptAnalysisRequestDto attemptAnalysisDto;
    private final List<String> failureMessages;
    private final ZonedDateTime createdAt;
    private final DomainEventPublisher<AttemptAnalysisRequestEvent> attemptAnalysisEventDomainEventPublisher;

    @Override
    public void fire() {
        attemptAnalysisEventDomainEventPublisher.publish(this);
    }
}
