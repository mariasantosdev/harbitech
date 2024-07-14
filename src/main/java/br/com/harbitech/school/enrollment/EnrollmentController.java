package br.com.harbitech.school.enrollment;

import br.com.harbitech.school.course.Course;
import br.com.harbitech.school.course.CourseRepository;
import br.com.harbitech.school.user.CurrentUser;
import br.com.harbitech.school.user.User;
import br.com.harbitech.school.user.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Controller
class EnrollmentController {

    private final CourseRepository courseRepository;

    private final EnrollmentRepository enrollmentRepository;

    private final UserRepository userRepository;
    private final CurrentUser currentUser;

    EnrollmentController(CourseRepository courseRepository, EnrollmentRepository enrollmentRepository,
                         UserRepository userRepository, CurrentUser currentUser) {
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.userRepository = userRepository;
        this.currentUser = currentUser;
    }

    @PostMapping("/courses/{code}/enroll")
    ResponseEntity<Void> newEnrollment(@PathVariable("code") String code) {
        Course course = courseRepository.findByCodeUrl(code).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, format("Course with code %s not found", code)));

        String userName = currentUser.getCurrentUsername().stream().findFirst().orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "User not found"));

        User user = userRepository.findByEmail(userName).orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (enrollmentRepository.existsByCourseAndUserAndFinished(course, user, true)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Enrollment enrollment = new Enrollment(user, course, true);
        enrollmentRepository.save(enrollment);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
