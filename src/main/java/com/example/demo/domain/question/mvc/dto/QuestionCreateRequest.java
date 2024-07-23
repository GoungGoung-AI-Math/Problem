package com.example.demo.domain.question.mvc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionCreateRequest {
    private Long problemId;
    private Long userId;
    private List<QuestionContent> contents;
    private String title;
}
