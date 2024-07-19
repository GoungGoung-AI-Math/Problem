package Problem.Math.AI.domain.problem.repository;

import Problem.Math.AI.domain.problem.entity.Problem;
import org.springframework.data.domain.Page;

public interface ProblemRepositoryCustom {
    Page<Problem> searchAll();
}
