package br.com.harbitech.school.subcategory;

import br.com.harbitech.school.course.Course;
import br.com.harbitech.school.category.Category;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static br.com.harbitech.school.validation.ValidationUtil.*;

@Entity
@NamedQuery(name = "Subcategory.allActive", query = "SELECT s FROM Subcategory s WHERE s.status = :status ORDER BY " +
        "s.orderVisualization")
@NamedQuery(name = "Subcategory.AllWithoutDescription", query = "SELECT s.name FROM Subcategory s WHERE s.description = '' " +
        "OR s.description IS NULL")
public class Subcategory implements Comparable<Subcategory>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "code_url")
    private String codeUrl;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(name = "study_guide", columnDefinition = "TEXT")
    private String studyGuide;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM")
    private SubCategoryStatus status;
    @Column(name = "order_visualization")
    private int orderVisualization;
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;
    @OneToMany(mappedBy = "subCategory")
    private List<Course> courses = new ArrayList<>();

    @Deprecated
    public Subcategory(){
    }

    public Subcategory(String name, String codeUrl, Category category){
        validateNonBlankText(name, "O nome da sub-categoria não pode estar em branco.");
        validateNonBlankText(codeUrl, "O código da URL da sub-categoria não pode estar em branco.");
        validateNonNullClass(category, "A sub-categoria deve ter uma categoria associada.");
        validateUrl(codeUrl, "O código da url da sub-categoria está incorreto (só aceita letras minúsculas e hífen): " + codeUrl) ;

        this.name = name;
        this.codeUrl = codeUrl;
        this.category = category;
        this.status = SubCategoryStatus.INACTIVE;
        this.orderVisualization = -1;
    }

    public Subcategory(String name, String codeUrl, int orderVisualization, String description, String studyGuide,
                       SubCategoryStatus status, Category category) {

        this(name,codeUrl,category);
        this.name = name;
        this.codeUrl = codeUrl;
        this.description = description;
        this.studyGuide = studyGuide;
        this.status = status;
        this.orderVisualization = orderVisualization;
        this.category = category;
        this.category.addSubcategory(this);
    }

    public Subcategory(Long id, String name, String codeUrl, int orderVisualization, String description, String studyGuide,
                       SubCategoryStatus status, Category category){
        this(name, codeUrl, orderVisualization, description, studyGuide, status, category);
        this.id = id;
    }

    public int getOrderVisualization() {
        return orderVisualization;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public int totalCourses() {
       return courses.size();
    }

    public List <String> nameOfCourses() {
        Stream<String> names = courses.stream().map(Course::getName);
        return names.collect(Collectors.toList());
    }

    public int totalTimeInHoursOfCourse() {
        return this.courses.stream().mapToInt(Course::getCompletionTimeInHours).sum();
    }

    public String getName() {
        return name;
    }

    public String getCodeUrl() {
        return codeUrl;
    }

    public Category getCategory() {
        return category;
    }

    public String getStudyGuide() {
        return studyGuide;
    }

    public SubCategoryStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "SubCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", codeUrl='" + codeUrl + '\'' +
                ", description='" + description + '\'' +
                ", studyGuide='" + studyGuide + '\'' +
                ", status=" + status +
                ", orderVisualization=" + orderVisualization +
                ", category=" + category +
                '}';
    }

    @Override
    public int compareTo(Subcategory otherSubCategory) {
        if (otherSubCategory.getOrderVisualization() < this.orderVisualization) {
            return otherSubCategory.getOrderVisualization();
        }
        return this.orderVisualization;
    }

    public void addCourse(Course course) {
        this.courses.add(course);
    }
}
