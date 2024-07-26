package com.example.demo.domain.exam.controller;

import com.example.demo.domain.exam.dto.request.ExamSearchCondition;
import com.example.demo.domain.exam.dto.response.SearchExamResponse;
import com.example.demo.domain.exam.service.ExamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/exam")
public class ExamController {

    private final ExamService examService;

    @PostMapping("/search")
    public ResponseEntity<Page<SearchExamResponse>> searchExams(
            @RequestBody ExamSearchCondition condition, Pageable pageable) {
        Page<SearchExamResponse> results = examService.searchExams(condition, pageable);
        return ResponseEntity.ok(results);
    }
}
