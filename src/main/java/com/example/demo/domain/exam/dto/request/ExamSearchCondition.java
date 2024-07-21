package com.example.demo.domain.exam.dto.request;

import com.example.demo.domain.exam.entity.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamSearchCondition {
    private Integer startYear;
    private Integer endYear;
    private List<Integer> months;
    private List<Type> types;
    private Integer page;
    private Integer pageSize;
}
