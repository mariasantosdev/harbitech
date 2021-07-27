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
import java.util.List;

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
    String formNew(Model model){
        List<Category> categories = categoryRepository.findAllByOrderByName();

        String formAction = "/admin/subcategories";

        model.addAttribute("subcategory", new Subcategory());
        model.addAttribute("categories", categories);
        model.addAttribute("formAction", formAction);
        return "admin/subcategory/formSubcategory";
    }

    @PostMapping(value = "/admin/subcategories")
    String save(@Valid Subcategory subcategory, BindingResult result) {
        if (result.hasErrors()) {
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

        List<Category> categories = categoryRepository.findAllByOrderByName();

        String formAction = "/admin/subcategories/" + categoryCodeUrl + "/" + subcategoryCodeUrl;

        model.addAttribute("subcategory", subcategory);
        model.addAttribute("categories", categories);
        model.addAttribute("formAction", formAction);

        return "admin/subcategory/formSubcategory";
    }

    @PostMapping("/admin/subcategories/{category}/{subcategoryCodeUrl}")
    String update(@Valid Subcategory subcategory, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/subcategory/formSubcategory";
        }
        subcategoryRepository.save(subcategory);
        return "redirect:/admin/subcategories/" + subcategory.getCategory().getCodeUrl();
    }
}
