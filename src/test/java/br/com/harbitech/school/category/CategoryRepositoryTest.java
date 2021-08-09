package br.com.harbitech.school.category;

import br.com.harbitech.school.util.builder.CategoryBuilder;
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

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class CategoryRepositoryTest {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    public void shouldLoadOneCategorySearchingByCodeUrl(){
        Category expectedCategory = mobileCategory();
        Optional<Category> category = categoryRepository.findByCodeUrl("mobile");

        //TODO colocar o expect e o passado em uma variavel.

        Assert.assertNotNull(category);
        Assert.assertEquals(expectedCategory.getName(), category.get().getName());
    }

    @Test
    public void shouldReturnAllCategoriesOrderlyByName(){
        mobileCategory();
        dataScienceCategory();
        List<Category> category = categoryRepository.findAllByOrderByName();

        Assert.assertNotNull(category);
//        Assert.assertEquals(mobileCategory().getName(), category.get().getName());

    }

    private Category dataScienceCategory() {
        Category dataScience = new CategoryBuilder("Data Science", "data-science")
                .withStatus(CategoryStatus.INACTIVE)
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

    private Category mobileCategory() {
        Category mobile = new CategoryBuilder("Mobile", "mobile")
                .withDescription("Crie aplicativos móveis para as principais plataformas, smartphones e tablets. " +
                        "Aprenda frameworks multiplataforma como Flutter e React Native e saiba como criar apps" +
                        " nativas para Android e iOS. Desenvolva também jogos mobile com Unity. Saiba como ")
                .withStudyGuide("Android, Testes automatizados, arquitetura android e flutter")
                .withStatus(CategoryStatus.INACTIVE)
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
}
