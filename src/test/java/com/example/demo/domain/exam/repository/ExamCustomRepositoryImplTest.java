package com.example.demo.domain.exam.repository;

import com.example.demo.domain.exam.dto.request.ExamSearchCondition;
import com.example.demo.domain.exam.dto.response.SearchExamResponse;
import com.example.demo.domain.exam.entity.Difficulty;
import com.example.demo.domain.exam.entity.Exam;
import com.example.demo.domain.exam.entity.RevisionState;
import com.example.demo.domain.exam.entity.Type;
import com.example.demo.domain.problem.entity.Problem;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ExamCustomRepositoryImplTest {
    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private ExamCustomRepositoryImpl examCustomRepository;

    @BeforeEach
    void setUp() {
        // given: 테스트를 위한 데이터 준비
        Set<Problem> problems = new HashSet<>();
        Exam exam1 = new Exam(1L, 1L, "202301", LocalDateTime.now(), Difficulty.a, RevisionState.a, Type.a, problems, 10L);
        Exam exam2 = new Exam(2L, 2L, "202302", LocalDateTime.now(), Difficulty.b, RevisionState.b, Type.b, problems, 20L);
        Exam exam3 = new Exam(3L, 3L, "202303", LocalDateTime.now(), Difficulty.c, RevisionState.c, Type.c, problems, 30L);
        examRepository.save(exam1);
        examRepository.save(exam2);
        examRepository.save(exam3);
    }

    @Test
    void searchExam() {
        // given: 검색 조건과 페이징 정보를 설정
        ExamSearchCondition condition = new ExamSearchCondition();
        condition.setStartYear(2023);
        condition.setEndYear(2023);
        condition.setMonth(1);

        PageRequest pageable = PageRequest.of(0, 10);

        // when: 리포지토리 메서드를 호출하여 검색
        Page<SearchExamResponse> result = examCustomRepository.searchExam(condition, pageable);

        // then: 검색 결과를 검증
        assertThat(result).isNotNull();
        assertThat(result.getTotalElements()).isEqualTo(1);
        assertThat(result.getContent().get(0).getExamName()).isEqualTo("202301");
        assertThat(result.getContent().get(0).getDifficulty()).isEqualTo(Difficulty.a);
        assertThat(result.getContent().get(0).getType()).isEqualTo(Type.a);
        assertThat(result.getContent().get(0).getRevisionState()).isEqualTo(RevisionState.a);
    }

}