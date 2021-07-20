package br.com.harbitech.school.category;

import br.com.harbitech.school.subcategory.Subcategory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin/categories")
public class CategoryController {

    private final CategoryRepository categoryRepository;

    CategoryController(CategoryRepository categoryRepository){
        this.categoryRepository =  categoryRepository;
    }

    @GetMapping(value = "/new")
    public ModelAndView form(){
        ModelAndView mv = new ModelAndView("formNewCategory");
        mv.addObject("category", new Category());
        return mv;
    }

    @PostMapping(value = "/new")
    @ResponseStatus(HttpStatus.CREATED)
    public ModelAndView save(@Valid Category category, BindingResult result) {
        ModelAndView mv = new ModelAndView("formNewCategory");
        if (result.hasErrors()){
            return mv;
    }
        categoryRepository.save(category);
        mv.addObject("mensagem", "Título salvo com sucesso");
        return mv;
    }

    @GetMapping(value = "{codeUrl}")
    public ModelAndView formUpdate(@PathVariable String codeUrl){
        Category category = categoryRepository.findByCodeUrl(codeUrl);
        ModelAndView mv = new ModelAndView("formNewCategory");
        mv.addObject(category);
        return mv;
    }

    @PostMapping("{codeUrl}")
    public ModelAndView update(@Valid Category category, BindingResult result) {
        ModelAndView mv = new ModelAndView("formNewCategory");
        if (result.hasErrors()){
            return mv;
        }
        categoryRepository.save(category);
        mv.addObject(category);
        return mv;
    }

    @GetMapping
    public ModelAndView findAll() {
        List <Category> allCategories =  categoryRepository.findAll();
        ModelAndView mv = new ModelAndView("listCategories");
        mv.addObject("categories", allCategories);
        return mv;
    }

    @GetMapping("{codeUrl}/subcategories")
    public ModelAndView findSubcategories(@PathVariable String codeUrl){
        Category category = categoryRepository.findByCodeUrl(codeUrl);
        List<Subcategory> subCategories = category.getSubCategories();
        ModelAndView mv = new ModelAndView("listSubcategories");
        mv.addObject("subcategories", subCategories);
        return mv;
    }
}
