package br.com.harbitech.school.dao;

import br.com.harbitech.school.category.Category;
import br.com.harbitech.school.category.CategoryStatus;
import br.com.harbitech.school.repository.dao.CategoryDao;
import br.com.harbitech.school.util.JPAUtil;
import br.com.harbitech.school.util.builder.CategoryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class CategoryDaoTest {

    private CategoryDao dao;
    private EntityManager em;

    @BeforeEach
    public void setUp() {
        this.em = JPAUtil.getEntityManager();
        this.dao = new CategoryDao(em);
        em.getTransaction().begin();
    }

    @AfterEach
    public void tearDown() {
        em.getTransaction().rollback();
    }

    @Test
    void shouldReturnAllWithActiveStatus() {
        mobileCategory(CategoryStatus.ACTIVE);

        List<Category> categories = this.dao.searchAllActive();

        assertThat(categories)
                .hasSize(1)
                .extracting(Category::getCodeUrl)
                .containsExactly("mobile");
    }

    @Test
    void shouldReturnEmptyBecauseDoNotHaveAnyCategory() {
        List<Category> categories = this.dao.searchAllActive();
        assertTrue(categories.isEmpty());
    }

    @Test
    void shouldReturnEmptyBecauseOnlyCategoryIsInactive() {
        mobileCategory(CategoryStatus.INACTIVE);

        List<Category> categories = this.dao.searchAllActive();

        assertTrue(categories.isEmpty());
    }

    @Test
    void shouldReturnLowestOrderOfVisualizationOfActiveCategories() {
        Category dataScience = dataScienceCategory(CategoryStatus.ACTIVE);
        mobileCategory(CategoryStatus.ACTIVE);

        List<Category> categories = this.dao.searchAllActive();

        assertEquals(categories.get(0), dataScience);
    }

    private Category dataScienceCategory(CategoryStatus status) {
        Category dataScience = new CategoryBuilder("Data Science", "data-science")
                .withDescription("Com o avanço da tecnologia, está cada vez maior a quantidade de dados disponíveis" +
                        " para análises avançadas. Desta forma, a área de Ciência de Dados emergiu auxiliando empresas" +
                        " e profissionais em suas tomadas de decisões: por isso, cresce a demanda por especialistas na " +
                        "área e o desenvolvimento de uma cultura de dados, empoderando profissionais de todas as áreas " +
                        "a lidar com dados e gerar insights que vão realmente impactar suas áreas de atuação.")
                .withStatus(status)
                .withOrderVisualization(1)
                .withIconPath("https://www.alura.com.br/cursos-online-data-science/data-science")
                .withHtmlHexColorCode("#008000")
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
                .withOrderVisualization(1)
                .withIconPath("https://www.google.com/search?q=forma%C3%A7%C3%A3o+mobile+alura+icon&client=ubuntu&hs=" +
                        "PUH&channel=fs&sxsrf=ALeKk01O4vjVbL33VupNCbN27rcLhDfgmQ:1625529442736&source=lnms&tbm=i" +
                        "sch&sa=X&ved=2ahUKEwjW-IWIkc3xAhWLK7kGHVP_BVUQ_AUoAXoECAEQAw&biw=1445&bih=733#imgrc=xIoKd" +
                        "XUJ9UtPvM")
                .withHtmlHexColorCode("#FFFF00")
                .create();
        em.persist(mobile);
        return mobile;
    }
}
