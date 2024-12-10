package br.com.harbitech.school.subcategory;

import br.com.harbitech.school.category.Category;
import br.com.harbitech.school.category.CategoryConverter;
import br.com.harbitech.school.category.CategoryRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringBootTest
class SubcategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryConverter categoryConverter;

    @Autowired
    private SubcategoryFormValidator subcategoryFormValidator;

    @Autowired
    private SubcategoryFormUpdateValidator subcategoryFormUpdateValidator;


    @BeforeEach
    public void init() {
        FormattingConversionService conversionService = new FormattingConversionService();
        conversionService.addConverter(categoryConverter);

        mockMvc = MockMvcBuilders.standaloneSetup(new SubcategoryController(
                        subcategoryRepository,
                        categoryRepository,
                        subcategoryFormValidator,
                        subcategoryFormUpdateValidator))
                .setConversionService(conversionService)
                .build();
    }

    @AfterEach
    public void tearDown() {
        subcategoryRepository.deleteAll();
        categoryRepository.deleteAll();
    }

    @Test
    @DisplayName("Should list all subcategories successfully")
    @WithMockUser(roles = "MANAGER", username = "maria@gmail.com", password = "123456")
    void list__should_show_a_list_of_all_subcategories_successfully() throws Exception {
        Category category = new Category("Programming", "programming");
        categoryRepository.save(category);

        Subcategory subcategory = new Subcategory("Java", "java", category);
        subcategoryRepository.save(subcategory);

        mockMvc.perform(get("/admin/subcategories/{category}", "programming"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/subcategory/listSubcategories"))
                .andExpect(model().attribute("subcategories", hasSize(1)));
    }

    @Test
    @DisplayName("Should show subcategory creation form successfully")
    @WithMockUser(roles = "MANAGER", username = "maria@gmail.com", password = "123456")
    void formNew__should_show_subcategory_creation_form_successfully() throws Exception {
        mockMvc.perform(get("/admin/subcategories/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/subcategory/formSubcategory"))
                .andExpect(model().attributeExists("subcategoryForm"));
    }

    @Test
    @DisplayName("Should create a subcategory successfully")
    @WithMockUser(roles = "MANAGER", username = "maria@gmail.com", password = "123456")
    void save__should_create_a_subcategory_successfully() throws Exception {
        Category category = new Category("Programming", "programming");
        categoryRepository.save(category);

        mockMvc.perform(post("/admin/subcategories")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("name", "Java")
                        .param("category", category.getId().toString())
                        .param("codeUrl", "java"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/subcategories/programming"));

        assertThat(subcategoryRepository.findByCodeUrl("java")).isPresent();
    }

    @Test
    @DisplayName("Should return errors when creating a subcategory with invalid data")
    @WithMockUser(roles = "MANAGER", username = "maria@gmail.com", password = "123456")
    void save__should_have_errors_when_data_is_invalid() throws Exception {
        mockMvc.perform(post("/admin/subcategories")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("name", "")
                        .param("codeUrl", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/subcategory/formSubcategory"))
                .andExpect(model().attributeHasFieldErrors("subcategoryForm", "name", "codeUrl"));
    }

    @Test
    @DisplayName("Should show subcategory update form successfully")
    @WithMockUser(roles = "MANAGER", username = "maria@gmail.com", password = "123456")
    void formUpdate__should_show_subcategory_update_form_successfully() throws Exception {
        Category category = new Category("Programming", "programming");
        categoryRepository.save(category);

        Subcategory subcategory = new Subcategory("Java", "java", category);
        subcategoryRepository.save(subcategory);

        mockMvc.perform(get("/admin/subcategories/{category}/{subcategory}", "programming", "java"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/subcategory/formUpdateSubcategory"))
                .andExpect(model().attributeExists("subcategoryFormUpdate"));
    }

    @Test
    @DisplayName("Should update a subcategory successfully")
    @WithMockUser(roles = "MANAGER", username = "maria@gmail.com", password = "123456")
    void update__should_update_a_subcategory_successfully() throws Exception {
        Category category = new Category("Programming", "programming");
        categoryRepository.save(category);

        Subcategory subcategory = new Subcategory("Java", "java", category);
        subcategoryRepository.save(subcategory);

        mockMvc.perform(post("/admin/subcategories/{category}/{subcategoryCodeUrl}", "programming", "java")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("name", "Advanced Java")
                        .param("id", subcategory.getId().toString())
                        .param("category", category.getId().toString())
                        .param("codeUrl", "advanced-java"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/subcategories/programming"));

        assertThat(subcategoryRepository.findByCodeUrl("java")).isEmpty();
        assertThat(subcategoryRepository.findByCodeUrl("advanced-java")).isPresent();
    }

    @Test
    @DisplayName("Should return errors when updating a subcategory with invalid data")
    @WithMockUser(roles = "MANAGER", username = "maria@gmail.com", password = "123456")
    void update__should_have_errors_when_data_is_invalid() throws Exception {
        Category category = new Category("Programming", "programming");
        categoryRepository.save(category);

        Subcategory subcategory = new Subcategory("Java", "java", category);
        subcategoryRepository.save(subcategory);

        mockMvc.perform(post("/admin/subcategories/{category}/{subcategoryCodeUrl}", "programming", "java")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("name", "")
                        .param("id", subcategory.getId().toString())
                        .param("category", category.getId().toString())
                        .param("codeUrl", ""))

                .andExpect(status().isOk())
                .andExpect(view().name("admin/subcategory/formUpdateSubcategory"))
                .andExpect(model().attributeHasFieldErrors("subcategoryFormUpdate", "name",
                        "codeUrl"));
    }

    @Test
    @DisplayName("Should return 404 when subcategory is not found")
    @WithMockUser(roles = "MANAGER", username = "maria@gmail.com", password = "123456")
    void formUpdate__should_return_not_found_when_subcategory_does_not_exist() throws Exception {
        mockMvc.perform(get("/admin/subcategories/{category}/{subcategory}", "programming", "non-existent-code"))
                .andExpect(status().isNotFound());
    }
}
