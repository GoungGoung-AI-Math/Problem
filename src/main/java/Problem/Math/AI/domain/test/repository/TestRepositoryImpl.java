package Problem.Math.AI.domain.test.repository;

import Problem.Math.AI.domain.test.dto.request.TestSearchCondition;
import Problem.Math.AI.domain.test.dto.response.TestSearchResponse;
import Problem.Math.AI.domain.test.entity.QTest;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TestRepositoryImpl implements TestRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<TestSearchResponse> searchTests(TestSearchCondition condition, Pageable pageable) {
        QTest test = QTest.test;
        BooleanBuilder builder = new BooleanBuilder();

        if (condition.getStartYear() != null) {
            builder.and(test.name.startsWith(condition.getStartYear().toString()));
        }
        if (condition.getEndYear() != null) {
            builder.and(test.name.startsWith(condition.getEndYear().toString()));
        }
        if (condition.getMonth() != null) {
            builder.and(test.name.contains(String.format("%02d", condition.getMonth())));
        }

        List<TestSearchResponse> results = queryFactory
                .select(Projections.constructor(TestSearchResponse.class,
                        test.name,
                        test.difficulty,
                        test.type,
                        test.revisionStatus
                ))
                .from(test)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory
                .select(test.count())
                .from(test)
                .where(builder)
                .fetchOne();

        long totalCount = (total != null) ? total : 0;

        return new PageImpl<>(results, pageable, totalCount);
    }
}
