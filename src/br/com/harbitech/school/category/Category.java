package br.com.harbitech.school.category;

import br.com.harbitech.school.handler.ValidationUrlCode;
import br.com.harbitech.school.handler.ValidationUtilUrlCode;

public class Category implements ValidationUrlCode {

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

    public Category(){
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

    @Override
    public void setCodeUrl(String codeUrl){
        this.validateUtil.setCodeUrl(codeUrl);
    }

    @Override
    public void validateUrl(String codeUrl){
        this.validateUtil.validateUrl(codeUrl);
    }
}

