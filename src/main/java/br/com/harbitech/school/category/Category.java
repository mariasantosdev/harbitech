package br.com.harbitech.school.category;

import br.com.harbitech.school.subcategory.Subcategory;
import org.springframework.util.Assert;

import javax.persistence.*;
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
        private String name;
        private String codeUrl;
        @Column(columnDefinition = "TEXT")
        private String description;
        @Column(columnDefinition = "TEXT")
        private String studyGuide;
        @Enumerated(EnumType.STRING)
        @Column(columnDefinition = "ENUM")
        private CategoryStatus status = CategoryStatus.INACTIVE;
        private int orderVisualization;
        private String iconPath;
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

    public Category(Long id, String name, String codeUrl, String description, String studyGuide, CategoryStatus status,
                    int orderVisualization, String iconPath, String htmlHexColorCode) {
        this(name,codeUrl,description,status,orderVisualization,iconPath,htmlHexColorCode);
        this.id = id;
        this.studyGuide = studyGuide;
    }

    public Long getId() {
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

    public String getStudyGuide() {
        return studyGuide;
    }

    public CategoryStatus getStatus() {
        return status;
    }

    public int getOrderVisualization() {
        return orderVisualization;
    }

    public String getIconPath() {
        return iconPath;
    }

    public String getHtmlHexColorCode() {
        return htmlHexColorCode;
    }

    public List<Subcategory> getSubCategories() {
        return subCategories;
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

