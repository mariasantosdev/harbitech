package br.com.harbitech.school.category;

import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.NOT_FOUND;

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

    public CategoryFormUpdate(){

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCodeUrl() {
        return codeUrl;
    }

    public String getDescription() {
        return description;
    }

    public String getStudyGuide() {
        return studyGuide;
    }

    public CategoryStatus getStatus() {
        return status;
    }

    public int getOrderVisualization() {
        return orderVisualization;
    }

    public String getIconPath() {
        return iconPath;
    }

    public String getHtmlHexColorCode() {
        return htmlHexColorCode;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStudyGuide(String studyGuide) {
        this.studyGuide = studyGuide;
    }

    public void setStatus(CategoryStatus status) {
        this.status = status;
    }

    public void setOrderVisualization(int orderVisualization) {
        this.orderVisualization = orderVisualization;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public void setHtmlHexColorCode(String htmlHexColorCode) {
        this.htmlHexColorCode = htmlHexColorCode;
    }

    public Category toModel(CategoryRepository categoryRepository) {
        Category category = categoryRepository.findById(this.getId())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND,
                        format("Category with code %s not found", this.getId())));

        category.update(this);
        return category;
    }
}