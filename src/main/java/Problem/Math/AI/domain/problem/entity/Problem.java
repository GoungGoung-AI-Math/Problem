package Problem.Math.AI.domain.problem.entity;

import Problem.Math.AI.common.entity.BaseEntity;
import Problem.Math.AI.domain.problem.dto.ProblemCreationRequest;
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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "problem_id")
    private Set<ProblemConceptTag> problemConceptTags;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "official_solution_id")
    private OfficialSolution officialSolution;


    public Problem toEntity(ProblemCreationRequest request, Set<ProblemConceptTag> tags, OfficialSolution solution){
        return Problem.builder()
                .userId(request.getUserId())
                .name(request.getName())
                .imgUrl(request.getImgUrl())
                .difficulty(request.getDifficulty())
                .answer(request.getAnswer())
                .problemConceptTags(tags)
                .officialSolution(solution)
                .build();
    }
}
