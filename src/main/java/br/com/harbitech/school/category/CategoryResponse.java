package br.com.harbitech.school.category;

import br.com.harbitech.school.subcategory.Subcategory;
import br.com.harbitech.school.subcategory.SubcategoryResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryResponse {
    private final String name;
    private final String codeUrl;
    private final String studyGuide;
    private final int orderVisualization;
    private final String iconPath;
    private final String htmlHexColorCode;
    private final List<SubcategoryResponse> subcategory;

    public CategoryResponse(String name, String codeUrl, String studyGuide, int orderVisualization, String iconPath,
                            String htmlHexColorCode, List<SubcategoryResponse> subcategoryResponse) {
        this.name = name;
        this.codeUrl = codeUrl;
        this.studyGuide = studyGuide;
        this.orderVisualization = orderVisualization;
        this.iconPath = iconPath;
        this.htmlHexColorCode = htmlHexColorCode;
        this.subcategory = subcategoryResponse;
    }


    public String getName() {
        return name;
    }

    public String getCodeUrl() {
        return codeUrl;
    }

    public String getStudyGuide() {
        return studyGuide;
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

    public List<SubcategoryResponse> getSubcategory() {
        return subcategory;
    }

    @JsonProperty
    public int totalCourses() {
        return this.subcategory.stream().mapToInt(SubcategoryResponse::totalCourses).sum();
    }

    public static List<CategoryResponse> convert(List<Category> categories) {
        return categories.stream().map(CategoryResponse::convert)
                .collect(Collectors.toList());
    }

    private static CategoryResponse convert(Category category) {
        return new CategoryResponse(
                category.getName(), category.getCodeUrl(), category.getStudyGuide(), category.getOrderVisualization(),
                category.getIconPath(), category.getHtmlHexColorCode(),
                SubcategoryResponse.convert(category.getSubCategories()));
    }
}
