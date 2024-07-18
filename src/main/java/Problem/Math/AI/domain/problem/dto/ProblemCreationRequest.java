package Problem.Math.AI.domain.problem.dto;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProblemCreationRequest {
    private Long userId;
    private String name;
    private String imgUrl;
    private Double difficulty;
    private Integer answer;
    private List<Long> conceptTags;
    private OfficialSolutionCreationRequest officialSolution;
}
