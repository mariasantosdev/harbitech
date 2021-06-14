package br.com.harbitech.school.subcategory;

import br.com.harbitech.school.category.CategoryStatus;
import br.com.harbitech.school.course.Course;
import br.com.harbitech.school.validation.ValidationUtil;
import br.com.harbitech.school.category.Category;

import java.util.*;

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

    public SubCategory(String name) {
        this.name = name;
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


    public SubCategory(String name, String codeUrl, int orderVisualization,
                       String description, String studyGuide,
                       SubCategoryStatus status, Category category) {
        this.name = name;
        this.codeUrl = codeUrl;
        this.description = description;
        this.studyGuide = studyGuide;
        this.status = status;
        this.orderVisualization = orderVisualization;
        this.category = category;
    }

    Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCodeUrl() {
        return codeUrl;
    }

    public String getDescription() {
        return description;
    }

    String getStudyGuide() {
        return studyGuide;
    }

    public SubCategoryStatus getStatus() {
        return status;
    }

    public int getOrderVisualization() {
        return orderVisualization;
    }

    public Category getCategory() {
        return category;
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
