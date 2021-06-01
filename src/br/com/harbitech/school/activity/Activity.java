package br.com.harbitech.school.activity;

import br.com.harbitech.school.validation.ValidationUtil;
import br.com.harbitech.school.section.Section;

public abstract class Activity {

    private Long id;
    private String codeUrl;
    private String title;
    private String description;
    private StatusIndicationActivity indication;
    private Section section;
    private ValidationUtil validateUtil;

    public Activity(){
        this.validateUtil = new ValidationUtil();
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

}