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

        this.category = new CategoryBuilder()
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
    }

    @AfterEach
    public void tearDown() {
        em.getTransaction().rollback();
    }

    @Test
    void shouldReturnAllWithActiveStatus() {
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
        Subcategory subcategory = new SubcategoryBuilder()
                .withName("Android")
                .withCodeUrl("android")
                .withDescription("Crie aplicativos móveis para as principais plataformas, smartphones e tablets. " +
                        "Aprenda frameworks multiplataforma como Flutter e React Native e saiba como criar apps" +
                        " nativas para Android e iOS. Desenvolva também jogos mobile com Unity. Saiba como ")
                .withStudyGuide("Android, Testes automatizados e arquitetura android")
                .withStatus(SubCategoryStatus.INACTIVE)
                .withOrderVisualization(1)
                .withCategory(category)
                .create();
        em.persist(subcategory);
        assertDoesNotThrow(() -> this.dao.searchAllActive());

        List<Subcategory> subcategories = this.dao.searchAllActive();
        assertTrue(subcategories.isEmpty());
    }

    @Test
    void shouldReturnAllWithDescriptionNull() {
        Subcategory subcategory = new SubcategoryBuilder()
                .withName("Android")
                .withCodeUrl("android")
                .withDescription(null)
                .withStatus(SubCategoryStatus.INACTIVE)
                .withOrderVisualization(1)
                .withCategory(category)
                .create();
        em.persist(subcategory);
        assertDoesNotThrow(() -> this.dao.searchAllActive());
        //TODO ASSERTNOT NULL
    }

//    @Test
//    void shouldReturnEmptyBecauseOnlySubcategoryIsWithDescription() {
//        Subcategory subcategory = new SubcategoryBuilder()
//                .withName("Android")
//                .withCodeUrl("android")
//                .withDescription("")
//                .withStatus(SubCategoryStatus.INACTIVE)
//                .withOrderVisualization(1)
//                .withCategory(category)
//                .create();
//        em.persist(subcategory);
//
//        assertDoesNotThrow(() -> this.dao.searchAllActive());
//
//        List<Subcategory> subcategories = this.dao.searchAllActive();
//        assertTrue(subcategories.isEmpty());
//    }
}
