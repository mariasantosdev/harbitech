package br.com.harbitech.school.category;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@NoArgsConstructor
@Getter
@Setter
public class CategoryFormUpdate {
    private Long id;
    @NotBlank(message = "{category.name.required}")
    @Size(max = 70, message = "{category.name.size.max}")
    private String name;
    @NotBlank(message = "{category.codeUrl.required}")
    @Size(max = 70, message = "{category.codeUrl.size.max}")
    @Pattern(regexp = "[-a-z]+", message = "{category.codeUrl.pattern}")
    private String codeUrl;
    private String description;
    private String studyGuide;
    private CategoryStatus status = CategoryStatus.INACTIVE;
    @Min(-1)
    private int orderVisualization;
    @Size(max = 400, message = "{category.iconPath.size.max}")
    private String iconPath;
    @Size(max = 7, message = "{category.htmlHexColorCode.size.max}")
    private String htmlHexColorCode;

    public CategoryFormUpdate(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.codeUrl = category.getCodeUrl();
        this.description = category.getDescription();
        this.studyGuide = category.getStudyGuide();
        this.status = category.getStatus();
        this.orderVisualization = category.getOrderVisualization();
        this.iconPath = category.getIconPath();
        this.htmlHexColorCode = category.getHtmlHexColorCode();
    }

    public Category toModel(CategoryRepository categoryRepository) {
        Category category = categoryRepository.findById(this.getId())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND,
                        format("Category with code %s not found", this.getId())));

        category.update(this);
        return category;
    }
}
