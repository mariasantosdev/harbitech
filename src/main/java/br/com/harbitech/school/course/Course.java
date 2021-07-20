package br.com.harbitech.school.course;

import br.com.harbitech.school.subcategory.Subcategory;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
@NamedQuery(name = "Course.allWithPublicVisibility", query = "SELECT c FROM Course c WHERE c.visibility = :visibility")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Pattern(regexp = "[-a-z]+", message = "O código da url do curso está incorreto (só aceita letras minúsculas e hífen)")
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
    private Subcategory subcategory;

    @Deprecated
    public Course(){}

    public Course(String name, String codeUrl, int completionTimeInHours, String instructor, Subcategory subcategory){
        this.name = name;
        this.codeUrl = codeUrl;
        this.completionTimeInHours = completionTimeInHours;
        this.instructor = instructor;
        this.visibility = CourseVisibility.PRIVATE;
        this.subcategory = subcategory;
    }

    public Long getId() {
        return id;
    }

    public String getCodeUrl() {
        return codeUrl;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public int getCompletionTimeInHours() {
        return completionTimeInHours;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDevelopedSkills() {
        return developedSkills;
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
