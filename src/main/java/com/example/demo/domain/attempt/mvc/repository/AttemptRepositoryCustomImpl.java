//package com.example.demo.domain.attempt.mvc.repository;
//
//import com.example.demo.domain.attempt.mvc.dto.MarkResultListResponse;
//import com.example.demo.domain.attempt.mvc.entity.QProblemAttempt;
//import com.example.demo.domain.problem.entity.QProblem;
//import com.querydsl.core.types.Projections;
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Repository;
//
//@Repository
//@RequiredArgsConstructor
//public class AttemptRepositoryCustomImpl implements AttemptRepositoryCustom{
//
//    private final JPAQueryFactory jpaQueryFactory;
//
//    @Override
//    public Page<MarkResultListResponse> findMarkResultListByProblemId(Long problemId, Pageable pageable) {
//        QProblemAttempt attempt = QProblemAttempt.problemAttempt;
//        QProblem problem = QProblem.problem;
//
//        Page<MarkResultListResponse> results = jpaQueryFactory
//                .select(Projections.constructor(MarkResultListResponse.class,
//                        problem.userId,
//                        ))
//    }
//}
