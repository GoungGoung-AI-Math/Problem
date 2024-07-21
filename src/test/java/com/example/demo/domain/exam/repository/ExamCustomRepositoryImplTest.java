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
        Exam exam4 = new Exam(4L, 4L, "202305", LocalDateTime.now(), Difficulty.a, RevisionState.a, Type.a, problems, 40L);
        Exam exam5 = new Exam(5L, 5L, "202306", LocalDateTime.now(), Difficulty.b, RevisionState.b, Type.b, problems, 50L);
        Exam exam6 = new Exam(6L, 6L, "202307", LocalDateTime.now(), Difficulty.c, RevisionState.c, Type.c, problems, 60L);
        Exam exam7 = new Exam(7L, 7L, "202401", LocalDateTime.now(), Difficulty.a, RevisionState.a, Type.a, problems, 70L);
        Exam exam8 = new Exam(8L, 8L, "202402", LocalDateTime.now(), Difficulty.b, RevisionState.b, Type.b, problems, 80L);
        examRepository.saveAll(Arrays.asList(exam1, exam2, exam3, exam4, exam5, exam6, exam7, exam8));
    }

    @Test
    void searchExamByMultipleMonths() {
        // given: 검색 조건과 페이징 정보를 설정
        ExamSearchCondition condition = new ExamSearchCondition();
        condition.setStartYear(2023);
        condition.setMonths(Arrays.asList(5, 6)); // 다중 월 조건 추가

        PageRequest pageable = PageRequest.of(0, 10);

        // when: 리포지토리 메서드를 호출하여 검색
        Page<SearchExamResponse> result = examCustomRepository.searchExam(condition, pageable);

        // then: 검색 결과를 검증
        assertThat(result).isNotNull();
        assertThat(result.getTotalElements()).isEqualTo(2);
        assertThat(result.getContent()).extracting("examName").containsExactlyInAnyOrder("202305", "202306");
    }

    @Test
    void searchExamByMultipleTypes() {
        // given: 검색 조건과 페이징 정보를 설정
        ExamSearchCondition condition = new ExamSearchCondition();
        condition.setTypes(Arrays.asList(Type.a, Type.b)); // 다중 타입 조건 추가

        PageRequest pageable = PageRequest.of(0, 10);

        // when: 리포지토리 메서드를 호출하여 검색
        Page<SearchExamResponse> result = examCustomRepository.searchExam(condition, pageable);

        // then: 검색 결과를 검증
        assertThat(result).isNotNull();
        assertThat(result.getTotalElements()).isEqualTo(6);
        assertThat(result.getContent()).extracting("examName").containsExactlyInAnyOrder("202301", "202302", "202305", "202306", "202401", "202402");
    }

    @Test
    void searchExamByYearAndMultipleMonths() {
        // given: 검색 조건과 페이징 정보를 설정
        ExamSearchCondition condition = new ExamSearchCondition();
        condition.setStartYear(2023);
        condition.setEndYear(2023);
        condition.setMonths(Arrays.asList(1, 2)); // 다중 월 조건 추가

        PageRequest pageable = PageRequest.of(0, 10);

        // when: 리포지토리 메서드를 호출하여 검색
        Page<SearchExamResponse> result = examCustomRepository.searchExam(condition, pageable);

        // then: 검색 결과를 검증
        assertThat(result).isNotNull();
//        assertThat(result.getTotalElements()).isEqualTo(2);
        assertThat(result.getContent()).extracting("examName").containsExactlyInAnyOrder("202301", "202302");
    }

    @Test
    void searchExamByMultipleYearsAndTypes() {
        // given: 검색 조건과 페이징 정보를 설정
        ExamSearchCondition condition = new ExamSearchCondition();
        condition.setStartYear(2023);
        condition.setEndYear(2024);
        condition.setTypes(Arrays.asList(Type.a, Type.c)); // 다중 타입 조건 추가

        PageRequest pageable = PageRequest.of(0, 10);

        // when: 리포지토리 메서드를 호출하여 검색
        Page<SearchExamResponse> result = examCustomRepository.searchExam(condition, pageable);

        // then: 검색 결과를 검증
        assertThat(result).isNotNull();
        assertThat(result.getTotalElements()).isEqualTo(5);
        assertThat(result.getContent()).extracting("examName").containsExactlyInAnyOrder("202301", "202303", "202305", "202307", "202401");
    }
}