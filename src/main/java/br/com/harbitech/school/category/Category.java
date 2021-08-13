package br.com.harbitech.school.category;

import br.com.harbitech.school.subcategory.Subcategory;
import br.com.harbitech.school.subcategory.SubcategoryResponse;
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

    public Category(Long id, String name, String codeUrl, CategoryStatus status, int orderVisualization,
                        String studyGuide, String iconPath,String htmlHexColorCode,String description) {
        this(name,codeUrl,status,orderVisualization,iconPath,htmlHexColorCode, description, studyGuide);
        this.id = id;
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

    public String getStatusDescription(){
        return this.status.getDescription();
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

