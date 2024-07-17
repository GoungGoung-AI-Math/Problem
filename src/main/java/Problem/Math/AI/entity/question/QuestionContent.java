package Problem.Math.AI.entity.question;

import Problem.Math.AI.entity.Content;
import Problem.Math.AI.entity.problem.OfficialSolution;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Table(name = "solution_content")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuestionContent extends Content {
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
}
