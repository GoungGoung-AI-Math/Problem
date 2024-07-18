package Problem.Math.AI.domain.problem.dto;

import Problem.Math.AI.common.dto.ContentRequest;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OfficialSolutionCreationRequest {
    private String source;
    private String textSolution;
    private List<ContentRequest> imgSolutions;
}
