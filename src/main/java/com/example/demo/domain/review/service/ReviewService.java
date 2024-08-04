package com.example.demo.domain.review.service;

import com.example.demo.domain.review.dto.ReviewResponse;
import com.example.demo.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public List<ReviewResponse> getReview(Long attemptId) {
        return reviewRepository.findReviewListByAttemptId(attemptId);
    }
}
