package br.com.harbitech.school.course;

import br.com.harbitech.school.subcategory.Subcategory;

import javax.persistence.*;

import static br.com.harbitech.school.validation.ValidationUtil.*;

@Entity
@NamedQuery(name = "Course.allWithPublicVisibility", query = "SELECT c FROM Course c WHERE c.visibility = :visibility")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "code_url")
    private String codeUrl;
    @Column(name = "completion_time_in_hours")
    private int completionTimeInHours;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM")
    private CourseVisibility visibility;
    @Column(name = "target_audience")
    private String targetAudience;
    private String instructor;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(name = "developed_skills", columnDefinition = "TEXT")
    private String developedSkills;
    @ManyToOne(fetch = FetchType.LAZY)
    private Subcategory subCategory;

    @Deprecated
    public Course(){

    }

    public Course(String name, String codeUrl, int completionTimeInHours, String instructor, Subcategory subCategory){
        validateNonBlankText(name, "O nome do curso não pode estar em branco.");
        validateNonBlankText(codeUrl, "O código do curso não pode estar em branco.");
        validateNonBlankText(instructor, "O nome do instrutor não pode estar em branco");
        validateUrl(codeUrl, "O código da url do curso está incorreto (só aceita letras minúsculas e hífen): " + codeUrl);
        validateInterval(completionTimeInHours,1,20,"O tempo estimado deve estar " +
                "entre 1 hora até 20 horas.");
        validateNonNullClass(subCategory, "A curso deve ter uma sub-categoria associada.");

        this.name = name;
        this.codeUrl = codeUrl;
        this.completionTimeInHours = completionTimeInHours;
        this.instructor = instructor;
        this.visibility = CourseVisibility.PRIVATE;
        this.subCategory = subCategory;
    }

    public Course(String name, String codeUrl, int completionTimeInHours, CourseVisibility visibility,
                  String targetAudience, String instructor, String description, String developedSkills,
                  Subcategory subCategory) {
        this(name, codeUrl, completionTimeInHours, instructor,subCategory);
        this.visibility = visibility;
        this.targetAudience = targetAudience;
        this.instructor = instructor;
        this.description = description;
        this.developedSkills = developedSkills;
        this.subCategory.addCourse(this);
    }

    public Course(Long id,String name, String codeUrl, int completionTimeInHours, CourseVisibility visibility,
                  String targetAudience, String instructor, String description, String developedSkills,
                  Subcategory subCategory) {
        this(name, codeUrl, completionTimeInHours, visibility, targetAudience, instructor, description, developedSkills,
                subCategory);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getCodeUrl() {
        return codeUrl;
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

    public String getName() {
        return name;
    }

    public int getCompletionTimeInHours() {
        return completionTimeInHours;
    }

    public Subcategory getSubCategory() {
        return subCategory;
    }

    public void setId(Long id) {
        this.id = id;
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
                ", subCategory=" + subCategory +
                '}';
    }

    public void publish() {
        this.visibility = CourseVisibility.PUBLIC;
    }
}
