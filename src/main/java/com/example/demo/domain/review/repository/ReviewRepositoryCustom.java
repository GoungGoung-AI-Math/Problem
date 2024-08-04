package com.example.demo.domain.review.repository;

import com.example.demo.domain.review.dto.ReviewResponse;

import java.util.List;

public interface ReviewRepositoryCustom {
    List<ReviewResponse> findReviewListByAttemptId(Long attemptId);
}
