package br.com.harbitech.school.course;

import br.com.harbitech.school.category.CategoryRepository;
import br.com.harbitech.school.subcategory.Subcategory;
import br.com.harbitech.school.subcategory.SubcategoryRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Controller
public class CourseController {
    private final SubcategoryRepository subcategoryRepository;

    private final CategoryRepository categoryRepository;

    private final CourseRepository courseRepository;

    CourseController(SubcategoryRepository subcategoryRepository, CategoryRepository categoryRepository,
                     CourseRepository courseRepository){
        this.subcategoryRepository = subcategoryRepository;
        this.categoryRepository = categoryRepository;
        this.courseRepository = courseRepository;
    }

    @GetMapping("/admin/courses/{category}/{subcategory}")
    String list(@PathVariable("category") String category, @PathVariable("subcategory") String subcategory,
                Model model) {
        categoryRepository.findByCodeUrl(category)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, category));

        Subcategory subcategories = subcategoryRepository.findByCodeUrl(subcategory)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, subcategory));

        Pageable pageable = PageRequest.of(1,5, Sort.unsorted());

        List<Course> courses = subcategories.getCourses();
        model.addAttribute("courses", courses);
        model.addAttribute("subcategories", subcategories);
        return "admin/course/listCourses";
    }
}
