package Problem.Math.AI.domain.test.repository;

import Problem.Math.AI.domain.test.dto.request.TestSearchCondition;
import Problem.Math.AI.domain.test.dto.response.TestSearchResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TestRepositoryCustom {
    Page<TestSearchResponse> searchTests(TestSearchCondition condition, Pageable pageable);
}
