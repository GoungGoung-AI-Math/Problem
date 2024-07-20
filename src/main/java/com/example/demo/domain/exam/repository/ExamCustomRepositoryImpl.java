package com.example.demo.domain.exam.repository;

import com.example.demo.domain.exam.dto.request.ExamSearchCondition;
import com.example.demo.domain.exam.dto.response.SearchExamResponse;
import com.example.demo.domain.exam.entity.QExam;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ExamCustomRepositoryImpl implements ExamCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<SearchExamResponse> searchExam(ExamSearchCondition condition, Pageable pageable) {
        QExam exam = QExam.exam;
        BooleanBuilder builder = new BooleanBuilder();

        if (condition.getStartYear() != null) {
            builder.and(exam.name.startsWith(condition.getStartYear().toString()));
        }
        if (condition.getEndYear() != null) {
            builder.and(exam.name.startsWith(condition.getEndYear().toString()));
        }
        if (condition.getMonth() != null) {
            builder.and(exam.name.contains(String.format("%02d", condition.getMonth())));
        }

        List<SearchExamResponse> responses = queryFactory
                .select(Projections.constructor(SearchExamResponse.class,
                        exam.name,
                        exam.difficulty,
                        exam.type,
                        exam.revisionState
                        ))
                .from(exam)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory
                .select(exam.count())
                .from(exam)
                .where(builder)
                .fetchOne();

        long totalCount = (total != null) ? total : 0;

        return new PageImpl<>(responses, pageable, totalCount);
    }
}
