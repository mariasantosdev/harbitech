package br.com.harbitech.school.subcategory;

import br.com.harbitech.school.category.Category;

public class SubCategory {

    private Long id;
    private String name;
    private String codeUrl;
    private String description;
    private String studyGuide;
    private TypeIndicationSubCategory indicationCategory;
    private int orderVisualization;
    private Category category;

    public SubCategory(Long id, String name, String codeUrl, String description, String studyGuide,
                       TypeIndicationSubCategory indicationCategory, int orderVisualization, Category category) {
        this.id = id;
        this.name = name;
        this.codeUrl = codeUrl;
        this.description = description;
        this.studyGuide = studyGuide;
        this.indicationCategory = indicationCategory;
        this.orderVisualization = orderVisualization;
        this.category = category;
    }

    Long getId() {
        return id;
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

    static void validateUrl(String codeUrl) {
        boolean validacao = codeUrl.matches("[a-z]*");
        if (!validacao) {
            throw new RuntimeException("Não validado");
        }
        System.out.println("Validado");
    }
}
