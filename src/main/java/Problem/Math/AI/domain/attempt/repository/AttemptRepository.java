package Problem.Math.AI.domain.attempt.repository;

import Problem.Math.AI.domain.attempt.entity.ProblemAttempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttemptRepository extends JpaRepository<ProblemAttempt, Long> {

}
