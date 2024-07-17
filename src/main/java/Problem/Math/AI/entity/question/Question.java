package Problem.Math.AI.entity.question;

import Problem.Math.AI.entity.BaseEntity;
import Problem.Math.AI.entity.problem.Problem;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Entity
@Builder
@Table(name = "problem_attempt")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Question extends BaseEntity {
    @Column
    private String content;

    @ManyToOne
    @JoinColumn(name = "problem_id", nullable = false)
    private Problem problem;
}
