package br.com.harbitech.school.category;

import org.junit.jupiter.api.BeforeEach;

import org.springframework.validation.Errors;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class CategoryFormValidatorTest {

    private CategoryRepository categoryRepository;
    private CategoryFormValidator categoryFormValidator;
    private Errors errors;

    @BeforeEach
    void setUp() {
        categoryRepository = mock(CategoryRepository.class);
        categoryFormValidator = new CategoryFormValidator(categoryRepository);
        errors = mock(Errors.class);
    }

    @Test
    void should_validate_incorrect_code_url_when_already_exists() {
        when(categoryRepository.existsByCodeUrl("programacao")).thenReturn(true);

        var form = new CategoryForm();
        form.setCodeUrl("programacao");

        categoryFormValidator.validate(form, errors);

        verify(errors).rejectValue("codeUrl", "category.codeUrl.already.exists");
    }

    @Test
    void when_code_url_doesnt_exist_should_validate_correct() {
        var form = new CategoryForm();
        form.setCodeUrl("programacao");

        categoryFormValidator.validate(form, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }
}
