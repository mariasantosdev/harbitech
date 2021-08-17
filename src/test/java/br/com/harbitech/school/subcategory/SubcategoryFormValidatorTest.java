package br.com.harbitech.school.subcategory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.Errors;

import static org.mockito.Mockito.*;

public class SubcategoryFormValidatorTest {

    private SubcategoryRepository subcategoryRepository;
    private SubcategoryFormValidator subcategoryFormValidator;
    private Errors errors;

    @BeforeEach
    void setUp() {
        subcategoryRepository = mock(SubcategoryRepository.class);
        subcategoryFormValidator = new SubcategoryFormValidator(subcategoryRepository);
        errors = mock(Errors.class);
    }

    @Test
    void should_validate_incorrect_code_url_because_already_exists() {
        when(subcategoryRepository.existsByCodeUrl("cobol")).thenReturn(true);

        var form = new SubcategoryForm();
        form.setCodeUrl("cobol");

        subcategoryFormValidator.validate(form, errors);

        verify(errors).rejectValue("codeUrl", "subcategory.codeUrl.already.exists");
    }

    @Test
    void when_code_url_doesnt_exist_should_validate_correct() {
        var form = new SubcategoryForm();
        form.setCodeUrl("cobol");

        subcategoryFormValidator.validate(form, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }
}
