package com.example.demo.domain.like.kafka.publisher;

import com.example.demo.domain.attempt.kafka.event.AttemptAnalysisRequestEvent;
import com.example.demo.domain.like.kafka.event.LikeAddRequestEvent;
import com.example.demo.my.kafka.infra.avrobuild.AttemptAnalysisRequestAvroModel;
import com.example.demo.my.kafka.infra.avrobuild.LikeAddRequestAvroModel;
import com.example.demo.my.kafka.infra.kafka.config.ProblemServiceKafkaConfigData;
import com.example.demo.my.kafka.infra.kafka.dtos.like.add.LikeAddRequestDto;
import com.example.demo.my.kafka.infra.kafka.mapper.AttemptAnalysisDataMapper;
import com.example.demo.my.kafka.infra.kafka.mapper.LikeAddDataMapper;
import com.example.demo.my.kafka.infra.kafka.producer.KafkaProducer;
import com.example.demo.my.kafka.infra.kafka.publisher.kafka.DomainEventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class LikeAddRequestPublisher implements DomainEventPublisher<LikeAddRequestEvent> {
    private final LikeAddDataMapper likeAddDataMapper;
    private final KafkaProducer<String, LikeAddRequestAvroModel> kafkaProducer;
    private final ProblemServiceKafkaConfigData problemServiceKafkaConfigData;

    @Override
    public void publish(LikeAddRequestEvent domainEvent) {
        Long likeId = domainEvent.getLikeAddRequestDto().getLikeId();
        log.info("Received AttemptAnalysisRequestEvent for attempt id: {}", likeId);
        try {
            LikeAddRequestAvroModel likeAddRequestAvroModel =
                    likeAddDataMapper.likeAddRequestToAvroModel(domainEvent.getLikeAddRequestDto());

            kafkaProducer.send(problemServiceKafkaConfigData.getLikeAddRequestTopicName(),
                    String.valueOf(likeId),
                    likeAddRequestAvroModel);

            log.info("LikeAddRequestAvroModel sent to kafka at: {}", System.nanoTime());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error while sending LikeAddRequestAvroModel message" +
                    " to kafka with order id: {}, error: {}", likeId, e.getMessage());
        }
    }
}
