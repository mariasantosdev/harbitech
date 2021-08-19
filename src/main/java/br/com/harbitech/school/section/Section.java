package br.com.harbitech.school.section;

import br.com.harbitech.school.course.Course;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@ToString
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "O nome da seção não pode estar em branco.")
    @Size(max = 70, message = "Ops! O nome da seção não pode ter mais do que 70 caracteres")
    private String name;
    @Size(max = 70, message = "Ops! O código da seção não pode ter mais do que 70 caracteres")
    @NotBlank(message = "O código da seção não pode estar em branco.")
    @Pattern(regexp = "[-a-z]+", message = "O código da url da seção está incorreto (só aceita letras minúsculas e hífen)")
    private String codeUrl;
    private int orderVisualization;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM")
    private SectionStatus status;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM")
    private SectionType type;
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull(message = "A seção precisa ter um curso associado.")
    private Course course;

    public Section(String name, String codeUrl, Course course){
        this.name = name;
        this.codeUrl = codeUrl;
        this.course = course;
        this.status = SectionStatus.INACTIVE;
        this.type = SectionType.LECTURE;
    }

    @Deprecated
    public Section(){}
}