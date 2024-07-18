package Problem.Math.AI.domain.problem.entity;

import Problem.Math.AI.common.entity.ContentEntity;
import jakarta.persistence.*;
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
public class SolutionContentEntity extends ContentEntity {

    @ManyToOne
    @JoinColumn(name = "official_solution_id")
    private OfficialSolution officialSolution;
}
