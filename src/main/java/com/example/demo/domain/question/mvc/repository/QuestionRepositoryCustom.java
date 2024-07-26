package com.example.demo.domain.question.mvc.repository;

import com.example.demo.domain.question.mvc.dto.QuestionResponse;

import java.util.List;

public interface QuestionRepositoryCustom {
    List<QuestionResponse> findQuestionListByProblemId(Long problemId);
}
