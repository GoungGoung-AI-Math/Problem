package com.example.demo.domain.exam.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamSearchCondition {
    private Integer startYear;
    private Integer endYear;
    private Integer month;
    private Integer page;
    private Integer pageSize;
}
