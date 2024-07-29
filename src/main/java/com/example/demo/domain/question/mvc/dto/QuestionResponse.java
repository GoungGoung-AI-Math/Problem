package com.example.demo.domain.question.mvc.dto;

import com.example.demo.common.entity.ContentType;
import lombok.*;

import java.util.List;

@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionResponse {
    private Long problemId;
    private Long userId;
    private String title;
    private List<String> contents;

    public QuestionResponse(Long problemId, Long userId, String title) {
        this.problemId = problemId;
        this.userId = userId;
        this.title = title;
    }
}

