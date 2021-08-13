package br.com.harbitech.school.home.admin;

import br.com.harbitech.school.course.CategoriesByCourseProjection;
import br.com.harbitech.school.course.InstructorByCourseProjection;
import br.com.harbitech.school.course.CourseRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class AdminController {

    private final CourseRepository courseRepository;

    AdminController(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }

    @GetMapping(value = {"/admin", "/"})
    String admin() {
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/admin/dashboard")
    String dashboard(Model model) {
        Optional<InstructorByCourseProjection> instructorWithGreaterNumberOfCourses = courseRepository
                .findInstructorWithGreaterNumberOfCourses();

        List<CategoriesByCourseProjection> allCategoriesFromCourse = courseRepository.findAllCoursesCountByCategories();

        model.addAttribute("instructorsProjection", instructorWithGreaterNumberOfCourses.get());
        model.addAttribute("categoriesFromCourseProjection", allCategoriesFromCourse);
        return "admin/home/dashboard";
    }
}
