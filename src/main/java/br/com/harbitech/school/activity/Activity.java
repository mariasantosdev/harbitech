package br.com.harbitech.school.activity;

import br.com.harbitech.school.section.Section;

import javax.persistence.*;

import static br.com.harbitech.school.validation.ValidationUtil.*;
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "subtype", columnDefinition = "ENUM('VIDEO','QUESTION','EXPLANATION')")
public abstract class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(name = "code_url")
    private String codeUrl;
    @Column(columnDefinition = "TEXT")
    private String text;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM")
    private ActivityStatus status;
    @ManyToOne(fetch = FetchType.LAZY)
    private Section section;

    public Activity(String title, String codeUrl, Section section){
        validateNonBlankText(title, "O nome da atividade não pode estar em branco.");
        validateNonBlankText(codeUrl, "O código da URL da atividade não pode estar em branco.");
        validateUrl(codeUrl, "O código da url do curso está incorreto (só aceita letras minúsculas e hífen): " + codeUrl);
        validateNonNullClass(section, "Não existe uma seção associada a essa atividade.");

        this.title = title;
        this.codeUrl = codeUrl;
        this.section = section;
        this.status = ActivityStatus.INACTIVITE;
    }

    Long getId() {
        return id;
    }

    String getCodeUrl() {
        return codeUrl;
    }

    String getTitle() {
        return title;
    }

    String getText() {
        return text;
    }

    public ActivityStatus getStatus() {
        return status;
    }

    Section getSection() {
        return section;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", codeUrl='" + codeUrl + '\'' +
                ", text='" + text + '\'' +
                ", status=" + status +
                ", section=" + section +
                '}';
    }
}