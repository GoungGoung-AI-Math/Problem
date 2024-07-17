package Problem.Math.AI.entity.problem;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@Table(name = "official_solution")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OfficialSolution {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "text_solution")
    private String textSolution;

    /**
     * 풀이의 출처를 작성
     */
    @Column
    private String source;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @OneToMany
    @JoinColumn(name = "official_solution_id")
    private Set<SolutionContent> solutionContents = new HashSet<>();

}
