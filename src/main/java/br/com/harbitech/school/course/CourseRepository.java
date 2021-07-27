package br.com.harbitech.school.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {

    @Query(value = "SELECT instructor AS instrutor, COUNT(*) AS quantidade FROM course course GROUP BY instructor ORDER " +
            "BY quantidade DESC LIMIT 1", nativeQuery = true)
    List<CourseProjection> findInstructorWithGreaterNumberOfCourses();

    @Query(value = "SELECT category.name AS Nome, COUNT(course.id) AS Quantidade FROM category category LEFT JOIN " +
            "subcategory subcategory ON category.id = subcategory.category_id LEFT JOIN course course ON " +
            "course.subcategory_id = subcategory.id GROUP BY category.id", nativeQuery = true)
    List<CourseProjection> findAllCategories();

}
