package br.com.harbitech.school.category;

import br.com.harbitech.school.course.Course;
import br.com.harbitech.school.course.CourseRepository;
import br.com.harbitech.school.course.CourseVisibility;
import br.com.harbitech.school.subcategory.SubCategoryStatus;
import br.com.harbitech.school.subcategory.Subcategory;
import br.com.harbitech.school.subcategory.SubcategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import java.util.List;

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

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Test
    @Transactional
    void should_retrieve_all_categories_by_status() throws Exception {
        Category devOps = categoryRepository.save(new Category("DevOps", "dev-ops", CategoryStatus.ACTIVE,
                1, "https://www.alura.com.br/assets/api/formacoes/categorias/128/devops.png",
                "hf#400", " Aprenda Git, Docker e Kubernetes e entenda a entrega contínua. " +
                "Estude administração de redes, Linux e gerencie servidores na nuvem. Explore o mundo de Internet das" +
                " coisas e da robótica. Saiba como começar com DevOps.", "Dê os primeiros passos com DevOps"));

        Subcategory linux = subcategoryRepository.save(new Subcategory("Linux", "linux",2,
                "linha de comando Linux, compreendendo sobre segurança, administração e gestão de usuário/grupo," +
                        " trabalhando na linha de comando e permissões , e mais habilidades para trabalhar com máquinas" +
                        " Linux no dia a dia.","Dê os primeiros passos no linux até se tornar avançado",
                SubCategoryStatus.ACTIVE,devOps));

        Course linuxCourse = courseRepository.save(new Course("Linux I: Conhecendo e utilizando o terminal",
                "linux-primeiros-passos",2, CourseVisibility.PUBLIC,null,
                "Guilherme Silveira" ,null,"Enteda termos técnicos básicos sobre esse " +
                "sistema operacional.",linux));

        devOps.setSubCategories(List.of(linux));
        linux.setCourses(List.of(linuxCourse));

        mockMvc.perform(get("/api/categories")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(1)))
                .andExpect(jsonPath("$[0].name", is("DevOps")))
                .andExpect(jsonPath("$[0].codeUrl", is("dev-ops")))
                .andExpect(jsonPath("$[0].orderVisualization", is(1)))
                .andExpect(jsonPath("$[0].iconPath", is("https://www.alura.com.br/assets/api/formacoes/categorias/128/devops.png")))
                .andExpect(jsonPath("$[0].htmlHexColorCode", is("hf#400")))
                .andExpect(jsonPath("$[0].studyGuide", is("Dê os primeiros passos com DevOps")))
                .andExpect(jsonPath("$[0].subcategory[0].name", is("Linux")))
                .andExpect(jsonPath("$[0].subcategory[0].codeUrl", is("linux")))
                .andExpect(jsonPath("$[0].subcategory[0].studyGuide", is("Dê os primeiros passos no linux" +
                        " até se tornar avançado")))
                .andExpect(jsonPath("$[0].subcategory[0].course[0].name", is("Linux I: Conhecendo e " +
                        "utilizando o terminal")))
                .andExpect(jsonPath("$[0].subcategory[0].course[0].codeUrl", is("linux-primeiros-passos")));
    }
}
