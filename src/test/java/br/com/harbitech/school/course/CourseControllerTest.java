package br.com.harbitech.school.course;

import br.com.harbitech.school.category.Category;
import br.com.harbitech.school.category.CategoryRepository;
import br.com.harbitech.school.subcategory.Subcategory;
import br.com.harbitech.school.subcategory.SubcategoryRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringBootTest
class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    @Autowired
    private CourseRepository courseRepository;

    @AfterEach
    public void tearDown() {
        courseRepository.deleteAll();
        subcategoryRepository.deleteAll();
        categoryRepository.deleteAll();
    }

    @Test
    @DisplayName("Should show list of courses successfully")
    @WithMockUser(roles = "MANAGER", username = "maria@gmail.com", password = "123456")
    void list__should_show_list_of_courses_successfully() throws Exception {
        Category category = new Category("Programação", "programacao");
        Subcategory subcategory = new Subcategory("Java", "java", category);
        categoryRepository.save(category);
        subcategoryRepository.save(subcategory);

        Course course = new Course("Java Basics", "java-basics",10, "Anne", subcategory);
        courseRepository.save(course);

        mockMvc.perform(get("/admin/courses/{category}/{subcategoryCodeUrl}", category.getCodeUrl(), subcategory.getCodeUrl()))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/course/listCourses"))
                .andExpect(model().attribute("courses", hasSize(1)));
    }

    @Test
    @DisplayName("Should show course creation form successfully")
    @WithMockUser(roles = "MANAGER", username = "maria@gmail.com", password = "123456")
    void formNew__should_show_course_creation_form_successfully() throws Exception {
        mockMvc.perform(get("/admin/courses/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/course/formCourse"))
                .andExpect(model().attributeExists("courseForm"))
                .andExpect(model().attributeExists("subcategories"))
                .andExpect(model().attribute("formAction", "/admin/courses"));
    }

    @Test
    @DisplayName("Should have errors when data is invalid")
    @WithMockUser(roles = "MANAGER", username = "maria@gmail.com", password = "123456")
    void save__should_have_errors_when_data_is_invalid() throws Exception {
        Category category = new Category("Programação", "programacao");
        Subcategory subcategory = new Subcategory("Java", "java", category);
        categoryRepository.save(category);
        subcategoryRepository.save(subcategory);

        mockMvc.perform(post("/admin/courses")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("name", "Java Basics")
                        .param("codeUrl", "java-basics")
                        .param("instructor", "")
                        .param("completionTimeInHours", "80")
                        .param("subcategoryCodeUrl", subcategory.getCodeUrl())
                        .param("categoryCodeUrl", category.getCodeUrl()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("admin/course/formCourse"))
                .andExpect(model().attributeHasFieldErrors("courseForm", "instructor", "completionTimeInHours"));

    }

    //TODO ARRUME-ME
//    @Test
//    @DisplayName("Should create a course successfully")
//    @WithMockUser(roles = "MANAGER", username = "maria@gmail.com", password = "123456")
//    void save__should_create_a_course_successfully() throws Exception {
//        Category category = new Category("Programação", "programacao");
//        Subcategory subcategory = new Subcategory("Java", "java", category);
//        categoryRepository.save(category);
//        subcategoryRepository.save(subcategory);
//
//        mockMvc.perform(post("/admin/courses")
//                        .with(csrf())
//                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                        .param("name", "Java Basics")
//                        .param("codeUrl", "java-basics")
//                        .param("instructor", "Anne")
//                        .param("completionTimeInHours", "18")
//                        .param("subcategory", subcategory.toString())
//                        .param("subcategoryCodeUrl", subcategory.getCodeUrl())
//                        .param("categoryCodeUrl", category.getCodeUrl()))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/admin/courses/programacao/java"));
//
//        assertThat(courseRepository.findByCodeUrl("java-basics")).isPresent();
//    }

    @Test
    @DisplayName("Should show course update form successfully")
    @WithMockUser(roles = "MANAGER", username = "maria@gmail.com", password = "123456")
    void formUpdate__should_show_course_update_form_successfully() throws Exception {
        Category category = new Category("Programação", "programacao");
        Subcategory subcategory = new Subcategory("Java", "java", category);
        categoryRepository.save(category);
        subcategoryRepository.save(subcategory);

        Course course = new Course("Java Basics", "java-basics", 18,
                "Anne",subcategory);
        courseRepository.save(course);

        mockMvc.perform(get("/admin/courses/{category}/{subcategory}/{course}", category.getCodeUrl(),
                        subcategory.getCodeUrl(), course.getCodeUrl()))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/course/formCourseUpdate"))
                .andExpect(model().attributeExists("courseFormUpdate"))
                .andExpect(model().attributeExists("subcategories"))
                .andExpect(model().attribute("formAction", "/admin/courses/programacao/java/java-basics"));
    }

    @Test
    @DisplayName("Should return 404 when course is not found")
    @WithMockUser(roles = "MANAGER", username = "maria@gmail.com", password = "123456")
    void formUpdate__should_return_not_found_when_course_does_not_exist() throws Exception {
        mockMvc.perform(get("/admin/courses/{category}/{subcategory}/{course}", "programacao", "java", "non-existent-course"))
                .andExpect(status().isNotFound());
    }

//    @Test
//    @DisplayName("Should update a course successfully")
//    @WithMockUser(roles = "MANAGER", username = "maria@gmail.com", password = "123456")
//    void update__should_update_a_course_successfully() throws Exception {
//        Category category = new Category("Programação", "programacao");
//        Subcategory subcategory = new Subcategory("Java", "java", category);
//        categoryRepository.save(category);
//        subcategoryRepository.save(subcategory);
//
//        Course course = new Course("Java Basics", "java-basics", 15,
//                "Anne",subcategory);
//        courseRepository.save(course);
//
//        mockMvc.perform(post("/admin/courses/{category}/{subcategory}/{course}", category.getCodeUrl(),
//                        subcategory.getCodeUrl(), course.getCodeUrl())
//                        .with(csrf())
//                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                        .param("name", "Java Basics Updated"))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/admin/courses/programacao/java"));
//
//        Course updatedCourse = courseRepository.findByCodeUrl("java-basics").orElseThrow();
//        assertThat(updatedCourse.getName()).isEqualTo("Java Basics Updated");
//    }

//    @Test
//    @DisplayName("Should show errors when updating course with invalid data")
//    @WithMockUser(roles = "MANAGER", username = "maria@gmail.com", password = "123456")
//    void update__should_have_errors_when_data_is_invalid() throws Exception {
//        Category category = new Category("Programação", "programacao");
//        Subcategory subcategory = new Subcategory("Java", "java", category);
//        categoryRepository.save(category);
//        subcategoryRepository.save(subcategory);
//
//        Course course = new Course("Java Basics", "java-basics", 15, "Anne",
//                subcategory);
//        courseRepository.save(course);
//
//        mockMvc.perform(post("/admin/courses/{category}/{subcategory}/{course}", category.getCodeUrl(), subcategory.getCodeUrl(), course.getCodeUrl())
//                        .with(csrf())
//                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                        .param("name", ""))
//                .andExpect(status().isOk())
//                .andExpect(view().name("admin/course/formCourseUpdate"))
//                .andExpect(model().attributeHasFieldErrors("courseFormUpdate", "name"));
//    }
}
