package br.com.harbitech.school.course;

import br.com.harbitech.school.subcategory.Subcategory;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.*;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.NOT_FOUND;

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

    public CourseFormUpdate() {}

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

    public Course toModel(CourseRepository courseRepository) {
        Course course = courseRepository.findById(this.getId())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND,
                        format("Course with code %s not found", this.getId())));

        course.update(this);
        return course;
    }
}
