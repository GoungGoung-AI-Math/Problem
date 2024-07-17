package Problem.Math.AI.domain.attempt;

import Problem.Math.AI.domain.BaseEntity;
import Problem.Math.AI.domain.problem.entity.Problem;
import jakarta.persistence.*;
import lombok.*;

/**
 * 학생이 문제를 푼 값.
 */
@Entity
@Builder
@Table(name = "problem_attempt")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProblemAttempt extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "problem_id", nullable = false)
    private Problem problem;

    @Column
    private String content;

    @Column(name = "is_success")
    private Boolean isSuccess;

    @Enumerated(EnumType.STRING)
    @Column
    private Status status;

    @Column(name = "user_id")
    private Long userId;

    @Column
    private Integer answer;
}
