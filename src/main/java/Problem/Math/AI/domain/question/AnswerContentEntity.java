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

@SuperBuilder
@Builder
@Table(name = "answer_content")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AnswerContentEntity extends ContentEntity {
    @ManyToOne
    @JoinColumn(name = "answer_id")
    private Question question;
}
