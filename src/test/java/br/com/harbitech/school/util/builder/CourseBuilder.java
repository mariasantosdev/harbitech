package br.com.harbitech.school.util.builder;

import br.com.harbitech.school.course.Course;
import br.com.harbitech.school.course.CourseVisibility;
import br.com.harbitech.school.subcategory.Subcategory;

public class CourseBuilder {
    private String name;
    private String codeUrl;
    private int completionTimeInHours;
    private CourseVisibility visibility;
    private String targetAudience;
    private String instructor;
    private String description;
    private String developedSkills;
    private Subcategory subCategory;

    public CourseBuilder(String name, String codeUrl, String instructor, Subcategory subCategory) {
        this.name = name;
        this.codeUrl = codeUrl;
        this.instructor = instructor;
        this.subCategory = subCategory;
    }

    public CourseBuilder withCompletionTimeInHours(Integer completionTimeInHours){
        this.completionTimeInHours = completionTimeInHours;
        return this;
    }

    public CourseBuilder withVisibility(CourseVisibility visibility){
        this.visibility= visibility;
        return this;
    }

    public CourseBuilder withTargetAudience(String targetAudience){
        this.targetAudience= targetAudience;
        return this;
    }

    public CourseBuilder withInstructor(String instructor){
        this.instructor = instructor;
        return this;
    }

    public CourseBuilder withDescription(String description){
        this.description = description;
        return this;
    }

    public CourseBuilder withDevelopedSkills(String developedSkills){
        this.developedSkills = developedSkills;
        return this;
    }

    public Course create(){
        return new Course(name,codeUrl,completionTimeInHours,visibility,targetAudience,instructor,description,
                developedSkills,subCategory);
    }
}
