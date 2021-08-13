package br.com.harbitech.school.subcategory;

import br.com.harbitech.school.category.Category;
import br.com.harbitech.school.category.CategoryStatus;
import br.com.harbitech.school.course.Course;
import br.com.harbitech.school.course.CourseVisibility;
import br.com.harbitech.school.util.builder.CategoryBuilder;
import br.com.harbitech.school.util.builder.CourseBuilder;
import br.com.harbitech.school.util.builder.SubcategoryBuilder;
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

    private Category mobile;

    @BeforeEach
    void setUp() {
        this.mobile = aCategory();
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
    void should_load_one_subcategory_searching_by_code_url() {
        Subcategory expectedSubcategory = flutterSubcategory(SubCategoryStatus.ACTIVE, mobile);

        Optional<Subcategory> possibleSubcategory = subcategoryRepository.findByCodeUrl("flutter");

        assertTrue(possibleSubcategory.isPresent());
        assertEquals(expectedSubcategory.getCodeUrl(), "flutter");
    }

    @Test
    void should_not_load_one_subcategory_because_doesnt_have_one_subcategory_with_the_code_url_passed() {
        Optional<Subcategory> possibleSubcategory = subcategoryRepository.findByCodeUrl("subcategoryDoesntExist");

        assertTrue(possibleSubcategory.isEmpty());
    }

    @Test
    void should_load_all_categories_orderly_by_name() {
        androidSubcategory(SubCategoryStatus.ACTIVE, mobile);
        flutterSubcategory(SubCategoryStatus.ACTIVE, mobile);

        List<Subcategory> subcategories = subcategoryRepository.findAllByOrderByName();

        String codeUrlFromFirstSubcategory = subcategories.get(0).getCodeUrl();

        assertThat(subcategories)
                .hasSize(2)
                .allMatch(course -> codeUrlFromFirstSubcategory.equals("android"));

        assertThat(subcategories)
                .hasSize(2)
                .extracting(Subcategory::getCodeUrl)
                .containsExactly("android","flutter");
}

    @Test
    void should_not_load_any_subcategory_because_doesnt_have_one_category_bound() {

        List<Subcategory> subcategories = subcategoryRepository
                .findAllByCategoryOrderByOrderVisualization(mobile);

        assertTrue(subcategories.isEmpty());
    }

    @Test
    void should_load_all_categories_orderly_by_order_visualization() {
        androidSubcategory(SubCategoryStatus.ACTIVE, mobile);
        flutterSubcategory(SubCategoryStatus.ACTIVE, mobile);

        List<Subcategory> subcategories = subcategoryRepository.findAllByCategoryOrderByOrderVisualization(mobile);

        assertThat(subcategories)
                .hasSize(2)
                .extracting(Subcategory::getCodeUrl)
                .containsExactly("android","flutter");
    }

    @Test
    void should_load_all_active_subcategories_with_public_courses() {
        androidCourse(CourseVisibility.PUBLIC, SubCategoryStatus.ACTIVE);

        List<Subcategory> subcategories = subcategoryRepository.findAllActiveSubcategories(mobile);

        assertThat(subcategories)
                .hasSize(1)
                .extracting(Subcategory::getCodeUrl)
                .containsExactly("android");
    }

    @Test
    void should_not_load_any_subcategories_because_the_course_is_private() {
        androidCourse(CourseVisibility.PRIVATE, SubCategoryStatus.ACTIVE);

        List<Subcategory> subcategories = subcategoryRepository.findAllActiveSubcategories(mobile);

        assertTrue(subcategories.isEmpty());
    }

    @Test
    void should_not_load_any_subcategories_because_the_subcategory_has_not_course() {
        flutterSubcategory(SubCategoryStatus.ACTIVE, mobile);

        List<Subcategory> subcategories = subcategoryRepository.findAllActiveSubcategories(mobile);

        assertTrue(subcategories.isEmpty());
    }

    @Test
    void should_not_load_any_subcategories_because_the_subcategory_is_inactive() {
        androidCourse(CourseVisibility.PUBLIC, SubCategoryStatus.INACTIVE);

        List<Subcategory> subcategories = subcategoryRepository.findAllActiveSubcategories(mobile);

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
        Course androidCourse = new CourseBuilder("Android parte 3: Refinando o projeto",
                "android-refinando-projeto", "Alex Felipe", androidSubcategory(subCategoryStatus, mobile))
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
