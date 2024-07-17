package Problem.Math.AI.domain.problem;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Table(name = "concep_tag")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ConceptTag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "tag_name")
    private String tageName;
}
