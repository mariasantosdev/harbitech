package br.com.harbitech.school.activity;

import br.com.harbitech.school.section.Section;

public class Activity {

    private String codeUrl;
    private String title;
    private String description;
    private StatusIndicationActivity indication;
    private Section section;

    public Activity(String codeUrl, String title, String description, StatusIndicationActivity indication,
                    Section section) {
        this.codeUrl = codeUrl;
        this.title = title;
        this.description = description;
        this.indication = indication;
        this.section = section;
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

    public static void validateUrl(String codeUrl) {
        boolean validacao = codeUrl.matches("[a-z]*");
        if (!validacao) {
            throw new RuntimeException("Não validado");
        }
        System.out.println("Validado");
    }
}
