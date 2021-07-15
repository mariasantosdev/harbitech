package br.com.harbitech.school.question;

import br.com.harbitech.school.activity.Activity;
import br.com.harbitech.school.section.Section;

import javax.persistence.*;

import static br.com.harbitech.school.validation.ValidationUtil.validateNonBlankText;

@Entity
@DiscriminatorValue("QUESTION")
public class Question extends Activity {
    @Column(columnDefinition = "TEXT")
    private String text;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM", name = "answer_type")
    private AnswerType type;

    public Question(String title, String codeUrl, Section section, String text) {
        super(title, codeUrl, section);
        validateNonBlankText(text, "O enunciado da questão é obrigatório.");
        this.text = text;
        this.type = AnswerType.SINGLE_CHOICE;
    }

    String getText() {
        return text;
    }

    AnswerType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Question{" +
                "text='" + text + '\'' +
                ", type=" + type +
                '}';
    }
}
