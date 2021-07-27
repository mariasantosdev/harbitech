package br.com.harbitech.school.course;

import br.com.harbitech.school.subcategory.Subcategory;
import br.com.harbitech.school.subcategory.SubcategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Controller
public class CourseController {
    private final SubcategoryRepository subcategoryRepository;

    private final CourseRepository courseRepository;

    CourseController(SubcategoryRepository subcategoryRepository, CourseRepository courseRepository){
        this.subcategoryRepository = subcategoryRepository;
        this.courseRepository = courseRepository;
    }

    @GetMapping("/admin/courses/{category}/{subcategoryCodeUrl}")
    String list(@PathVariable("subcategoryCodeUrl") String subcategoryCodeUrl,
                @PathVariable("category") String category,
                Model model, @PageableDefault(size = 5) Pageable pageable) {
        Subcategory subcategory = subcategoryRepository.findByCodeUrl(subcategoryCodeUrl)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, subcategoryCodeUrl));

        Page<Course> coursesPages = courseRepository.findAllBySubcategory(subcategory, pageable);

        model.addAttribute("category", category);
        model.addAttribute("subcategories", subcategory);

        model.addAttribute("courses", coursesPages.getContent());
        model.addAttribute("totalPages", coursesPages.getTotalPages());
        return "admin/course/listCourses";
    }
}
