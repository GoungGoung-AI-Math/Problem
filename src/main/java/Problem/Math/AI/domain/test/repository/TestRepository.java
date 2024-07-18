package Problem.Math.AI.domain.test.repository;

import Problem.Math.AI.domain.test.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<Test, Long>, TestRepositoryCustom {
}
