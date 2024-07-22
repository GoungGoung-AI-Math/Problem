package com.example.demo.kafka.listener;

import com.example.demo.domain.attempt.event.AttemptAnalysisDto;
import com.example.demo.kafka.mapper.AttemptAnalysisDataMapper;
import com.example.demo.my.kafka.infra.avrobuild.AttemptAnalysisResponseAvroModel;
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
public class AttemptAnalysisResponseKafkaListener implements KafkaConsumer<AttemptAnalysisResponseAvroModel>  {

    private final AttemptAnalysisDataMapper attemptAnalysisDataMapper;
    @Override
    @KafkaListener(id = "${kafka-consumer.attempt-analysis-consumer-group-id}", topics = "${problem-service.attempt-analysis-response-topic-name}")
    public void receive(@Payload List<AttemptAnalysisResponseAvroModel> messages,
                        @Header(KafkaHeaders.RECEIVED_KEY)  List<String> keys,
                        @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
                        @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
        log.info("{} number of payment responses received with keys:{}, partitions:{} and offsets: {}",
                messages.size(),
                keys.toString(),
                partitions.toString(),
                offsets.toString());
        messages.forEach(avroModel ->{
            AttemptAnalysisDto dto = attemptAnalysisDataMapper.avroModelToAttemptAnalysisDto(avroModel);
            log.info("Processing successful analysis for attempt id: {} \n content : {}", dto.getAttemptId(), dto.getContent());
        });
    }
}
