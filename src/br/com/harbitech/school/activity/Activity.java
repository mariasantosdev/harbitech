package br.com.harbitech.school.activity;

import br.com.harbitech.school.validation.ValidationUtilUrlCode;
import br.com.harbitech.school.section.Section;

public abstract class Activity {

    private Long id;
    private String codeUrl;
    private String title;
    private String description;
    private StatusIndicationActivity indication;
    private Section section;
    private ValidationUtilUrlCode validateUtil;

    public Activity(){
        this.validateUtil = new ValidationUtilUrlCode();
    }

    Long getId() {
        return id;
    }

    String getCodeUrl() {
        return codeUrl;
    }

    String getTitle() {
        return title;
    }

    String getDescription() {
        return description;
    }

    StatusIndicationActivity getIndication() {
        return indication;
    }

    Section getSection() {
        return section;
    }

    void setCodeUrl(String codeUrl) {
        ValidationUtilUrlCode.validateUrl(codeUrl) ;
        this.codeUrl = codeUrl;
    }
}