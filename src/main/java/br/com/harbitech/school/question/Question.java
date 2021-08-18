package br.com.harbitech.school.question;

import br.com.harbitech.school.activity.Activity;
import br.com.harbitech.school.section.Section;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@DiscriminatorValue("QUESTION")
@ToString
public class Question extends Activity {
    @Column(columnDefinition = "TEXT")
    @NotBlank(message = "O enunciado da questão é obrigatório")
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
}
