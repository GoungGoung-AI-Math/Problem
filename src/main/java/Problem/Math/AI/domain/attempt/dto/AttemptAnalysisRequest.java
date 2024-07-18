package Problem.Math.AI.domain.attempt.dto;

import Problem.Math.AI.common.AnalysisType;
import Problem.Math.AI.domain.gpt.dto.VisionReqDto;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AttemptAnalysisRequest{
    private Long attemptId;
    private AnalysisType type;
    private VisionReqDto visionReqDto;
}
