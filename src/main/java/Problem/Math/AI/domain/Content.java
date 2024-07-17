package Problem.Math.AI.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "img_url")
    private String imgUrl;
}
