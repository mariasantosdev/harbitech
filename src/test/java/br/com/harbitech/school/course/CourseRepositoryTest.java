package br.com.harbitech.school.course;

import br.com.harbitech.school.category.Category;
import br.com.harbitech.school.category.CategoryStatus;
import br.com.harbitech.school.subcategory.SubCategoryStatus;
import br.com.harbitech.school.subcategory.Subcategory;
import br.com.harbitech.school.util.builder.CategoryBuilder;
import br.com.harbitech.school.util.builder.CourseBuilder;
import br.com.harbitech.school.util.builder.SubcategoryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.*;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TestEntityManager em;

    private Category category;

    private Subcategory subcategory;

    @BeforeEach
    public void setUp() {
        this.category = aCategory();
        this.subcategory = aSubcategory();
    }

    private Category aCategory() {
        Category category = new CategoryBuilder("Mobile", "mobile")
                .withDescription("Crie aplicativos móveis para as principais plataformas, smartphones e tablets. " +
                        "Aprenda frameworks multiplataforma como Flutter e React Native e saiba como criar apps" +
                        " nativas para Android e iOS. Desenvolva também jogos mobile com Unity. Saiba como ")
                .withStudyGuide("Android, Testes automatizados, arquitetura android e flutter")
                .withStatus(CategoryStatus.ACTIVE)
                .withOrderVisualization(1)
                .withIconPath("https://www.google.com/search?q=forma%C3%A7%C3%A3o+mobile+alura+icon&client=ubuntu&hs=" +
                        "PUH&channel=fs&sxsrf=ALeKk01O4vjVbL33VupNCbN27rcLhDfgmQ:1625529442736&source=lnms&tbm=i" +
                        "sch&sa=X&ved=2ahUKEwjW-IWIkc3xAhWLK7kGHVP_BVUQ_AUoAXoECAEQAw&biw=1445&bih=733#imgrc=xIoKd" +
                        "XUJ9UtPvM")
                .withHtmlHexColorCode("#FFFF00")
                .create();
        em.persist(category);
        return category;
    }

    private Subcategory aSubcategory(){
        Subcategory subcategory = new SubcategoryBuilder("Android","android",category)
                .withDescription("Crie aplicativos móveis para as principais plataformas, smartphones e tablets. " +
                        "Aprenda frameworks multiplataforma como Flutter e React Native e saiba como criar apps" +
                        " nativas para Android e iOS. Desenvolva também jogos mobile com Unity. Saiba como ")
                .withStudyGuide("Android, Testes automatizados e arquitetura android")
                .withStatus(SubCategoryStatus.ACTIVE)
                .withOrderVisualization(1)
                .create();
        em.persist(subcategory);
        return subcategory;
    }

    @Test
    void shouldLoadOneCourseSearchingByCodeUrl() {
        Course expectedCourse = androidCourse(CourseVisibility.PUBLIC, subcategory);

        Optional<Course> possibleCourse = courseRepository.findByCodeUrl("android-refinando-projeto");

        assertTrue(possibleCourse.isPresent());
        assertEquals(expectedCourse.getCodeUrl(), possibleCourse.get().getCodeUrl());
    }

    @Test
    void shouldNotLoadOneCourseBecauseDoesntHaveOneCourseWithTheCodeUrlPassed() {
        Optional<Course> possibleCourse = courseRepository.findByCodeUrl("java-poo");

        assertTrue(possibleCourse.isEmpty());
    }

    @Test
    void shouldLoadTheInstructorWithMoreCourse() {
        androidCourse(CourseVisibility.PUBLIC, subcategory);
        androidTestsCourse(CourseVisibility.PUBLIC, subcategory);
        htmlAndCss(CourseVisibility.PUBLIC, subcategory);

        List<InstructorByCourseProjection> instructors = courseRepository.findInstructorWithGreaterNumberOfCourses();

        assertThat(instructors)
                .hasSize(1)
                .extracting(InstructorByCourseProjection::getInstructor)
                .containsExactly("Alex Felipe");
    }

    @Test
    void shouldNotLoadTheInstructorWithMoreCourse() {

        List<InstructorByCourseProjection> instructors = courseRepository.findInstructorWithGreaterNumberOfCourses();

        assertTrue(instructors.isEmpty());
    }

    @Test
    void shouldLoadCoursesByCategories() {
        dataScienceCategory(CategoryStatus.ACTIVE);
        frontEnd(CategoryStatus.ACTIVE);

        List<CategoriesByCourseProjection> categories = courseRepository.findAllCategories();

        String codeUrlFromFirstCategory = categories.get(0).getName();

        assertThat(categories)
                .hasSize(3)
                .allMatch(category -> codeUrlFromFirstCategory.equals("Mobile"));
    }

    @Test
    void shouldLoadCoursesBySubcategory() {
        androidCourse(CourseVisibility.PUBLIC, subcategory);
        androidTestsCourse(CourseVisibility.PUBLIC, subcategory);

        Pageable pageable = PageRequest.of(0, 2);

        Page<Course> courses = courseRepository.findAllBySubcategory(subcategory, pageable);

        assertThat(courses)
                .hasSize(2);
    }

    @Test
    void shouldLoadOnlyOneCourseBySubcategoryAccordingToPagination() {
        androidCourse(CourseVisibility.PUBLIC, subcategory);
        androidTestsCourse(CourseVisibility.PUBLIC, subcategory);

        Pageable pageable = PageRequest.of(0, 1);

        Page<Course> courses = courseRepository.findAllBySubcategory(subcategory, pageable);

        assertThat(courses)
                .hasSize(1);
    }

    @Test
    void shouldNotLoadCourseBySubcategory() {

        Pageable pageable = PageRequest.of(0, 1);

        Page<Course> courses = courseRepository.findAllBySubcategory(subcategory, pageable);

        assertTrue(courses.isEmpty());
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

    private Course androidCourse(CourseVisibility visibility, Subcategory subcategory) {
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
        em.persist(androidCourse);
        return androidCourse;
    }

    private Course androidTestsCourse(CourseVisibility visibility, Subcategory subcategory) {
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
        em.persist(androidTestsCourse);
        return androidTestsCourse;
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

    private Course htmlAndCss(CourseVisibility visibility, Subcategory subCategory) {
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
        em.persist(htmlAndCss);

        return htmlAndCss;
    }
}
