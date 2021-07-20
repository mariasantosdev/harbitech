package br.com.harbitech.school.question;

import br.com.harbitech.school.activity.Activity;
import br.com.harbitech.school.section.Section;

import javax.persistence.*;

@Entity
@DiscriminatorValue("QUESTION")
public class Question extends Activity {
    @Column(columnDefinition = "TEXT")
    private String text;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM", name = "answer_type")
    private AnswerType type;

    @Deprecated
    public Question(){}

    public Question(String title, String codeUrl, Section section, String text) {
        super(title, codeUrl, section);
        this.text = text;
        this.type = AnswerType.SINGLE_CHOICE;
    }

    @Override
    public String toString() {
        return "Question{" +
                "text='" + text + '\'' +
                ", type=" + type +
                '}';
    }
}
