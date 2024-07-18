package Problem.Math.AI.domain.question;

import Problem.Math.AI.domain.BaseEntity;
import Problem.Math.AI.domain.problem.entity.Problem;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;


@Entity
@Builder
@Table(name = "problem_attempt")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Question extends BaseEntity {

    @Column
    private String title;

    @Column
    private String content;

    @ManyToOne
    @JoinColumn(name = "problem_id", nullable = false)
    private Problem problem;

    @Column(name = "user_id")
    private Long userId;

    @OneToMany
    @JoinColumn(name = "question_id")
    private Set<Answer> answers;
}
