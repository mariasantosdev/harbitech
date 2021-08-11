package br.com.harbitech.school.course;

import br.com.harbitech.school.category.CategoryRepository;
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

import static br.com.harbitech.school.course.CourseForm.*;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Controller
public class CourseController {

    private final CategoryRepository categoryRepository;

    private final SubcategoryRepository subcategoryRepository;

    private final CourseRepository courseRepository;

    CourseController(SubcategoryRepository subcategoryRepository, CourseRepository courseRepository,
                     CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
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
    String formNew(CourseForm courseForm, Model model){

        String formAction = "/admin/courses";

        model.addAllAttributes(this.setupForm(formAction, courseForm));

        return "admin/course/formCourse";
    }

    @PostMapping(value = "/admin/courses")
    String save(@Valid CourseForm courseForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            String formAction = "/admin/courses";
            model.addAllAttributes(this.setupForm(formAction, courseForm));
            return "admin/course/formCourse";
        }

        courseRepository.save(courseForm.toModel());
        return "redirect:/admin/courses/" + courseForm.getCategoryCodeUrl() +
                "/" + courseForm.getSubcategoryCodeUrl();
    }

    @GetMapping(value = "/admin/courses/{category}/{subcategory}/{course}")
    String formUpdate(@PathVariable("category") String categoryCodeUrl,
                      @PathVariable("subcategory") String subcategoryCodeUrl,
                      @PathVariable("course") String courseCodeUrl, Model model) {
        categoryRepository.findByCodeUrl(categoryCodeUrl)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, categoryCodeUrl));

        subcategoryRepository.findByCodeUrl(subcategoryCodeUrl)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, subcategoryCodeUrl));

        Course course = courseRepository.findByCodeUrl(courseCodeUrl)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, courseCodeUrl));

        String formAction = "/admin/courses/" + categoryCodeUrl + "/" + subcategoryCodeUrl + "/" + courseCodeUrl;

        model.addAllAttributes(this.setupForm(formAction, from(course)));

        return "admin/course/formCourse";
    }

    @PostMapping("/admin/courses/{category}/{subcategory}/{courseCodeUrl}")
    String update(@Valid CourseForm courseForm, BindingResult result, Model model,
                  @PathVariable("category") String categoryCodeUrl,
                  @PathVariable("subcategory") String subcategoryCodeUrl,
                  @PathVariable("courseCodeUrl") String courseCodeUrl) {
        if (result.hasErrors()) {
            String formAction = "/admin/courses/" + categoryCodeUrl + "/" + subcategoryCodeUrl + "/" + courseCodeUrl;
            model.addAllAttributes(this.setupForm(formAction, courseForm));
            return "admin/course/formCourse";
        }
        courseRepository.save(CourseForm.toModel(courseForm, courseRepository));
        return "redirect:/admin/courses/" + categoryCodeUrl + "/" +subcategoryCodeUrl;
    }

    private Map<String,Object> setupForm(String formAction, CourseForm courseForm) {
        Map<String,Object> attributes = new HashMap<>();
        List<Subcategory> subcategories = subcategoryRepository.findAllByOrderByName();

        attributes.put("courseForm", courseForm);
        attributes.put("subcategories", subcategories);
        attributes.put("formAction", formAction);

        return attributes;
    }
}
