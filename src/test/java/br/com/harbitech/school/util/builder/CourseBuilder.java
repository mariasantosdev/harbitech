package br.com.harbitech.school.util.builder;

import br.com.harbitech.school.category.CategoryStatus;
import br.com.harbitech.school.course.Course;
import br.com.harbitech.school.course.CourseVisibility;
import br.com.harbitech.school.subcategory.SubCategoryStatus;
import br.com.harbitech.school.subcategory.Subcategory;

import static br.com.harbitech.school.util.builder.SubcategoryBuilder.androidTestSubcategory;

public class CourseBuilder {
    private String name = "Um curso";
    private String codeUrl = "course-code-url";
    private int completionTimeInHours = 10;
    private CourseVisibility visibility = CourseVisibility.PUBLIC;
    private String targetAudience = "Pessoas desenvolvedoras";
    private String instructor = "Um(a) instrutor(a)";
    private String description = "Um curso excelente";
    private String developedSkills = "Muitas habilidades serão desenvolvidas";
    private Subcategory subCategory;

    private CourseBuilder() {
    }

    private CourseBuilder(String name, String codeUrl, String instructor, Subcategory subCategory) {
        this.name = name;
        this.codeUrl = codeUrl;
        this.instructor = instructor;
        this.subCategory = subCategory;
    }

    public CourseBuilder withCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl;
        return this;
    }

    public CourseBuilder withCompletionTimeInHours(Integer completionTimeInHours) {
        this.completionTimeInHours = completionTimeInHours;
        return this;
    }

    public CourseBuilder withVisibility(CourseVisibility visibility) {
        this.visibility = visibility;
        return this;
    }

    public CourseBuilder withTargetAudience(String targetAudience) {
        this.targetAudience = targetAudience;
        return this;
    }

    public CourseBuilder withInstructor(String instructor) {
        this.instructor = instructor;
        return this;
    }

    public CourseBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public CourseBuilder withDevelopedSkills(String developedSkills) {
        this.developedSkills = developedSkills;
        return this;
    }

    public CourseBuilder withSubcategory(Subcategory subcategory) {
        this.subCategory = subcategory;
        return this;
    }

    public Course create() {
        return new Course(name, codeUrl, completionTimeInHours, visibility, targetAudience, instructor, description,
                developedSkills, subCategory);
    }

    public static CourseBuilder aCourse() {
        return new CourseBuilder();
    }

    public static Course androidCourse(CourseVisibility visibility, Subcategory subcategory) {
        Course androidCourse = new CourseBuilder("Android parte 3: Refinando o projeto",
                "android-refinando-projeto", "Alex Felipe", subcategory)
                .withCompletionTimeInHours(10)
                .withVisibility(visibility)
                .withTargetAudience("Pessoas com foco em java/kotlin/desenvolvimento mobile")
                .withDescription("""
                            Implementar um layout personalizado para um AdapterView
                            Entender e utilizar a entidade Application do Android Framework
                            Interagir com o usuário por meio de dialogs
                            Analisar possíveis melhorias no projeto por meio do inspetor de código
                            Compreender e resolver tópicos apresentado no resultado da inspeção de código
                        """)
                .withDevelopedSkills("Aprenda a refatorar, usando os principios de SOLID nesse curso")
                .create();

        return androidCourse;
    }

    public static Course androidTestsCourse(CourseVisibility visibility, Subcategory subcategory) {
        Course androidTestsCourse = new CourseBuilder("Android parte 1: Testes automatizados e TDD",
                "android-tdd", "Alex Felipe", subcategory)
                .withCompletionTimeInHours(10)
                .withVisibility(visibility)
                .withTargetAudience("Pessoas com foco em java/kotlin/desenvolvimento mobile")
                .withDescription("""
                        Entenda o motivo de para testar Apps automaticamente
                        Aprenda a criar testes automatizados em projetos Android com JUnit
                        Entenda o que são testes de unidades e como e implementá-los
                        Modifique a sua implementação de código sem medo
                        Aprenda o que é TDD e como pode ser utilizado
                        Explore possibilidades de casos uso durante a criação de testes
                        """)
                .withDevelopedSkills("Aprenda boas práticas em testes como TDD")
                .create();
        return androidTestsCourse;
    }

    public static Course androidWithTddCourse(CourseVisibility visibility, SubCategoryStatus subCategoryStatus,
                                              CategoryStatus categoryStatus, String codeUrl) {
        Course androidWithTddCourse = new CourseBuilder("Android parte 1: Testes automatizados e TDD",
                codeUrl, "Alex Felipe", androidTestSubcategory(subCategoryStatus, categoryStatus))
                .withCompletionTimeInHours(14)
                .withVisibility(visibility)
                .withTargetAudience("Pessoas com foco em java/kotlin/desenvolvimento mobile")
                .withDescription("""
                            Implementar um layout personalizado para um AdapterView
                            Entender e utilizar a entidade Application do Android Framework
                            Interagir com o usuário por meio de dialogs
                            Analisar possíveis melhorias no projeto por meio do inspetor de código
                            Compreender e resolver tópicos apresentado no resultado da inspeção de código
                        """)
                .withDevelopedSkills("Aprenda a refatorar, usando os principios de SOLID nesse curso")
                .create();

        return androidWithTddCourse;
    }

    public static Course htmlAndCssCourse(CourseVisibility visibility, Subcategory subcategory) {
        Course htmlAndCss = new CourseBuilder("HTML5 e CSS3 parte 1: A primeira página da Web",
                "html-css", "Pedro Marins", subcategory)
                .withCompletionTimeInHours(13)
                .withVisibility(visibility)
                .withTargetAudience("Pessoas que tem interesse em front end e quer aprender como construir uma página")
                .withDescription("""
                         Aprenda o que é o HTML e o CSS
                        Entenda a estrutura básica de um arquivo HTML
                        """)
                .withDevelopedSkills("Primeiros passos com html e css e conceitos fundamentais")
                .create();

        return htmlAndCss;
    }

}
