package br.com.harbitech.school.subcategory;

import br.com.harbitech.school.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

import java.util.List;

@Repository
public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {
    Optional<Subcategory> findByCodeUrl(String codeUrl);

    @Query(value = """
            SELECT * FROM subcategory s
            	JOIN course c on c.subcategory_id = s.id
            WHERE s.level =:level
            AND s.status = 'ACTIVE'
            AND c.visibility = 'PUBLIC'
            GROUP BY s.id; 
            """, nativeQuery = true)
    List<Subcategory> findAllActiveByLevel(int level);

    @Query(value = """
            SELECT * FROM subcategory s
            	JOIN course c on c.subcategory_id = s.id
            WHERE s.level =:level
            AND s.status = 'ACTIVE'
            AND c.visibility = 'PUBLIC'
            AND s.subcategory_base = :subcategoryBaseId
            GROUP BY s.id
            """, nativeQuery = true)
    List<Subcategory> findAllActiveByLevelWithSubcategoryBase(int level, Long subcategoryBaseId);

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
                                    WHERE usa.user_id = :userId
                                    ),
                                    PublicSubcatoriesWithCourses AS(
                                    	SELECT sub.* FROM subcategory sub
                                    JOIN course c ON c.subcategory_id = sub.id
                                    AND c.visibility = 'PUBLIC'
                                    AND sub.status = 'ACTIVE'
                                    AND sub.category_id = :categoryId
                                    GROUP BY sub.id)
                                    SELECT * FROM PublicSubcatoriesWithCourses pswc WHERE pswc.level = (SELECT nextLevel FROM LevelOfCurrentKnowledge)
                                    ORDER BY level
            """, nativeQuery = true)
    List<Subcategory> findNextLevelSubcategories(@Param("categoryId") Long categoryId, @Param("userId") Long userId);

    @Query(value = """
                                  WITH LevelOfCurrentKnowledge AS(
                                                SELECT
                                                	COALESCE(max(s.`level`), 0) AS currentKnowledge, COALESCE(max(s.`level`) + 1, 0) AS nextLevel,\s
                                                	s.id as subcategoryId
                                                FROM
                                                	user_self_assessment usa
                                                JOIN subcategory s ON
                                                	s.id = usa.subcategory_id
                                                JOIN category c ON
                                                	c.id = s.category_id AND s.category_id = :categoryId
                                                WHERE usa.user_id = :userId
                                                ),
                                                PublicSubcatoriesWithCourses AS(
                                                	SELECT sub.* FROM subcategory sub
                                                JOIN course c ON c.subcategory_id = sub.id
                                                AND c.visibility = 'PUBLIC'
                                                AND sub.status = 'ACTIVE'
                                                AND sub.category_id = :categoryId
                                                AND sub.subcategory_base = (SELECT subcategoryId FROM LevelOfCurrentKnowledge)
                                                GROUP BY sub.id)
                                                SELECT * FROM PublicSubcatoriesWithCourses pswc WHERE pswc.level = (SELECT nextLevel FROM LevelOfCurrentKnowledge)
                                                ORDER BY level
            """, nativeQuery = true)
    List<Subcategory> findNextLevelSubcategoriesWithSubcategoryBase(@Param("categoryId") Long categoryId, @Param("userId") Long userId);


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
                                    SELECT * FROM PublicSubcatoriesWithCourses pswc WHERE pswc.level >= (SELECT currentKnowledge FROM LevelOfCurrentKnowledge)
                                    ORDER BY level
            """, nativeQuery = true)
    List<Subcategory> findCurrentKnowLevelSubcategories(@Param("categoryId") Long categoryId);

    @Query(value = """
                WITH LevelOfCurrentKnowledge AS (
                    SELECT
                    COUNT(*) as totalCoursesBySubcatoryAndUser
                    FROM user_self_assessment usa
                        JOIN subcategory s ON s.id = usa.subcategory_id
                        JOIN course c ON c.subcategory_id = s.id
                        JOIN enrollment e ON e.course_id = c.id
                    WHERE s.id =:subcategoryId
                    AND e.finished = true
                    AND e.user_id =:userId
                    AND s.status = 'ACTIVE'
            ), AllCousesByLevel AS (
                    SELECT COUNT(c.id) as totalCoursesByCategory
                    FROM course c
                        JOIN subcategory s ON s.id = c.subcategory_id
                    WHERE s.id =:subcategoryId
            )
            SELECT CASE
                    WHEN (SELECT * FROM AllCousesByLevel) = (SELECT * FROM LevelOfCurrentKnowledge)
                    THEN 'true'
                    ELSE 'false'
                END as comparisonResult;
            """, nativeQuery = true)
    Boolean getAllCoursesCompleted(Long userId, Long subcategoryId);

    @Query(value = """
                WITH LevelOfCurrentKnowledge AS (
                    SELECT
                    COUNT(*) as totalCoursesBySubcatoryAndUser
                        FROM subcategory s
                        JOIN course c ON c.subcategory_id = s.id
                        JOIN enrollment e ON e.course_id = c.id
                    WHERE s.id =:subcategoryId
                    AND e.finished = true
                    AND e.user_id =:userId
                    AND s.status = 'ACTIVE'
            ), AllCousesByLevel AS (
                    SELECT COUNT(c.id) as totalCoursesByCategory
                    FROM course c
                        JOIN subcategory s ON s.id = c.subcategory_id
                    WHERE s.id =:subcategoryId
            )
            SELECT CASE
                    WHEN (SELECT * FROM AllCousesByLevel) = (SELECT * FROM LevelOfCurrentKnowledge)
                    THEN 'true'
                    ELSE 'false'
                END as comparisonResult;
            """, nativeQuery = true)
    Boolean getAllCoursesCompleted2(Long userId, Long subcategoryId);

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

    @Query(value = """
            WITH AllCoursesByCategory AS (
                SELECT DISTINCT c.id
                FROM subcategory sc
                JOIN course c ON c.subcategory_id = sc.id
                WHERE c.visibility = 'PUBLIC'
                  AND sc.status = 'ACTIVE'
                  AND sc.category_id = :categoryId
            )
            SELECT
                CASE
                    WHEN NOT EXISTS (
                        SELECT 1
                        FROM AllCoursesByCategory ac
                        WHERE NOT EXISTS (
                            SELECT 1
                            FROM enrollment e
                            WHERE e.course_id = ac.id
                            AND e.user_id = :userId
                        )
                    ) THEN 'true'
                    ELSE 'false'
                END AS all_courses_enrolled
            """, nativeQuery = true)
    Boolean isFinishedAllCourses(Long categoryId, Long userId);

    @Query(value = "SELECT MAX(level) FROM subcategory s WHERE s.category_id = :categoryId", nativeQuery = true)
    int findMaxLevel(Long categoryId);

    boolean existsByCodeUrl(String codeUrl);

    @Deprecated
    boolean existsByCodeUrlAndIdNot(String codeUrl, Long id);

    default boolean existsByCodeUrlWithDifferentId(String codeUrl, Long id) {
        return existsByCodeUrlAndIdNot(codeUrl, id);
    }

}
