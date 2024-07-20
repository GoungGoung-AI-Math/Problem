package com.example.demo.domain.exam.service;


import com.example.demo.domain.exam.dto.request.ExamSearchCondition;
import com.example.demo.domain.exam.dto.response.SearchExamResponse;
import com.example.demo.domain.exam.repository.ExamRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class ExamService {
    private final ExamRepository examRepository;

    public Page<SearchExamResponse> searchExams(ExamSearchCondition condition, Pageable pageable) {
        return examRepository.searchExam(condition, pageable);
    }
}
