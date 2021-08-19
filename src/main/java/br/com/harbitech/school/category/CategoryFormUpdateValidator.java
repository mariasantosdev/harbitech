package br.com.harbitech.school.category;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;

@Component
public class CategoryFormUpdateValidator implements Validator {

    private final CategoryRepository categoryRepository;

    public CategoryFormUpdateValidator(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return CategoryFormUpdate.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CategoryFormUpdate categoryFormUpdate = (CategoryFormUpdate) target;
        if (categoryRepository.existsByCodeUrlWithDifferentId(categoryFormUpdate.getId(),
                categoryFormUpdate.getCodeUrl(),categoryFormUpdate.getName())) {
            errors.rejectValue("codeUrl", "category.codeUrl.already.exists");
            errors.rejectValue("name", "category.name.already.exists");
        }
    }
}
