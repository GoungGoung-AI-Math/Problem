package com.example.demo.domain.problem.repository;

import com.example.demo.domain.problem.entity.OfficialSolution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficialSolutionRepository extends JpaRepository<OfficialSolution, Long> {
}
