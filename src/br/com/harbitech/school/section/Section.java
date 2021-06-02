package br.com.harbitech.school.section;

import br.com.harbitech.school.course.Course;

import static br.com.harbitech.school.validation.ValidationUtil.*;

public class Section {

    private Long id;
    private String name;
    private String codeUrl;
    private int orderVisualization;
    private SectionStatus status;
    private SectionType type;
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