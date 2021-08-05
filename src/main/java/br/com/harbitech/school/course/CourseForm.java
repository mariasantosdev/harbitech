package br.com.harbitech.school.course;

import br.com.harbitech.school.subcategory.Subcategory;

import javax.validation.constraints.*;

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

    public CourseForm(){}

    public CourseForm(Long id, String name, String codeUrl, CourseVisibility visibility, int completionTimeInHours,
                      String targetAudience, String instructor, String description, String developedSkills,
                      Subcategory subcategory) {
        this.id = id;
        this.name = name;
        this.codeUrl = codeUrl;
        this.visibility = visibility;
        this.completionTimeInHours = completionTimeInHours;
        this.targetAudience = targetAudience;
        this.instructor = instructor;
        this.description = description;
        this.developedSkills = developedSkills;
        this.subcategory = subcategory;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCodeUrl() {
        return codeUrl;
    }

    public int getCompletionTimeInHours() {
        return completionTimeInHours;
    }

    public CourseVisibility getVisibility() {
        return visibility;
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

    public Subcategory getSubcategory() {
        return subcategory;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl;
    }

    public void setCompletionTimeInHours(int completionTimeInHours) {
        this.completionTimeInHours = completionTimeInHours;
    }

    public void setVisibility(CourseVisibility visibility) {
        this.visibility = visibility;
    }

    public void setTargetAudience(String targetAudience) {
        this.targetAudience = targetAudience;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDevelopedSkills(String developedSkills) {
        this.developedSkills = developedSkills;
    }

    public void setSubcategory(Subcategory subcategory) {
        this.subcategory = subcategory;
    }

    public String getSubcategoryCodeUrl(){
        return this.subcategory.getCodeUrl();
    }

    public String getCategoryCodeUrl(){
        return this.subcategory.getCategory().getCodeUrl();
    }

    public static Course convert(CourseForm courseForm) {
        return new Course(courseForm.getName(), courseForm.getCodeUrl(), courseForm.getCompletionTimeInHours(),
                courseForm.getVisibility(), courseForm.getTargetAudience(), courseForm.getInstructor(),
                courseForm.getDescription(), courseForm.getDevelopedSkills(), courseForm.getSubcategory());
    }

    public static Course convert(CourseForm courseForm, CourseRepository courseRepository) {
        Course course = courseRepository.findById(courseForm.getId()).get();
        course.setId(courseForm.getId());
        course.setCodeUrl(courseForm.getCodeUrl());
        course.setName(courseForm.getName());
        course.setCompletionTimeInHours(courseForm.getCompletionTimeInHours());
        course.setVisibility(courseForm.getVisibility());
        course.setTargetAudience(courseForm.getTargetAudience());
        course.setInstructor(courseForm.getInstructor());
        course.setDescription(courseForm.getDescription());
        course.setDevelopedSkills(courseForm.getDevelopedSkills());
        course.setSubcategory(courseForm.getSubcategory());
        return course;
    }

    public static CourseForm from(Course course){
        return new CourseForm(course.getId(),course.getName(), course.getCodeUrl(),course.getVisibility(),
                course.getCompletionTimeInHours(), course.getTargetAudience(), course.getInstructor(),
                course.getDescription(), course.getDevelopedSkills(), course.getSubcategory());
    }

}
