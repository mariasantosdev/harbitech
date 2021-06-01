package br.com.harbitech.school.course;

import br.com.harbitech.school.validation.ValidationUtil;
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
    private ValidationUtil validateUtil;

    public Course(){
        this.validateUtil = new ValidationUtil();
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

}
