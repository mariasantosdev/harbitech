package br.com.harbitech.school.course;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.Errors;

import static org.mockito.Mockito.*;

class CourseFormValidatorTest {

    private CourseRepository courseRepository;
    private CourseFormValidator courseFormValidator;
    private Errors errors;

    @BeforeEach
    void setUp() {
        courseRepository = mock(CourseRepository.class);
        courseFormValidator = new CourseFormValidator(courseRepository);
        errors = mock(Errors.class);
    }

    @Test
    void should_validate_incorrect_code_url_when_already_exists() {
        when(courseRepository.existsByCodeUrl("java-introducao-orientacao-objetos")).thenReturn(true);

        var form = new CourseForm();
        form.setCodeUrl("java-introducao-orientacao-objetos");

        courseFormValidator.validate(form, errors);

        verify(errors).rejectValue("codeUrl", "course.codeUrl.already.exists");
    }

    @Test
    void when_code_url_doesnt_exist_should_validate_correct() {
        var form = new CourseForm();
        form.setCodeUrl("java-introducao-orientacao-objetos");

        courseFormValidator.validate(form, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }
}
