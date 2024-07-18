package Problem.Math.AI.domain.test.service;

import Problem.Math.AI.domain.test.dto.request.TestSearchCondition;
import Problem.Math.AI.domain.test.dto.response.TestSearchResponse;
import Problem.Math.AI.domain.test.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TestService {

    private final TestRepository testRepository;

    public Page<TestSearchResponse> searchTests(TestSearchCondition condition, Pageable pageable) {
        return testRepository.searchTests(condition, pageable);
    }
}
