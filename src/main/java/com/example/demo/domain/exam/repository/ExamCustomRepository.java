package com.example.demo.domain.exam.repository;

import com.example.demo.domain.exam.dto.request.ExamSearchCondition;
import com.example.demo.domain.exam.dto.response.SearchExamResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ExamCustomRepository {
    Page<SearchExamResponse> searchExam(ExamSearchCondition condition, Pageable pageable);
}
