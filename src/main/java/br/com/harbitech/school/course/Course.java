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
    @NotBlank(message = "Por favor insira o nome do curso")
    @Size(max = 70, message = "Ops! O nome do curso não pode ter mais do que 70 caracteres")
    private String name;
    @Size(max = 70, message = "Ops! O código do curso não pode ter mais do que 70 caracteres")
    @NotBlank(message = "Por favor insira o código do curso")
    @Pattern(regexp = "[-a-z]+", message = "O código da url do curso está incorreto (só aceita letras minúsculas e hífen)")
    private String codeUrl;
    @Min(value = 1L, message = "O tempo estimado não pode ser menor do que 1 hora")
    @Min(value = 20L, message = "O tempo estimado não pode ultrapassar 20 horas")
    @NotBlank(message = "Por favor insira o tempo estimado do curso")
    private int completionTimeInHours;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM")
    @NotNull
    private CourseVisibility visibility = CourseVisibility.PRIVATE;
    @Size(max = 250, message = "Ops! O público alvo do curso não pode ter mais do que 250 caracteres")
    private String targetAudience;
    @NotBlank(message = "Por favor insira o nome do instrutor do curso")
    @Size(max = 70, message = "Ops! O nome do intrutor do curso não pode ter mais do que 70 caracteres")
    private String instructor;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(columnDefinition = "TEXT")
    private String developedSkills;
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull(message = "O curso precisa ter uma subcategoria associada")
    private Subcategory subcategory;

    @Deprecated
    public Course(){}

    public Course(String name, String codeUrl, int completionTimeInHours, String instructor, Subcategory subCategory){
        Assert.hasText(name, "O nome da categoria não pode estar em branco.");
        Assert.hasText(codeUrl, "O código do curso não pode estar em branco.");
        Assert.hasText(instructor, "O nome do instrutor não pode estar em branco");

        Assert.isTrue(completionTimeInHours >= 1 && completionTimeInHours <= 20, "O tempo estimado deve estar entre 1 hora até 20 horas.");
        Assert.notNull(subCategory, "A curso deve ter uma sub-categoria associada.");
        validateUrl(codeUrl, "O código da url do curso está incorreto (só aceita letras minúsculas e hífen): " + codeUrl);

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

    public CourseVisibility getVisibility() {
        return visibility;
    }

    public String getInstructor() {
        return instructor;
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
