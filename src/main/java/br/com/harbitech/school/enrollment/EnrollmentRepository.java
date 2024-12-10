package br.com.harbitech.school.enrollment;

import br.com.harbitech.school.course.Course;
import br.com.harbitech.school.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    boolean existsByCourseAndUserAndFinished(Course course, User user, boolean finished);

    @Query("SELECT e FROM Enrollment e WHERE e.user = :user AND e.finished IS TRUE AND e.course IN :course")
    List<Enrollment> findAllByUserAndCourses(User user, List<Course> course);
}
