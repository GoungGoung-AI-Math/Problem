package Problem.Math.AI.domain.attempt;

import Problem.Math.AI.common.entity.ContentEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Table(name = "attempt_content")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AttemptContentEntity extends ContentEntity {

    @ManyToOne
    @JoinColumn(name = "problem_attempt")
    private ProblemAttempt problemAttempt;
}
