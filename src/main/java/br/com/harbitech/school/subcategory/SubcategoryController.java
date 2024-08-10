package br.com.harbitech.school.subcategory;

import br.com.harbitech.school.category.Category;
import br.com.harbitech.school.category.CategoryRepository;
import br.com.harbitech.school.course.Course;
import br.com.harbitech.school.enrollment.EnrollmentRepository;
import br.com.harbitech.school.user.CurrentUser;
import br.com.harbitech.school.user.User;
import br.com.harbitech.school.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.*;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Controller
@RequiredArgsConstructor
public class SubcategoryController {

    private final SubcategoryRepository subcategoryRepository;

    private final CategoryRepository categoryRepository;

    private final CurrentUser currentUser;

    private final SubcategoryFormValidator subcategoryFormValidator;

    private final SubcategoryFormUpdateValidator subcategoryFormUpdateValidator;
    private final UserRepository userRepository;
    private final EnrollmentRepository enrollmentRepository;

    @InitBinder("subcategoryForm")
    void initBinderSubcategoryForm(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(subcategoryFormValidator);
    }

    @InitBinder("subcategoryFormUpdate")
    void initBinderSubcategoryFormUpdate(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(subcategoryFormUpdateValidator);
    }

    @GetMapping("/admin/categories/{category}/subcategories")
    String list(@PathVariable("category") String categoryCodeUrl) {
        return "redirect:/admin/subcategories/" + categoryCodeUrl;
    }

