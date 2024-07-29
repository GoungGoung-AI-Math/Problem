package com.example.demo.domain.attempt.mvc.dto;

import com.example.demo.domain.attempt.mvc.entity.Status;
import com.example.demo.domain.exam.entity.Difficulty;
import com.example.demo.domain.review.entity.ReviewerType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class MarkResultListResponse {
    private Long userId;
    private String nickName;
    private Difficulty difficulty;
    private ReviewerType type;
    private LocalDateTime time;
    private Long reviewId;
    private Status status;

    public MarkResultListResponse(Long userId, Difficulty difficulty, LocalDateTime time, Status status) {
        this.userId = userId;
        this.difficulty = difficulty;
        //        this.reviewId = reviewId;
        this.time = time;
        this.status = status;
    }
}
