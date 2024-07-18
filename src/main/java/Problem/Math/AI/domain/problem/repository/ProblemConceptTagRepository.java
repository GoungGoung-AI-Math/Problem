package Problem.Math.AI.domain.problem.repository;

import Problem.Math.AI.domain.problem.entity.ProblemConceptTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProblemConceptTagRepository extends JpaRepository<ProblemConceptTag, Long> {
}
