package br.com.harbitech.school.explanation;

import br.com.harbitech.school.activity.Activity;
import br.com.harbitech.school.section.Section;

import static br.com.harbitech.school.validation.ValidationUtil.validateNonBlankText;

public class Explanation extends Activity {

    private String text;

    public Explanation(String title, String codeUrl, Section section, String text) {
        super(title, codeUrl, section);
        validateNonBlankText(title, "O texto de explicação não pode estar em branco.");
        this.text = text;

    }

    String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "Explanation{" +
                "text='" + text + '\'' +
                '}';
    }
}
