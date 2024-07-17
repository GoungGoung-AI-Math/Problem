package Problem.Math.AI.entity.attempt;

import Problem.Math.AI.entity.Content;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Table(name = "attempt_content")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AttemptContent extends Content {

    @ManyToOne
    @JoinColumn(name = "problem_attempt")
    private ProblemAttempt problemAttempt;
}
