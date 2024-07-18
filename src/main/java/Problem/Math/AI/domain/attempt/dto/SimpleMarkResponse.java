package Problem.Math.AI.domain.attempt.dto;

import Problem.Math.AI.domain.attempt.entity.Status;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SimpleMarkResponse {
    private Long attemptId;
    private Long problemId;
    private Status status;
}
