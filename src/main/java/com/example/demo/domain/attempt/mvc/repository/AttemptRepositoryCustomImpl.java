package com.example.demo.domain.attempt.mvc.repository;

import com.example.demo.domain.attempt.mvc.dto.MarkResultListResponse;
import com.example.demo.domain.attempt.mvc.entity.QProblemAttempt;
import com.example.demo.domain.attempt.mvc.service.AttemptService;
import com.example.demo.domain.problem.entity.QProblem;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
@Slf4j
public class AttemptRepositoryCustomImpl implements AttemptRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<MarkResultListResponse> findMarkResultListByProblemId(Long problemId, Pageable pageable) {
        QProblemAttempt attempt = QProblemAttempt.problemAttempt;
        QProblem problem = QProblem.problem;

        log.info("###############={}", attempt.id);
        List<MarkResultListResponse> results = jpaQueryFactory
                .select(Projections.constructor(MarkResultListResponse.class,
                        attempt.userId,
                        problem.difficulty,
                        attempt.createDate,
                        attempt.status
                ))
                .from(attempt)
                .leftJoin(attempt.problem, problem)
                .where(problem.id.eq(problemId))
                .fetch();

        // 페이징 처리
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), results.size());
        return new PageImpl<>(results.subList(start, end), pageable, results.size());
    }

    @Override
    public Page<MarkResultListResponse> findMarkResultListByProblemIdAndUserId(Long problemId, Long userId ,Pageable pageable) {
        QProblemAttempt attempt = QProblemAttempt.problemAttempt;
        QProblem problem = QProblem.problem;

        log.info("###############={}", attempt.id);
        List<MarkResultListResponse> results = jpaQueryFactory
                .select(Projections.constructor(MarkResultListResponse.class,
                        attempt.userId,
                        problem.difficulty,
                        attempt.createDate,
                        attempt.status
                ))
                .from(attempt)
                .leftJoin(attempt.problem, problem)
                .where(problem.id.eq(problemId)
                        .and(attempt.userId.eq(userId)))
                .fetch();

        // 페이징 처리
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), results.size());
        return new PageImpl<>(results.subList(start, end), pageable, results.size());
    }
}
