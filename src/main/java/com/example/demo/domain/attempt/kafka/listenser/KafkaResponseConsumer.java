//package com.example.demo.domain.attempt.kafka.listenser;
//
//import com.example.demo.avro.NicknameListAvro;
//import com.example.demo.my.kafka.infra.kafka.listener.kafka.KafkaConsumer;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Service;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.concurrent.CompletableFuture;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.ConcurrentMap;
//
//@Slf4j
//@Service
//public class KafkaResponseConsumer implements KafkaConsumer<com.example.demo.avro.NicknameListAvro> {
//
//    private final ConcurrentMap<String, CompletableFuture<List<String>>> nicknameFutures = new ConcurrentHashMap<>();
//
//    @Override
//    @KafkaListener(topics = "nickname-list-topic", groupId = "group_id")
//    public void receive(List<NicknameListAvro> messages, List<String> keys, List<Integer> partitions, List<Long> offsets) {
//        for (int i = 0; i < messages.size(); i++) {
//            NicknameListAvro message = messages.get(i);
//            String key = keys.get(i);
//            List<String> nicknames = Arrays.asList(message.getNicknames().split(","));
//            log.info("Received message: {}", message);
//            CompletableFuture<List<String>> future = nicknameFutures.remove(key);
//            if (future != null) {
//                future.complete(nicknames);
//            }
//        }
//    }
//
//    public CompletableFuture<List<String>> createNicknameFuture(String requestId) {
//        CompletableFuture<List<String>> future = new CompletableFuture<>();
//        nicknameFutures.put(requestId, future);
//        return future;
//    }
//}
