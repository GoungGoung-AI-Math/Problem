package Problem.Math.AI.domain.attempt.service;

import Problem.Math.AI.domain.attempt.dto.AttemptMarkRequest;
import Problem.Math.AI.domain.attempt.dto.SimpleMarkResponse;
import Problem.Math.AI.domain.attempt.entity.ProblemAttempt;
import Problem.Math.AI.domain.attempt.entity.Status;
import Problem.Math.AI.domain.attempt.exception.AttemptException;
import Problem.Math.AI.domain.attempt.repository.AttemptRepository;
import Problem.Math.AI.domain.gpt.GptAsyncService;
import Problem.Math.AI.domain.gpt.exception.GptAsyncException;
import Problem.Math.AI.domain.problem.entity.Problem;
import Problem.Math.AI.domain.problem.exception.ProblemException;
import Problem.Math.AI.domain.problem.repository.ProblemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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
        Map<Boolean, Problem> check = checkAnswer(attempt);
        ProblemAttempt problemAttempt;
        if(check.get(Boolean.TRUE) != null){ // 참인 경우
            // gpt에 분석 요청
            try{
                gptAsyncService.markRequest(attempt);
            } catch (RuntimeException g){
                throw new GptAsyncException(g.getMessage(), g.getCause());
            }
            problemAttempt = ProblemAttempt.toEntity(attempt, check.get(Boolean.TRUE), Status.PENDING);
        } else{
            problemAttempt = ProblemAttempt.toEntity(attempt, check.get(Boolean.FALSE), Status.FAIL);
        }
        try{
            ProblemAttempt savedProblemAttempt = attemptRepository.save(problemAttempt);
            return SimpleMarkResponse.builder()
                    .problemId(savedProblemAttempt.getId())
                    .status(savedProblemAttempt.getStatus())
                    .build();
        } catch (RuntimeException e){
            throw new AttemptException(e.getMessage(), e.getCause());
        }
    }

    private Map<Boolean, Problem> checkAnswer(AttemptMarkRequest attemptMark) {
        Problem problem = problemRepository.findById(attemptMark.getProblemId())
                .orElseThrow(() -> new ProblemException("존재하지 않는 문제입니다."));
        Integer expectAnswer = problem.getAnswer();
        Integer realAnswer = attemptMark.getAnswer();
        if(!expectAnswer.equals(realAnswer)){
            return new HashMap<>(){{
                put(false, problem);
            }};
        }
        return new HashMap<>(){{
            put(true, problem);
        }};
    }
}
