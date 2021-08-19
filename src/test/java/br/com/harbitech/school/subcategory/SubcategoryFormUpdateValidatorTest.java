package br.com.harbitech.school.subcategory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.Errors;

import static org.mockito.AdditionalMatchers.not;
import static org.mockito.Mockito.*;

public class SubcategoryFormUpdateValidatorTest {

    private SubcategoryRepository subcategoryRepository;
    private SubcategoryFormUpdateValidator subcategoryFormUpdateValidator;
    private Errors errors;

    @BeforeEach
    void setUp() {
        subcategoryRepository = mock(SubcategoryRepository.class);
        when(subcategoryRepository.existsByCodeUrlWithDifferentId(eq("java"),
                not(eq(1L)))).thenReturn(true);
        subcategoryFormUpdateValidator = new SubcategoryFormUpdateValidator(subcategoryRepository);
        errors = mock(Errors.class);
    }

    @Test
    void should_validate_correct_because_the_code_url_exists_and_id_is_same(){
        var form = new SubcategoryFormUpdate();
        form.setId(1L);
        form.setCodeUrl("java");

        subcategoryFormUpdateValidator.validate(form, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }

    @Test
    void should_validate_incorrect_code_url_when_code_exists_with_different_id(){
        var form = new SubcategoryFormUpdate();
        form.setId(26L);
        form.setCodeUrl("java");

        subcategoryFormUpdateValidator.validate(form, errors);

        verify(errors).rejectValue("codeUrl", "subcategory.codeUrl.already.exists");
    }

    @Test
    void should_validate_correct_when_code_url_dont_exists_for_id(){
        var form = new SubcategoryFormUpdate();
        form.setId(1L);
        form.setCodeUrl("angular-cli");

        subcategoryFormUpdateValidator.validate(form, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }

    @Test
    void should_validate_correct_when_code_url_dont_exists_and_id_is_different() {
        var form = new SubcategoryFormUpdate();
        form.setId(24L);
        form.setCodeUrl("java-primeiros-passos");

        subcategoryFormUpdateValidator.validate(form, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }
}

