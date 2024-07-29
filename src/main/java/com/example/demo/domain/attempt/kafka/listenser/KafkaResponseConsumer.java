package com.example.demo.domain.attempt.kafka.listenser;

import com.example.demo.avro.NicknameListAvro;
import com.example.demo.my.kafka.infra.kafka.listener.kafka.KafkaConsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class KafkaResponseConsumer implements KafkaConsumer<com.example.demo.avro.NicknameListAvro> {

    @Override
    @KafkaListener(topics = "nickname-list-topic", groupId = "group_id")
    public void receive(List<NicknameListAvro> messages, List<String> keys, List<Integer> partitions, List<Long> offsets) {
        for (NicknameListAvro message : messages) {
            List<String> nicknames = Arrays.asList(message.getNicknames().split(","));
            log.info("Received message: {}", message);

            // 닉네임 리스트를 받아서 처리하는 로직
        }
    }
}
