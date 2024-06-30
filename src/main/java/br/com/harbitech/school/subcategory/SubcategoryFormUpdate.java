package br.com.harbitech.school.subcategory;

import br.com.harbitech.school.category.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.*;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
@Setter
@NoArgsConstructor
public class SubcategoryFormUpdate {
    private Long id;
    @NotBlank(message = "{subcategory.name.required}")
    @Size(max = 70, message = "{subcategory.name.size.max}")
    private String name;
    @NotBlank(message = "{subcategory.codeUrl.required}")
    @Size(max = 70, message = "{subcategory.codeUrl.size.max}")
    @Pattern(regexp = "[-a-z]+", message = "{subcategory.codeUrl.pattern}")
    private String codeUrl;
    private String description;
    private String studyGuide;
    @PositiveOrZero(message = "O nível precisa ser maior ou igual a zero")
    private int level;
    @NotNull
    private SubCategoryStatus status = SubCategoryStatus.INACTIVE;
    @Min(value = -1, message = "{subcategory.orderVisualization.min}")
    private int orderVisualization;
    @NotNull(message = "{subcategory.category.required}")
    private Category category;

    public SubcategoryFormUpdate(Subcategory subcategory){
        this.id = subcategory.getId();
        this.name = subcategory.getName();
        this.level = subcategory.getLevel();
        this.codeUrl = subcategory.getCodeUrl();
        this.description = subcategory.getDescription();
        this.studyGuide = subcategory.getStudyGuide();
        this.status = subcategory.getStatus();
        this.orderVisualization = subcategory.getOrderVisualization();
        this.category = subcategory.getCategory();
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Subcategory toModel(SubcategoryRepository subcategoryRepository) {
        Subcategory subcategory = subcategoryRepository.findById(this.getId())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND,
                        format("Subcategory with code %s not found", this.getId())));

        subcategory.update(this);
        return subcategory;
    }
}
