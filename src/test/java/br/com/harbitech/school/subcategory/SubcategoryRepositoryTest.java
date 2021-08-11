package br.com.harbitech.school.subcategory;

import br.com.harbitech.school.category.Category;
import br.com.harbitech.school.category.CategoryStatus;
import br.com.harbitech.school.course.Course;
import br.com.harbitech.school.course.CourseVisibility;
import br.com.harbitech.school.util.builder.CategoryBuilder;
import br.com.harbitech.school.util.builder.CourseBuilder;
import br.com.harbitech.school.util.builder.SubcategoryBuilder;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class SubcategoryRepositoryTest {

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    @Autowired
    private TestEntityManager em;

    private Category category;

    private Course course;

    @BeforeEach
    public void setUp() {
        this.category = aCategory();
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

    @Test
    void shouldLoadOneSubcategorySearchingByCodeUrl() {
        Subcategory expectedSubcategory = flutterSubcategory(SubCategoryStatus.ACTIVE, category);

        Optional<Subcategory> possibleSubcategory = subcategoryRepository.findByCodeUrl("flutter");

        assertTrue(possibleSubcategory.isPresent());
        assertEquals(expectedSubcategory.getCodeUrl(), possibleSubcategory.get().getCodeUrl());
    }

    @Test
    void shouldNotLoadOneSubcategoryBecauseDoesntHaveOneSubcategoryWithTheCodeUrlPassed() {
        Optional<Subcategory> possibleSubcategory = subcategoryRepository.findByCodeUrl("java");

        assertTrue(possibleSubcategory.isEmpty());
    }

    @Test
    void shouldLoadAllCategoriesOrderlyByName() {
        androidSubcategory(SubCategoryStatus.ACTIVE, category);
        flutterSubcategory(SubCategoryStatus.ACTIVE, category);

        List<Subcategory> subcategories = subcategoryRepository.findAllByOrderByName();

        String codeUrlFromFirstSubcategory = subcategories.get(0).getCodeUrl();

        assertThat(subcategories)
                .hasSize(2)
                .allMatch(course -> codeUrlFromFirstSubcategory.equals("android"));
}

    @Test
    void shouldNotLoadAnySubcategoryBecauseDoesntHaveOneCategoryBound() {

        List<Subcategory> subcategories = subcategoryRepository
                .findAllByCategoryOrderByOrderVisualization(category);

        assertTrue(subcategories.isEmpty());
    }

    @Test
    void shouldLoadAllCategoriesOrderlyByOrderVisualization() {
        androidSubcategory(SubCategoryStatus.ACTIVE, category);
        flutterSubcategory(SubCategoryStatus.ACTIVE, category);

        List<Subcategory> subcategories = subcategoryRepository.findAllByCategoryOrderByOrderVisualization(category);

        String codeUrlFromFirstSubcategory = subcategories.get(0).getCodeUrl();

        assertThat(subcategories)
                .hasSize(2)
                .allMatch(course -> codeUrlFromFirstSubcategory.equals("android"));
    }

    @Test
    void shouldLoadAllActiveSubcategoriesWithPublicCourses() {
        androidCourse(CourseVisibility.PUBLIC, SubCategoryStatus.ACTIVE);

        List<Subcategory> subcategories = subcategoryRepository.findAllActiveSubcategories(category);

        assertThat(subcategories)
                .hasSize(1)
                .extracting(Subcategory::getCodeUrl)
                .containsExactly("android");
    }

    @Test
    void shouldNotLoadAnySubcategoriesBecauseTheCourseIsPrivate() {
        androidCourse(CourseVisibility.PRIVATE, SubCategoryStatus.ACTIVE);

        List<Subcategory> subcategories = subcategoryRepository.findAllActiveSubcategories(category);

        assertTrue(subcategories.isEmpty());
    }

    @Test
    void shouldNotLoadAnySubcategoriesBecauseTheSubcategoryHasNotCourse() {
        flutterSubcategory(SubCategoryStatus.ACTIVE, category);

        List<Subcategory> subcategories = subcategoryRepository.findAllActiveSubcategories(category);

        assertTrue(subcategories.isEmpty());
    }

    @Test
    void shouldNotLoadAnySubcategoriesBecauseTheSubcategoryIsInactive() {
        androidCourse(CourseVisibility.PUBLIC, SubCategoryStatus.INACTIVE);

        List<Subcategory> subcategories = subcategoryRepository.findAllActiveSubcategories(category);

        assertTrue(subcategories.isEmpty());
    }

    private Subcategory androidSubcategory(SubCategoryStatus status, Category category) {
        Subcategory android = new SubcategoryBuilder("Android","android",category)
                .withDescription("Crie aplicativos móveis para as principais plataformas, smartphones e tablets. " +
                        "Aprenda frameworks multiplataforma como Flutter e React Native e saiba como criar apps" +
                        " nativas para Android e iOS. Desenvolva também jogos mobile com Unity. Saiba como ")
                .withStudyGuide("Android, Testes automatizados e arquitetura android")
                .withStatus(status)
                .withOrderVisualization(2)
                .create();
        em.persist(android);
        return android;
    }

    private Course androidCourse(CourseVisibility visibility, SubCategoryStatus subCategoryStatus) {
        this.course = new CourseBuilder("Android parte 3: Refinando o projeto",
                "android-refinando-projeto", "Alex Felipe", androidSubcategory(subCategoryStatus, category))
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

    private Subcategory flutterSubcategory(SubCategoryStatus status, Category category) {
        Subcategory flutter = new SubcategoryBuilder("Flutter","flutter",category)
                .withDescription("Aprenda a utilizar o Flutter, programar em Dart e criar seu primeiro projeto\n" +
                        "Entenda o que é Widget e como funciona a árvore de Widgets\n" +
                        "Crie layouts com Widgets do Material Design")
                .withStatus(status)
                .withOrderVisualization(5)
                .create();
        em.persist(flutter);
        return flutter;
    }
}
