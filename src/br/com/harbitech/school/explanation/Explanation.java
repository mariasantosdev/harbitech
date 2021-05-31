package br.com.harbitech.school.explanation;

import br.com.harbitech.school.activity.Activity;
import br.com.harbitech.school.activity.StatusIndicationActivity;
import br.com.harbitech.school.section.Section;

public class Explanation extends Activity {
    private String description;

    public Explanation(String codeUrl, String title, String description, StatusIndicationActivity indication,
                       Section section, String description1) {
        super(codeUrl, title, description, indication, section);
        this.description = description1;
    }

    String getDescription() {
        return description;
    }
}
