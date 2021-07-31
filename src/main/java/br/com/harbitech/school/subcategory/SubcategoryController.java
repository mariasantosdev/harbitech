package br.com.harbitech.school.subcategory;

import br.com.harbitech.school.category.Category;
import br.com.harbitech.school.category.CategoryRepository;
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
public class SubcategoryController {

    private final SubcategoryRepository subcategoryRepository;

    private final CategoryRepository categoryRepository;

    SubcategoryController(SubcategoryRepository subcategoryRepository, CategoryRepository categoryRepository){
        this.subcategoryRepository = subcategoryRepository;
        this.categoryRepository = categoryRepository;
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
    String formNew(Subcategory subcategory, Model model){
        categoryRepository.findAllByOrderByName();

        String formAction = "/admin/subcategories";

        model.addAllAttributes(this.setupForm(formAction, subcategory));

        return "admin/subcategory/formSubcategory";
    }

    @PostMapping(value = "/admin/subcategories")
    String save(@Valid Subcategory subcategory, BindingResult result, Model model) {
        if (result.hasErrors()) {
            String formAction = "/admin/subcategories";
            model.addAllAttributes(this.setupForm(formAction, subcategory));
            return "admin/subcategory/formSubcategory";
        }
        subcategoryRepository.save(subcategory);
        return "redirect:/admin/subcategories/" + subcategory.getCategory().getCodeUrl();
    }

    @GetMapping(value = "/admin/subcategories/{category}/{subcategory}")
    String formUpdate(@PathVariable("category") String categoryCodeUrl,
                      @PathVariable("subcategory") String subcategoryCodeUrl, Model model) {
        categoryRepository.findByCodeUrl(categoryCodeUrl)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, categoryCodeUrl));

        Subcategory subcategory = subcategoryRepository.findByCodeUrl(subcategoryCodeUrl)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, subcategoryCodeUrl));

        String formAction = "/admin/subcategories/" + categoryCodeUrl + "/" + subcategoryCodeUrl;

        model.addAllAttributes(this.setupForm(formAction, subcategory));

        return "admin/subcategory/formSubcategory";
    }

    @PostMapping("/admin/subcategories/{category}/{subcategoryCodeUrl}")
    String update(@Valid Subcategory subcategory, BindingResult result, Model model,
                  @PathVariable("category") String categoryCodeUrl,
                  @PathVariable("subcategoryCodeUrl") String subcategoryCodeUrl) {
        if (result.hasErrors()) {
            String formAction = "/admin/subcategories/" + categoryCodeUrl + "/" + subcategoryCodeUrl;
            model.addAllAttributes(this.setupForm(formAction, subcategory));
            return "admin/subcategory/formSubcategory";
        }
        subcategoryRepository.save(subcategory);
        return "redirect:/admin/subcategories/" + categoryCodeUrl;
        }

    private Map<String,Object> setupForm(String formAction, Subcategory subcategory) {
        Map<String,Object> attributes = new HashMap<>();
        List<Category> categories = categoryRepository.findAllByOrderByName();

        attributes.put("subcategory", subcategory);
        attributes.put("categories", categories);
        attributes.put("formAction", formAction);

        return attributes;
    }
}
