package br.com.harbitech.school.activity;

import br.com.harbitech.school.section.Section;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "subtype", columnDefinition = "ENUM('VIDEO','QUESTION','EXPLANATION')")
public abstract class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Pattern(regexp = "[-a-z]+", message = "O código da url da atividade está incorreto (só aceita letras minúsculas e hífen)")
    @Column(name = "code_url")
    private String codeUrl;
    @Column(columnDefinition = "TEXT")
    private String text;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM")
    private ActivityStatus status;
    @ManyToOne(fetch = FetchType.LAZY)
    private Section section;

    @Deprecated
    public Activity() {}

    public Activity(String title, String codeUrl, Section section){
        this.title = title;
        this.codeUrl = codeUrl;
        this.section = section;
        this.status = ActivityStatus.INACTIVITE;
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