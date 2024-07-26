package com.example.demo.domain.question.mvc.repository;

import com.example.demo.domain.question.mvc.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long>, QuestionRepositoryCustom {
}
