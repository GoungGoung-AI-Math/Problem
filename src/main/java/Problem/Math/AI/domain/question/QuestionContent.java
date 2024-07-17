package Problem.Math.AI.domain.question;

import Problem.Math.AI.domain.Content;
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
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuestionContent extends Content {
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
}
