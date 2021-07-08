package br.com.harbitech.school.dao;

import br.com.harbitech.school.category.Category;
import br.com.harbitech.school.category.CategoryStatus;
import br.com.harbitech.school.repository.dao.SubcategoryDao;
import br.com.harbitech.school.subcategory.SubCategoryStatus;
import br.com.harbitech.school.subcategory.Subcategory;
import br.com.harbitech.school.util.JPAUtil;
import br.com.harbitech.school.util.builder.CategoryBuilder;
import br.com.harbitech.school.util.builder.SubcategoryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SubcategoryDaoTest {

    private SubcategoryDao dao;
    private EntityManager em;
    private Category category;

    @BeforeEach
    public void setUp() {
        this.em = JPAUtil.getEntityManager();
        this.dao = new SubcategoryDao(em);
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
    }

    @AfterEach
    public void tearDown() {
        em.getTransaction().rollback();
    }

    @Test
    void shouldReturnAllWithActiveStatus() {
        Subcategory android = new SubcategoryBuilder("Android","android",category)
                .withDescription("Crie aplicativos móveis para as principais plataformas, smartphones e tablets. " +
                        "Aprenda frameworks multiplataforma como Flutter e React Native e saiba como criar apps" +
                        " nativas para Android e iOS. Desenvolva também jogos mobile com Unity. Saiba como ")
                .withStudyGuide("Android, Testes automatizados e arquitetura android")
                .withStatus(SubCategoryStatus.ACTIVE)
                .withOrderVisualization(1)
                .create();
        em.persist(android);

        List<Subcategory> subcategories = this.dao.searchAllActive();

        assertFalse(subcategories.isEmpty());
    }

    @Test
    void shouldReturnEmptyBecauseDoNotHaveAnySubcategory() {
        List<Subcategory> subcategories = this.dao.searchAllActive();
        assertTrue(subcategories.isEmpty());
    }

    @Test
    void shouldReturnEmptyBecauseOnlySubcategoryIsInactive()  {
        Subcategory android = new SubcategoryBuilder("Android","android",category)
                .withDescription("Crie aplicativos móveis para as principais plataformas, smartphones e tablets. " +
                        "Aprenda frameworks multiplataforma como Flutter e React Native e saiba como criar apps" +
                        " nativas para Android e iOS. Desenvolva também jogos mobile com Unity. Saiba como ")
                .withStudyGuide("Android, Testes automatizados e arquitetura android")
                .withStatus(SubCategoryStatus.INACTIVE)
                .withOrderVisualization(1)
                .create();
        em.persist(android);
        assertDoesNotThrow(() -> this.dao.searchAllActive());

        List<Subcategory> subcategories = this.dao.searchAllActive();
        assertTrue(subcategories.isEmpty());
    }

    @Test
    void shouldReturnAllWithDescriptionNull() {
        Subcategory android = new SubcategoryBuilder("Android","android",category)
                .withDescription(null)
                .withStatus(SubCategoryStatus.INACTIVE)
                .withOrderVisualization(1)
                .create();
        em.persist(android);

        List<String> subcategories = this.dao.searchAllWithoutDescription();
        assertFalse(subcategories.isEmpty());
    }

    @Test
    void shouldReturnAllWithDescriptionEmpty(){
        Subcategory subcategory = new SubcategoryBuilder("Android","android",category)
                .withDescription("")
                .withStatus(SubCategoryStatus.INACTIVE)
                .withOrderVisualization(1)
                .create();
        em.persist(subcategory);

        List<String> subcategories = this.dao.searchAllWithoutDescription();
        assertFalse(subcategories.isEmpty());
    }

    @Test
    void shouldReturnLowestOrderOfVisualizationOfActiveSubcategories(){
        Subcategory android = new SubcategoryBuilder("Android","android",category)
                .withDescription("Crie sua primeira app Android com suporte ao Kotlin\n" +
                        "Construa classes modelos e entenda o que são properties\n" +
                        "Adicione comportamentos em outras classes com a extension function\n" +
                        "Crie classes enums")
                .withStatus(SubCategoryStatus.ACTIVE)
                .withOrderVisualization(1)
                .create();
        em.persist(android);

        Subcategory flutter = new SubcategoryBuilder("Flutter","flutter",category)
                .withDescription("Aprenda a utilizar o Flutter, programar em Dart e criar seu primeiro projeto\n" +
                        "Entenda o que é Widget e como funciona a árvore de Widgets\n" +
                        "Crie layouts com Widgets do Material Design")
                .withStatus(SubCategoryStatus.ACTIVE)
                .withOrderVisualization(1)
                .create();
        em.persist(flutter);

        List<Subcategory> subcategories = this.dao.searchAllActive();

        assertEquals(subcategories.get(0),android);
    }
}
