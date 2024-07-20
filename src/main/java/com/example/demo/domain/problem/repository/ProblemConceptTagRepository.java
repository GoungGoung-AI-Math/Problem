package com.example.demo.domain.problem.repository;

import com.example.demo.domain.problem.entity.ProblemConceptTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProblemConceptTagRepository extends JpaRepository<ProblemConceptTag, Long> {
}
