package br.com.harbitech.school.activity;

import br.com.harbitech.school.handler.ValidationUrlCode;
import br.com.harbitech.school.handler.ValidationUtilUrlCode;
import br.com.harbitech.school.section.Section;

public abstract class Activity implements ValidationUrlCode {

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

    @Override
    public void setCodeUrl(String codeUrl){
        this.validateUtil.setCodeUrl(codeUrl);
    }

    @Override
    public void validateUrl(String codeUrl){
        this.validateUtil.validateUrl(codeUrl);
    }

}
