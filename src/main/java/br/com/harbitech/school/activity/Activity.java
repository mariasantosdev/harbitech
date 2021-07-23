package br.com.harbitech.school.activity;

import br.com.harbitech.school.section.Section;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "subtype", columnDefinition = "ENUM('VIDEO','QUESTION','EXPLANATION')")
public abstract class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String title;
    @Pattern(regexp = "[-a-z]+", message = "O código da url da atividade está incorreto (só aceita letras minúsculas e hífen)")
    private String codeUrl;
    @Column(columnDefinition = "TEXT")
    private String text;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM")
    private ActivityStatus status;
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull(message = "A atividade precisa ter uma seção vinculada.")
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