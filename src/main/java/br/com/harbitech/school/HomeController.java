package br.com.harbitech.school;

import br.com.harbitech.school.category.CategoryRepository;
import br.com.harbitech.school.course.CourseRepository;
import br.com.harbitech.school.subcategory.SubcategoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final SubcategoryRepository subcategoryRepository;

    private final CategoryRepository categoryRepository;

    private final CourseRepository courseRepository;

    HomeController(SubcategoryRepository subcategoryRepository, CategoryRepository categoryRepository,
                     CourseRepository courseRepository){
        this.subcategoryRepository = subcategoryRepository;
        this.categoryRepository = categoryRepository;
        this.courseRepository = courseRepository;
    }

    @GetMapping(path = {"/admin", "/admin/dashboard"})
    String admin() {
        courseRepository.findInstructorWithGreaterNumberOfCourses();
    }
}
