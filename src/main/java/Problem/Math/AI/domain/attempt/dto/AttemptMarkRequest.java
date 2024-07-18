package Problem.Math.AI.domain.attempt.dto;

import Problem.Math.AI.common.dto.ContentRequest;
import Problem.Math.AI.domain.attempt.entity.AttemptType;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@JsonNaming(value = PropertyNamingStrategy.class)
public class  AttemptMarkRequest{
    private Long problemId;
    private AttemptType attemptType;
    private List<ContentRequest> imgUrlsContent;
    private String textContent;
    private Integer answer;
    private Long userId;
}
