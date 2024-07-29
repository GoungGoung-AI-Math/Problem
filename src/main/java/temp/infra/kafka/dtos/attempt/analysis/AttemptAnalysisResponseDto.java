package temp.infra.kafka.dtos.attempt.analysis;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import temp.infra.kafka.dtos.AnalysisType;
import temp.infra.kafka.dtos.MessageType;

@Getter
@Builder
@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AttemptAnalysisResponseDto {
    private Long attemptId;
    private AnalysisType analysisType;
    private MessageType messageType;
    private String content;
}
