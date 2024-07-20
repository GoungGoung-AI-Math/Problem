package com.example.demo.domain.attempt.dto;

import com.example.demo.common.dto.ContentRequest;
import com.example.demo.domain.attempt.entity.AttemptType;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class  AttemptMarkRequest{
    private Long problemId;
    private AttemptType type;
    private List<ContentRequest> imgUrlsContent;
    private String textContent;
    private Integer answer;
    private Long userId;
}
