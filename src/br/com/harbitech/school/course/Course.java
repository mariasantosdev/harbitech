package br.com.harbitech.school.course;

import br.com.harbitech.school.subcategory.SubCategory;

import java.time.OffsetDateTime;

public class Course {

    private Long id;
    private String codeUrl;
    private String name;
    private OffsetDateTime completionTime;
    private TypeVisibilityCourse visibilityCourse;
    private String targetAudience;
    private String instructor;
    private String description;
    private String developedSkills;
    private SubCategory subCategory;

    public Course(Long id, String codeUrl, String name, OffsetDateTime completionTime,
                  TypeVisibilityCourse visibilityCourse, String targetAudience, String instructor,
                  String description, String developedSkills, SubCategory subCategory) {
        this.id = id;
        this.codeUrl = codeUrl;
        this.name = name;
        this.completionTime = completionTime;
        this.visibilityCourse = visibilityCourse;
        this.targetAudience = targetAudience;
        this.instructor = instructor;
        this.description = description;
        this.developedSkills = developedSkills;
        this.subCategory = subCategory;
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

    OffsetDateTime getCompletionTime() {
        return completionTime;
    }

    TypeVisibilityCourse getVisibilityCourse() {
        return visibilityCourse;
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

    static void validateUrl(String codeUrl) {
        boolean validacao = codeUrl.matches("[a-z]*");
        if (!validacao) {
            throw new RuntimeException("Não validado");
        }
        System.out.println("Validado");
    }
}
