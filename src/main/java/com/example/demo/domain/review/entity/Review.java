package com.example.demo.domain.review.entity;

import com.example.demo.domain.attempt.mvc.entity.ProblemAttempt;
import com.example.demo.domain.problem.entity.Problem;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@Table(name = "review")
@AllArgsConstructor
@NoArgsConstructor
public class Review {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "create_date", updatable = false)
    @CreatedDate
    private LocalDateTime createDate;

    @LastModifiedDate
    private LocalDateTime updatedDate;

    @Getter
    @Column(name = "user_id", nullable = true)
    private Long userId;

    @Column
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column
    @Enumerated(EnumType.STRING)
    private ReviewerType reviewerType;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "review_id")
    private Set<ReviewContent> reviewContents;

    @ManyToOne
    @JoinColumn(name = "problem_attempt_id", nullable = false)
    private ProblemAttempt problemAttempt;


    public static Review aiAnalysis(String content) {
        // content에서 앞 15글자를 추출하여 title 변수에 저장합니다.
        String title = content.length() > 15 ? content.substring(0, 15) : content;
        return Review.builder()
                .createDate(LocalDateTime.now())
                .content(content)
                .title(title)
                .reviewerType(ReviewerType.AI)
                .build();
    }
}
