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
    private CategoryRepository categoryRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    void should_load_one_category_searching_by_code_url() {
        Category expectedCategory = mobileCategory(CategoryStatus.ACTIVE);

        Optional<Category> possibleCategory = categoryRepository.findByCodeUrl("mobile");

        assertTrue(possibleCategory.isPresent());
        assertEquals(expectedCategory.getCodeUrl(), "mobile");
    }

    @Test
    void should_not_load_one_category_because_doesnt_have_one_category_with_the_code_url_passed() {
        Optional<Category> possibleCategory = categoryRepository.findByCodeUrl("categoryDoesntExist");

        assertTrue(possibleCategory.isEmpty());
    }

    @Test
    void should_load_all_categories_orderly_by_name() {
        mobileCategory(CategoryStatus.ACTIVE);
        dataScienceCategory(CategoryStatus.INACTIVE);

        List<Category> categories = categoryRepository.findAllByOrderByName();

        assertThat(categories)
                .hasSize(2)
                .extracting(Category::getCodeUrl)
                .containsExactly("data-science", "mobile");
    }

    @Test
    void should_not_load_any_categories_because_dont_have() {
        List<Category> categories = categoryRepository.findAllByOrderByName();

        assertTrue(categories.isEmpty());
    }

    @Test
    void should_load_all_categories_searching_by_status() {
        mobileCategory(CategoryStatus.ACTIVE);
        dataScienceCategory(CategoryStatus.INACTIVE);

        List<Category> categories = categoryRepository.findAllByStatus(CategoryStatus.ACTIVE);

        assertThat(categories)
                .hasSize(1)
                .extracting(Category::getCodeUrl)
                .containsExactly("mobile");
    }

    @Test
    void should_not_load_categories_because_dont_have_with_the_status_passed() {
        List<Category> categories = categoryRepository.findAllByStatus(CategoryStatus.ACTIVE);

        assertTrue(categories.isEmpty());
    }

    @Test
    void should_load_all_active_categories_with_public_courses() {
        androidCourse(CourseVisibility.PUBLIC, SubCategoryStatus.ACTIVE, CategoryStatus.ACTIVE);

        List<Category> categories = categoryRepository.findAllActiveCategoriesWithPublicCourses();

        assertThat(categories)
                .hasSize(1)
                .extracting(Category::getCodeUrl)
                .containsExactly("mobile");
    }

    @Test
    void should_not_load_any_categories_because_the_course_is_private() {
        androidCourse(CourseVisibility.PRIVATE,SubCategoryStatus.ACTIVE, CategoryStatus.ACTIVE);

        List<Category> categories = categoryRepository.findAllActiveCategoriesWithPublicCourses();

        assertTrue(categories.isEmpty());
    }

    @Test
    void should_not_load_any_categories_because_the_category_is_inactive() {
        androidCourse(CourseVisibility.PUBLIC,SubCategoryStatus.ACTIVE, CategoryStatus.INACTIVE);

        List<Category> categories = categoryRepository.findAllActiveCategoriesWithPublicCourses();

        assertTrue(categories.isEmpty());
    }

    @Test
    void should_not_load_any_categories_because_the_subCategory_is_inactive() {
        androidCourse(CourseVisibility.PUBLIC,SubCategoryStatus.INACTIVE, CategoryStatus.ACTIVE);

        List<Category> categories = categoryRepository.findAllActiveCategoriesWithPublicCourses();

        assertTrue(categories.isEmpty());
    }

    @Test
    void should_not_load_any_categories_because_the_category_has_not_subcategory() {
       dataScienceCategory(CategoryStatus.ACTIVE);

        List<Category> categories = categoryRepository.findAllActiveCategoriesWithPublicCourses();

        assertTrue(categories.isEmpty());
    }

    @Test
    void should_not_load_any_categories_because_the_category_has_not_course() {
        sql(SubCategoryStatus.ACTIVE, CategoryStatus.ACTIVE);

        List<Category> categories = categoryRepository.findAllActiveCategoriesWithPublicCourses();

        assertTrue(categories.isEmpty());
    }

