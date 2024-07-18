package Problem.Math.AI.domain.attempt.entity;

import Problem.Math.AI.common.entity.ContentEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@Table(name = "attempt_content")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AttemptContentEntity extends ContentEntity {

    @ManyToOne
    @JoinColumn(name = "problem_attempt_id")
    private ProblemAttempt problemAttempt;
}
