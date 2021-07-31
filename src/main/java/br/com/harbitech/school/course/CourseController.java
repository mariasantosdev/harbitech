package br.com.harbitech.school.course;

import br.com.harbitech.school.category.Category;
import br.com.harbitech.school.subcategory.Subcategory;
import br.com.harbitech.school.subcategory.SubcategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        model.addAttribute("subcategory", subcategory);

        model.addAttribute("courses", coursesPages.getContent());
        model.addAttribute("totalPages", coursesPages.getTotalPages());
        return "admin/course/listCourses";
    }

    @GetMapping(value = "/admin/courses/new")
    String formNew(Course course, Model model){
        subcategoryRepository.findAllByOrderByName();

        String formAction = "/admin/courses";

        model.addAllAttributes(this.setupForm(formAction, course));

        return "admin/course/formCourse";
    }

    @PostMapping(value = "/admin/courses")
    String save(@Valid Course course, BindingResult result, Model model) {
        if (result.hasErrors()) {
            String formAction = "/admin/courses";
            model.addAllAttributes(this.setupForm(formAction, course));
            return "admin/course/formCourse";
        }
        courseRepository.save(course);
        return "redirect:/admin/courses/" + course.getSubcategory().getCodeUrl();
    }

    private Map<String,Object> setupForm(String formAction, Course course) {
        Map<String,Object> attributes = new HashMap<>();
        List<Subcategory> subcategories = subcategoryRepository.findAllByOrderByName();

        attributes.put("course", course);
        attributes.put("subcategories", subcategories);
        attributes.put("formAction", formAction);

        return attributes;
    }

//
//    @GetMapping(value = "/admin/subcategories/new")
//    String formNew(Subcategory subcategory, Model model){
//        List<Category> categories = categoryRepository.findAllByOrderByName();
//
//        String formAction = "/admin/subcategories";
//
//        model.addAllAttributes(this.setupForm(formAction, subcategory));
//
//        return "admin/subcategory/formSubcategory";
//    }
//
//    @PostMapping(value = "/admin/subcategories")
//    String save(@Valid Subcategory subcategory, BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            String formAction = "/admin/subcategories";
//            model.addAllAttributes(this.setupForm(formAction, subcategory));
//            return "admin/subcategory/formSubcategory";
//        }
//        subcategoryRepository.save(subcategory);
//        return "redirect:/admin/subcategories/" + subcategory.getCategory().getCodeUrl();
//    }

}
