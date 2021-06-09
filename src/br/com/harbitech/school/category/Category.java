package br.com.harbitech.school.category;

import br.com.harbitech.school.course.Course;
import br.com.harbitech.school.subcategory.SubCategory;

import java.util.*;

import static br.com.harbitech.school.validation.ValidationUtil.validateNonBlankText;
import static br.com.harbitech.school.validation.ValidationUtil.validateUrl;

public class Category {

    private Long id;
    private String name;
    private String codeUrl;
    private String description;
    private String studyGuide;
    private CategoryStatus status;
    private int orderVisualization;
    private String iconPath;
    private String htmlHexColorCode;
    private List<SubCategory> subCategorys = new ArrayList<>();
    private Set<Course> courses = new HashSet<>();

    public Category(String name, String codeUrl) {
        validateNonBlankText(name, "O nome da categoria não pode estar em branco.");
        validateNonBlankText(codeUrl, "O código da URL da categoria não pode estar em branco.");
        validateUrl(codeUrl, "O código da url da categoria está incorreto (só aceita letras minúsculas e hífen): " + codeUrl);

        this.name = name;
        this.codeUrl = codeUrl;
        this.status = CategoryStatus.INACTIVE;
    }

    Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    String getCodeUrl() {
        return codeUrl;
    }

    String getDescription() {
        return description;
    }

    String getStudyGuide() {
        return studyGuide;
    }

    CategoryStatus getStatus() {
        return status;
    }

    int getOrderVisualization() {
        return orderVisualization;
    }

    String getIconPath() {
        return iconPath;
    }

    String getHtmlHexColorCode() {
        return htmlHexColorCode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public void setHtmlHexColorCode(String htmlHexColorCode) {
        this.htmlHexColorCode = htmlHexColorCode;
    }

    public void setAllSubCategorys() {
        this.subCategorys.forEach(s -> System.out.println(s.getName().toString()));
        this.subCategorys.forEach(s -> System.out.println(s.getDescription().toString()));
        this.courses.forEach(s -> System.out.println(s.getSubCategory().toString()));
    }

    public int TotalCourses() {
        return this.courses.size();
    }

    public int TotalTimeInHoursOfCourse() {
        return this.courses.stream().mapToInt(Course::getCompletionTimeInHours).sum();
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", codeUrl='" + codeUrl + '\'' +
                ", description='" + description + '\'' +
                ", studyGuide='" + studyGuide + '\'' +
                ", status=" + status +
                ", orderVisualization=" + orderVisualization +
                ", iconPath='" + iconPath + '\'' +
                ", htmlHexColorCode='" + htmlHexColorCode + '\'' +
                '}';
    }
}

