package com.example.demo.domain.review.dto;

import com.example.demo.domain.review.entity.ReviewContent;
import com.example.demo.domain.review.entity.ReviewerType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ReviewResponse {
    private Long problemId;
    private Long problemAttemptId;
    private ReviewerType type;
    private String title;
    private String content;
    private List<String> imgUrls;

    public ReviewResponse(Long problemId, Long problemAttemptId, ReviewerType type, String title, String content) {
        this.problemId = problemId;
        this.problemAttemptId = problemAttemptId;
        this.type = type;
        this.title = title;
        this.content = content;
    }
}
