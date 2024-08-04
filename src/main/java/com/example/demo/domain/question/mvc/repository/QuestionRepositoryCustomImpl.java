package com.example.demo.domain.question.mvc.repository;

import com.example.demo.domain.question.mvc.dto.QuestionContent;
import com.example.demo.domain.question.mvc.dto.QuestionResponse;
import com.example.demo.domain.question.mvc.entity.QQuestion;
import com.example.demo.domain.question.mvc.entity.QQuestionContentEntity;
import com.example.demo.domain.question.mvc.entity.QuestionContentEntity;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
@RequiredArgsConstructor
@Slf4j
public class QuestionRepositoryCustomImpl implements QuestionRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<QuestionResponse> findQuestionListByProblemId(Long problemId) {
        QQuestion question = QQuestion.question;
        QQuestionContentEntity contentEntity = QQuestionContentEntity.questionContentEntity;


        List<QuestionResponse> results = jpaQueryFactory
                .select(Projections.constructor(QuestionResponse.class,
                        question.problem.id,
                        question.userId,
                        question.title,
                        question.content
                ))
                .from(question)
                .where(question.problem.id.eq(problemId))
                .fetch();

        for (QuestionResponse response : results) {
            List<String> imgUrls = jpaQueryFactory
                    .select(contentEntity.imgUrl)
                    .from(contentEntity)
                    .where(contentEntity.question.id.eq(response.getUserId())) // Use correct foreign key
                    .fetch();

            response.setImgUrls(imgUrls);
        }

        return results;
    }
}
