package br.com.harbitech.school.course;

import br.com.harbitech.school.handler.ValidationUrlCode;
import br.com.harbitech.school.handler.ValidationUtilUrlCode;
import br.com.harbitech.school.subcategory.SubCategory;

import java.time.OffsetDateTime;

public class Course implements ValidationUrlCode {

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

    private ValidationUtilUrlCode validateUtil;

    public Course(){
        this.validateUtil = new ValidationUtilUrlCode();
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

    @Override
    public void setCodeUrl(String codeUrl){
        this.validateUtil.setCodeUrl(codeUrl);
    }

    @Override
    public void validateUrl(String codeUrl){
        this.validateUtil.validateUrl(codeUrl);
    }
}
