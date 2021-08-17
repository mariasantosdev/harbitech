package br.com.harbitech.school.course;

import br.com.harbitech.school.category.CategoryForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CourseFormValidator implements Validator {

    private final CourseRepository courseRepository;

    public CourseFormValidator(CourseRepository course) {
        this.courseRepository = course;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return CourseForm.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CourseForm courseForm = (CourseForm) target;
        if (courseRepository.existsByCodeUrl(courseForm.getCodeUrl())) {
            errors.rejectValue("codeUrl", "course.codeUrl.already.exists");
        }
    }
}
