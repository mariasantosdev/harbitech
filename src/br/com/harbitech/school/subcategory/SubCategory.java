package br.com.harbitech.school.subcategory;

import br.com.harbitech.school.category.Category;

public class SubCategory {

    private String name;
    private String codeUrl;
    private String description;
    private String studyGuide;
    private TypeIndicationSubCategory indicationCategory;
    private int orderVisualization;
    private Category category;

    public SubCategory(String name, String codeUrl, String description, String studyGuide,
                       TypeIndicationSubCategory indicationCategory, int orderVisualization, Category category) {
        this.name = name;
        this.codeUrl = codeUrl;
        this.description = description;
        this.studyGuide = studyGuide;
        this.indicationCategory = indicationCategory;
        this.orderVisualization = orderVisualization;
        this.category = category;
    }

    String getName() {
        return name;
    }

    String getCodeUrl() {
        return codeUrl;
    }

    String getDescription() {
        return description;
    }

    String getStudyGuide() {
        return studyGuide;
    }

    TypeIndicationSubCategory getIndicationCategory() {
        return indicationCategory;
    }

    int getOrderVisualization() {
        return orderVisualization;
    }

    Category getCategory() {
        return category;
    }

    public static void validateUrl(String codeUrl) {
        boolean validacao = codeUrl.matches("[a-z]*");
        if (!validacao) {
            throw new RuntimeException("Não validado");
        }
        System.out.println("Validado");
    }
}
