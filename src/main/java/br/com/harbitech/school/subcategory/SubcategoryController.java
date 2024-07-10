package br.com.harbitech.school.subcategory;

import br.com.harbitech.school.category.Category;
import br.com.harbitech.school.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Controller
@RequiredArgsConstructor
public class SubcategoryController {

    private final SubcategoryRepository subcategoryRepository;

    private final CategoryRepository categoryRepository;

    private final SubcategoryFormValidator subcategoryFormValidator;

    private final SubcategoryFormUpdateValidator subcategoryFormUpdateValidator;

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
    String coursesByLevel(@PathVariable("categoryCode") String categoryCodeUrl, Model model) {
        Category category = categoryRepository.findByCodeUrl(categoryCodeUrl)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, categoryCodeUrl));

        List<Subcategory> allActiveSubcategories = subcategoryRepository.findAllActiveSubcategories(category);

        model.addAttribute("allActiveSubcategories", allActiveSubcategories);
        model.addAttribute("maxSubcategoryLevel", subcategoryRepository.findMaxLevel());
        model.addAttribute("category", category);

        return "category/courses-by-levels";
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
