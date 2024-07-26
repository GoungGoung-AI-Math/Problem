//package com.example.demo.domain.problem.repository;
//
//import com.example.demo.domain.exam.entity.Difficulty;
//import com.example.demo.domain.exam.entity.Exam;
//import com.example.demo.domain.exam.repository.ExamRepository;
//import com.example.demo.domain.problem.dto.response.ProblemResponse;
//import com.example.demo.domain.problem.entity.ConceptTag;
//import com.example.demo.domain.problem.entity.Problem;
//import com.example.demo.domain.problem.entity.ProblemConceptTag;
//import jakarta.transaction.Transactional;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.annotation.Import;
//
//import java.time.LocalDateTime;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@Transactional
//class ProblemRepositoryCustomImplTest {
//    @Autowired
//    private ProblemRepository problemRepository;
//
//    @Autowired
//    private ConceptTagRepository conceptTagRepository;
//
//    @Autowired
//    private ExamRepository examRepository;
//
//    @Autowired
//    private ProblemRepositoryCustomImpl problemRepositoryCustom;
//
//    private Long examId;
//
//    @BeforeEach
//    void setUp() {
//        // Given: 테스트 데이터 설정
//        Exam exam = new Exam("Test Exam", 100L);
//        examId = examRepository.save(exam).getId();
//
//        ConceptTag tag1 = new ConceptTag(null, "Tag1");
//        ConceptTag tag2 = new ConceptTag(null, "Tag2");
//        conceptTagRepository.saveAll(List.of(tag1, tag2));
//
//        Problem problem1 = new Problem(null, LocalDateTime.now(), LocalDateTime.now(), 1L, "Problem 1",
//                "http://example.com/img1.jpg", Difficulty.a, exam, 1, new HashSet<>(), null);
//        Problem problem2 = new Problem(null, LocalDateTime.now(), LocalDateTime.now(), 1L, "Problem 2",
//                "http://example.com/img2.jpg", Difficulty.b, exam, 2, new HashSet<>(), null);
//
//        ProblemConceptTag pct1 = new ProblemConceptTag(null, problem1, tag1);
//        ProblemConceptTag pct2 = new ProblemConceptTag(null, problem1, tag2);
//        ProblemConceptTag pct3 = new ProblemConceptTag(null, problem2, tag1);
//
//        problem1.getProblemConceptTags().add(pct1);
//        problem1.getProblemConceptTags().add(pct2);
//        problem2.getProblemConceptTags().add(pct3);
//
//        problemRepository.saveAll(List.of(problem1, problem2));
//    }
//
//    @Test
//    void findProblemsByExamId() {
//        // When: examId로 문제를 조회
//        List<ProblemResponse> problems = problemRepositoryCustom.findProblemsByExamId(examId);
//
//        // Then: 결과 검증
//        assertThat(problems).hasSize(2);
//
//        ProblemResponse problem1 = problems.get(0);
//        assertThat(problem1.getName()).isEqualTo("Problem 1");
//        assertThat(problem1.getDifficulty()).isEqualTo(Difficulty.a);
//        assertThat(problem1.getImgUrl()).isEqualTo("http://example.com/img1.jpg");
//        assertThat(problem1.getTags()).containsExactlyInAnyOrder("Tag1", "Tag2");
//        assertThat(problem1.getTotalSolved()).isEqualTo(100L);
//
//        ProblemResponse problem2 = problems.get(1);
//        assertThat(problem2.getName()).isEqualTo("Problem 2");
//        assertThat(problem2.getDifficulty()).isEqualTo(Difficulty.b);
//        assertThat(problem2.getImgUrl()).isEqualTo("http://example.com/img2.jpg");
//        assertThat(problem2.getTags()).containsExactly("Tag1");
//        assertThat(problem2.getTotalSolved()).isEqualTo(100L);
//    }
//}