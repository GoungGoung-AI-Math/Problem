package com.example.demo.domain.attempt.mvc.dto;

import com.example.demo.domain.attempt.mvc.entity.Status;
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