//    @Test
//    void should_not_load_all_active_categories_with_public_courses_in_order_of_category() {
//        androidCourse(CourseVisibility.PUBLIC, SubCategoryStatus.ACTIVE, CategoryStatus.ACTIVE);
//        htmlAndCss(CourseVisibility.PUBLIC, SubCategoryStatus.ACTIVE, CategoryStatus.ACTIVE);
//
//        List<Category> categories = categoryRepository.findAllActiveCategoriesWithPublicCourses();
//
//        assertThat(categories)
//                .hasSize(2)
//                .extracting(Category::getCodeUrl)
//                .containsExactly("mobile","front-end");
//    }

//    @Test
//    void should_load_active_categories_with_public_courses_in_order_of_subcategory() {
//        androidCourse(CourseVisibility.PUBLIC, SubCategoryStatus.ACTIVE, CategoryStatus.ACTIVE);
//        androidWithTddCourse(CourseVisibility.PUBLIC, SubCategoryStatus.ACTIVE, CategoryStatus.ACTIVE);
//
//        List<Category> categories = categoryRepository.findAllActiveCategoriesWithPublicCourses();
//
//        categories.stream().collect()
//    }

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
        Subcategory sql = new SubcategoryBuilder("SQL", "sql", dataScienceCategory(categoryStatus))
                .withDescription("Saiba instalar e acessar o banco de dados MySQL e realizar consultas importantes")
                .withStudyGuide("Aprendas comandos SQL como: Insert, Select, Update e Delete")
                .withStatus(status)
                .withOrderVisualization(6)
                .create();
        em.persist(sql);

        return sql;
    }

    private Category frontEnd(CategoryStatus status){
        Category frontEnd  = new CategoryBuilder("Front end", "front-end")
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

//    private Subcategory html (SubCategoryStatus status, CategoryStatus categoryStatus) {
//        Subcategory html = subcategory = new SubcategoryBuilder("Html", "html", frontEnd(categoryStatus))
//                .withDescription("Entenda html e css na prática, utilize navegador para inspecionar elementos")
//                .withStudyGuide("Html e css básico")
//                .withStatus(status)
//                .withOrderVisualization(2)
//                .create();
//        em.persist(subcategory);
//
//        return subcategory;
//    }
//
//    private Course htmlAndCss(CourseVisibility visibility, SubCategoryStatus subCategoryStatus,
//                                 CategoryStatus categoryStatus) {
//        this.course = new CourseBuilder("HTML5 e CSS3 parte 1: A primeira página da Web",
//                "html-css", "Pedro Marins", html(subCategoryStatus, categoryStatus))
//                .withCompletionTimeInHours(13)
//                .withVisibility(visibility)
//                .withTargetAudience("Pessoas que tem interesse em front end e quer aprender como construir uma página")
//                .withDescription("""
//                         Aprenda o que é o HTML e o CSS
//                        Entenda a estrutura básica de um arquivo HTML
//                        """)
//                .withDevelopedSkills("Primeiros passos com html e css e conceitos fundamentais")
//                .create();
//        em.persist(course);
//
//        return course;
//    }

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
        Subcategory androidSubcategory = new SubcategoryBuilder("Android", "android", mobileCategory (categoryStatus))
                .withDescription("Crie aplicativos móveis para as principais plataformas, smartphones e tablets. " +
                        "Aprenda frameworks multiplataforma como Flutter e React Native e saiba como criar apps" +
                        " nativas para Android e iOS. Desenvolva também jogos mobile com Unity. Saiba como ")
                .withStudyGuide("Android, Testes automatizados e arquitetura android")
                .withStatus(status)
                .withOrderVisualization(1)
                .create();
        em.persist(androidSubcategory);

        return androidSubcategory;
    }
    private Course androidCourse(CourseVisibility visibility, SubCategoryStatus subCategoryStatus,
                                 CategoryStatus categoryStatus) {
        Course androidCourse = new CourseBuilder("Android parte 3: Refinando o projeto",
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
        em.persist(androidCourse);

        return androidCourse;
    }

    private Course androidWithTddCourse(CourseVisibility visibility, SubCategoryStatus subCategoryStatus,
                                        CategoryStatus categoryStatus) {
        Course androidWithTddCourse = new CourseBuilder("Android parte 1: Testes automatizados e TDD",
                "android-tdd", "Alex Felipe", androidSubcategory (subCategoryStatus, categoryStatus))
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
        em.persist(androidWithTddCourse);

        return androidWithTddCourse;
    }
}
