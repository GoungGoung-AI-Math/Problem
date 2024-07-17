package Problem.Math.AI.domain.problem.entity;

import Problem.Math.AI.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Builder
@Table(name = "problem")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Problem extends BaseEntity {

    @Column(name = "user_id")
    private Long userId;

    @Column
    private String name;

    @Column(name = "img_url")
    private String imgUrl;

    @Column
    private Double difficulty;

    @Column
    private Integer answer;

    @OneToMany
    @JoinColumn(name = "problem_id")
    private Set<ProblemConceptTag> problemConceptTags;

    @OneToOne
    @JoinColumn(name = "official_solution_id")
    private OfficialSolution officialSolution;

}
