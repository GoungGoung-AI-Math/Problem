package Problem.Math.AI.domain.problem.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.boot.model.naming.NamingStrategyHelper;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProblemCreationResponse {
    private Long problemId;
    private LocalDateTime createDate;
    private Long userId;
    private String userName;
    private String problemName;
    private String imgUrl;
    private Double difficulty;
    private Integer answer;
    private List<Long> conceptTags;
}
