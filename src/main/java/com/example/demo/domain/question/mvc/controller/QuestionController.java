package com.example.demo.domain.question.mvc.controller;

import com.example.demo.domain.question.mvc.dto.QuestionCreateRequest;
import com.example.demo.domain.question.mvc.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("question")
public class QuestionController {
    private final QuestionService questionService;

    @PostMapping
    public ResponseEntity<Long> createQuestion(@RequestBody QuestionCreateRequest request){
        Long id = questionService.createQuestion(request);
        return ResponseEntity.ok(id);
    }
}
