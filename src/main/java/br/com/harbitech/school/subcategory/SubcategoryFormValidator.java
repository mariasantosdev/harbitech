package br.com.harbitech.school.subcategory;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SubcategoryFormValidator implements Validator {

    private final SubcategoryRepository subcategoryRepository;

    public SubcategoryFormValidator(SubcategoryRepository subcategoryRepository) {
        this.subcategoryRepository = subcategoryRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return SubcategoryForm.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SubcategoryForm subcategoryForm = (SubcategoryForm) target;
        if (subcategoryRepository.existsByCodeUrl(subcategoryForm.getCodeUrl())) {
            errors.rejectValue("codeUrl", "subcategory.codeUrl.already.exists");
        }
    }
}
