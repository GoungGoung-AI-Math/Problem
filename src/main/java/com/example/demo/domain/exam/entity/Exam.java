package com.example.demo.domain.exam.entity;

import com.example.demo.domain.problem.entity.Problem;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column()
    private Long userId;

    private String name;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    @Enumerated(EnumType.STRING)
    private RevisionState revisionState;

    @Enumerated(EnumType.STRING)
    private Type type;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "exam_id")
    private Set<Problem> problems;

    @Column(name = "total_solved")
    private Long totalSolved;


    public Exam(String name, Long totalSolved) {
        this.name = name;
        this.totalSolved = totalSolved;
    }
}
