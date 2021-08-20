package br.com.harbitech.school.category;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.validation.Errors;

import org.junit.jupiter.api.Test;

import static org.mockito.AdditionalMatchers.not;
import static org.mockito.Mockito.*;

public class CategoryFormUpdateValidatorTest {

    private CategoryFormUpdateValidator categoryFormUpdateValidator;
    private Errors errors;

    @BeforeEach
    void setUp() {
        CategoryRepository categoryRepository = mock(CategoryRepository.class);
        when(categoryRepository.existsByCodeUrlWithDifferentId(eq("programacao"),(not(eq(1L)))))
                .thenReturn(true);

        categoryFormUpdateValidator = new CategoryFormUpdateValidator(categoryRepository);
        errors = mock(Errors.class);
    }

    @Test
    void should_validate_correct_when_code_url_exists_and_id_is_same(){
        var form = new CategoryFormUpdate();
        form.setId(1L);
        form.setCodeUrl("programacao");

        categoryFormUpdateValidator.validate(form, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }

    @Test
    void should_validate_incorrect_code_url_when_code_exists_with_different_id(){
        var form = new CategoryFormUpdate();
        form.setId(24L);
        form.setCodeUrl("programacao");

        categoryFormUpdateValidator.validate(form, errors);

        verify(errors).rejectValue("codeUrl", "category.codeUrl.already.exists");
    }

    @Test
    void should_validate_correct_when_code_url_dont_exists_for_id(){
        var form = new CategoryFormUpdate();
        form.setId(1L);
        form.setCodeUrl("devops");

        categoryFormUpdateValidator.validate(form, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }


    @Test
    void should_validate_correct_when_code_url_dont_exists_and_id_is_different() {
        var form = new CategoryFormUpdate();
        form.setId(24L);
        form.setCodeUrl("gestao-ti");

        categoryFormUpdateValidator.validate(form, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }
}

