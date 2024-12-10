package br.com.harbitech.school.course;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CourseVisibilityTest {
    @Test
    void should_throw_exception_when_is_an_invalid_course_description() {
        assertThrows(IllegalArgumentException.class, () -> CourseVisibility.from("AN_INVALID_VISIBILITY"));
    }

    @Test
    void should_not_throw_exception_is_a_valid_course_description() {
        assertDoesNotThrow (()-> CourseVisibility.from("PÚBLICA"));
    }
}
