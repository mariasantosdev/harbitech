package br.com.harbitech.school.login;

import br.com.harbitech.school.category.Category;
import br.com.harbitech.school.category.CategoryRepository;
import br.com.harbitech.school.subcategory.SubcategoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class LoginController {

    private final CategoryRepository categoryRepository;

    private final SubcategoryRepository subcategoryRepository;

    LoginController(CategoryRepository categoryRepository, SubcategoryRepository subcategoryRepository){
        this.categoryRepository = categoryRepository;
        this.subcategoryRepository = subcategoryRepository;
    }

    @GetMapping("/login")
    String login(Model model) {
        List<Category> categories = categoryRepository.findAllActiveCategoriesWithPublicCourses();
//        categories.forEach(c -> c.getSubCategories().sort(Comparator.comparing(Subcategory::getOrderVisualization)));

        model.addAttribute("categories", categories);
        return "login/login";
    }
}
