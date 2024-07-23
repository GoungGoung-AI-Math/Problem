package com.example.demo.domain.question.mvc.entity;

import com.example.demo.common.entity.ContentType;
import com.example.demo.domain.problem.entity.OfficialSolution;
import com.example.demo.domain.problem.entity.Problem;
import com.example.demo.domain.question.mvc.dto.QuestionCreateRequest;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;


@Entity
@Builder
@Table(name = "question")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Question {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "create_date", updatable = false)
    @CreatedDate
    private LocalDateTime createDate;

    @LastModifiedDate
    private LocalDateTime updatedDate;

    @Column
    private String title;

    @Column
    private String content;

    @ManyToOne
    @JoinColumn(name = "problem_id", nullable = false)
    private Problem problem;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Set<Answer> answers;

    @Getter
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Set<QuestionContentEntity> questionContentEntities;

    public static Question toEntity(QuestionCreateRequest questionCreateRequest) {
        Set<QuestionContentEntity> contentEntities = questionCreateRequest.getContents().stream()
                .filter(qc -> qc.getType().equals(ContentType.IMAGE_URL))
                .map(qc -> QuestionContentEntity.builder().imgUrl(qc.getContent()).build()).collect(Collectors.toSet());
        StringBuilder sb = new StringBuilder();
        questionCreateRequest.getContents().stream().filter(qc -> qc.getType().equals(ContentType.TEXT))
                .forEach(c -> sb.append(c.getContent()).append(" "));
        String textContent = sb.toString();

        return Question.builder()
                .userId(questionCreateRequest.getUserId())
                .createDate(LocalDateTime.now())
                .title(questionCreateRequest.getTitle())
                .content(textContent)
                .questionContentEntities(contentEntities)
                .build();
    }
}
