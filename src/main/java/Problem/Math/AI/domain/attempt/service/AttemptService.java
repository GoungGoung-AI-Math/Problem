package Problem.Math.AI.domain.attempt.service;

import Problem.Math.AI.domain.attempt.dto.AttemptMarkRequest;
import Problem.Math.AI.domain.attempt.exception.AttemptException;
import Problem.Math.AI.domain.attempt.repository.AttemptRepository;
import Problem.Math.AI.domain.problem.repository.ProblemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AttemptService {

    private final ProblemRepository problemRepository;
    private final AttemptRepository attemptRepository;

    /**
     * 1. 답 체크
     * 2. PENDDING 상태의 시도 저장
     * 3. GPT에게 비동기로 분석 요청
     * 3-1. 시도 응답
     * @param attempt
     */
    public void markAttemptSolution(AttemptMarkRequest attempt) {
        checkAnswer(attempt);
    }

    private void checkAnswer(AttemptMarkRequest attemptMark) {
        Integer expectAnswer = problemRepository.findAnswerById(attemptMark.getProblemId());
        Integer realAnswer = attemptMark.getAnswer();
        if(!expectAnswer.equals(realAnswer)){
            throw new AttemptException("틀린 답입니다. 다른 답을 전달해주세요.");
        }
    }
}
