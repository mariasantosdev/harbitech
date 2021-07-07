package br.com.harbitech.school.util.builder;

import br.com.harbitech.school.category.Category;
import br.com.harbitech.school.subcategory.SubCategoryStatus;
import br.com.harbitech.school.subcategory.Subcategory;

//TODO CONSTRUTOR COM OS ATRIBUTOS OBRIGATORIOS DE SUBCATEGORY
public class SubcategoryBuilder {
    private String name;
    private String codeUrl;
    private String description;
    private String studyGuide;
    private SubCategoryStatus status;
    private int orderVisualization;
    private Category category;

    public SubcategoryBuilder withName(String name){
        this.name = name;
        return this;
    }
    public SubcategoryBuilder withCodeUrl(String codeUrl){
        this.codeUrl = codeUrl;
        return this;
    }

    public  SubcategoryBuilder withDescription(String description){
        this.description = description;
        return this;
    }

    public  SubcategoryBuilder withStudyGuide(String studyGuide){
        this.studyGuide = studyGuide;
        return this;
    }

    public  SubcategoryBuilder withStatus(SubCategoryStatus status){
        this.status = status;
        return this;
    }

    public  SubcategoryBuilder withOrderVisualization(Integer orderVisualization){
        this.orderVisualization = orderVisualization;
        return this;
    }

    public SubcategoryBuilder withCategory(Category category){
        this.category = category;
        return this;
    }

    public Subcategory create(){
        return new Subcategory(name,codeUrl,orderVisualization,description,studyGuide,status,category);
    }

}
