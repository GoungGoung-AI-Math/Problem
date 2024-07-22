package com.example.demo.domain.problem.entity;

import com.example.demo.common.dto.ContentRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@SuperBuilder
@Table(name = "solution_content")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SolutionContentEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Getter
    @Column(name = "img_url")
    private String imgUrl;
    @ManyToOne
    @JoinColumn(name = "official_solution_id")
    private OfficialSolution officialSolution;

    public static Set<SolutionContentEntity> toEntity(OfficialSolution officialSolution, List<ContentRequest> requests){
        return requests.stream().map( req -> SolutionContentEntity.builder()
                        .officialSolution(officialSolution)
                        .imgUrl(req.getImgUrl()).build())
                .collect(Collectors.toSet());
    }
}
