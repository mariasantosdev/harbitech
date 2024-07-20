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
            WITH LevelOfCurrentKnowledge AS(
            SELECT
            	COALESCE(max(s.`level`), 0) AS currentKnowledge, COALESCE(max(s.`level`) + 1, 0) AS nextLevel, s.id
            FROM
            	user_self_assessment usa
            JOIN subcategory s ON
            	s.id = usa.subcategory_id
            JOIN category c ON
            	c.id = s.category_id AND s.category_id = :categoryId
            ),
            PublicSubcatoriesWithCourses AS(
            	SELECT sub.* FROM subcategory sub
            JOIN course c ON c.subcategory_id = sub.id
            AND c.visibility = 'PUBLIC'
            AND sub.status = 'ACTIVE'
            AND sub.category_id = :categoryId
            GROUP BY sub.id)
            SELECT * FROM PublicSubcatoriesWithCourses pswc WHERE pswc.level = (SELECT nextLevel FROM LevelOfCurrentKnowledge)
     """, nativeQuery = true)
    List<Subcategory> findNextLevelSubcategories(@Param("categoryId") Long categoryId);

    @Query(value = """

            WITH LevelOfCurrentKnowledge AS (
         SELECT s.level, s.id AS subcategoryId, e.course_id AS courseId
         FROM course
             JOIN subcategory s ON s.id = course.subcategory_id
             JOIN enrollment e ON e.course_id = course.id
             JOIN user_self_assessment usa ON usa.user_id = :userId
         WHERE e.user_id = :userId AND e.finished = true
         GROUP BY course.id
     ),
     AllCousesByLevel AS (
         SELECT course.*, s.id AS subcategoryId
         FROM course
             JOIN subcategory s ON s.id = course.subcategory_id
     )
     SELECT
     CASE
     WHEN COUNT(kock.courseId) = (
         SELECT COUNT(*)
     FROM AllCousesByLevel acl
     WHERE acl.subcategoryId = kock.subcategoryId
     ) THEN 'true'
     ELSE 'false'
     END AS allCoursesCompleted, kock.subcategoryId
     FROM LevelOfCurrentKnowledge kock	
     GROUP BY kock.subcategoryId
     ORDER BY kock.level DESC
     LIMIT 1
    """, nativeQuery = true)
    Optional<Boolean> getAllCoursesCompleted(Long userId);

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
