package br.com.harbitech.school.category;

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
    private List<SubCategory> subCategories = new ArrayList<>();

    public Category(String name, String codeUrl) {
        validateNonBlankText(name, "O nome da categoria não pode estar em branco.");
        validateNonBlankText(codeUrl, "O código da URL da categoria não pode estar em branco.");
        validateUrl(codeUrl, "O código da url da categoria está incorreto (só aceita letras minúsculas e hífen): " + codeUrl);

        this.name = name;
        this.codeUrl = codeUrl;
        this.status = CategoryStatus.INACTIVE;
        this.orderVisualization = -1;
    }

    public Category(String name, String codeUrl,String description, CategoryStatus status,
                    int orderVisualization, String iconPath, String htmlHexColorCode) {
        this(name,codeUrl);
        this.description = description;
        this.status = status;
        this.orderVisualization = orderVisualization;
        this.iconPath = iconPath;
        this.htmlHexColorCode = htmlHexColorCode;
    }

    public List<SubCategory> getSubCategories() {
        return subCategories;
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

    public CategoryStatus getStatus() {
        return status;
    }

    public String getIconPath() {
        return iconPath;
    }

    public String getHtmlHexColorCode() {
        return htmlHexColorCode;
    }

    public int totalCourses() {
        return this.subCategories.stream().mapToInt(SubCategory::totalCourses).sum();
    }

    public int totalTimeInHoursOfCourse() {
       return this.subCategories.stream().mapToInt(SubCategory::totalTimeInHoursOfCourse).sum();
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

    public void addSubcategory(SubCategory subCategory) {
        this.subCategories.add(subCategory);
    }

}

