package br.com.harbitech.school.course;

import br.com.harbitech.school.subcategory.Subcategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseForm {
    private Long id;
    @NotBlank(message = "{course.name.required}")
    @Size(max = 70, message = "{course.name.size.max}")
    private String name;
    @NotBlank(message = "{course.codeUrl.required}")
    @Size(max = 70, message = "{course.codeUrl.size.max}")
    @Pattern(regexp = "[-a-z]+", message = "{course.codeUrl.pattern}")
    private String codeUrl;
    @Min(value = 1L, message = "{course.completionTimeInHours.min}")
    @Max(value = 20L, message = "{course.completionTimeInHours.max}")
    private int completionTimeInHours = 0;
    private CourseVisibility visibility = CourseVisibility.PRIVATE;
    @Size(max = 250, message = "{course.targetAudience.size.max}")
    private String targetAudience;
    @NotBlank(message = "{course.instructor.required}")
    @Size(max = 70, message = "{course.instructor.size.max}")
    private String instructor;
    private String description;
    private String developedSkills;
    @NotNull(message = "{subcategory.category.required}")
    private Subcategory subcategory;


    public String getSubcategoryCodeUrl(){
        return this.subcategory.getCodeUrl();
    }

    public String getCategoryCodeUrl(){
        return this.subcategory.getCategory().getCodeUrl();
    }

    public Course toModel() {
        return new Course(this.name, this.codeUrl, this.completionTimeInHours,
                this.visibility, this.targetAudience, this.instructor,
                this.description, this.developedSkills, this.subcategory);
    }
}
