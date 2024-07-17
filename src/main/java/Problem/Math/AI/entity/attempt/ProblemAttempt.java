package Problem.Math.AI.entity.attempt;

import Problem.Math.AI.entity.BaseEntity;
import Problem.Math.AI.entity.problem.Problem;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Table(name = "problem_attempt")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProblemAttempt extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "problem_id", nullable = false)
    private Problem problem;

    @Column
    private String title;

    @Column
    private String content;
}
