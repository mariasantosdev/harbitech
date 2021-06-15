package br.com.harbitech.school.subcategory;

import br.com.harbitech.school.course.Course;
import br.com.harbitech.school.category.Category;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static br.com.harbitech.school.validation.ValidationUtil.*;

public class SubCategory {

    private Long id;
    private String name;
    private String codeUrl;
    private String description;
    private String studyGuide;
    private SubCategoryStatus status;
    private int orderVisualization;
    private Category category;
    private List<Course> courses = new ArrayList<>();

    public SubCategory(String codeUrl) {
        this.codeUrl = codeUrl;
    }

    public SubCategory(String name, String codeUrl, int orderVisualization,
                       String description, String studyGuide,
                       SubCategoryStatus status, Category category) {
        validateNonBlankText(name, "O nome da sub-categoria não pode estar em branco.");
        validateNonBlankText(codeUrl, "O código da URL da sub-categoria não pode estar em branco.");
        validateNonNullClass(category, "A sub-categoria deve ter uma categoria associada.");
        validateUrl(codeUrl, "O código da url da sub-categoria está incorreto (só aceita letras minúsculas e hífen): " + codeUrl) ;

        this.name = name;
        this.codeUrl = codeUrl;
        this.description = description;
        this.studyGuide = studyGuide;
        this.status = status;
        this.orderVisualization = orderVisualization;
        this.category = category;
    }

    public SubCategory(String name, String codeUrl, Category category){
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

    Long getId() {
        return id;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public String getDescription() {
        return description;
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

    public List <String> nameCourses() {
        Stream<String> names = courses.stream().map(Course::getName);
        return names.collect(Collectors.toList());
    }

    public int totalTimeInHoursOfCourse() {
        return this.courses.stream().mapToInt(Course::getCompletionTimeInHours).sum();
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
}
