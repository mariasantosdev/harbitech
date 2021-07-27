package br.com.harbitech.school.home;

import br.com.harbitech.school.course.CategoriesByCourseProjection;
import br.com.harbitech.school.course.InstructorByCourseProjection;
import br.com.harbitech.school.course.CourseRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final CourseRepository courseRepository;

    HomeController(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }

    @GetMapping("/admin")
    String admin() {
        return "redirect:/admin/dashboard";
    }

    @GetMapping(path = {"/", "/admin/dashboard"})
    String dashboard(Model model) {
        List<InstructorByCourseProjection> instructorWithGreaterNumberOfCourses = courseRepository
                .findInstructorWithGreaterNumberOfCourses();

        List<CategoriesByCourseProjection> allCategoriesFromCourse = courseRepository.findAllCategories();

        model.addAttribute("instructorsProjection", instructorWithGreaterNumberOfCourses);
        model.addAttribute("categoriesFromCourseProjection", allCategoriesFromCourse);
        return "admin/home/dashboard";
    }
}