    @GetMapping("/admin/subcategories/{category}")
    String list(@PathVariable("category") String categoryCodeUrl, Model model) {
        Category category = categoryRepository.findByCodeUrl(categoryCodeUrl)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, categoryCodeUrl));

        List<Subcategory> subcategories = subcategoryRepository.findAllByCategoryOrderByOrderVisualization(category);

        model.addAttribute("category", category);
        model.addAttribute("subcategories", subcategories);
        return "admin/subcategory/listSubcategories";
    }

    @GetMapping(value = "/admin/subcategories/new")
    String formNew(SubcategoryForm subcategoryform, Model model) {
        categoryRepository.findAllByOrderByName();

        String formAction = "/admin/subcategories";

        model.addAllAttributes(this.setupForm(formAction, subcategoryform));

        return "admin/subcategory/formSubcategory";
    }

    @PostMapping(value = "/admin/subcategories")
    String save(@Valid SubcategoryForm subcategoryForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            String formAction = "/admin/subcategories";
            model.addAllAttributes(this.setupForm(formAction, subcategoryForm));
            return "admin/subcategory/formSubcategory";
        }
        subcategoryRepository.save(subcategoryForm.toModel());

        return "redirect:/admin/subcategories/" + subcategoryForm.getCategory().getCodeUrl();
    }

    @GetMapping(value = "/admin/subcategories/{category}/{subcategory}")
    String formUpdate(@PathVariable("category") String categoryCodeUrl,
                      @PathVariable("subcategory") String subcategoryCodeUrl, Model model) {
        categoryRepository.findByCodeUrl(categoryCodeUrl)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, categoryCodeUrl));

        Subcategory subcategory = subcategoryRepository.findByCodeUrl(subcategoryCodeUrl)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, subcategoryCodeUrl));

        String formAction = "/admin/subcategories/" + categoryCodeUrl + "/" + subcategoryCodeUrl;

        model.addAllAttributes(this.setupFormUpdate(formAction, new SubcategoryFormUpdate(subcategory)));

        return "admin/subcategory/formUpdateSubcategory";
    }

    @PostMapping("/admin/subcategories/{category}/{subcategoryCodeUrl}")
    String update(@Valid SubcategoryFormUpdate subcategoryFormUpdate, BindingResult result, Model model,
                  @PathVariable("category") String categoryCodeUrl,
                  @PathVariable("subcategoryCodeUrl") String subcategoryCodeUrl) {
        if (result.hasErrors()) {
            String formAction = "/admin/subcategories/" + categoryCodeUrl + "/" + subcategoryCodeUrl;
            model.addAllAttributes(this.setupFormUpdate(formAction, subcategoryFormUpdate));
            return "admin/subcategory/formUpdateSubcategory";
        }
        subcategoryRepository.save(subcategoryFormUpdate.toModel(subcategoryRepository));
        return "redirect:/admin/subcategories/" + categoryCodeUrl;
    }

    @GetMapping("/{categoryCode}")
    String subcategoryByCategoryCodeUrl(@PathVariable("categoryCode") String categoryCodeUrl, Model model) {
        Category category = categoryRepository.findByCodeUrl(categoryCodeUrl)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, categoryCodeUrl));

        List<Subcategory> allActiveSubcategories = subcategoryRepository.findAllActiveSubcategories(category);

        model.addAttribute("allActiveSubcategories", allActiveSubcategories);
        model.addAttribute("category", category);

        return "category/category";
    }

    @GetMapping("/{categoryCode}/courses-by-levels")
    String coursesByLevel(@PathVariable("categoryCode") String categoryCodeUrl, @RequestParam Long subcategoryId, Model model) {
        Category category = categoryRepository.findByCodeUrl(categoryCodeUrl)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, categoryCodeUrl));

        String userName = currentUser.getCurrentUsername().stream().findFirst().orElseThrow(() ->
                new ResponseStatusException(NOT_FOUND, "User not found"));

        User user = userRepository.findByEmail(userName)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        List<Subcategory> allActiveSubcategories = subcategoryRepository.findCurrentKnowLevelSubcategories(category.getId());

        List<Course> courses = allActiveSubcategories.stream()
                .flatMap(s -> s.getCourses().stream())
                .toList();

        List<Long> allEnrollmentsIdByLoggedUser = enrollmentRepository.findAllByUserAndCourses(user, courses).stream()
                .map(enrollment -> enrollment.getCourse().getId())
                .toList();

        if(subcategoryRepository.isFinishedAllCourses(category.getId(), user.getId())) {
            model.addAttribute("category", category);
            return "category/finished-all-courses";
        }

        Boolean allCoursesCompleted = subcategoryRepository
                .getAllCoursesCompleted(user.getId(), subcategoryId);

        model.addAttribute("allCoursesCompleted", allCoursesCompleted);
        model.addAttribute("enrolledCourseIds", allEnrollmentsIdByLoggedUser);
        model.addAttribute("allActiveSubcategories", allActiveSubcategories);
        model.addAttribute("category", category);

        return "category/courses-by-levels";
    }

    @GetMapping("/{categoryCode}/courses-by-levels/next-level")
    String nextLevel(@PathVariable("categoryCode") String categoryCodeUrl, Model model) {
        Category category = categoryRepository.findByCodeUrl(categoryCodeUrl)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, categoryCodeUrl));

        String userName = currentUser.getCurrentUsername().stream().findFirst().orElseThrow(() ->
                new ResponseStatusException(NOT_FOUND, "User not found"));

        User user = userRepository.findByEmail(userName)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        List<Subcategory> nextLevelSubcategories = subcategoryRepository.findNextLevelSubcategories(category.getId(), user.getId());

        model.addAttribute("category", nextLevelSubcategories);
        return "category/courses-by-levels-next-step";
    }

    @GetMapping("/{categoryCode}/self-assessment")
    String selfAssessment(@PathVariable("categoryCode") String categoryCodeUrl, Model model) {
        Category category = categoryRepository.findByCodeUrl(categoryCodeUrl)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, categoryCodeUrl));

        List<Subcategory> allActiveSubcategories = subcategoryRepository.findAllActiveSubcategories(category);
        model.addAttribute("allActiveSubcategories", allActiveSubcategories);
        return "category/self-assessment";
    }

    @GetMapping("/all-subcategories")
    String listAllSubcategories(Model model) {
        List<Category> allCategories = categoryRepository.findAllActiveCategoriesWithPublicCourses();

        model.addAttribute("categories", allCategories);
        return "/subcategory/chooseASubcategory";
    }

    private Map<String, Object> setupForm(String formAction, SubcategoryForm subcategoryForm) {
        Map<String, Object> attributes = new HashMap<>();
        List<Category> categories = categoryRepository.findAllByOrderByName();

        attributes.put("subcategoryForm", subcategoryForm);
        attributes.put("categories", categories);
        attributes.put("formAction", formAction);

        return attributes;
    }

    private Map<String, Object> setupFormUpdate(String formAction, SubcategoryFormUpdate subcategoryFormUpdate) {
        Map<String, Object> attributes = new HashMap<>();
        List<Category> categories = categoryRepository.findAllByOrderByName();

        attributes.put("subcategoryFormUpdate", subcategoryFormUpdate);
        attributes.put("categories", categories);
        attributes.put("formAction", formAction);

        return attributes;
    }
}
