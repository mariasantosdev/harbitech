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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


public class CourseDaoTest {

    private CourseDao dao;
    private EntityManager em;
    private Category category;
    private Subcategory subcategory;

    @BeforeEach
    public void setUp() {
        this.em = JPAUtil.getEntityManager();
        this.dao = new CourseDao(em);
        em.getTransaction().begin();
        this.category = new CategoryBuilder("Mobile", "mobile")
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

        this.subcategory = new SubcategoryBuilder("Android","android",category)
                .withDescription("Crie aplicativos móveis para as principais plataformas, smartphones e tablets. " +
                        "Aprenda frameworks multiplataforma como Flutter e React Native e saiba como criar apps" +
                        " nativas para Android e iOS. Desenvolva também jogos mobile com Unity. Saiba como ")
                .withStudyGuide("Android, Testes automatizados e arquitetura android")
                .withStatus(SubCategoryStatus.ACTIVE)
                .withOrderVisualization(1)
                .create();
        em.persist(subcategory);
    }

    @AfterEach
    public void tearDown() {
        em.getTransaction().rollback();
    }

    @Test
    void shouldReturnAllPublicVisibilities() {
        androidCourse(CourseVisibility.PUBLIC);

        List<Course> courses = this.dao.searchAllWithPublicVisibility();

        assertThat(courses)
                .hasSize(1)
                .extracting(Course::getCodeUrl)
                .containsExactly("android-refinando-projeto");
    }

    @Test
    void shouldReturnEmptyBecauseDoNotHaveAnyCourse() {
        List<Course> courses = this.dao.searchAllWithPublicVisibility();
        assertTrue(courses.isEmpty());
    }

    @Test
    void shouldReturnEmptyBecauseOnlyCourseIsPrivate() {
        androidCourse(CourseVisibility.PRIVATE);

        List<Course> courses = this.dao.searchAllWithPublicVisibility();

        assertTrue(courses.isEmpty());
    }

    @Test
    void shouldUpdateAllToPublicVisibility() {
        androidCourse(CourseVisibility.PRIVATE);
        jpaCourse(CourseVisibility.PRIVATE);
        servletCourse(CourseVisibility.PUBLIC);

        this.dao.updateAllToPublicVisibility();

        em.clear();

        List<Course> courses = this.dao.findAll();
        assertThat(courses)
                .hasSize(3)
                .allMatch(course -> CourseVisibility.PUBLIC.equals(course.getVisibility()));
    }

    private Course androidCourse(CourseVisibility visibility) {
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

    private Course jpaCourse(CourseVisibility visibility) {
        Course jpaCourse = new CourseBuilder("Persistência com JPA: Introdução ao Hibernate",
                "persistencia-jpa-introducao-hibernate", "Rodrigo Ferreira", subcategory)
                .withCompletionTimeInHours(8)
                .withVisibility(visibility)
                .withTargetAudience("Pessoas que queiram aprender como funciona a JPA.")
                .withDescription("""
                        Cardinalidade do relacionamento
                         """)
                .withDevelopedSkills("Realize o mapeamento de entidades JPA e seus relacionamentos")
                .create();
        em.persist(jpaCourse);
        return jpaCourse;
    }

    private Course servletCourse(CourseVisibility visibility) {
        Course servletCourse = new CourseBuilder("Java Servlet: Fundamentos da programação web Java",
                "servlets-fundamentos-programacao-web-java","Nico Steppat",subcategory)
                .withCompletionTimeInHours(10)
                .withVisibility(visibility)
                .withTargetAudience("Programadores que conhecem a linguagem java e começarão a trabalhar na web.")
                .withDescription("""
                                   Gere HTML dinamicamente com JSP e JSTL
                                    """)
                .withDevelopedSkills("Crie uma CRUD completa")
                .create();
        em.persist(servletCourse);
        return servletCourse;
    }
}