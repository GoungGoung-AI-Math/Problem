package com.example.demo.domain.question.mvc.controller;

import com.example.demo.domain.question.mvc.dto.QuestionCreateRequest;
import com.example.demo.domain.question.mvc.dto.QuestionResponse;
import com.example.demo.domain.question.mvc.service.QuestionService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/get-question-list/{problemId}")
    public ResponseEntity<List<QuestionResponse>> getQuestionList(@PathVariable Long problemId) {
        return ResponseEntity.ok(questionService.getQuestion(problemId));
    }
}
