package br.com.harbitech.school.util.builder;

import br.com.harbitech.school.category.Category;
import br.com.harbitech.school.category.CategoryStatus;
import br.com.harbitech.school.subcategory.SubCategoryStatus;
import br.com.harbitech.school.subcategory.Subcategory;

import static br.com.harbitech.school.util.builder.CategoryBuilder.mobileCategory;

public class SubcategoryBuilder {

    private String name;
    private String codeUrl;
    private String description;
    private String studyGuide;
    private SubCategoryStatus status;
    private int orderVisualization;
    private Category category;

    public SubcategoryBuilder(String name, String codeUrl, Category category) {
        this.name = name;
        this.codeUrl = codeUrl;
        this.category = category;
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

    public Subcategory create(){
        return new Subcategory(name,codeUrl,orderVisualization,description,studyGuide,status,category);
    }

    public static Subcategory sqlSubcategory(SubCategoryStatus status, Category category){
        Subcategory sql = new SubcategoryBuilder("SQL", "sql", category)
                .withDescription("Saiba instalar e acessar o banco de dados MySQL e realizar consultas importantes")
                .withStudyGuide("Aprendas comandos SQL como: Insert, Select, Update e Delete")
                .withStatus(status)
                .withOrderVisualization(6)
                .create();

        return sql;
    }

    public static Subcategory htmlSubcategory(SubCategoryStatus status, Category category) {
        Subcategory html = new SubcategoryBuilder("Html", "html", category)
                .withDescription("Entenda html e css na prática, utilize navegador para inspecionar elementos")
                .withStudyGuide("Html e css básico")
                .withStatus(status)
                .withOrderVisualization(2)
                .create();

        return html;
    }

    public static Subcategory androidSubcategory (SubCategoryStatus status, Category category) {
        Subcategory androidSubcategory = new SubcategoryBuilder("Android", "android", category)
                .withDescription("Crie aplicativos móveis para as principais plataformas, smartphones e tablets. " +
                        "Aprenda frameworks multiplataforma como Flutter e React Native e saiba como criar apps" +
                        " nativas para Android e iOS. Desenvolva também jogos mobile com Unity. Saiba como ")
                .withStudyGuide("Android, Testes automatizados e arquitetura android")
                .withStatus(status)
                .withOrderVisualization(1)
                .create();

        return androidSubcategory;
    }

    public static Subcategory androidTestSubcategory(SubCategoryStatus status, CategoryStatus categoryStatus) {
        Subcategory androidTestSubcategory = new SubcategoryBuilder("Android", "android", mobileCategory
                (categoryStatus))
                .withDescription("Crie aplicativos móveis para as principais plataformas, smartphones e tablets. " +
                        "Aprenda frameworks multiplataforma como Flutter e React Native e saiba como criar apps" +
                        " nativas para Android e iOS. Desenvolva também jogos mobile com Unity. Saiba como ")
                .withStudyGuide("Android, Testes automatizados e arquitetura android")
                .withStatus(status)
                .withOrderVisualization(2)
                .create();

        return androidTestSubcategory;
    }

    public static Subcategory flutterSubcategory(SubCategoryStatus status, Category category) {
        Subcategory flutter = new SubcategoryBuilder("Flutter","flutter",category)
                .withDescription("Aprenda a utilizar o Flutter, programar em Dart e criar seu primeiro projeto\n" +
                        "Entenda o que é Widget e como funciona a árvore de Widgets\n" +
                        "Crie layouts com Widgets do Material Design")
                .withStatus(status)
                .withOrderVisualization(5)
                .create();
        return flutter;
    }

}
