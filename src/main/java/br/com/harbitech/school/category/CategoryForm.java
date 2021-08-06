package br.com.harbitech.school.category;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.*;

import static java.lang.String.format;

public class CategoryForm {

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

    public CategoryForm() {}

    public CategoryForm(String name, String codeUrl, CategoryStatus status, int orderVisualization,
                        String studyGuide, String iconPath, String htmlHexColorCode, String description) {
        this.name = name;
        this.codeUrl = codeUrl;
        this.description = description;
        this.studyGuide = studyGuide;
        this.status = status;
        this.orderVisualization = orderVisualization;
        this.iconPath = iconPath;
        this.htmlHexColorCode = htmlHexColorCode;
    }

    public CategoryForm(Long id, String name, String codeUrl, CategoryStatus status, int orderVisualization,
                        String studyGuide, String iconPath, String htmlHexColorCode, String description) {
        this(name, codeUrl, status, orderVisualization, studyGuide, iconPath, htmlHexColorCode, description);
        this.id = id;
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

    public static Category convert(CategoryForm categoryform) {
        return new Category(categoryform.getName(), categoryform.getCodeUrl(),categoryform.status,categoryform.getOrderVisualization(),
                categoryform.studyGuide, categoryform.getIconPath(), categoryform.getHtmlHexColorCode(), categoryform.getDescription());
    }

    public static Category convert(CategoryForm categoryform, CategoryRepository categoryRepository) {
        Category category = categoryRepository.findById(categoryform.getId())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND,
                        format("Category with code %s not found", categoryform.getId())));

        category.setId(categoryform.getId());
        category.setCodeUrl(categoryform.getCodeUrl());
        category.setName(categoryform.getName());
        category.setDescription(categoryform.getDescription());
        category.setStudyGuide(categoryform.getStudyGuide());
        category.setStatus(categoryform.getStatus());
        category.setOrderVisualization(categoryform.getOrderVisualization());
        category.setIconPath(categoryform.getIconPath());
        category.setHtmlHexColorCode(categoryform.getHtmlHexColorCode());
        return category;
    }

    public static CategoryForm from(Category category){
        return new CategoryForm(category.getId(), category.getName(),category.getCodeUrl(),
                category.getStatus(), category.getOrderVisualization(), category.getStudyGuide(), category.getIconPath(),
                category.getHtmlHexColorCode(), category.getDescription());
    }
}
