package br.com.harbitech.school.course;

import br.com.harbitech.school.subcategory.Subcategory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.*;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@NoArgsConstructor
@Getter
@Setter
public class CourseFormUpdate {
    private Long id;
    @NotBlank(message = "{course.name.required}")
    @Size(max = 70, message = "{course.name.size.max}")
    private String name;
    @NotBlank(message = "{course.codeUrl.required}")
    @Size(max = 70, message = "{course.codeUrl.size.max}")
    @Pattern(regexp = "[-a-z]+", message = "{course.codeUrl.pattern}")
    private String codeUrl;
    @Min(value = 1L, message = "{course.completionTimeInHours.min}")
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

    public CourseFormUpdate(Course course) {
        this.id = course.getId();
        this.name = course.getName();
        this.codeUrl = course.getCodeUrl();
        this.description = course.getDescription();
        this.completionTimeInHours = course.getCompletionTimeInHours();
        this.visibility = course.getVisibility();
        this.targetAudience = course.getTargetAudience();
        this.instructor = course.getInstructor();
        this.developedSkills = course.getDevelopedSkills();
        this.subcategory = getSubcategory();
    }

    public Course toModel(CourseRepository courseRepository) {
        Course course = courseRepository.findById(this.getId())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND,
                        format("Course with code %s not found", this.getId())));

        course.update(this);
        return course;
    }
}
