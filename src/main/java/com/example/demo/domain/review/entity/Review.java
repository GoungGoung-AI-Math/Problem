package com.example.demo.domain.review.entity;

import com.example.demo.domain.problem.entity.Problem;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
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

    @Column
    private String content;

    @Column
    private Boolean isSuccess;

    @Column
    @Enumerated(EnumType.STRING)
    private ReviewerType reviewerType;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "review_id")
    private Set<ReviewContent> reviewContents;

}
