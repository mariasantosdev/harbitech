package br.com.harbitech.school.subcategory;

import br.com.harbitech.school.category.Category;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.*;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.NOT_FOUND;

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
    @NotNull
    private SubCategoryStatus status = SubCategoryStatus.INACTIVE;
    @Min(value = -1, message = "{subcategory.orderVisualization.min}")
    private int orderVisualization;
    @NotNull(message = "{subcategory.category.required}")
    private Category category;

    public SubcategoryFormUpdate(Subcategory subcategory){
        this.id = subcategory.getId();
        this.name = subcategory.getName();
        this.codeUrl = subcategory.getCodeUrl();
        this.description = subcategory.getDescription();
        this.studyGuide = subcategory.getStudyGuide();
        this.status = subcategory.getStatus();
        this.orderVisualization = subcategory.getOrderVisualization();
        this.category = subcategory.getCategory();
    }

    public SubcategoryFormUpdate(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCodeUrl() {
        return codeUrl;
    }

    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStudyGuide() {
        return studyGuide;
    }

    public void setStudyGuide(String studyGuide) {
        this.studyGuide = studyGuide;
    }

    public SubCategoryStatus getStatus() {
        return status;
    }

    public void setStatus(SubCategoryStatus status) {
        this.status = status;
    }

    public int getOrderVisualization() {
        return orderVisualization;
    }

    public void setOrderVisualization(int orderVisualization) {
        this.orderVisualization = orderVisualization;
    }

    public Category getCategory() {
        return category;
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
