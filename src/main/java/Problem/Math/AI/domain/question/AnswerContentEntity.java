package Problem.Math.AI.domain.question;

import Problem.Math.AI.common.entity.ContentEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Table(name = "answer_content")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AnswerContentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "img_url")
    private String imgUrl;
    @ManyToOne
    @JoinColumn(name = "answer_id")
    private Question question;
}
