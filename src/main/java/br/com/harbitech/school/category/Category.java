package br.com.harbitech.school.category;

import br.com.harbitech.school.subcategory.Subcategory;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

import static br.com.harbitech.school.validation.ValidationUtil.validateUrl;

@Entity
@NamedQuery(name = "Category.allWithStatus", query = "SELECT c FROM Category c WHERE c.status = :status ORDER BY " +
        "c.orderVisualization")
public class Category {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @NotBlank(message = "{category.name.required}")
        @Size(max = 70, message = "{category.name.size.max}")
        private String name;
        @NotBlank(message = "{category.codeUrl.required}")
        @Size(max = 70, message = "{category.codeUrl.size.max}")
        @Pattern(regexp = "[-a-z]+", message = "{category.codeUrl.pattern}")
        private String codeUrl;
        @Column(columnDefinition = "TEXT")
        private String description;
        @Column(columnDefinition = "TEXT")
        private String studyGuide;
        @Enumerated(EnumType.STRING)
        @Column(columnDefinition = "ENUM")
        private CategoryStatus status = CategoryStatus.INACTIVE;
        @Min(-1)
        private int orderVisualization;
        @Size(max = 400, message = "{category.iconPath.size.max}")
        private String iconPath;
        @Size(max = 7, message = "{category.htmlHexColorCode.size.max}")
        private String htmlHexColorCode;
        @OneToMany(mappedBy = "category")
        private List<Subcategory> subCategories = new ArrayList<>();

    @Deprecated
    public Category(){}

    public Category(String name, String codeUrl) {
        Assert.hasText(name, "{category.name.required}");
        Assert.hasText(codeUrl, "{category.codeUrl.required}");
        validateUrl(codeUrl, "{category.codeUrl.pattern}" + codeUrl);

        this.name = name;
        this.codeUrl = codeUrl;
        this.status = CategoryStatus.INACTIVE;
        this.orderVisualization = -1;
    }

    public Category(String name, String codeUrl, String description, CategoryStatus status, int orderVisualization,
                    String iconPath, String htmlHexColorCode) {
        this(name, codeUrl);
        this.description = description;
        this.status = status;
        this.orderVisualization = orderVisualization;
        this.iconPath = iconPath;
        this.htmlHexColorCode = htmlHexColorCode;
    }

    public Long getId() {
        return id;
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

    public void setId(Long id) {
        this.id = id;
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

    public void setStudyGuide(String studyGuide) {
        this.studyGuide = studyGuide;
    }

    public void setStatus(CategoryStatus status) {
        this.status = status;
    }

    public void setOrderVisualization(int orderVisualization) {
        this.orderVisualization = orderVisualization;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public void setHtmlHexColorCode(String htmlHexColorCode) {
        this.htmlHexColorCode = htmlHexColorCode;
    }

    public void setSubCategories(List<Subcategory> subCategories) {
        this.subCategories = subCategories;
    }

    public String getStatusDescription(){
        return this.status.getDescription();
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

