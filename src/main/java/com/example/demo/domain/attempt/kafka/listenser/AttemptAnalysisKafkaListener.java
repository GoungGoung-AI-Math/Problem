package com.example.demo.domain.attempt.kafka.listenser;

import com.example.demo.domain.attempt.kafka.event.AttemptAnalysisDto;
import com.example.demo.domain.attempt.kafka.event.AttemptAnalysisEvent;
import com.example.demo.domain.attempt.kafka.mapper.AttemptAnalysisDataMapper;
import com.example.demo.my.kafka.infra.avrobuild.AttemptAnalysisResponseAvroModel;
import com.example.demo.my.kafka.infra.kafka.listener.kafka.KafkaConsumer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class AttemptAnalysisKafkaListener implements KafkaConsumer<AttemptAnalysisResponseAvroModel> {

    private final AttemptAnalysisDataMapper attemptAnalysisDataMapper;

    @Override
    @KafkaListener(id = "${kafka-consumer.attempt-analysis-consumer-group-id}", topics = "${problem-service.attempt-analysis-response-topic-name}")
    public void receive(@Payload List<AttemptAnalysisResponseAvroModel> messages,
                        @Header(KafkaHeaders.RECEIVED_KEY) List<String> keys,
                        @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
                        @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
        log.info("{} number of analysis responses received with keys:{}, partitions:{} and offsets: {}",
                messages.size(),
                keys.toString(),
                partitions.toString(),
                offsets.toString());

        messages.forEach(avroModel -> {
            AttemptAnalysisDto dto = attemptAnalysisDataMapper.avroModelToAttemptAnalysisDto(avroModel);
            log.info("Processing successful analysis for attempt id: {} type : {}", dto.getAttemptId(), dto.getMessageType());
        });
    }
}
