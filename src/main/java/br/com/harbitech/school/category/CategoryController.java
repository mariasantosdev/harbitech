package br.com.harbitech.school.category;

import br.com.harbitech.school.subcategory.Subcategory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
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

    @GetMapping(value = "/admin/categories/new")
    public ModelAndView form(){
        ModelAndView mv = new ModelAndView("formNewCategory");
        mv.addObject("category", new Category());
        return mv;
    }

    //TODO PADRÃO PARA NOME DOS MÉTODOS OS FORMS PODERIAM SER NEW

    @PostMapping(value = "/admin/categories/new")
    public ModelAndView save(@Valid Category category, BindingResult result) {
        ModelAndView mv = new ModelAndView("formNewCategory");
        if (result.hasErrors()){
            return mv;
        }
        categoryRepository.save(category);
        return mv;
    }

    @GetMapping(value = "/admin/categories/{codeUrl}")
    public ModelAndView formUpdate(@PathVariable String codeUrl){
        Optional<Category> category = categoryRepository.findByCodeUrl(codeUrl);
        if(category.isEmpty()){
            ModelAndView mv = new ModelAndView("notFound");
            mv.setStatus(HttpStatus.NOT_FOUND);
            return mv;
        }
        ModelAndView mv = new ModelAndView("formNewCategory");
        mv.addObject(category.get());
        return mv;
    }

    @PostMapping("/admin/categories/{codeUrl}")
    public ModelAndView update(@Valid Category category, BindingResult result) {
        if (result.hasErrors()){
            ModelAndView mv = new ModelAndView("formNewCategory");
            return mv;
        }
        categoryRepository.save(category);
        ModelAndView mv = new ModelAndView("listCategories");
        mv.addObject("category",category);
        return mv;
    }

    @GetMapping("/admin/categories")
    public ModelAndView list() {
        List <Category> allCategories =  categoryRepository.findAll();
        ModelAndView mv = new ModelAndView("listCategories");
        mv.addObject("categories", allCategories);
        return mv;
    }

    @GetMapping("/admin/categories/{codeUrl}/subcategories")
    public ModelAndView listSubcategories(@PathVariable String codeUrl){
        Optional<Category> category = categoryRepository.findByCodeUrl(codeUrl);
        if(category.isEmpty()){
            ModelAndView mv = new ModelAndView("notFound");
            mv.setStatus(HttpStatus.NOT_FOUND);
            return mv;
        }
        List<Subcategory> subCategories = category.get().getSubCategories();
        ModelAndView mv = new ModelAndView("listSubcategories");
        mv.addObject("subcategories", subCategories);
        return mv;
    }
}
