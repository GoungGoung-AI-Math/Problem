package Problem.Math.AI.domain.problem.entity;

import Problem.Math.AI.common.dto.ContentRequest;
import Problem.Math.AI.common.entity.BaseEntity;
import Problem.Math.AI.domain.problem.dto.OfficialSolutionCreationRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@SuperBuilder
@Table(name = "official_solution")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OfficialSolution extends BaseEntity {

    @Column(name = "text_solution")
    private String textSolution;

    /**
     * 풀이의 출처를 작성
     */
    @Column
    private String source;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "official_solution_id")
    private Set<SolutionContentEntity> solutionContents;

    public static OfficialSolution toEntity(OfficialSolutionCreationRequest request) {

        return OfficialSolution.builder()
                .createDate(LocalDateTime.now())
                .textSolution(request.getTextSolution())
                .source(request.getSource())
                .solutionContents(toEntity(request.getImgSolutions()))
                .build();
    }

    private static Set<SolutionContentEntity> toEntity(List<ContentRequest> requests){
        return requests.parallelStream().map( req -> SolutionContentEntity.builder()
                                                    .imgUrl(req.getImgUrl()).build())
                .collect(Collectors.toSet());
    }
}
