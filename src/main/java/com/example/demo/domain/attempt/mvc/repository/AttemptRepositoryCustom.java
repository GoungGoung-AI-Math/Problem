package com.example.demo.domain.attempt.mvc.repository;

import com.example.demo.domain.attempt.mvc.dto.MarkResultListResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AttemptRepositoryCustom {
    Page<MarkResultListResponse> findMarkResultListByProblemId(Long problemId, Pageable pageable);
}
