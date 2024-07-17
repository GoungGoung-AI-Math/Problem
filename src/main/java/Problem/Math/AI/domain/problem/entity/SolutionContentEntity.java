package Problem.Math.AI.domain.problem.entity;

import Problem.Math.AI.domain.ContentEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Table(name = "solution_content")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SolutionContentEntity extends ContentEntity {

    @ManyToOne
    @JoinColumn(name = "official_solution_id")
    private OfficialSolution officialSolution;
}
