package br.com.harbitech.school.category;

import br.com.harbitech.school.subcategory.SubcategoryResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.stream.Collectors;

public record CategoryResponse(
        String name,
        String codeUrl,
        String studyGuide,
        int orderVisualization,
        String iconPath,
        String htmlHexColorCode,
        List<SubcategoryResponse> subcategory) {

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

