package br.com.harbitech.school.subcategory;

import br.com.harbitech.school.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import java.util.List;

@Repository
public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {
    Optional<Subcategory> findByCodeUrl(String codeUrl);

    List<Subcategory> findAllByCategory(Category category);

    List<Subcategory> findAllByCategoryOrderByOrderVisualization(Category category);

    List<Subcategory> findAllByOrderByName();

    @Query(value = "SELECT DISTINCT sc FROM Subcategory sc JOIN FETCH sc.courses c " +
            "WHERE c.visibility = 'PUBLIC' AND sc.status = 'ACTIVE' AND sc.category = :category " +
            "ORDER BY sc.level")
    List<Subcategory> findAllActiveSubcategories(@Param("category") Category category);

    @Query(value = """
            SELECT COALESCE(MAX(s.`level`), 0) FROM user_self_assessment usa
                JOIN subcategory s ON s.id = usa.subcategory_id
                JOIN category c ON c.id = :categoryId
                JOIN course co ON co.subcategory_id = s.id
            WHERE user_id = :userId
            AND co.visibility = 'PUBLIC'
            AND s.status = 'ACTIVE'
            """, nativeQuery = true)
    int userLevel(@Param("userId") Long userId, Long categoryId);

    @Query(value = "SELECT MAX(level) FROM subcategory", nativeQuery = true)
    int findMaxLevel();

    boolean existsByCodeUrl(String codeUrl);

    @Deprecated
    boolean existsByCodeUrlAndIdNot(String codeUrl, Long id);

    default boolean existsByCodeUrlWithDifferentId(String codeUrl, Long id) {
        return existsByCodeUrlAndIdNot(codeUrl, id);
    }
}
