package Problem.Math.AI.domain.test.entity;

import Problem.Math.AI.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Builder
@Table(name = "test")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Test extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    @Enumerated(EnumType.STRING)
    private Type type;

    @Enumerated(EnumType.STRING)
    private RevisionStatus revisionStatus;
}
