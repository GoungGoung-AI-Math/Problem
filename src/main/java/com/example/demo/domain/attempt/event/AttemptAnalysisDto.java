package com.example.demo.domain.attempt.event;

import com.example.demo.common.AnalysisType;
import com.example.demo.domain.gpt.dto.MessageType;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AttemptAnalysisDto {
    private Long attemptId;
    private AnalysisType analysisType;
    private MessageType messageType;
    private String content;
}
