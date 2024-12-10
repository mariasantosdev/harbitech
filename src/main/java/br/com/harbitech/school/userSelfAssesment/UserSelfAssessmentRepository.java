package br.com.harbitech.school.userSelfAssesment;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserSelfAssessmentRepository extends JpaRepository<UserSelfAssessment, Long> {
    @Query(value = """
            SELECT usa.* FROM user_self_assessment usa
             	JOIN subcategory sc ON sc.id = usa.subcategory_id
             WHERE usa.user_id = :userId
             AND sc.category_id = :categoryId
            """, nativeQuery = true)
    Optional<UserSelfAssessment> findByUserAndCategory(@Param("userId") Long userId, @Param("categoryId") Long categoryId);
}
