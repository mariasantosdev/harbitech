package br.com.harbitech.school.category;

import br.com.harbitech.school.course.Course;
import br.com.harbitech.school.course.CourseVisibility;
import br.com.harbitech.school.subcategory.SubCategoryStatus;
import br.com.harbitech.school.subcategory.Subcategory;
import br.com.harbitech.school.util.builder.CategoryBuilder;
import br.com.harbitech.school.util.builder.CourseBuilder;
import br.com.harbitech.school.util.builder.SubcategoryBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import org.junit.runner.RunWith;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
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
    public void shouldLoadOneCategorySearchingByCodeUrl() {
        Category expectedCategory = mobileCategory(CategoryStatus.ACTIVE);

        Optional<Category> actualCategory = categoryRepository.findByCodeUrl("mobile");

        Assert.assertNotNull(actualCategory);
        Assert.assertEquals(expectedCategory.getCodeUrl(), actualCategory.get().getCodeUrl());
    }

    @Test
    public void shouldntLoadOneCategoryBecauseDoesntHaveOneCategoryWithTheCodeUrlPassed() {
        Optional<Category> possibleCategory = categoryRepository.findByCodeUrl("programacao");

        assertTrue(possibleCategory.isEmpty());
    }

    @Test
    public void shouldLoadAllCategoriesOrderlyByName() {
        mobileCategory(CategoryStatus.ACTIVE);
        dataScienceCategory(CategoryStatus.INACTIVE);

        List<Category> categories = categoryRepository.findAllByOrderByName();

        String codeUrlFromFirstCategory = categories.get(0).getCodeUrl();

        assertThat(categories)
                .hasSize(2)
                .allMatch(course -> codeUrlFromFirstCategory.equals("data-science"));
    }

    @Test
    public void shouldntLoadAnyCategoriesBecauseDontHave() {
        List<Category> categories = categoryRepository.findAllByOrderByName();

        assertTrue(categories.isEmpty());
    }

    @Test
    public void shouldLoadAllCategoriesSearchingByStatus() {
        mobileCategory(CategoryStatus.ACTIVE);
        dataScienceCategory(CategoryStatus.INACTIVE);

        List<Category> categories = categoryRepository.findAllByStatus(CategoryStatus.ACTIVE);

        assertThat(categories)
                .hasSize(1)
                .extracting(Category::getCodeUrl)
                .containsExactly("mobile");
    }

    @Test
    public void shouldntLoadCategoriesBecauseDontHaveWithTheStatusPassed() {
        List<Category> categories = categoryRepository.findAllByStatus(CategoryStatus.ACTIVE);

        assertTrue(categories.isEmpty());
    }

    @Test
    public void shouldLoadALlPublicCategoriesWithPublicCourses() {
        androidCourse(CourseVisibility.PUBLIC);

        List<Category> categories = categoryRepository.findAllActiveCategoriesWithPublicCourses();

        assertThat(categories)
                .hasSize(1)
                .extracting(Category::getCodeUrl)
                .containsExactly("mobile");
    }

    @Test
    public void shouldntLoadAnyCategoriesBecauseTheOnlyCategoryIsPrivate() {
        androidCourse(CourseVisibility.PRIVATE);

        List<Category> categories = categoryRepository.findAllActiveCategoriesWithPublicCourses();

        assertTrue(categories.isEmpty());
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

    private Subcategory androidSubcategory () {
        this.subcategory = new SubcategoryBuilder("Android", "android", mobileCategory (CategoryStatus.ACTIVE))
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

    private Course androidCourse(CourseVisibility visibility) {
        this.course = new CourseBuilder("Android parte 3: Refinando o projeto",
                "android-refinando-projeto", "Alex Felipe", androidSubcategory ())
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
