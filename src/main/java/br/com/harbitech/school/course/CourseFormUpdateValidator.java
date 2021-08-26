package br.com.harbitech.school.course;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CourseFormUpdateValidator implements Validator {

    private final CourseRepository courseRepository;

    public CourseFormUpdateValidator(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return CourseFormUpdate.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CourseFormUpdate courseFormUpdate = (CourseFormUpdate) target;
        if (courseRepository.existsByCodeUrlWithDifferentId(courseFormUpdate.getCodeUrl(), courseFormUpdate.getId())) {
            errors.rejectValue("codeUrl", "course.codeUrl.already.exists");
        }
    }
}
