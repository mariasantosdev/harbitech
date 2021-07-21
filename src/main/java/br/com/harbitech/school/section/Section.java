package br.com.harbitech.school.section;

import br.com.harbitech.school.course.Course;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
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

    @Override
    public String toString() {
        return "Section{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", codeUrl='" + codeUrl + '\'' +
                ", orderVisualization=" + orderVisualization +
                ", status=" + status +
                ", type=" + type +
                ", course=" + course +
                '}';
    }
}