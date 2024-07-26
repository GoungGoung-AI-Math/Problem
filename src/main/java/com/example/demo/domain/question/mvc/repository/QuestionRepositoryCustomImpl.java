package com.example.demo.domain.question.mvc.repository;

import com.example.demo.domain.question.mvc.dto.QuestionResponse;
import com.example.demo.domain.question.mvc.entity.QQuestion;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class QuestionRepositoryCustomImpl implements QuestionRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<QuestionResponse> findQuestionListByProblemId(Long problemId) {
        QQuestion question = QQuestion.question;


        List<QuestionResponse> results = jpaQueryFactory
                .select(Projections.constructor(QuestionResponse.class,
                        question.problem.id,
                        question.userId,
                        question.title
                ))
                .from(question)
                .where(question.problem.id.eq(problemId))
                .fetch();

        for (QuestionResponse response : results) {
            List<String> contents = jpaQueryFactory
                    .select(question.content)
                    .from(question)
                    .where(question.problem.id.eq(problemId))
                    .fetch();
            response.setContents(contents);
        }
        return results;
    }

}
