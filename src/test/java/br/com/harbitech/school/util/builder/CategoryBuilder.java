package br.com.harbitech.school.util.builder;

import br.com.harbitech.school.category.Category;
import br.com.harbitech.school.category.CategoryStatus;

public class CategoryBuilder {
    private String name;
    private String codeUrl;
    private String description;
    private String studyGuide;
    private CategoryStatus status;
    private int orderVisualization;
    private String iconPath;
    private String htmlHexColorCode;

    public CategoryBuilder(String name, String codeUrl) {
        this.name = name;
        this.codeUrl = codeUrl;
    }

    public CategoryBuilder withDescription(String description){
        this.description = description;
        return this;
    }

    public CategoryBuilder withStudyGuide(String studyGuide){
        this.studyGuide = studyGuide;
        return this;
    }

    public CategoryBuilder withStatus(CategoryStatus status){
        this.status = status;
        return this;
    }

    public CategoryBuilder withOrderVisualization(Integer orderVisualization){
        this.orderVisualization = orderVisualization;
        return this;
    }

    public CategoryBuilder withIconPath(String iconPath){
        this.iconPath = iconPath;
        return this;
    }

    public CategoryBuilder withHtmlHexColorCode(String htmlHexColorCode){
        this.htmlHexColorCode = htmlHexColorCode;
        return this;
    }

    public Category create(){
        return new Category(name,codeUrl,description,status,orderVisualization,iconPath,htmlHexColorCode);
    }

}
