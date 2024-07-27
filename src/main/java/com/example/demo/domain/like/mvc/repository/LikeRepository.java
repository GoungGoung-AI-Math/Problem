package com.example.demo.domain.like.mvc.repository;

import com.example.demo.domain.problem.entity.Problem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Problem, Long> {

    @Query(value = "Select COUNT(p.id) FROM Problem p where p.id = :id and p.userId = :userId limit 1", nativeQuery = true)
    boolean exitsProblemById(@Param("id") Long id, @Param("userId") Long userId);

    @Query(value = "Select COUNT(p.id) FROM ProblemAttempt p where p.id = :id and p.userId = :userId  limit 1", nativeQuery = true)
    boolean exitsAttemptById(@Param("id") Long id, @Param("userId") Long userId);

    @Query(value = "Select COUNT(p.id) FROM Review p where p.id = :id and p.userId = :userId  limit 1", nativeQuery = true)
    boolean exitsReviewById(@Param("id") Long id, @Param("userId") Long userId);

    @Query(value = "Select COUNT(p.id) FROM Question p where p.id = :id and p.userId = :userId  limit 1", nativeQuery = true)
    boolean exitsQuestionById(@Param("id") Long id, @Param("userId") Long userId);

    @Query(value = "Select COUNT(p.id) FROM Answer p where p.id = :id and p.userId = :userId  limit 1", nativeQuery = true)
    boolean exitsAnswerById(@Param("id") Long id, @Param("userId") Long userId);
}
