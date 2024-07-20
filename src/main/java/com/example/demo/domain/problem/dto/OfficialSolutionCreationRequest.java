package com.example.demo.domain.problem.dto;

import com.example.demo.common.dto.ContentRequest;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OfficialSolutionCreationRequest {
    private String source;
    private String textSolution;
    private List<ContentRequest> imgSolutions;
}
