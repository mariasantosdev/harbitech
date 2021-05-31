package br.com.harbitech.school.subcategory;

import br.com.harbitech.school.validation.ValidationUtilUrlCode;
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
    private ValidationUtilUrlCode validateUtil;

    public SubCategory(){
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

    TypeIndicationSubCategory getIndicationCategory() {
        return indicationCategory;
    }

    int getOrderVisualization() {
        return orderVisualization;
    }

    Category getCategory() {
        return category;
    }

    void setCodeUrl(String codeUrl) {
        ValidationUtilUrlCode.validateUrl(codeUrl) ;
        this.codeUrl = codeUrl;
    }
}
