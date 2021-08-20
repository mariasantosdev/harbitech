package br.com.harbitech.school.category;

import br.com.harbitech.school.subcategory.Subcategory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static br.com.harbitech.school.validation.ValidationUtil.validateUrl;

@Entity
@NamedQuery(name = "Category.allWithStatus", query = "SELECT c FROM Category c WHERE c.status = :status ORDER BY " +
        "c.orderVisualization")
@Getter
@NoArgsConstructor(onConstructor = @__(@Deprecated))
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

    public Category(String name, String codeUrl) {
        Assert.hasText(name, "{category.name.required}");
        Assert.hasText(codeUrl, "{category.codeUrl.required}");
        validateUrl(codeUrl, "{category.codeUrl.pattern}" + codeUrl);

        this.name = name;
        this.codeUrl = codeUrl;
        this.status = CategoryStatus.INACTIVE;
        this.orderVisualization = -1;
    }

    public Category(String name, String codeUrl, CategoryStatus status, int orderVisualization,
                    String iconPath, String htmlHexColorCode,String description, String studyGuide) {
        this(name, codeUrl);
        this.description = description;
        this.status = status;
        this.orderVisualization = orderVisualization;
        this.iconPath = iconPath;
        this.htmlHexColorCode = htmlHexColorCode;
        this.studyGuide = studyGuide;
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

    public void setSubCategories(List<Subcategory> subCategories) {
        this.subCategories = subCategories;
    }

    public void addSubcategory(Subcategory subCategory) {
        this.subCategories.add(subCategory);
    }

    public String getStatusDescription(){
        return this.status.getDescription();
    }

    public void  update(CategoryFormUpdate categoryFormUpdate) {
        this.name = categoryFormUpdate.getName();
        this.codeUrl = categoryFormUpdate.getCodeUrl();
        this.description = categoryFormUpdate.getDescription();
        this.status = categoryFormUpdate.getStatus();
        this.orderVisualization = categoryFormUpdate.getOrderVisualization();
        this.iconPath = categoryFormUpdate.getIconPath();
        this.htmlHexColorCode = categoryFormUpdate.getHtmlHexColorCode();
        this.studyGuide = categoryFormUpdate.getStudyGuide();
    }
}

