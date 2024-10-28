package br.com.harbitech.school.category;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.security.test.context.support.WithMockUser;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringBootTest
class CategoryControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CategoryRepository categoryRepository;


    @AfterEach
    public void tearDown() {
        categoryRepository.deleteAll();
    }

    @Test
    @DisplayName("Should show the list of categories successfully")
    @WithMockUser(roles = "MANAGER",
            username = "maria@gmail.com", password = "123456")
    void list__should_show_a_list_of_all_categories_successfully() throws Exception {
        Category category = new Category("Programação", "programacao");
        categoryRepository.save(category);

        mockMvc.perform(get("/admin/categories"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/category/listCategories"))
                .andExpect(model().attribute("categories", hasSize(1)));
    }

    @Test
    @DisplayName("Should show category creation form successfully")
    @WithMockUser(roles = "MANAGER",
            username = "maria@gmail.com", password = "123456")
    void formNew__should_show_category_creation_form_successfully() throws Exception {
        mockMvc.perform(get("/admin/categories/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/category/formCategory"))
                .andExpect(model().attributeExists("categoryForm"));
    }

    @Test
    @DisplayName("Should create a category successfully")
    @WithMockUser(roles = "MANAGER",
            username = "maria@gmail.com", password = "123456")
    void save__should_create_a_category_successfully() throws Exception {
        Category category = new Category("Programação", "programacao");

        mockMvc.perform(post("/admin/categories/new")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("name", category.getName())
                        .param("codeUrl", category.getCodeUrl()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/categories"));

        assertThat(categoryRepository.findByCodeUrl(category.getCodeUrl())).isPresent();
    }

    @Test
    @DisplayName("Should return errors when creating a category with invalid data")
    @WithMockUser(roles = "MANAGER",
            username = "maria@gmail.com", password = "123456")
    void save__should_have_errors_when_data_is_invalid() throws Exception {
        mockMvc.perform(post("/admin/categories/new")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("name", "")
                        .param("codeUrl", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/category/formCategory"))
                .andExpect(model().attributeHasFieldErrors("categoryForm", "name", "codeUrl"));
    }

    @Test
    @DisplayName("Should show category update form successfully")
    @WithMockUser(roles = "MANAGER",
            username = "maria@gmail.com", password = "123456")
     void formUpdate__should_show_category_update_form_successfully() throws Exception {
        Category category = new Category("Programação", "programacao");
        categoryRepository.save(category);

        mockMvc.perform(get("/admin/categories/{codeUrl}", "programacao"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/category/formUpdateCategory"))
                .andExpect(model().attributeExists("categoryFormUpdate"));
    }

    @Test
    @DisplayName("Should update a category successfully")
    @WithMockUser(roles = "MANAGER",
            username = "maria@gmail.com", password = "123456")
    void update__should_update_a_category_successfully() throws Exception {
        Category category = new Category("Programação", "programacao");
        categoryRepository.save(category);

        mockMvc.perform(post("/admin/categories/{codeUrl}", "programacao")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("id", category.getId().toString())
                        .param("name", "Programação")
                        .param("codeUrl", "programacao-nova"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/categories"));

        assertThat(categoryRepository.findByCodeUrl("programacao")).isEmpty();
        assertThat(categoryRepository.findByCodeUrl("programacao-nova")).isPresent();
    }

    @Test
    @DisplayName("Should return errors when updating a category with invalid data")
    @WithMockUser(roles = "MANAGER",
            username = "maria@gmail.com", password = "123456")
    void update__should_have_errors_when_data_is_invalid() throws Exception {
        Category category = new Category("Programação", "programacao");
        categoryRepository.save(category);

        mockMvc.perform(post("/admin/categories/{codeUrl}", "programacao")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("id", category.getId().toString())
                        .param("name", "")
                        .param("codeUrl", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/category/formUpdateCategory"))
                .andExpect(model().attributeHasFieldErrors("categoryFormUpdate", "name", "codeUrl"));
    }

    @Test
    @DisplayName("Should return 404 when category is not found")
    @WithMockUser(roles = "MANAGER",
            username = "maria@gmail.com", password = "123456")
    void formUpdate__should_return_not_found_when_category_does_not_exist() throws Exception {
        mockMvc.perform(get("/admin/categories/{codeUrl}", "non-existent-code"))
                .andExpect(status().isNotFound());
    }
}
