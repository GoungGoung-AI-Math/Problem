package Problem.Math.AI.domain.test.dto.response;

import Problem.Math.AI.domain.test.entity.Difficulty;
import Problem.Math.AI.domain.test.entity.RevisionStatus;
import Problem.Math.AI.domain.test.entity.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestSearchResponse {
    private String name;
    private LocalDateTime createDate;
    private Difficulty difficulty;
    private Type type;
    private RevisionStatus revisionStatus;
    private Long totalSolveCount;
}
