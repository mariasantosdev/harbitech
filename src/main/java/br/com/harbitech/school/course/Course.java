package br.com.harbitech.school.course;

import br.com.harbitech.school.subcategory.Subcategory;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.*;

import static br.com.harbitech.school.validation.ValidationUtil.validateUrl;

@Entity
@NamedQuery(name = "Course.allWithPublicVisibility", query = "SELECT c FROM Course c WHERE c.visibility = :visibility")
@Getter
@ToString
@NoArgsConstructor(onConstructor = @__(@Deprecated))
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String codeUrl;
    private int completionTimeInHours;
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
    private int orderVisualization;
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull(message = "{subcategory.course.required}")
    private Subcategory subcategory;

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

    public String getVisibilityDescription(){
        return this.visibility.getDescription();
    }

    public String getSubcategoryCodeUrl(){
        return this.subcategory.getCodeUrl();
    }

    public String getCategoryCodeUrl(){
        return this.subcategory.getCategoryCodeUrl();
    }

    public void  update(CourseFormUpdate courseFormUpdate) {
        this.name = courseFormUpdate.getName();
        this.codeUrl = courseFormUpdate.getCodeUrl();
        this.description = courseFormUpdate.getDescription();
        this.completionTimeInHours = courseFormUpdate.getCompletionTimeInHours();
        this.visibility = courseFormUpdate.getVisibility();
        this.targetAudience = courseFormUpdate.getTargetAudience();
        this.instructor = courseFormUpdate.getInstructor();
        this.developedSkills = courseFormUpdate.getDevelopedSkills();
        this.subcategory = courseFormUpdate.getSubcategory();
    }
}
