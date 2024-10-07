package br.com.harbitech.school.course;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.Errors;

import static org.mockito.AdditionalMatchers.not;
import static org.mockito.Mockito.*;

class CourseFormUpdateValidatorTest {

    private CourseFormUpdateValidator courseFormUpdateValidator;
    private Errors errors;

    @BeforeEach
    void setUp() {
        CourseRepository courseRepository = mock(CourseRepository.class);
        when(courseRepository.existsByCodeUrlWithDifferentId(eq("java-introducao-orientacao-objetos"),
                not(eq(3L)))).thenReturn(true);
        courseFormUpdateValidator = new CourseFormUpdateValidator(courseRepository);
        errors = mock(Errors.class);
    }

    @Test
    void should_validate_correct_when_code_url_exists_and_id_is_same(){
        var form = new CourseFormUpdate();
        form.setId(3L);
        form.setCodeUrl("java-introducao-orientacao-objetos");

        courseFormUpdateValidator.validate(form, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }

    @Test
    void should_validate_incorrect_code_url_when_code_exists_with_different_id(){
        var form = new CourseFormUpdate();
        form.setId(26L);
        form.setCodeUrl("java-introducao-orientacao-objetos");

        courseFormUpdateValidator.validate(form, errors);

        verify(errors).rejectValue("codeUrl", "course.codeUrl.already.exists");
    }

    @Test
    void should_validate_correct_when_code_url_dont_exists_for_id(){
        var form = new CourseFormUpdate();
        form.setId(1L);
        form.setCodeUrl("angular-cli");

        courseFormUpdateValidator.validate(form, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }

    @Test
    void should_validate_correct_when_code_url_dont_exists_and_id_is_different() {
        var form = new CourseFormUpdate();
        form.setId(24L);
        form.setCodeUrl("java-primeiros-passos");

        courseFormUpdateValidator.validate(form, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }
}

