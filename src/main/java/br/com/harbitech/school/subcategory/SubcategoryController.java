package br.com.harbitech.school.subcategory;

import br.com.harbitech.school.category.Category;
import org.springframework.ui.Model;
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
    public String list(Model model) {
        List<Subcategory> allSubcategories =  subcategoryRepository.findAll();
        model.addAttribute("subcategories", allSubcategories);
        return "admin/subcategory/listSubcategories";
    }

}
