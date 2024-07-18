package Problem.Math.AI.domain.question;

import Problem.Math.AI.common.entity.ContentEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@Table(name = "solution_content")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuestionContentEntity extends ContentEntity {
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
}
