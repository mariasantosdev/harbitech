package br.com.harbitech.school.activity;

import br.com.harbitech.school.section.Section;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "subtype", columnDefinition = "ENUM('VIDEO','QUESTION','EXPLANATION')")
@ToString
@NoArgsConstructor(onConstructor = @__(@Deprecated))
public abstract class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Por favor insira o título da atividade")
    @Size(max = 150, message = "Ops! O título da atividade não pode ter mais do que 150 caracteres")
    private String title;
    @NotBlank(message = "Por favor insira o código da atividade")
    @Size(max = 70, message = "Ops! O código de uma categoria não pode ter mais do que 70 caracteres")
    @Pattern(regexp = "[-a-z]+", message = "O código da url da atividade está incorreto (só aceita letras minúsculas e hífen)")
    private String codeUrl;
    @Column(columnDefinition = "TEXT")
    private String text;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM")
    private ActivityStatus status;
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull(message = "A atividade precisa ter uma seção associada.")
    private Section section;

    public Activity(String title, String codeUrl, Section section){
        this.title = title;
        this.codeUrl = codeUrl;
        this.section = section;
        this.status = ActivityStatus.INACTIVITE;
    }
}