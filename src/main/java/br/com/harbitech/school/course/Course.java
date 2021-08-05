package br.com.harbitech.school.course;

import br.com.harbitech.school.subcategory.Subcategory;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.*;

import static br.com.harbitech.school.validation.ValidationUtil.validateUrl;

@Entity
@NamedQuery(name = "Course.allWithPublicVisibility", query = "SELECT c FROM Course c WHERE c.visibility = :visibility")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String codeUrl;
    private int completionTimeInHours = 0;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM")
    @NotNull
    private CourseVisibility visibility = CourseVisibility.PRIVATE;
    private String targetAudience;
    private String instructor;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(columnDefinition = "TEXT")
    private String developedSkills;
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull(message = "{subcategory.course.required}")
    private Subcategory subcategory;

    @Deprecated
    public Course(){}

    public Course(String name, String codeUrl, int completionTimeInHours, String instructor, Subcategory subcategory){
        Assert.hasText(name, "{course.name.required}");
        Assert.hasText(codeUrl, "{course.codeUrl.required}");
        Assert.hasText(instructor, "{course.instructor.required}");

        Assert.isTrue(completionTimeInHours >= 1 && completionTimeInHours <= 20, "O tempo estimado deve estar entre 1 hora até 20 horas.");
        Assert.notNull(subcategory, "{course.subcategory.required}");
        validateUrl(codeUrl, "{course.codeUrl.pattern}" + codeUrl);

        this.name = name;
        this.codeUrl = codeUrl;
        this.completionTimeInHours = completionTimeInHours;
        this.instructor = instructor;
        this.visibility = CourseVisibility.PRIVATE;
        this.subcategory = subcategory;
    }

    public Course(String name, String codeUrl, int completionTimeInHours, CourseVisibility visibility,
                  String targetAudience, String instructor, String description, String developedSkills,
                  Subcategory subcategory) {
        this(name, codeUrl, completionTimeInHours, instructor,subcategory);
        this.visibility = visibility;
        this.targetAudience = targetAudience;
        this.description = description;
        this.developedSkills = developedSkills;
    }

    public Course(Long id,String name, String codeUrl, int completionTimeInHours, CourseVisibility visibility,
                  String targetAudience, String instructor, String description, String developedSkills,
                  Subcategory subcategory){
        this(name, codeUrl, completionTimeInHours,visibility,targetAudience,instructor,description,developedSkills,subcategory);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCodeUrl() {
        return codeUrl;
    }

    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl;
    }

    public int getCompletionTimeInHours() {
        return completionTimeInHours;
    }

    public void setCompletionTimeInHours(int completionTimeInHours) {
        this.completionTimeInHours = completionTimeInHours;
    }

    public CourseVisibility getVisibility() {
        return visibility;
    }

    public void setVisibility(CourseVisibility visibility) {
        this.visibility = visibility;
    }

    public String getTargetAudience() {
        return targetAudience;
    }

    public void setTargetAudience(String targetAudience) {
        this.targetAudience = targetAudience;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDevelopedSkills() {
        return developedSkills;
    }

    public void setDevelopedSkills(String developedSkills) {
        this.developedSkills = developedSkills;
    }

    public Subcategory getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(Subcategory subcategory) {
        this.subcategory = subcategory;
    }

    public String getVisibilityDescription(){
        return this.visibility.getDescription();
    }

    public String getSubcategoryCodeUrl(){
        return this.subcategory.getCodeUrl();
    }

    public String getCategoryCodeUrl(){
        return this.subcategory.getCategoryCodeUrl();
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
                ", subCategory=" + subcategory +
                '}';
    }
}
