package com.example.demo.domain.attempt.mvc.controller;


import com.example.demo.domain.attempt.kafka.event.AttemptAnalysisRequestEvent;
import com.example.demo.domain.attempt.kafka.publisher.AttemptAnalysisRequestPublisher;
import com.example.demo.domain.attempt.mvc.dto.AttemptMarkRequest;
import com.example.demo.domain.attempt.mvc.dto.SimpleMarkResponse;
import com.example.demo.domain.attempt.mvc.service.AttemptService;
import com.example.demo.my.kafka.infra.kafka.dtos.AnalysisType;
import com.example.demo.my.kafka.infra.kafka.dtos.MessageType;
import com.example.demo.my.kafka.infra.kafka.dtos.attempt.analysis.AttemptAnalysisRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/attempt")
public class AttemptController {

    private final AttemptService attemptService;
    private final AttemptAnalysisRequestPublisher attemptAnalysisRequestPublisher;

    @PostMapping
    public ResponseEntity<SimpleMarkResponse> sendAttemptToMarking(@RequestBody AttemptMarkRequest attempt){
        SimpleMarkResponse response = attemptService.markAttemptSolution(attempt);
        log.info("id : {}, status : {}",response.getProblemId(), response.getStatus().getStatus());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/request-nicknames")
    public ResponseEntity<Void> requestNicknames(@RequestBody List<Long> userIds) {
        attemptService.requestUserNicknames(userIds);
        return ResponseEntity.ok().build();
    }

//    @PostMapping("/test-kafka")
//    public void test(){
//        AttemptAnalysisRequestDto dto = AttemptAnalysisRequestDto.builder()
//                .analysisType(AnalysisType.ATTEMPT)
//                .attemptId(1L)
//                .content(List.of("테스트 가러 갑니다!!~ kafka야 동작해야!!"))
//                .build();
//        AttemptAnalysisRequestEvent event = AttemptAnalysisRequestEvent.builder()
//                .attemptAnalysisDto(dto)
//                .attemptAnalysisEventDomainEventPublisher(attemptAnalysisRequestPublisher)
//                .createdAt(ZonedDateTime.now())
//                .failureMessages(List.of())
//                .build();
//        event.fire();
//    }
}
