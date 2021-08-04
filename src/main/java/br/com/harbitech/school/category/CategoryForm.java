package br.com.harbitech.school.category;

import javax.validation.constraints.*;
import java.util.List;
import java.util.stream.Collectors;

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

    public CategoryForm() {
    }

    public CategoryForm(Long id, String name, String codeUrl, String description, String studyGuide,
                        CategoryStatus status, int orderVisualization, String iconPath, String htmlHexColorCode) {
        this.id = id;
        this.name = name;
        this.codeUrl = codeUrl;
        this.description = description;
        this.studyGuide = studyGuide;
        this.status = status;
        this.orderVisualization = orderVisualization;
        this.iconPath = iconPath;
        this.htmlHexColorCode = htmlHexColorCode;
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
        return new Category(categoryform.getName(), categoryform.getCodeUrl(),
                categoryform.getDescription(),categoryform.status,categoryform.getOrderVisualization(),
                categoryform.iconPath, categoryform.getIconPath());
    }

    public static Category convertUpdate(CategoryForm categoryform) {
        return new Category(categoryform.getId(),categoryform.getName(), categoryform.getCodeUrl(),
                categoryform.getDescription(), categoryform.getStudyGuide(), categoryform.status,
                categoryform.getOrderVisualization(), categoryform.iconPath, categoryform.getIconPath());
    }

    public static CategoryForm from(Category category){
        return new CategoryForm(category.getId(),category.getName(),category.getCodeUrl(),
                category.getStatus().getDescription(), category.getStudyGuide(), category.getStatus(),
                category.getOrderVisualization(), category.getIconPath(), category.getHtmlHexColorCode());
    }
}
