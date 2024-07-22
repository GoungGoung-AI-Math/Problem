package com.example.demo.domain.attempt.kafka.publisher;

import com.example.demo.domain.attempt.kafka.event.AttemptAnalysisEvent;
import com.example.demo.domain.attempt.kafka.mapper.AttemptAnalysisDataMapper;
import com.example.demo.my.kafka.infra.avrobuild.AttemptAnalysisRequestAvroModel;
import com.example.demo.my.kafka.infra.avrobuild.AttemptAnalysisResponseAvroModel;
import com.example.demo.my.kafka.infra.kafka.config.ProblemServiceKafkaConfigData;
import com.example.demo.my.kafka.infra.kafka.producer.KafkaProducer;
import com.example.demo.my.kafka.infra.kafka.publisher.kafka.DomainEventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AttemptAnalysisRequestPublisher implements DomainEventPublisher<AttemptAnalysisEvent> {

    private final AttemptAnalysisDataMapper attemptAnalysisDataMapper;
    private final KafkaProducer<String, AttemptAnalysisRequestAvroModel> kafkaProducer;
    private final ProblemServiceKafkaConfigData problemServiceKafkaConfigData;
    @Override
    public void publish(AttemptAnalysisEvent domainEvent) {
        Long attemptId = domainEvent.getAttemptAnalysisDto().getAttemptId();
        log.info("Received AttemptAnalysisEvent for attempt id: {}", attemptId);
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
