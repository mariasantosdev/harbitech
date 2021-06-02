package br.com.harbitech.school.course;

import br.com.harbitech.school.validation.ValidationUtil;
import br.com.harbitech.school.subcategory.SubCategory;

import static br.com.harbitech.school.validation.ValidationUtil.*;

public class Course {

    private Long id;
    private String name;
    private String codeUrl;
    private int completionTimeInHours;
    private CourseVisibility visibility;
    private String targetAudience;
    private String instructor;
    private String description;
    private String developedSkills;
    private SubCategory subCategory;

    public Course(String name, String codeUrl, int completionTimeInHours, String instructor){
        validateNonBlankText(name, "O nome do curso não pode estar em branco.");
        validateNonBlankText(codeUrl, "O código do curso não pode estar em branco.");
        validateNonBlankText(instructor, "O nome do instrutor não pode estar em branco");
        validateUrl(codeUrl, "O código da url do curso está incorreto (só aceita letras minúsculas e hífen): " + codeUrl);
        validateInterval(completionTimeInHours,1,20,"O tempo estimado deve estar " +
                "entre 1 hora até 20 horas.");

        this.name = name;
        this.codeUrl = codeUrl;
        this.completionTimeInHours = completionTimeInHours;
        this.instructor = instructor;
        this.visibility = CourseVisibility.PRIVATE;
    }

    Long getId() {
        return id;
    }

    String getCodeUrl() {
        return codeUrl;
    }

    String getName() {
        return name;
    }

    int getCompletionTimeInHours() {
        return completionTimeInHours;
    }

    String getTargetAudience() {
        return targetAudience;
    }

    String getInstructor() {
        return instructor;
    }

    String getDescription() {
        return description;
    }

    String getDevelopedSkills() {
        return developedSkills;
    }

    SubCategory getSubCategory() {
        return subCategory;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", codeUrl='" + codeUrl + '\'' +
                ", completionTimeInHours=" + completionTimeInHours +
                ", visibility=" + visibility +
                ", targetAudience='" + targetAudience + '\'' +
                ", instructor='" + instructor + '\'' +
                ", description='" + description + '\'' +
                ", developedSkills='" + developedSkills + '\'' +
                ", subCategory=" + subCategory +
                '}';
    }
}
