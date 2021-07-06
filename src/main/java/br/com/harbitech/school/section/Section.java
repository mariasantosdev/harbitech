package br.com.harbitech.school.section;

import br.com.harbitech.school.course.Course;

import javax.persistence.*;

import static br.com.harbitech.school.validation.ValidationUtil.*;
@Entity
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "code_url")
    private String codeUrl;
    @Column(name = "order_visualization")
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
        validateNonBlankText(name, "O nome da seção não pode estar em branco.");
        validateNonBlankText(codeUrl, "O código da URL da seção não pode estar em branco.");
        validateUrl(codeUrl, "O código da url da seção está incorreto (só aceita letras minúsculas e hífen): " + codeUrl);
        validateNonNullClass(course,"Não existe um curso associado a essa seção");

        this.name = name;
        this.codeUrl = codeUrl;
        this.course = course;
        this.status = SectionStatus.INACTIVE;
        this.type = SectionType.LECTURE;
    }

    Long getId() {
        return id;
    }

    String getName() {
        return name;
    }

    String getCodeUrl() {
        return codeUrl;
    }

    int getOrderVisualization() {
        return orderVisualization;
    }

    SectionType getType() {
        return type;
    }

    Course getCourse() {
        return course;
    }

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