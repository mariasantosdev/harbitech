package br.com.harbitech.school.category;

import br.com.harbitech.school.validation.ValidationUtilUrlCode;

public class Category {

    private Long id;
    private String name;
    private String codeUrl;
    private String description;
    private String studyGuide;
    private TypeIndicationCategory indicationCategory;
    private int orderVisualization;
    private String pathImageOfIcon;
    private String codeColorHtml;
    private ValidationUtilUrlCode validateUtil;

    public Category() {
        this.validateUtil = new ValidationUtilUrlCode();
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

    TypeIndicationCategory getIndicationCategory() {
        return indicationCategory;
    }

    int getOrderVisualization() {
        return orderVisualization;
    }

    String getPathImageOfIcon() {
        return pathImageOfIcon;
    }

    String getCodeColorHtml() {
        return codeColorHtml;
    }

    void setCodeUrl(String codeUrl) {
        ValidationUtilUrlCode.validateUrl(codeUrl) ;
        this.codeUrl = codeUrl;
    }
}

