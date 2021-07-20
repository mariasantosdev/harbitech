package br.com.harbitech.school.category;

import br.com.harbitech.school.subcategory.Subcategory;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQuery(name = "Category.allWithStatus", query = "SELECT c FROM Category c WHERE c.status = :status ORDER BY " +
        "c.orderVisualization")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Por favor insira o nome da categoria")
    @NotNull(message = "Por favor insira o nome da categoria")
    private String name;
    @NotBlank(message = "Por favor insira o código da categoria")
    @NotNull(message = "Por favor insira o código da categoria")
    @Pattern(regexp = "[-a-z]+", message = "O código da url da categoria está incorreto (só aceita letras minúsculas e hífen)")
    @Column(name = "code_url")
    private String codeUrl;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(name = "study_guide", columnDefinition = "TEXT")
    private String studyGuide;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM")
    private CategoryStatus status = CategoryStatus.INACTIVE;
    @Column(name = "order_visualization")
    private int orderVisualization;
    @Column(name = "icon_path")
    private String iconPath;
    @Column(name = "html_hex_color_code")
    private String htmlHexColorCode;
    @OneToMany(mappedBy = "category")
    private List<Subcategory> subCategories = new ArrayList<>();

    public Category(){}

    public Category(String name, String codeUrl) {
        this.name = name;
        this.codeUrl = codeUrl;
        this.status = CategoryStatus.INACTIVE;
        this.orderVisualization = -1;
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

    public void setSubCategories(List<Subcategory> subCategories) {
        this.subCategories = subCategories;
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

    public void setStatus(CategoryStatus status) {
        this.status = status;
    }

    public void setStudyGuide(String studyGuide) {
        this.studyGuide = studyGuide;
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

