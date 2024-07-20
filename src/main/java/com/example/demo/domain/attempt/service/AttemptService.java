package com.example.demo.domain.attempt.service;

import com.example.demo.domain.attempt.AttemptMarkGPTMapper;
import com.example.demo.domain.attempt.dto.AttemptMarkRequest;
import com.example.demo.domain.attempt.dto.SimpleMarkResponse;
import com.example.demo.domain.attempt.entity.ProblemAttempt;
import com.example.demo.domain.attempt.entity.Status;
import com.example.demo.domain.attempt.exception.AttemptException;
import com.example.demo.domain.attempt.repository.AttemptRepository;
import com.example.demo.domain.gpt.exception.GptAsyncException;
import com.example.demo.domain.gpt.service.GptAsyncService;
import com.example.demo.domain.problem.entity.Problem;
import com.example.demo.domain.problem.exception.ProblemException;
import com.example.demo.domain.problem.repository.ProblemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AttemptService {

    private final ProblemRepository problemRepository;
    private final AttemptRepository attemptRepository;
    private final GptAsyncService gptAsyncService;

    /**
     * 1. 답 체크
     * 2. PENDDING 상태의 시도 저장
     * 3. GPT에게 비동기로 분석 요청
     * 3-1. 시도 응답
     * @param attempt
     */
    public SimpleMarkResponse markAttemptSolution(AttemptMarkRequest attempt) {

        Problem problem = problemRepository.findById(attempt.getProblemId())
                .orElseThrow(() -> new ProblemException("존재하지 않는 문제입니다."));
        Status status = problem.getAnswer().equals(attempt.getAnswer()) ? Status.PENDING : Status.FAIL;
        ProblemAttempt problemAttempt = ProblemAttempt.toEntity(attempt, problem, status);

        ProblemAttempt savedProblemAttempt;
        try {
            savedProblemAttempt = attemptRepository.save(problemAttempt);
        } catch (RuntimeException e) {
            throw new AttemptException("ProblemAttempt 저장 중 오류가 발생했습니다.", e);
        }

        if (status == Status.PENDING) {
            try {
                gptAsyncService.attemptMarkRequest(AttemptMarkGPTMapper.mapTo(attempt, savedProblemAttempt.getId()));
            } catch (RuntimeException e) {
                throw new GptAsyncException("GPT 분석 요청 중 오류가 발생했습니다.", e);
            }
        }
        return SimpleMarkResponse.builder()
                .attemptId(savedProblemAttempt.getId())
                .problemId(savedProblemAttempt.getId())
                .status(savedProblemAttempt.getStatus())
                .build();
    }

}
