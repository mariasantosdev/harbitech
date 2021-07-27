package br.com.harbitech.school.category;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@Controller
public class CategoryController {

    private final CategoryRepository categoryRepository;

    CategoryController(CategoryRepository categoryRepository){
        this.categoryRepository =  categoryRepository;
    }

    @GetMapping("/admin/categories")
    public String list(Model model) {
        List <Category> allCategories =  categoryRepository.findAll();
        model.addAttribute("categories", allCategories);
        return "admin/category/listCategories";
    }

    @GetMapping(value = "/admin/categories/new")
    public String formNew(Model model){
        model.addAttribute("category", new Category());
        return "admin/category/formNewCategory";
    }

    @PostMapping(value = "/admin/categories/new")
    public String save(@Valid Category category, BindingResult result) {
        if (result.hasErrors()){
            return "admin/category/formNewCategory";
        }
        categoryRepository.save(category);
        return "redirect:/admin/categories";
    }

    @GetMapping(value = "/admin/categories/{codeUrl}")
    public ModelAndView formUpdate(@PathVariable String codeUrl){
        Optional<Category> category = categoryRepository.findByCodeUrl(codeUrl);
        if(category.isEmpty()){
            ModelAndView mv = new ModelAndView("notFound");
            mv.setStatus(HttpStatus.NOT_FOUND);
            return mv;
        }
        ModelAndView mv = new ModelAndView("admin/category/formNewCategory");
        mv.addObject(category.get());
        return mv;
    }

    @PostMapping("/admin/categories/{codeUrl}")
    public String update(@Valid Category category, BindingResult result) {
        if (result.hasErrors()){
            return "admin/category/formNewCategory";
        }
        categoryRepository.save(category);
        return "redirect:/admin/categories";
    }
}
