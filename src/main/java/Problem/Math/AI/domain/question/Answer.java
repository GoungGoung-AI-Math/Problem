package Problem.Math.AI.domain.question;

import Problem.Math.AI.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Entity
@Builder
@Table(name = "problem_attempt")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Answer extends BaseEntity {
    @Column
    private String title;

    @Column
    private String content;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;
}
