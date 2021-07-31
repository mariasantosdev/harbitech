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
    @NotBlank(message = "{course.name.required}")
    @Size(max = 70, message = "{course.name.size.max}")
    private String name;
    @NotBlank(message = "{course.codeUrl.required}")
    @Size(max = 70, message = "{course.codeUrl.size.max}")
    @Pattern(regexp = "[-a-z]+", message = "{course.codeUrl.pattern}")
    private String codeUrl;
    @Min(value = 1L, message = "{course.completionTimeInHours.min}")
    @Min(value = 20L, message = "{course.completionTimeInHours.max}")
    @NotBlank(message = "{course.completionTimeInHours.required}")
    private int completionTimeInHours;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM")
    @NotNull
    private CourseVisibility visibility = CourseVisibility.PRIVATE;
    @Size(max = 250, message = "Ops! O público alvo do curso não pode ter mais do que 250 caracteres")
    private String targetAudience;
    @NotBlank(message = "{course.instructor.required}")
    @Size(max = 70, message = "{course.instructor.size.max}")
    private String instructor;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(columnDefinition = "TEXT")
    private String developedSkills;
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull(message = "{course.subcategory.required}")
    private Subcategory subcategory;

    @Deprecated
    public Course(){}

    public Course(String name, String codeUrl, int completionTimeInHours, String instructor, Subcategory subCategory){
        Assert.hasText(name, "{course.name.required}");
        Assert.hasText(codeUrl, "{course.codeUrl.required}");
        Assert.hasText(instructor, "{course.instructor.required}");

        Assert.isTrue(completionTimeInHours >= 1 && completionTimeInHours <= 20, "O tempo estimado deve estar entre 1 hora até 20 horas.");
        Assert.notNull(subCategory, "{course.subcategory.required}");
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
