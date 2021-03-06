package br.com.harbitech.school.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    Optional<Category> findByCodeUrl(String codeUrl);

    List<Category> findAllByStatus(CategoryStatus status);

    List<Category> findAllByOrderByName();

    @Query(value = "SELECT DISTINCT c FROM Category c JOIN FETCH c.subCategories sc JOIN sc.courses co " +
            "WHERE c.status = 'ACTIVE' AND co.visibility = 'PUBLIC' AND sc.status = 'ACTIVE'" +
            "ORDER BY c.orderVisualization, sc.orderVisualization")
    List<Category> findAllActiveCategoriesWithPublicCourses();

    boolean existsByCodeUrl(String codeUrl);

    @Deprecated
    boolean existsByCodeUrlAndIdNot(String codeUrl, Long id);

    default boolean existsByCodeUrlWithDifferentId(String codeUrl, Long id){
        return existsByCodeUrlAndIdNot(codeUrl, id);
    }
}
