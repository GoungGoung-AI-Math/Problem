package com.example.demo.domain.attempt.dto;

import com.example.demo.common.AnalysisType;
import com.example.demo.domain.gpt.dto.ReqContent;
import com.example.demo.domain.gpt.dto.VisionReqDto;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.List;

@Builder
@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AttemptAnalysisRequest{
    private Long attemptId;
    private AnalysisType type;
    private List<ReqContent> contents;
}
