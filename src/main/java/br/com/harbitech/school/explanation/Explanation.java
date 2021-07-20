package br.com.harbitech.school.explanation;

import br.com.harbitech.school.activity.Activity;
import br.com.harbitech.school.section.Section;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("EXPLANATION")
public class Explanation extends Activity {
    @Column(columnDefinition = "TEXT")
    private String text;

    @Deprecated
    public Explanation() {
    }

    public Explanation(String title, String codeUrl, Section section, String text) {
        super(title, codeUrl, section);
        this.text = text;
    }

    @Override
    public String toString() {
        return "Explanation{" +
                "text='" + text + '\'' +
                '}';
    }
}
