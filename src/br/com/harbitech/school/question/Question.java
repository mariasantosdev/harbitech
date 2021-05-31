package br.com.harbitech.school.question;

import br.com.harbitech.school.activity.Activity;
import br.com.harbitech.school.activity.StatusIndicationActivity;
import br.com.harbitech.school.section.Section;

public class Question extends Activity {

    private String enunciated;
    private TypeAnswer answer;

    public Question(Long id, String codeUrl, String title, String description, StatusIndicationActivity indication,
                    Section section, String enunciated, TypeAnswer answer) {
        super(id, codeUrl, title, description, indication, section);
        this.enunciated = enunciated;
        this.answer = answer;
    }

    String getEnunciated() {
        return enunciated;
    }

    TypeAnswer getAnswer() {
        return answer;
    }
}
