package br.com.harbitech.school.home.admin;

import br.com.harbitech.school.course.CategoriesByCourseProjection;
import br.com.harbitech.school.course.InstructorByCourseProjection;
import br.com.harbitech.school.course.CourseRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

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
        List<InstructorByCourseProjection> instructorWithGreaterNumberOfCourses = courseRepository
                .findInstructorWithGreaterNumberOfCourses();

        List<CategoriesByCourseProjection> allCategoriesFromCourse = courseRepository.findAllCategories();

        model.addAttribute("instructorsProjection", instructorWithGreaterNumberOfCourses);
        model.addAttribute("categoriesFromCourseProjection", allCategoriesFromCourse);
        return "admin/home/dashboard";
    }
}
