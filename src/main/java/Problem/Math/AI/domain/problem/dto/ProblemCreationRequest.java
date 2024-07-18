package Problem.Math.AI.domain.problem.dto;

import jakarta.persistence.Column;

import java.time.LocalDateTime;

public class ProblemCreationRequest {
    private Long userId;

    private String name;

    private String imgUrl;

    private Double difficulty;

    private LocalDateTime createDate;
    private Integer answer;
}
