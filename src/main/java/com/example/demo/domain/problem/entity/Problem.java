package com.example.demo.domain.problem.entity;

import com.example.demo.domain.exam.entity.Difficulty;
import com.example.demo.domain.exam.entity.Exam;
import com.example.demo.domain.problem.dto.ProblemCreationRequest;
import com.example.demo.domain.review.entity.ReviewContent;
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
@Table(name = "problem")
@AllArgsConstructor
@NoArgsConstructor
public class Problem {
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
    @Column(name = "user_id")
    private Long userId;

    @Column
    private String name;

    @Getter
    @Column(name = "img_url")
    private String imgUrl;

    @Column
    private Difficulty difficulty;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;

    @Getter
    @Column
    private Integer answer;

    @Getter
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "problem_id")
    private Set<ProblemConceptTag> problemConceptTags;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "official_solution_id")
    private OfficialSolution officialSolution;


    public static Problem toEntity(ProblemCreationRequest request, OfficialSolution solution){
        return Problem.builder()
                .userId(request.getUserId())
                .name(request.getName())
                .imgUrl(request.getImgUrl())
                .difficulty(request.getDifficulty())
                .answer(request.getAnswer())
                .officialSolution(solution)
                .createDate(LocalDateTime.now())
                .build();
    }
}
