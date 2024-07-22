package com.example.demo.domain.attempt.mvc.service;

import com.example.demo.domain.attempt.kafka.event.AttemptAnalysisRequestEvent;
import com.example.demo.domain.attempt.kafka.publisher.AttemptAnalysisRequestPublisher;
import com.example.demo.domain.attempt.mvc.dto.AttemptMarkRequest;
import com.example.demo.domain.attempt.mvc.dto.SimpleMarkResponse;
import com.example.demo.domain.attempt.mvc.entity.AttemptType;
import com.example.demo.domain.attempt.mvc.entity.ProblemAttempt;
import com.example.demo.domain.attempt.mvc.entity.Status;
import com.example.demo.domain.attempt.exception.AttemptException;
import com.example.demo.domain.attempt.mvc.repository.AttemptRepository;
import com.example.demo.domain.problem.entity.OfficialSolution;
import com.example.demo.domain.problem.entity.Problem;
import com.example.demo.domain.problem.entity.SolutionContentEntity;
import com.example.demo.domain.problem.exception.ProblemException;
import com.example.demo.domain.problem.repository.ProblemRepository;
import com.example.demo.domain.review.entity.Review;
import com.example.demo.domain.review.repository.ReviewRepository;
import com.example.demo.my.kafka.infra.kafka.dtos.AnalysisType;
import com.example.demo.my.kafka.infra.kafka.dtos.MessageType;
import com.example.demo.my.kafka.infra.kafka.dtos.attempt.analysis.AttemptAnalysisRequestDto;
import com.example.demo.my.kafka.infra.kafka.dtos.attempt.analysis.AttemptAnalysisResponseDto;
import com.example.demo.my.kafka.infra.kafka.dtos.attempt.analysis.ContentDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.KafkaException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class AttemptService {
    private final ReviewRepository reviewRepository;
    private final ProblemRepository problemRepository;
    private final AttemptRepository attemptRepository;
    private final AttemptAnalysisRequestPublisher attemptAnalysisRequestPublisher;

    /**
     * 1. 답 체크
     * 2. PENDDING 상태의 시도 저장
     * 3. GPT에게 비동기로 분석 요청
     * 3-1. 시도 응답
     * @param attempt
     */
    public SimpleMarkResponse markAttemptSolution(AttemptMarkRequest attempt) {
        log.info("problem id : {}, content : {}",attempt.getProblemId(), attempt.getTextContent());
        Problem problem = problemRepository.findByIdWithSolution(attempt.getProblemId());
        if(problem == null) throw new ProblemException("존재하지 않는 문제입니다.");

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
                analysisRequest(attempt,
                        savedProblemAttempt.getId(),
                        problem.getImgUrl(),
                        problem.getOfficialSolution());
                log.info("나중에 publisher 로 gpt에게 attempt를 분석 요청!");
            } catch (RuntimeException e) {
                throw new KafkaException("GPT 분석 요청 중 오류가 발생했습니다.", e);
            }
        }
        return SimpleMarkResponse.builder()
                .attemptId(savedProblemAttempt.getId())
                .problemId(savedProblemAttempt.getId())
                .status(savedProblemAttempt.getStatus())
                .build();
    }

    @Transactional
    public void saveAttemptAnalysis(AttemptAnalysisResponseDto dto) {
        Review review = Review.aiAnalysis(dto.getContent());
        reviewRepository.save(review);
    }

    private void analysisRequest(AttemptMarkRequest attempt, Long attemptId, String problemImgUrl, OfficialSolution officialSolution) {
        List<ContentDto> contents = new ArrayList<>();
        // 문제 사진 입력
        contents.add(new ContentDto(MessageType.IMAGE_URL, problemImgUrl));
        // 정답 정보 입력
        if(!officialSolution.getTextSolution().isBlank()){
            contents.add(new ContentDto(MessageType.TEXT, officialSolution.getTextSolution()));
        }
        officialSolution.getSolutionContents()
                .forEach(solution -> contents.add(new ContentDto(MessageType.IMAGE_URL, solution.getImgUrl())));

        // 학생의 풀이가 Text인 경우
        if (attempt.getType() == AttemptType.TEXT) {
            contents.add(new ContentDto(MessageType.TEXT, attempt.getTextContent()));
            // 학생의 풀이가 Image인 경우
        } else if (attempt.getType() == AttemptType.IMAGE_URL) {
            attempt.getImgUrlsContent().forEach(imgUrl ->
                    contents.add(new ContentDto(MessageType.IMAGE_URL, imgUrl.getImgUrl())));
        } else {
            throw new IllegalArgumentException("Unsupported AttemptType: " + attempt.getType());
        }

        AttemptAnalysisRequestEvent event = AttemptAnalysisRequestEvent.builder()
                .attemptAnalysisEventDomainEventPublisher(attemptAnalysisRequestPublisher)
                .createdAt(ZonedDateTime.now())
                .attemptAnalysisDto(AttemptAnalysisRequestDto.builder()
                        .attemptId(attemptId)
                        .contents(contents) // AttemptType에 따라 설정된 content
                        .analysisType(AnalysisType.ATTEMPT)
                        .build())
                .build();
        event.fire();
    }


}
