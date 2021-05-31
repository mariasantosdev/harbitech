package br.com.harbitech.school.question;

import br.com.harbitech.school.activity.Activity;
import br.com.harbitech.school.activity.StatusIndicationActivity;
import br.com.harbitech.school.section.Section;

public class Question extends Activity {

    private String enunciated;
    private TypeAnswer answer;

    public Question() {
        super();
    }

    String getEnunciated() {
        return enunciated;
    }

    TypeAnswer getAnswer() {
        return answer;
    }
}
