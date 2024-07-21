package com.example.demo.domain.problem.dto.response;

import com.example.demo.domain.exam.entity.Difficulty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class ProblemResponse {
    private Long id;
    private String name;
    private String imgUrl;
    private Difficulty difficulty;
    private LocalDateTime createDate;
    private Set<String> tags;
    private Long totalSolved;

    public ProblemResponse(Long id, String name, String imgUrl, Difficulty difficulty, LocalDateTime createDate, Long totalSolved) {
        this.id = id;
        this.name = name;
        this.imgUrl = imgUrl;
        this.difficulty = difficulty;
        this.createDate = createDate;
        this.totalSolved = totalSolved;
    }
}
