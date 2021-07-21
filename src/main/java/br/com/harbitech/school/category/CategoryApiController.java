package br.com.harbitech.school.category;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryApiController {

    private final CategoryRepository categoryRepository;

    CategoryApiController(CategoryRepository categoryRepository){
        this.categoryRepository =  categoryRepository;
    }

    @GetMapping(value = "api/categories",produces= {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    List<CategoryResponse> allCategories() {
        List<Category> activeCategories = categoryRepository.findByStatus(CategoryStatus.ACTIVE);
        return CategoryResponse.convert(activeCategories);
    }
}
