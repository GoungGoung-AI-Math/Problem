package com.example.demo.domain.problem.repository;

import com.example.demo.domain.problem.dto.response.ProblemResponse;
import com.example.demo.domain.problem.entity.Problem;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProblemRepositoryCustom {
//    Page<Problem> searchAll();
    List<ProblemResponse> findProblemsByExamId(Long examId);
    List<ProblemResponse> findTop3LowestCorrectRateProblems();
}
