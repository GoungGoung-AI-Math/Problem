package com.example.demo.domain.question.mvc.dto;

import com.example.demo.common.entity.ContentType;
import com.example.demo.domain.question.mvc.entity.QuestionContentEntity;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionResponse {
    private Long problemId;
    private Long userId;
    private String title;
//    private List<Set<QuestionContentEntity>> contents;
    private String content;
    private List<String> imgUrls;

    public QuestionResponse(Long problemId, Long userId, String title, String content) {
        this.problemId = problemId;
        this.userId = userId;
        this.title = title;
        this.content = content;
    }
}

