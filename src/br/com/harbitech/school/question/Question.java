package br.com.harbitech.school.question;

import br.com.harbitech.school.activity.Activity;
import br.com.harbitech.school.section.Section;

public class Question extends Activity {

    private String text;
    private TypeAnswer answer;

    public Question(String title, String codeUrl, Section section, String text) {
        super(title, codeUrl, section);
        this.text = text;
        this.answer = TypeAnswer.UNIQUE_ANSWER;
    }

    String getText() {
        return text;
    }

    TypeAnswer getAnswer() {
        return answer;
    }
}
