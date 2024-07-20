package com.example.demo.domain.problem.repository;

import com.example.demo.domain.problem.entity.Problem;
import org.springframework.data.domain.Page;

public interface ProblemRepositoryCustom {
    Page<Problem> searchAll();
}
