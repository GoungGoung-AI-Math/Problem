package com.example.demo.domain.question.mvc.dto;

import com.example.demo.common.entity.ContentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionContent {
    private ContentType type;
    private String content;
}
