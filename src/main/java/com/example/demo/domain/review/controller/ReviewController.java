package com.example.demo.domain.review.controller;

import com.example.demo.domain.review.dto.ReviewResponse;
import com.example.demo.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("review")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/get-review-list/{attemptId}")
    public ResponseEntity<List<ReviewResponse>> getReviewList(@PathVariable Long attemptId) {
        return ResponseEntity.ok(reviewService.getReview(attemptId));
    }
}
