package br.com.harbitech.school.course;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course,Long> {
    Optional<Course> findByVisibility(CourseVisibility visibility);
}
