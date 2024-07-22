package com.example.demo.domain.problem.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

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
}
