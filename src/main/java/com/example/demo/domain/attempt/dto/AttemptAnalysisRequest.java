package com.example.demo.domain.attempt.dto;

import com.example.demo.common.AnalysisType;
import com.example.demo.domain.gpt.dto.MessageType;
import com.example.demo.domain.gpt.dto.ReqContent;
import com.example.demo.domain.gpt.dto.VisionReqDto;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Generated;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AttemptAnalysisRequest{
    private Long attemptId;
    private AnalysisType analysisType;
    private MessageType messageType;
    private String content;
}
