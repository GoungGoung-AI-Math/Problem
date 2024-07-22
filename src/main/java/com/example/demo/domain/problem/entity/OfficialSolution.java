package com.example.demo.domain.problem.entity;

import com.example.demo.common.dto.ContentRequest;
import com.example.demo.domain.problem.dto.OfficialSolutionCreationRequest;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Builder
@Table(name = "official_solution")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OfficialSolution{

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "create_date", updatable = false)
    @CreatedDate
    private LocalDateTime createDate;

    @LastModifiedDate
    private LocalDateTime updatedDate;

    @Getter
    @Column(name = "text_solution")
    private String textSolution;

    /**
     * 풀이의 출처를 작성
     */
    @Column
    private String source;

    @Getter
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "official_solution_id")
    private Set<SolutionContentEntity> solutionContents;

    public static OfficialSolution toEntity(OfficialSolutionCreationRequest request) {

        return OfficialSolution.builder()
                .createDate(LocalDateTime.now())
                .textSolution(request.getTextSolution())
                .source(request.getSource())
                .build();
    }
    public void setSolutionContents(Set<SolutionContentEntity> entitySet){
        this.solutionContents = entitySet;
    }


}
