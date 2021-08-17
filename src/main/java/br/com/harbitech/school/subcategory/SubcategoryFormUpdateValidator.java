package br.com.harbitech.school.subcategory;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SubcategoryFormUpdateValidator implements Validator {

    private final SubcategoryRepository subcategoryRepository;

    public SubcategoryFormUpdateValidator(SubcategoryRepository subcategoryRepository) {
        this.subcategoryRepository = subcategoryRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return SubcategoryFormUpdate.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SubcategoryFormUpdate subcategoryFormUpdate = (SubcategoryFormUpdate) target;
        if (subcategoryRepository.existsByCodeUrlWithDifferentId(subcategoryFormUpdate.getCodeUrl(),
                subcategoryFormUpdate.getId())) {
            errors.rejectValue("codeUrl", "subcategory.codeUrl.already.exists");
        }
    }
}
