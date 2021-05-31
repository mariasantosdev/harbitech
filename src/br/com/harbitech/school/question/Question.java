package br.com.harbitech.school.question;

import br.com.harbitech.school.activity.Activity;
import br.com.harbitech.school.activity.StatusIndicationActivity;
import br.com.harbitech.school.section.Section;

public class Question extends Activity {

    private Long id;
    private String enunciated;
    private TypeAnswer answer;

    public Question(String codeUrl, String title, String description, StatusIndicationActivity indication,
                    Section section, Long id, String enunciated, TypeAnswer answer) {
        super(codeUrl, title, description, indication, section);
        this.id = id;
        this.enunciated = enunciated;
        this.answer = answer;
    }

    Long getId() {
        return id;
    }

    String getEnunciated() {
        return enunciated;
    }

    TypeAnswer getAnswer() {
        return answer;
    }



}
