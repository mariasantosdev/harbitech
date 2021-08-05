package br.com.harbitech.school.subcategory;

import br.com.harbitech.school.category.Category;
import br.com.harbitech.school.category.CategoryForm;
import br.com.harbitech.school.category.CategoryRepository;

import javax.validation.constraints.*;

public class SubcategoryForm {

    private Long id;
    @NotBlank(message = "{subcategory.name.required}")
    @Size(max = 70, message = "{subcategory.name.size.max}")
    private String name;
    @NotBlank(message = "{subcategory.codeUrl.required}")
    @Size(max = 70, message = "{subcategory.codeUrl.size.max}")
    @Pattern(regexp = "[-a-z]+", message = "{subcategory.codeUrl.pattern}")
    private String codeUrl;
    private String description;
    private String studyGuide;
    @NotNull
    private SubCategoryStatus status = SubCategoryStatus.INACTIVE;
    @Min(value = -1, message = "{subcategory.orderVisualization.min}")
    private int orderVisualization;
    @NotNull(message = "{subcategory.category.required}")
    private Category category;

    public SubcategoryForm() {
    }

    public SubcategoryForm(Long id, String name, String codeUrl, String description, String studyGuide,
                           SubCategoryStatus status, int orderVisualization, Category category) {
        this.id = id;
        this.name = name;
        this.codeUrl = codeUrl;
        this.description = description;
        this.studyGuide = studyGuide;
        this.status = status;
        this.orderVisualization = orderVisualization;
        this.category = category;
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

    public SubCategoryStatus getStatus() {
        return status;
    }

    public int getOrderVisualization() {
        return orderVisualization;
    }

    public Category getCategory() {
        return category;
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

    public void setStatus(SubCategoryStatus status) {
        this.status = status;
    }

    public void setOrderVisualization(int orderVisualization) {
        this.orderVisualization = orderVisualization;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public static Subcategory convert(SubcategoryForm subcategoryForm) {
        return new Subcategory(subcategoryForm.getName(), subcategoryForm.getCodeUrl(),
                subcategoryForm.getOrderVisualization(), subcategoryForm.getDescription(), subcategoryForm.getStudyGuide(),
                subcategoryForm.status, subcategoryForm.getCategory());
    }

    public static Subcategory convert(SubcategoryForm subcategoryForm, SubcategoryRepository subcategoryRepository) {
        Subcategory subcategory = subcategoryRepository.findById(subcategoryForm.getId()).get();
        subcategory.setId(subcategoryForm.getId());
        subcategory.setCodeUrl(subcategoryForm.getCodeUrl());
        subcategory.setName(subcategoryForm.getName());
        subcategory.setDescription(subcategoryForm.getDescription());
        subcategory.setStudyGuide(subcategoryForm.getStudyGuide());
        subcategory.setStatus(subcategoryForm.getStatus());
        subcategory.setOrderVisualization(subcategoryForm.getOrderVisualization());
        subcategory.setCategory(subcategoryForm.getCategory());
        return subcategory;
    }

    public static SubcategoryForm from(Subcategory subcategory){
        return new SubcategoryForm(subcategory.getId(),subcategory.getName(),subcategory.getCodeUrl(),
                subcategory.getDescription(), subcategory.getStudyGuide(), subcategory.getStatus(),
                subcategory.getOrderVisualization(), subcategory.getCategory());
    }
}
