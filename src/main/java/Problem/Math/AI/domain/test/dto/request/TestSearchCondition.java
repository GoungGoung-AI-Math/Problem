package Problem.Math.AI.domain.test.dto.request;

import lombok.Getter;

@Getter
public class TestSearchCondition {
    private Integer startYear;
    private Integer endYear;
    private Integer month;
    private Integer page;
    private Integer pageSize;
}
