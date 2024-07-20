package com.example.demo.domain.exam.dto.response;

import com.example.demo.domain.exam.entity.Difficulty;
import com.example.demo.domain.exam.entity.RevisionState;
import com.example.demo.domain.exam.entity.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchExamResponse {
    private String examName;
    private LocalDateTime createDate;
    private Difficulty difficulty;
    private Type type;
    private RevisionState revisionState;
    private Long totalSolveCount;
}
