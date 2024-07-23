package com.example.demo.domain.attempt.mvc.entity;

import com.example.demo.common.dto.ContentRequest;
import com.example.demo.domain.attempt.mvc.dto.AttemptMarkRequest;
import com.example.demo.domain.problem.entity.Problem;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 학생이 문제를 푼 값.
 */
@Entity
@Builder
@Table(name = "problem_attempt")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProblemAttempt{

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "create_date", updatable = false)
    @CreatedDate
    private LocalDateTime createDate;

    @LastModifiedDate
    private LocalDateTime updatedDate;

    @ManyToOne
    @JoinColumn(name = "problem_id", nullable = false)
    private Problem problem;

    @Column
    private String content;

    @Getter
    @Enumerated(EnumType.STRING)
    @Column
    private Status status;

    @Column(name = "user_id")
    private Long userId;

    @Column
    private Integer answer;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "problem_attempt_id")
    private Set<AttemptContentEntity> attemptContents;



    public static ProblemAttempt toEntity(AttemptMarkRequest request, Problem problem, Status status){
        return ProblemAttempt.builder()
                .problem(problem)
                .content(request.getTextContent())
                .status(status)
                .userId(request.getUserId())
                .answer(request.getAnswer())
                .attemptContents(toEntity(request.getImgUrlsContent()))
                .build();
    }

    private static Set<AttemptContentEntity> toEntity(List<ContentRequest> requests){
        if(requests==null || requests.isEmpty()){
            return new HashSet<>();
        }
        return requests.parallelStream().map( req -> AttemptContentEntity.builder()
                        .imgUrl(req.getImgUrl()).build())
                .collect(Collectors.toSet());
    }

    public void updateState(Status status) {
        this.status = status;
    }
}
