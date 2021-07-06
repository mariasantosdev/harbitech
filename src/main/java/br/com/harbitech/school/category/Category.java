package br.com.harbitech.school.category;

import br.com.harbitech.school.subcategory.Subcategory;

import javax.persistence.*;
import java.util.*;

import static br.com.harbitech.school.validation.ValidationUtil.validateNonBlankText;
import static br.com.harbitech.school.validation.ValidationUtil.validateUrl;

@Entity
@NamedQuery(name = "Category.allActive", query = "SELECT c FROM Category c WHERE c.status = :status")
public class Category {

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
    private CategoryStatus status;
    @Column(name = "order_visualization")
    private int orderVisualization;
    @Column(name = "icon_path")
    private String iconPath;
    @Column(name = "html_hex_color_code")
    private String htmlHexColorCode;
    @OneToMany(mappedBy = "category")
    private List<Subcategory> subCategories = new ArrayList<>();

    @Deprecated
    public Category(){

    }

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

    public String getStudyGuide() {
        return studyGuide;
    }

    public int getOrderVisualization() {
        return orderVisualization;
    }

    public List<Subcategory> getSubCategories() {
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
        return this.subCategories.stream().mapToInt(Subcategory::totalCourses).sum();
    }

    public int totalTimeInHoursOfCourse() {
       return this.subCategories.stream().mapToInt(Subcategory::totalTimeInHoursOfCourse).sum();
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

    public void addSubcategory(Subcategory subCategory) {
        this.subCategories.add(subCategory);
    }

}

