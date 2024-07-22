package com.example.demo.my.kafka.infra.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@Component
public class KafkaMessageHelper {

    public <T> ListenableFutureCallback<SendResult<Long, T>>
    getKafkaCallback(String responseTopicName, T avroModel, Long id, String avroModelName) {
        return new ListenableFutureCallback<SendResult<Long, T>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.error("Error while sending " + avroModelName +
                        " message {} to topic {}", avroModel.toString(), responseTopicName, ex);
            }

            @Override
            public void onSuccess(SendResult<Long, T> result) {
                RecordMetadata metadata = result.getRecordMetadata();
                log.info("Received successful response from Kafka for order id: {}" +
                                " Topic: {} Partition: {} Offset: {} Timestamp: {}",
                        id,
                        metadata.topic(),
                        metadata.partition(),
                        metadata.offset(),
                        metadata.timestamp());
            }
        };
    }
}
