package com.example.demo.domain.problem.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Table(name = "problem_concep_tag")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProblemConceptTag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Setter
    @ManyToOne
    @JoinColumn(name = "problem_id")
    private Problem problem;

    @ManyToOne
    @JoinColumn(name = "concep_tag_id")
    private ConceptTag conceptTag;
}
