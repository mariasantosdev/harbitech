package br.com.harbitech.school.category;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

import static br.com.harbitech.school.category.CategoryForm.*;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Controller
public class CategoryController {

    private final CategoryRepository categoryRepository;

    CategoryController(CategoryRepository categoryRepository){
        this.categoryRepository =  categoryRepository;
    }

    @GetMapping("/admin/categories")
    String list(Model model) {
        List<Category> categories =  categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "admin/category/listCategories";
    }

    @GetMapping(value = "/admin/categories/new")
    String formNew(Model model){
        model.addAttribute("categoryForm", new CategoryForm());
        return "admin/category/formCategory";
    }

    @PostMapping(value = "/admin/categories/new")
    String save(@Valid CategoryForm categoryForm, BindingResult result) {
        if (result.hasErrors()){
            return "admin/category/formCategory";
        }
        categoryRepository.save(convert(categoryForm));
        return "redirect:/admin/categories";
    }

    @GetMapping(value = "/admin/categories/{codeUrl}")
    String formUpdate(@PathVariable String codeUrl, Model model){
        Category category = categoryRepository.findByCodeUrl(codeUrl)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, codeUrl));

        model.addAttribute("categoryForm", from(category));
        return "admin/category/formCategory";
    }

    @PostMapping("/admin/categories/{codeUrl}")
    String update(@Valid CategoryForm categoryForm, BindingResult result) {
        if (result.hasErrors()){
            return "admin/category/formCategory";
        }
        categoryRepository.save(convertUpdate(categoryForm, categoryRepository));
        return "redirect:/admin/categories";
    }
}
