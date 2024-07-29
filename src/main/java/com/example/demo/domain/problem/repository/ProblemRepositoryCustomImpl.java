package com.example.demo.domain.problem.repository;

import com.example.demo.domain.problem.dto.response.ProblemResponse;
import com.example.demo.domain.problem.entity.Problem;
import com.example.demo.domain.problem.entity.QConceptTag;
import com.example.demo.domain.problem.entity.QProblem;
import com.example.demo.domain.problem.entity.QProblemConceptTag;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

import static com.example.demo.domain.problem.entity.QProblem.problem;

@Repository
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Slf4j
public class ProblemRepositoryCustomImpl implements ProblemRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<ProblemResponse> findProblemsByExamId(Long examId) {
        QProblem problem = QProblem.problem;
        QProblemConceptTag problemConceptTag = QProblemConceptTag.problemConceptTag;
        QConceptTag conceptTag = QConceptTag.conceptTag;

        List<ProblemResponse> results = jpaQueryFactory
                .select(Projections.constructor(ProblemResponse.class,
                        problem.id,
                        problem.name,
                        problem.imgUrl,
                        problem.difficulty,
                        problem.createDate,
                        problem.exam.totalSolved
                ))
                .from(problem)
                .where(problem.exam.id.eq(examId))
                .fetch();

        for (ProblemResponse response : results) {
            List<String> tags = jpaQueryFactory
                    .select(conceptTag.tageName)
                    .from(problemConceptTag)
                    .join(problemConceptTag.conceptTag, conceptTag)
                    .where(problemConceptTag.problem.id.eq(response.getId()))
                    .fetch();

            response.setTags(new HashSet<>(tags));
        }

        return results;
    }
}
