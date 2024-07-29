package com.example.demo.domain.attempt.kafka.listener;


import com.example.demo.domain.attempt.mvc.service.AttemptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import math.ai.my.kafka.infra.avrobuild.AttemptAnalysisResponseAvroModel;
import math.ai.my.kafka.infra.kafka.dtos.attempt.analysis.AttemptAnalysisResponseDto;
import math.ai.my.kafka.infra.kafka.listener.kafka.KafkaConsumer;
import math.ai.my.kafka.infra.kafka.mapper.AttemptAnalysisDataMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class AttemptAnalysisResponseKafkaListener implements KafkaConsumer<AttemptAnalysisResponseAvroModel> {

    private final AttemptAnalysisDataMapper attemptAnalysisDataMapper;
    private final AttemptService attemptService;

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
            AttemptAnalysisResponseDto dto = attemptAnalysisDataMapper.avroModelToAttemptAnalysisResponseDto(avroModel);
            log.info("Processing successful analysis for attempt id: {} type : {} \n content : {}", dto.getAttemptId(), dto.getMessageType(), dto.getContent());
            attemptService.saveAttemptAnalysis(dto);
        });
    }
}
