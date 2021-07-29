package br.com.harbitech.school.category;

import br.com.harbitech.school.subcategory.Subcategory;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryForm {
    private Long id;
    @NotBlank(message = "Por favor insira o nome da categoria")
    @Size(max = 70, message = "Ops! O nome de uma categoria não pode ter mais do que 70 caracteres")
    private String name;
    @NotBlank(message = "Por favor insira o código da categoria")
    @Size(max = 70, message = "Ops! O código de uma categoria não pode ter mais do que 70 caracteres")
    @Pattern(regexp = "[-a-z]+", message = "O código da url da categoria está incorreto (só aceita letras minúsculas e hífen)")
    private String codeUrl;
    private String description;
    private String studyGuide;
    @NotNull
    private CategoryStatus status = CategoryStatus.INACTIVE;
    @Min(-1)
    private int orderVisualization;
    @Size(max = 400, message = "Ops! O caminho do ícone não deve ter mais do que 400 caracteres")
    private String iconPath;
    @Size(max = 7, message = "Ops! Uma cor em hexa decimal não tem mais que 7 caracteres")
    private String htmlHexColorCode;
    private List<Subcategory> subCategories = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCodeUrl() {
        return codeUrl;
    }

    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStudyGuide() {
        return studyGuide;
    }

    public void setStudyGuide(String studyGuide) {
        this.studyGuide = studyGuide;
    }

    public CategoryStatus getStatus() {
        return status;
    }

    public void setStatus(CategoryStatus status) {
        this.status = status;
    }

    public int getOrderVisualization() {
        return orderVisualization;
    }

    public void setOrderVisualization(int orderVisualization) {
        this.orderVisualization = orderVisualization;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public String getHtmlHexColorCode() {
        return htmlHexColorCode;
    }

    public void setHtmlHexColorCode(String htmlHexColorCode) {
        this.htmlHexColorCode = htmlHexColorCode;
    }

    public List<Subcategory> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<Subcategory> subCategories) {
        this.subCategories = subCategories;
    }


}
