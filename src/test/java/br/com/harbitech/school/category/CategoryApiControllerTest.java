package br.com.harbitech.school.category;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.Matchers.is;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class CategoryApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    @Transactional
     void shouldRetrieveAllCategoriesByStatus() throws Exception {
        categoryRepository.save(new Category("DevOps","dev-ops",CategoryStatus.ACTIVE,1,
                "https://www.alura.com.br/assets/api/formacoes/categorias/128/devops.png","hf#400",
                " Aprenda Git, Docker e Kubernetes e entenda a entrega contínua. Estude administração de" +
                        " redes, Linux e gerencie servidores na nuvem. Explore o mundo de Internet das coisas e da " +
                        "robótica. Saiba como começar com DevOps.","Dê os primeiros passos com DevOps"));

        categoryRepository.save(new Category("Front-end","front-end",CategoryStatus.ACTIVE,2,
                "https://www.alura.com.br/cursos-online-front-end","##f890","Desenvolva " +
                "sites e webapps com HTML, CSS e JavaScript. Aprenda as boas práticas e as últimas versões do JavaScript. " +
                "Estude ferramentas e frameworks do mercado como React, Angular, Webpack, jQuery e mais. Saiba como começar com Front-end.",
                "Aprenda os primeiros passos com JS, HTML css e muitos outros frameworks do mercado"));

                mockMvc.perform(get("/api/categories")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andExpect(jsonPath("$.length()", is(2)))
                        .andExpect(jsonPath("$[0].name", is("DevOps")))
                        .andExpect(jsonPath("$[0].codeUrl", is("dev-ops")))
                        .andExpect(jsonPath("$[0].orderVisualization", is(1)))
                        .andExpect(jsonPath("$[0].iconPath", is("https://www.alura.com.br/assets/api/formacoes/categorias/128/devops.png")))
                        .andExpect(jsonPath("$[0].htmlHexColorCode", is("hf#400")))
                        .andExpect(jsonPath("$[0].studyGuide", is("Dê os primeiros passos com DevOps")))
                        .andExpect(jsonPath("$[1].name", is("Front-end")))
                        .andExpect(jsonPath("$[1].codeUrl", is("front-end")))
                        .andExpect(jsonPath("$[1].orderVisualization", is(2)))
                        .andExpect(jsonPath("$[1].iconPath", is("https://www.alura.com.br/cursos-online-front-end")))
                        .andExpect(jsonPath("$[1].htmlHexColorCode", is("##f890")))
                        .andExpect(jsonPath("$[1].studyGuide", is("Aprenda os primeiros passos com JS, HTML css e muitos outros frameworks do mercado")));
    }

    @Test
    @Transactional
    void shouldReturnAListWithoutCategories() throws Exception {
        mockMvc.perform(get("/api/categories")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
         .andExpect(jsonPath("$.length()", is(0)));
    }
}
