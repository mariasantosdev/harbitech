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

    public Long getId() {
        return id;
    }

    public String getCodeUrl() {
        return codeUrl;
    }

    public String getName() {
        return name;
    }

    public OffsetDateTime getCompletionTime() {
        return completionTime;
    }

    public TypeVisibilityCourse getVisibilityCourse() {
        return visibilityCourse;
    }

    public String getTargetAudience() {
        return targetAudience;
    }

    public String getInstructor() {
        return instructor;
    }

    public String getDescription() {
        return description;
    }

    public String getDevelopedSkills() {
        return developedSkills;
    }

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public static void validateUrl(String codeUrl) {
        boolean validacao = codeUrl.matches("[a-z]*");
        if (!validacao) {
            throw new RuntimeException("Não validado");
        }
        System.out.println("Validado");
    }
}
