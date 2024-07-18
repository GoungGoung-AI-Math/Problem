package Problem.Math.AI.domain.problem.repository;

import Problem.Math.AI.domain.problem.entity.Problem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProblemRepository extends JpaRepository<Problem, Long> {

    @Query(value = "Select p.answer FROM Problem p WHERE p.id = :id")
    Integer findAnswerById(@Param("id") Long id);
}
