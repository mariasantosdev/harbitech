package br.com.harbitech.school.category;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryApiController {

    private final CategoryRepository categoryRepository;

    CategoryApiController(CategoryRepository categoryRepository){
        this.categoryRepository =  categoryRepository;
    }

    @GetMapping(value = "api/categories",produces= {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @Cacheable(value = "listOfCategories")
    public List<CategoryResponse> allCategories() {
        List<Category> activeCategories = categoryRepository.findAllByStatus(CategoryStatus.ACTIVE);
        return CategoryResponse.convert(activeCategories);
    }

    @GetMapping(value = "api/categories/bGltcGEtby1jYWNoZS1kYS1hcGktYWU")
    @CacheEvict(value = "listOfCategories", allEntries = true)
    public String clearCache(){
        return "Chave limpa com sucesso";
    }
}
