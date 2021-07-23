package br.com.harbitech.school.subcategory;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

public class SubcategoryController {
    private final SubcategoryRepository subcategoryRepository;

    SubcategoryController(SubcategoryRepository subcategoryRepository){
        this.subcategoryRepository = subcategoryRepository;
    }

    @GetMapping("/admin/subcategories/{category}")
    public ModelAndView list() {
        List<Subcategory> allSubcategories =  subcategoryRepository.findAll();
        ModelAndView mv = new ModelAndView("subcategory/listSubcategories");
        mv.addObject("subcategories", allSubcategories);
        return mv;
    }

    @GetMapping(value = "/admin/subcategories/new")
    public ModelAndView formNew(){
        ModelAndView mv = new ModelAndView("subcategory/formNewSubcategory");
        mv.addObject("subcategory", new Subcategory());
        return mv;
    }

    @PostMapping(value = "/admin/subcategories/new")
    public String save(@Valid Subcategory subcategory, BindingResult result) {
        if (result.hasErrors()){
            return "subcategory/formNewSubcategory";
        }
        subcategoryRepository.save(subcategory);
        return "redirect:/admin/subcategories/{category}";
    }
}
