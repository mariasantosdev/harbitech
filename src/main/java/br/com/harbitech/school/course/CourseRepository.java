package br.com.harbitech.school.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long>, PagingAndSortingRepository<Course, Long> {

    @Query(value = "SELECT instructor, COUNT(*) AS amount FROM course course GROUP BY instructor ORDER BY amount DESC " +
            "LIMIT 1", nativeQuery = true)
    List<InstructorByCourseProjection> findInstructorWithGreaterNumberOfCourses();

    @Query(value = "SELECT category.name AS Name, COUNT(course.id) AS amount FROM category category LEFT JOIN subcategory subcategory ON " +
            "category.id = subcategory.category_id LEFT JOIN course course ON course.subcategory_id = subcategory.id GROUP BY category.id",
            nativeQuery = true)
    List<CategoriesByCourseProjection> findAllCategories();
}
