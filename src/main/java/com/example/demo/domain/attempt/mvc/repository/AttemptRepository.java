package com.example.demo.domain.attempt.mvc.repository;

import com.example.demo.domain.attempt.mvc.entity.ProblemAttempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttemptRepository extends JpaRepository<ProblemAttempt, Long> {

}
