package com.example.demo.domain.attempt.mvc.service;

import com.example.demo.common.entity.ContentType;
import com.example.demo.domain.attempt.kafka.event.AttemptAnalysisRequestEvent;
import com.example.demo.domain.attempt.kafka.publisher.AttemptAnalysisRequestPublisher;
import com.example.demo.domain.attempt.kafka.publisher.UserUpdateEventPublisher;
import com.example.demo.domain.attempt.mvc.dto.AttemptMarkRequest;
import com.example.demo.domain.attempt.mvc.dto.MarkResultListResponse;
import com.example.demo.domain.attempt.mvc.dto.SimpleMarkResponse;
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
import com.example.demo.my.kafka.infra.kafka.producer.KafkaProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.KafkaException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AttemptService {
    private final ReviewRepository reviewRepository;
    private final ProblemRepository problemRepository;
    private final AttemptRepository attemptRepository;
    private final AttemptAnalysisRequestPublisher attemptAnalysisRequestPublisher;
    private final UserUpdateEventPublisher userUpdateEventPublisher;
    private final KafkaProducer<String, com.example.demo.avro.UserIdListAvro> kafkaProducer;
    private final ConcurrentMap<String, CompletableFuture<List<String>>> nicknameFutures = new ConcurrentHashMap<>();

    /**
     * 1. 답 체크
     * 2. PENDDING 상태의 시도 저장
     * 3. GPT에게 비동기로 분석 요청
     * 3-1. 시도 응답
     *
     * @param attempt
     */
    @Transactional
    public SimpleMarkResponse markAttemptSolution(AttemptMarkRequest attempt) {
        log.info("problem id : {}, content : {}", attempt.getProblemId(), attempt.getTextContent());
        Problem problem = problemRepository.findByIdWithSolution(attempt.getProblemId());
        if (problem == null) throw new ProblemException("존재하지 않는 문제입니다.");

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
            } catch (RuntimeException e) {
                throw new KafkaException("GPT 분석 요청 중 오류가 발생했습니다.", e);
            }
        }

        // 유저 정보 업데이트 이벤트 생성 및 퍼블리싱
        com.example.demo.avro.UserUpdateEvent userUpdateEvent = com.example.demo.avro.UserUpdateEvent.newBuilder()
                .setUserId(attempt.getUserId())
                .setProblemId(savedProblemAttempt.getProblem().getId())
                .setStatus(savedProblemAttempt.getStatus().toString())
                .build();

        userUpdateEventPublisher.publish("user-update-topic", userUpdateEvent);

        return SimpleMarkResponse.builder()
                .attemptId(savedProblemAttempt.getId())
                .problemId(savedProblemAttempt.getId())
                .status(savedProblemAttempt.getStatus())
                .build();
    }

    @Transactional
    public void saveAttemptAnalysis(AttemptAnalysisResponseDto dto) {
        // 분석 저장
        Review review = Review.aiAnalysis(dto.getContent());
        reviewRepository.save(review);
        // attempt 상태 수정
        ProblemAttempt attempt = attemptRepository.findById(dto.getAttemptId())
                .orElseThrow(() -> new AttemptException("존재하지 않는 시도입니다."));
        attempt.updateState(Status.SUCCESS);
    }

    private void analysisRequest(AttemptMarkRequest attempt, Long attemptId, String problemImgUrl, OfficialSolution officialSolution) {
        List<ContentDto> contents = new ArrayList<>();
        // 문제 사진 입력
        contents.add(new ContentDto(MessageType.IMAGE_URL, problemImgUrl));
        // 정답 정보 입력
        if (!(officialSolution.getTextSolution() == null || officialSolution.getTextSolution().isBlank())) {
            contents.add(new ContentDto(MessageType.TEXT, officialSolution.getTextSolution()));
        }
        officialSolution.getSolutionContents()
                .forEach(solution -> contents.add(new ContentDto(MessageType.IMAGE_URL, solution.getImgUrl())));

        // 학생의 풀이가 Text인 경우
        if (attempt.getType() == ContentType.TEXT) {
            contents.add(new ContentDto(MessageType.TEXT, attempt.getTextContent()));
            // 학생의 풀이가 Image인 경우
        } else if (attempt.getType() == ContentType.IMAGE_URL) {
            attempt.getImgUrlsContent().forEach(imgUrl ->
                    contents.add(new ContentDto(MessageType.IMAGE_URL, imgUrl.getImgUrl())));
        } else {
            throw new IllegalArgumentException("Unsupported ContentType: " + attempt.getType());
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

    @KafkaListener(topics = "nickname-list-topic2", groupId = "group-id")
    public void receive(com.example.demo.avro.NicknameListAvro message) {
        String requestId = message.getRequestId(); // 메시지 페이로드에서 requestId 추출
        List<String> nicknames = Arrays.asList(message.getNicknames().split(","));
        CompletableFuture<List<String>> future = nicknameFutures.remove(requestId);
        if (future != null) {
            future.complete(nicknames);
        }
    }

    public Page<MarkResultListResponse> getMarkResultListByProblemId(Long problemId, Pageable pageable) throws ExecutionException, InterruptedException {
        Page<MarkResultListResponse> resultsPage = attemptRepository.findMarkResultListByProblemId(problemId, pageable);
        log.info("###########={}", resultsPage);
        List<MarkResultListResponse> results = resultsPage.getContent();
        log.info("###########={}", results);
        List<Long> userIds = results.stream().map(MarkResultListResponse::getUserId).collect(Collectors.toList());
        log.info("###########={}", userIds);
        List<String> nicknames = requestUserNicknames(userIds);

        Map<Long, String> userIdToNicknameMap = createUserIdToNicknameMap(userIds, nicknames);

        results.forEach(result -> result.setNickName(userIdToNicknameMap.get(result.getUserId())));

        return resultsPage;
    }

    public List<String> requestUserNicknames(List<Long> userIds) throws ExecutionException, InterruptedException {
        String requestId = generateRequestId();
        CompletableFuture<List<String>> future = new CompletableFuture<>();
        nicknameFutures.put(requestId, future);

        String userIdList = userIds.stream().map(String::valueOf).collect(Collectors.joining(","));
        com.example.demo.avro.UserIdListAvro userIdListAvro = new com.example.demo.avro.UserIdListAvro();
        userIdListAvro.setUserIds(userIdList); // 필드 설정
        userIdListAvro.setRequestId(requestId); // 필드 설정
        kafkaProducer.send("user-id-list-topic", null, userIdListAvro);

        return future.get(); // 닉네임 응답을 기다림
    }


    private String generateRequestId() {
        return java.util.UUID.randomUUID().toString();
    }

    private Map<Long, String> createUserIdToNicknameMap(List<Long> userIds, List<String> nicknames) {
        return userIds.stream().collect(Collectors.toMap(userId -> userId, userId -> {
            int index = userIds.indexOf(userId);
            return nicknames.get(index);
        }));
    }
}
