package com.example.demo.domain.review.repository;

import com.example.demo.domain.review.dto.ReviewResponse;
import com.example.demo.domain.review.entity.QReview;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ReviewRepositoryCustomImpl implements ReviewRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<ReviewResponse> findReviewListByAttemptId(Long attemptId) {
        QReview review = QReview.review;

        List<ReviewResponse> results = jpaQueryFactory
                .select(Projections.constructor(ReviewResponse.class,
                        review.problemAttempt.problem.id,
                        review.problemAttempt.id,
                        review.reviewerType,
                        review.title
                        ))
                .from(review)
                .where(review.problemAttempt.id.eq(attemptId))
                .fetch();

        for (ReviewResponse response : results) {
            List<String> contents = jpaQueryFactory
                    .select(review.content)
                    .from(review)
                    .where(review.problemAttempt.id.eq(attemptId))
                    .fetch();
            response.setContents(contents);
        }
        return results;
    }
}
