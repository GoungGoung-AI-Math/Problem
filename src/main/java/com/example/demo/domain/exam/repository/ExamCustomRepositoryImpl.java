package com.example.demo.domain.exam.repository;

import com.example.demo.domain.exam.dto.request.ExamSearchCondition;
import com.example.demo.domain.exam.dto.response.SearchExamResponse;
import com.example.demo.domain.exam.entity.QExam;
import com.example.demo.domain.exam.entity.Type;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Slf4j
public class ExamCustomRepositoryImpl implements ExamCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<SearchExamResponse> searchExam(ExamSearchCondition condition, Pageable pageable) {
        QExam exam = QExam.exam;
        BooleanBuilder builder = new BooleanBuilder();

        log.info("Search condition: {}", condition);

        if ((condition.getStartYear() != null || condition.getEndYear() != null)
                && condition.getMonths() != null && !condition.getMonths().isEmpty()) {
            BooleanBuilder yearMonthBuilder = new BooleanBuilder();

            int startYear = condition.getStartYear() != null ? condition.getStartYear() : condition.getEndYear();
            int endYear = condition.getEndYear() != null ? condition.getEndYear() : condition.getStartYear();

            for (int year = startYear; year <= endYear; year++) {
                for (Integer month : condition.getMonths()) {
                    yearMonthBuilder.or(exam.name.startsWith(String.format("%d%02d", year, month)));
                }
            }

            builder.and(yearMonthBuilder);
        } else {
            // 년도만 있는 경우
            if (condition.getStartYear() != null || condition.getEndYear() != null) {
                BooleanBuilder yearBuilder = new BooleanBuilder();
                if (condition.getStartYear() != null) {
                    yearBuilder.or(exam.name.startsWith(condition.getStartYear().toString()));
                }
                if (condition.getEndYear() != null) {
                    yearBuilder.or(exam.name.startsWith(condition.getEndYear().toString()));
                }
                builder.and(yearBuilder);
            }

            // 월만 있는 경우
            if (condition.getMonths() != null && !condition.getMonths().isEmpty()) {
                BooleanBuilder monthBuilder = new BooleanBuilder();
                for (Integer month : condition.getMonths()) {
                    monthBuilder.or(exam.name.contains(String.format("%02d", month)));
                }
                builder.and(monthBuilder);
            }
        }

        // Type 필터링 (기존과 동일)
        if (condition.getTypes() != null && !condition.getTypes().isEmpty()) {
            BooleanBuilder typeBuilder = new BooleanBuilder();
            for (Type type : condition.getTypes()) {
                typeBuilder.or(exam.type.eq(type));
            }
            builder.and(typeBuilder);
        }

        log.info("Query builder: {}", builder);


        log.info("Query builder: {}", builder);

        List<SearchExamResponse> responses = queryFactory
                .select(Projections.constructor(SearchExamResponse.class,
                        exam.name,
                        exam.createDate,
                        exam.difficulty,
                        exam.type,
                        exam.revisionState,
                        exam.totalSolved
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

        log.info("Total count: {}", total);

        long totalCount = (total != null) ? total : 0;

        return new PageImpl<>(responses, pageable, totalCount);

    }
}
