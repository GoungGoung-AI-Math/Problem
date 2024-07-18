package Problem.Math.AI.domain.problem.repository;

import Problem.Math.AI.domain.problem.entity.OfficialSolution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficialSolutionRepository extends JpaRepository<OfficialSolution, Long> {
}
