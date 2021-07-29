package br.com.harbitech.school.category;

import br.com.harbitech.school.subcategory.Subcategory;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CategoryForm {
    private Long id;
    @NotBlank(message = "Por favor insira o nome da categoria")
    @Size(max = 70, message = "Ops! O nome de uma categoria não pode ter mais do que 70 caracteres")
    private final String name;
    @NotBlank(message = "Por favor insira o código da categoria")
    @Size(max = 70, message = "Ops! O código de uma categoria não pode ter mais do que 70 caracteres")
    @Pattern(regexp = "[-a-z]+", message = "O código da url da categoria está incorreto (só aceita letras minúsculas e hífen)")
    private final String codeUrl;
    private final String description;
    private final String studyGuide;
    @NotNull
    private CategoryStatus status = CategoryStatus.INACTIVE;
    @Min(-1)
    private final int orderVisualization;
    @Size(max = 400, message = "Ops! O caminho do ícone não deve ter mais do que 400 caracteres")
    private final String iconPath;
    @Size(max = 7, message = "Ops! Uma cor em hexa decimal não tem mais que 7 caracteres")
    private final String htmlHexColorCode;
    private final List<Subcategory> subCategories = new ArrayList<>();

    public CategoryForm(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.codeUrl = category.getCodeUrl();
        this.description = category.getDescription();
        this.studyGuide = category.getStudyGuide();
        this.orderVisualization = category.getOrderVisualization();
        this.iconPath = category.getIconPath();
        this.htmlHexColorCode = category.getHtmlHexColorCode();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setStatus(CategoryStatus status) {
        this.status = status;
    }

    public static List<CategoryForm> convert(List<Category> categories) {
        return categories.stream().map(CategoryForm::new).collect(Collectors.toList());
    }
}
