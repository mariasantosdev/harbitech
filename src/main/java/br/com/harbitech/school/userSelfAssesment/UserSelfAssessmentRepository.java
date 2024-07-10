package br.com.harbitech.school.userSelfAssesment;

import br.com.harbitech.school.category.Category;
import br.com.harbitech.school.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserSelfAssessmentRepository extends JpaRepository<UserSelfAssessment, Long> {
    @Query("SELECT usa FROM UserSelfAssessment usa WHERE usa.user = :user AND usa.subcategory.category = :category")
    Optional<UserSelfAssessment> findByUserAndCategory(@Param("user") User user, @Param("category") Category category);
}
