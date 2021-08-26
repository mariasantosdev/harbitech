package br.com.harbitech.school.subcategory;

import br.com.harbitech.school.category.Category;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubcategoryForm {
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
    @NotNull
    private SubCategoryStatus status = SubCategoryStatus.INACTIVE;
    @Min(value = -1, message = "{subcategory.orderVisualization.min}")
    private int orderVisualization;
    @NotNull(message = "{subcategory.category.required}")
    private Category category;

    public Subcategory toModel() {
        return new Subcategory(this.name, this.codeUrl, this.orderVisualization, this.description, this.studyGuide,
                this.status, this.category);
    }
}
