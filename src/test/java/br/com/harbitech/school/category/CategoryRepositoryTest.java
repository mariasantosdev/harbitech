package br.com.harbitech.school.category;

import br.com.harbitech.school.course.Course;
import br.com.harbitech.school.course.CourseVisibility;
import br.com.harbitech.school.subcategory.SubCategoryStatus;
import br.com.harbitech.school.subcategory.Subcategory;
import br.com.harbitech.school.util.builder.CategoryBuilder;
import br.com.harbitech.school.util.builder.CourseBuilder;
import br.com.harbitech.school.util.builder.SubcategoryBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class CategoryRepositoryTest {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    private TestEntityManager em;

    private Subcategory subcategory;

    private Course course;

    @Test
    void shouldLoadOneCategorySearchingByCodeUrl() {
        Category expectedCategory = mobileCategory(CategoryStatus.ACTIVE);

        Optional<Category> possibleCategory = categoryRepository.findByCodeUrl("mobile");

        assertTrue(possibleCategory.isPresent());
        assertEquals(expectedCategory.getCodeUrl(), possibleCategory.get().getCodeUrl());
    }

    @Test
    void shouldNotLoadOneCategoryBecauseDoesntHaveOneCategoryWithTheCodeUrlPassed() {
        Optional<Category> possibleCategory = categoryRepository.findByCodeUrl("programacao");

        assertTrue(possibleCategory.isEmpty());
    }

    @Test
    void shouldLoadAllCategoriesOrderlyByName() {
        mobileCategory(CategoryStatus.ACTIVE);
        dataScienceCategory(CategoryStatus.INACTIVE);

        List<Category> categories = categoryRepository.findAllByOrderByName();

        String codeUrlFromFirstCategory = categories.get(0).getCodeUrl();

        assertThat(categories)
                .hasSize(2)
                .allMatch(category -> codeUrlFromFirstCategory.equals("data-science"));
    }

    @Test
    void shouldNotLoadAnyCategoriesBecauseDontHave() {
        List<Category> categories = categoryRepository.findAllByOrderByName();

        assertTrue(categories.isEmpty());
    }

    @Test
    void shouldLoadAllCategoriesSearchingByStatus() {
        mobileCategory(CategoryStatus.ACTIVE);
        dataScienceCategory(CategoryStatus.INACTIVE);

        List<Category> categories = categoryRepository.findAllByStatus(CategoryStatus.ACTIVE);

        assertThat(categories)
                .hasSize(1)
                .extracting(Category::getCodeUrl)
                .containsExactly("mobile");
    }

    @Test
    void shouldNotLoadCategoriesBecauseDontHaveWithTheStatusPassed() {
        List<Category> categories = categoryRepository.findAllByStatus(CategoryStatus.ACTIVE);

        assertTrue(categories.isEmpty());
    }

    @Test
    void shouldLoadAllActiveCategoriesWithPublicCourses() {
        androidCourse(CourseVisibility.PUBLIC, SubCategoryStatus.ACTIVE, CategoryStatus.ACTIVE);

        List<Category> categories = categoryRepository.findAllActiveCategoriesWithPublicCourses();

        assertThat(categories)
                .hasSize(1)
                .extracting(Category::getCodeUrl)
                .containsExactly("mobile");
    }

    @Test
    void shouldNotLoadAnyCategoriesBecauseTheCourseIsPrivate() {
        androidCourse(CourseVisibility.PRIVATE,SubCategoryStatus.ACTIVE, CategoryStatus.ACTIVE);

        List<Category> categories = categoryRepository.findAllActiveCategoriesWithPublicCourses();

        assertTrue(categories.isEmpty());
    }

    @Test
    void shouldNotLoadAnyCategoriesBecauseTheCategoryIsInactive() {
        androidCourse(CourseVisibility.PUBLIC,SubCategoryStatus.ACTIVE, CategoryStatus.INACTIVE);

        List<Category> categories = categoryRepository.findAllActiveCategoriesWithPublicCourses();

        assertTrue(categories.isEmpty());
    }

    @Test
    void shouldNotLoadAnyCategoriesBecauseTheSubCategoryIsInactive() {
        androidCourse(CourseVisibility.PUBLIC,SubCategoryStatus.INACTIVE, CategoryStatus.ACTIVE);

        List<Category> categories = categoryRepository.findAllActiveCategoriesWithPublicCourses();

        assertTrue(categories.isEmpty());
    }

    @Test
    void shouldNotLoadAnyCategoriesBecauseTheCategoryHasNotSubcategory() {
       dataScienceCategory(CategoryStatus.ACTIVE);

        List<Category> categories = categoryRepository.findAllActiveCategoriesWithPublicCourses();

        assertTrue(categories.isEmpty());
    }

    @Test
    void shouldNotLoadAnyCategoriesBecauseTheCategoryHasNotCourse() {
        sql(SubCategoryStatus.ACTIVE, CategoryStatus.ACTIVE);

        List<Category> categories = categoryRepository.findAllActiveCategoriesWithPublicCourses();

        assertTrue(categories.isEmpty());
    }

    @Test
    void shouldNotLoadALlActiveCategoriesWithPublicCoursesInOrderOfCategory() {
        androidCourse(CourseVisibility.PUBLIC, SubCategoryStatus.ACTIVE, CategoryStatus.ACTIVE);
        htmlAndCss(CourseVisibility.PUBLIC, SubCategoryStatus.ACTIVE, CategoryStatus.ACTIVE);

        List<Category> categories = categoryRepository.findAllActiveCategoriesWithPublicCourses();

        String codeUrlFromFirstCategory = categories.get(0).getCodeUrl();

        assertThat(categories)
                .hasSize(2)
                .allMatch(category -> codeUrlFromFirstCategory.equals("mobile"));
    }

    @Test
    void shouldLoadActiveCategoriesWithPublicCoursesInOrderOfSubcategory() {
        androidCourse(CourseVisibility.PUBLIC, SubCategoryStatus.ACTIVE, CategoryStatus.ACTIVE);
        htmlAndCss(CourseVisibility.PUBLIC, SubCategoryStatus.ACTIVE, CategoryStatus.ACTIVE);

        List<Category> categories = categoryRepository.findAllActiveCategoriesWithPublicCourses();

        List<Subcategory> subcategories = categories.get(0).getSubCategories();

        assertThat(subcategories)
                .allMatch(category -> subcategories.contains("android"));
    }

    private Category dataScienceCategory(CategoryStatus status) {
        Category dataScience = new CategoryBuilder("Data Science", "data-science")
                .withStatus(status)
                .withDescription("Com o avanço da tecnologia, está cada vez maior a quantidade de dados disponíveis" +
                        " para análises avançadas. Desta forma, a área de Ciência de Dados emergiu auxiliando empresas" +
                        " e profissionais em suas tomadas de decisões: por isso, cresce a demanda por especialistas na " +
                        "área e o desenvolvimento de uma cultura de dados, empoderando profissionais de todas as áreas " +
                        "a lidar com dados e gerar insights que vão realmente impactar suas áreas de atuação.")
                .withOrderVisualization(1)
                .withHtmlHexColorCode("#008000")
                .withIconPath("https://www.alura.com.br/cursos-online-data-science/data-science")
                .create();
        em.persist(dataScience);

        return dataScience;
    }

    private Subcategory sql(SubCategoryStatus status, CategoryStatus categoryStatus){
        this.subcategory = new SubcategoryBuilder("SQL", "sql", dataScienceCategory(categoryStatus))
                .withDescription("Saiba instalar e acessar o banco de dados MySQL e realizar consultas importantes")
                .withStudyGuide("Aprendas comandos SQL como: Insert, Select, Update e Delete")
                .withStatus(status)
                .withOrderVisualization(6)
                .create();
        em.persist(subcategory);

        return subcategory;
    }

    private Category frontEnd(CategoryStatus status){
        Category frontEnd  = new CategoryBuilder("Front end", "data-end")
                .withStatus(status)
                .withDescription("Desenvolva sites e webapps com HTML, CSS e JavaScript. Aprenda as boas práticas e as " +
                        "últimas versões do JavaScript. Estude ferramentas e frameworks do mercado como React, Angular," +
                        " Webpack, jQuery e mais. Saiba como começar com Front-end.")
                .withOrderVisualization(4)
                .withHtmlHexColorCode("#fh2893")
                .withIconPath("https://www.alura.com.br/cursos-online-front-end")
                .create();
        em.persist(frontEnd);

        return frontEnd;
    }

    private Subcategory html (SubCategoryStatus status, CategoryStatus categoryStatus) {
        this.subcategory = new SubcategoryBuilder("Html", "html", frontEnd(categoryStatus))
                .withDescription("Entenda html e css na prática, utilize navegador para inspecionar elementos")
                .withStudyGuide("Html e css básico")
                .withStatus(status)
                .withOrderVisualization(2)
                .create();
        em.persist(subcategory);

        return subcategory;
    }

    private Course htmlAndCss(CourseVisibility visibility, SubCategoryStatus subCategoryStatus,
                                 CategoryStatus categoryStatus) {
        this.course = new CourseBuilder("HTML5 e CSS3 parte 1: A primeira página da Web",
                "html-css", "Pedro Marins", html(subCategoryStatus, categoryStatus))
                .withCompletionTimeInHours(13)
                .withVisibility(visibility)
                .withTargetAudience("Pessoas que tem interesse em front end e quer aprender como construir uma página")
                .withDescription("""
                         Aprenda o que é o HTML e o CSS
                        Entenda a estrutura básica de um arquivo HTML
                        """)
                .withDevelopedSkills("Primeiros passos com html e css e conceitos fundamentais")
                .create();
        em.persist(course);

        return course;
    }

    private Category mobileCategory(CategoryStatus status) {
        Category mobile = new CategoryBuilder("Mobile", "mobile")
                .withDescription("Crie aplicativos móveis para as principais plataformas, smartphones e tablets. " +
                        "Aprenda frameworks multiplataforma como Flutter e React Native e saiba como criar apps" +
                        " nativas para Android e iOS. Desenvolva também jogos mobile com Unity. Saiba como ")
                .withStudyGuide("Android, Testes automatizados, arquitetura android e flutter")
                .withStatus(status)
                .withOrderVisualization(3)
                .withIconPath("https://www.google.com/search?q=forma%C3%A7%C3%A3o+mobile+alura+icon&client=ubuntu&hs=" +
                        "PUH&channel=fs&sxsrf=ALeKk01O4vjVbL33VupNCbN27rcLhDfgmQ:1625529442736&source=lnms&tbm=i" +
                        "sch&sa=X&ved=2ahUKEwjW-IWIkc3xAhWLK7kGHVP_BVUQ_AUoAXoECAEQAw&biw=1445&bih=733#imgrc=xIoKd" +
                        "XUJ9UtPvM")
                .withHtmlHexColorCode("#FFFF00")
                .create();
        em.persist(mobile);

        return mobile;
    }
    private Subcategory androidSubcategory (SubCategoryStatus status, CategoryStatus categoryStatus) {
        this.subcategory = new SubcategoryBuilder("Android", "android", mobileCategory (categoryStatus))
                .withDescription("Crie aplicativos móveis para as principais plataformas, smartphones e tablets. " +
                        "Aprenda frameworks multiplataforma como Flutter e React Native e saiba como criar apps" +
                        " nativas para Android e iOS. Desenvolva também jogos mobile com Unity. Saiba como ")
                .withStudyGuide("Android, Testes automatizados e arquitetura android")
                .withStatus(status)
                .withOrderVisualization(1)
                .create();
        em.persist(subcategory);

        return subcategory;
    }
    private Course androidCourse(CourseVisibility visibility, SubCategoryStatus subCategoryStatus,
                                 CategoryStatus categoryStatus) {
        this.course = new CourseBuilder("Android parte 3: Refinando o projeto",
                "android-refinando-projeto", "Alex Felipe", androidSubcategory (subCategoryStatus, categoryStatus))
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
        em.persist(course);

        return course;
    }
}
