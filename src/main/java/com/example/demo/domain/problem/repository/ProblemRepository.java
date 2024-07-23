package com.example.demo.domain.problem.repository;

import com.example.demo.domain.problem.entity.Problem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProblemRepository extends JpaRepository<Problem, Long>, ProblemRepositoryCustom {

    @Query(value = "Select p.answer FROM Problem p WHERE p.id = :id")
    Integer findAnswerById(@Param("id") Long id);

    @Query(value = "Select p From Problem p " +
            "join fetch p.officialSolution o " +
            "join fetch o.solutionContents sc " +
            " where p.id = :id ")
    Problem findByIdWithSolution(@Param("id") Long problemId);
}
