package br.com.harbitech.school.category;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CategoryFormValidator implements Validator {

    private final CategoryRepository categoryRepository;

    public CategoryFormValidator(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return CategoryForm.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CategoryForm categoryForm = (CategoryForm) target;
        if (categoryRepository.existsByNameAndCodeUrl(categoryForm.getName(), categoryForm.getCodeUrl())) {
            errors.rejectValue("name", "category.name.already.exists");
            errors.rejectValue("codeUrl", "category.codeUrl.already.exists");
        }
    }
}
