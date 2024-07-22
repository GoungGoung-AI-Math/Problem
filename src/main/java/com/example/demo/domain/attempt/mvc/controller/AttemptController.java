package com.example.demo.domain.attempt.mvc.controller;


import com.example.demo.common.AnalysisType;
import com.example.demo.domain.attempt.kafka.event.AttemptAnalysisDto;
import com.example.demo.domain.attempt.kafka.event.AttemptAnalysisEvent;
import com.example.demo.domain.attempt.kafka.publisher.AttemptAnalysisRequestPublisher;
import com.example.demo.domain.attempt.mvc.dto.AttemptMarkRequest;
import com.example.demo.domain.attempt.mvc.dto.SimpleMarkResponse;
import com.example.demo.domain.attempt.mvc.service.AttemptService;
import com.example.demo.domain.gpt.dto.MessageType;
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

    @PostMapping("/test-kafka")
    public void test(){
        AttemptAnalysisDto dto = AttemptAnalysisDto.builder()
                .analysisType(AnalysisType.ATTEMPT)
                .messageType(MessageType.TEXT)
                .attemptId(1L)
                .content("테스트 가러 갑니다!!~ kafka야 동작해야!!")
                .build();
        AttemptAnalysisEvent event = AttemptAnalysisEvent.builder()
                .attemptAnalysisDto(dto)
                .attemptAnalysisEventDomainEventPublisher(attemptAnalysisRequestPublisher)
                .createdAt(ZonedDateTime.now())
                .failureMessages(List.of())
                .build();
        event.fire();
    }
}
