package Problem.Math.AI.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Builder
@Table(name = "problem")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Problem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column
    private String name;

    @Column(name = "img_url")
    private String imgUrl;

    @Column
    private Double difficulty;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column
    private Integer answer;

}
