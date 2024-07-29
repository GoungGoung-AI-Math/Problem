package com.example.demo.domain.attempt.kafka.publisher;

import com.example.demo.domain.attempt.kafka.event.AttemptAnalysisRequestEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import math.ai.my.kafka.infra.avrobuild.AttemptAnalysisRequestAvroModel;
import math.ai.my.kafka.infra.kafka.config.ProblemServiceKafkaConfigData;
import math.ai.my.kafka.infra.kafka.mapper.AttemptAnalysisDataMapper;
import math.ai.my.kafka.infra.kafka.producer.KafkaProducer;
import math.ai.my.kafka.infra.kafka.publisher.kafka.DomainEventPublisher;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AttemptAnalysisRequestPublisher implements DomainEventPublisher<AttemptAnalysisRequestEvent> {

    private final AttemptAnalysisDataMapper attemptAnalysisDataMapper;
    private final KafkaProducer<String, AttemptAnalysisRequestAvroModel> kafkaProducer;
    private final ProblemServiceKafkaConfigData problemServiceKafkaConfigData;
    @Override
    public void publish(AttemptAnalysisRequestEvent domainEvent) {
        Long attemptId = domainEvent.getAttemptAnalysisDto().getAttemptId();
        log.info("Received AttemptAnalysisRequestEvent for attempt id: {}", attemptId);
        try {
            AttemptAnalysisRequestAvroModel attemptAnalysisRequestAvroModel =
                    attemptAnalysisDataMapper
                            .attemptAnalysisRequestToAvroModel(domainEvent.getAttemptAnalysisDto());

            kafkaProducer.send(problemServiceKafkaConfigData.getAttemptAnalysisRequestTopicName(),
                    String.valueOf(attemptId),
                    attemptAnalysisRequestAvroModel);

            log.info("AttemptAnalysisResponseAvroModel sent to kafka at: {}", System.nanoTime());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error while sending AttemptAnalysisResponseAvroModel message" +
                    " to kafka with order id: {}, error: {}", attemptId, e.getMessage());
        }
    }
}
