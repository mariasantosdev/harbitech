package br.com.harbitech.school.course;

import br.com.harbitech.school.subcategory.Subcategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long>, PagingAndSortingRepository<Course, Long> {

    @Query(value = "SELECT instructor, COUNT(*) AS amount FROM course GROUP BY instructor ORDER BY amount DESC " +
            "LIMIT 1", nativeQuery = true)
    List<InstructorByCourseProjection> findInstructorWithGreaterNumberOfCourses();

    @Query(value = "SELECT category.name AS name, COUNT(course.id) AS amount FROM category LEFT JOIN subcategory " +
            "ON category.id = subcategory.category_id LEFT JOIN course course ON course.subcategory_id = subcategory.id " +
            "GROUP BY category.id ORDER BY amount DESC", nativeQuery = true)
    List<CategoriesByCourseProjection> findAllCoursesCategories();

   Page<Course> findAllBySubcategory(Subcategory subcategory, Pageable pageable);

    Optional<Course> findByCodeUrl(String codeUrl);
}
