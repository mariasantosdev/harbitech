package br.com.harbitech.school.category;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryDto {

    private final Long id;
    private final String name;
    private final String codeUrl;
    private final String description;
    private final String studyGuide;
    private final CategoryStatus status;
    private final int orderVisualization;
    private final String iconPath;
    private final String htmlHexColorCode;

    public CategoryDto(Category category) {
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

    public static List<CategoryDto> convert(List<Category> categories){
        return categories.stream().map(CategoryDto::new).collect(Collectors.toList());
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


}
