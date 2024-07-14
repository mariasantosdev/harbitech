package br.com.harbitech.school.enrollment;

import br.com.harbitech.school.course.Course;
import br.com.harbitech.school.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long>  {
    boolean existsByCourseAndUser(Course course, User user);
}
