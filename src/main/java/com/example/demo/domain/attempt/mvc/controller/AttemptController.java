package com.example.demo.domain.attempt.mvc.controller;


import com.example.demo.domain.attempt.kafka.event.AttemptAnalysisRequestEvent;
import com.example.demo.domain.attempt.kafka.publisher.AttemptAnalysisRequestPublisher;
import com.example.demo.domain.attempt.mvc.dto.AttemptMarkRequest;
import com.example.demo.domain.attempt.mvc.dto.MarkResultListResponse;
import com.example.demo.domain.attempt.mvc.dto.SimpleMarkResponse;
import com.example.demo.domain.attempt.mvc.service.AttemptService;
import com.example.demo.my.kafka.infra.kafka.dtos.AnalysisType;
import com.example.demo.my.kafka.infra.kafka.dtos.MessageType;
import com.example.demo.my.kafka.infra.kafka.dtos.attempt.analysis.AttemptAnalysisRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.concurrent.ExecutionException;

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

    @GetMapping("/problem/{problemId}")
    public ResponseEntity<Page<MarkResultListResponse>> getMarkResultListByProblemId(@PathVariable Long problemId, Pageable pageable) {
        try {
            Page<MarkResultListResponse> results = attemptService.getMarkResultListByProblemId(problemId, pageable);
            return ResponseEntity.ok(results);
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(500).build();
        }
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
