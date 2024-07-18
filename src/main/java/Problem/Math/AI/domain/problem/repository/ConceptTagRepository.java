package Problem.Math.AI.domain.problem.repository;

import Problem.Math.AI.domain.problem.entity.ConceptTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConceptTagRepository extends JpaRepository<ConceptTag, Long> {
    List<ConceptTag> findByIdIn(List<Long> ids);
}
