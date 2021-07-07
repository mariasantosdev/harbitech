package br.com.harbitech.school.dao;

import br.com.harbitech.school.category.Category;
import br.com.harbitech.school.category.CategoryStatus;
import br.com.harbitech.school.course.Course;
import br.com.harbitech.school.course.CourseVisibility;
import br.com.harbitech.school.repository.dao.CourseDao;
import br.com.harbitech.school.subcategory.SubCategoryStatus;
import br.com.harbitech.school.subcategory.Subcategory;
import br.com.harbitech.school.util.JPAUtil;
import br.com.harbitech.school.util.builder.CategoryBuilder;
import br.com.harbitech.school.util.builder.CourseBuilder;
import br.com.harbitech.school.util.builder.SubcategoryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CourseDaoTest {

    private CourseDao dao;
    private EntityManager em;

    @BeforeEach
    public void setUp() {
        this.em = JPAUtil.getEntityManager();
        this.dao = new CourseDao(em);
        em.getTransaction().begin();
    }

    @AfterEach
    public void tearDown() {
        em.getTransaction().rollback();
    }

    @Test
    void shouldValidateCorrectQueryWithAllCategoriesActive() {
        Category category = new CategoryBuilder()
                .withName("Mobile")
                .withCodeUrl("mobile")
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

        Subcategory subcategory = new SubcategoryBuilder()
                .withName("Android")
                .withCodeUrl("android")
                .withDescription("Crie aplicativos móveis para as principais plataformas, smartphones e tablets. " +
                        "Aprenda frameworks multiplataforma como Flutter e React Native e saiba como criar apps" +
                        " nativas para Android e iOS. Desenvolva também jogos mobile com Unity. Saiba como ")
                .withStudyGuide("Android, Testes automatizados e arquitetura android")
                .withStatus(SubCategoryStatus.ACTIVE)
                .withOrderVisualization(1)
                .withCategory(category)
                .create();
        em.persist(subcategory);

        Course course = new CourseBuilder()
                .withName("Android parte 3: Refinando o projeto")
                .withCodeUrl("android-refinando-projeto")
                .withCompletionTimeInHours(10)
                .withVisibility(CourseVisibility.PUBLIC)
                .withTargetAudience("Pessoas com foco em java/kotlin/desenvolvimento mobile")
                .withInstructor("Alex Felipe")
                .withDescription("\n" +
                        "\n" +
                        "    Implementar um layout personalizado para um AdapterView\n" +
                        "    Entender e utilizar a entidade Application do Android Framework\n" +
                        "    Interagir com o usuário por meio de dialogs\n" +
                        "    Analisar possíveis melhorias no projeto por meio do inspetor de código\n" +
                        "    Compreender e resolver tópicos apresentado no resultado da inspeção de código\n" +
                        "\n")
                .withDevelopedSkills("Aprenda a refatorar, usando os principios de SOLID nesse curso")
                .withSubcategory(subcategory)
                .create();
        em.persist(course);

        List<Course> courses = assertDoesNotThrow(() -> this.dao.searchAllWithPublicVisibility());
    }

    @Test
    void shouldValidateCorrectQueryButItDoesNotHaveRegistry() {
        List<Course> courses = this.dao.searchAllWithPublicVisibility();
        assertTrue(courses.isEmpty());
    }

    @Test
    void shouldValidateCorrectQueryButItDoesNotHaveRegistryBecauseOnlyCourseIsPrivate() {
        Category category = new CategoryBuilder()
                .withName("Mobile")
                .withCodeUrl("mobile")
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

        Subcategory subcategory = new SubcategoryBuilder()
                .withName("Android")
                .withCodeUrl("android")
                .withDescription("Crie aplicativos móveis para as principais plataformas, smartphones e tablets. " +
                        "Aprenda frameworks multiplataforma como Flutter e React Native e saiba como criar apps" +
                        " nativas para Android e iOS. Desenvolva também jogos mobile com Unity. Saiba como ")
                .withStudyGuide("Android, Testes automatizados e arquitetura android")
                .withStatus(SubCategoryStatus.ACTIVE)
                .withOrderVisualization(1)
                .withCategory(category)
                .create();
        em.persist(subcategory);

        Course course = new CourseBuilder()
                .withName("Android parte 3: Refinando o projeto")
                .withCodeUrl("android-refinando-projeto")
                .withCompletionTimeInHours(10)
                .withVisibility(CourseVisibility.PRIVATE)
                .withTargetAudience("Pessoas com foco em java/kotlin/desenvolvimento mobile")
                .withInstructor("Alex Felipe")
                .withDescription("\n" +
                        "\n" +
                        "    Implementar um layout personalizado para um AdapterView\n" +
                        "    Entender e utilizar a entidade Application do Android Framework\n" +
                        "    Interagir com o usuário por meio de dialogs\n" +
                        "    Analisar possíveis melhorias no projeto por meio do inspetor de código\n" +
                        "    Compreender e resolver tópicos apresentado no resultado da inspeção de código\n" +
                        "\n")
                .withDevelopedSkills("Aprenda a refatorar, usando os principios de SOLID nesse curso")
                .withSubcategory(subcategory)
                .create();
        em.persist(course);

        List<Course> courses = this.dao.searchAllWithPublicVisibility();

        assertTrue(courses.isEmpty());
    }
}
