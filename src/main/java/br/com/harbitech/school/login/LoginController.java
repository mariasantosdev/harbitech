package br.com.harbitech.school.login;

import br.com.harbitech.school.category.ActiveCategoriesProjection;
import br.com.harbitech.school.category.CategoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class LoginController {

    private final CategoryRepository categoryRepository;

    LoginController(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/login")
    String login(Model model) {
        List<ActiveCategoriesProjection> activeCategories = categoryRepository.findAllActiveCategories();

        System.out.println(activeCategories);
        model.addAttribute("activeCategories", activeCategories);
        return "login/login";
    }
}
