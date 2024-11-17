package br.com.harbitech.school.course;

import br.com.harbitech.school.category.Category;
import br.com.harbitech.school.subcategory.Subcategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CourseTest {

    private Subcategory subCategory;

    @BeforeEach
    void setUp() {
        Category category = new Category("Programação", "programacao");
        this.subCategory = new Subcategory("Java", "java", category);
    }

    @Test
    void should_add_new_course() {
        Course course = assertDoesNotThrow(() -> new Course("Git e Github para Sobrevivência",
                        "git-e-github-para-sobrevivencia", 3, "Nico", subCategory),
                "Erro de validação ao criar um curso");

        assertThat(course).isNotNull().extracting(Course::getName, Course::getCodeUrl, Course::getCompletionTimeInHours,
                Course::getInstructor).containsExactly("Git e Github para Sobrevivência",
                "git-e-github-para-sobrevivencia", 3, "Nico");
    }

    @ParameterizedTest
    @ValueSource(strings = {"git-e-github-para-sobrevivência, Git-e-github-para-sobrevivencia," +
            "git e github para sobrevivencia", "git_e_github_para_sobrevivencia"})
    @NullAndEmptySource
    @DisplayName("should throw IllegalArgumentException when codeUrl is invalid")
    void should_throw_exception_when_code_url_is_invalid(String codeUrl) {
        assertThrows(IllegalArgumentException.class, () -> new Course("Git e Github para Sobrevivência",
                codeUrl, 3, "Nico", subCategory));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("should throw IllegalArgumentException when instructorName is null or blank")
    void should_throw_exception_when_instructor_name_is_invalid(String instructorName) {
        assertThrows(IllegalArgumentException.class, () -> new Course("Git e Github para Sobrevivência",
                "git-e-github-para-sobrevivencia", 3, instructorName, subCategory));
    }

    @Test
    void should_throw_exception_when_subcategory_is_null() {
        assertThrows(IllegalArgumentException.class, () -> new Course("Git e Github para Sobrevivência",
                "git-e-github-para-sobrevivencia", 3,
                "Paulo Silveira", null));
    }

    @Test
    void should_validate_correct_completion_time_in_hours_when_is_between_values_valid() {
        assertDoesNotThrow(() -> new Course("Git e Github para Sobrevivência",
                        "git-e-github-para-sobrevivencia", 5, "Nico", subCategory),
                "Erro de validação ao criar um curso");
    }

    @Test
    void should_validate_incorrect_completion_time_in_hours_when_is_greater() {
        assertThrows(IllegalArgumentException.class, () -> new Course("Git e Github para Sobrevivência",
                "git-e-github-para-sobrevivencia", 21,
                "Paulo Silveira", subCategory));
    }

    @Test
    void should_validate_correct_completion_time_in_hours_when_is_exactly_value() {
        assertDoesNotThrow(() -> new Course("Git e Github para Sobrevivência",
                "git-e-github-para-sobrevivencia", 20,
                "Paulo Silveira", subCategory), "Erro de validação ao criar um curso");
    }

    @Test
    void should_validate_correct_completion_time_in_hours_when_is_limit_value() {
        assertDoesNotThrow(() -> new Course("Git e Github para Sobrevivência",
                "git-e-github-para-sobrevivencia", 1,
                "Paulo Silveira", subCategory), "Erro de validação ao criar um curso");
    }

    @Test
    void should_validate_incorrect_completion_time_in_hours_when_is_less() {
        assertThrows(IllegalArgumentException.class, () -> new Course("Git e Github para Sobrevivência",
                "git-e-github-para-sobrevivencia", -1,
                "Paulo Silveira", subCategory));
    }

    @Test
    void should_update_course_attributes() {
        Course course = new Course("Git e Github para Sobrevivência",
                "git-e-github-para-sobrevivencia", 3, "Nico", subCategory);
        CourseFormUpdate courseFormUpdate = new CourseFormUpdate();

        courseFormUpdate.setName("New Course Name");
        courseFormUpdate.setCodeUrl("new-code-url");
        courseFormUpdate.setDescription("Updated course description");
        courseFormUpdate.setCompletionTimeInHours(5);
        courseFormUpdate.setVisibility(CourseVisibility.PUBLIC);
        courseFormUpdate.setTargetAudience("Developers");
        courseFormUpdate.setInstructor("Updated Instructor");
        courseFormUpdate.setDevelopedSkills("Updated skills");
        courseFormUpdate.setSubcategory(subCategory);

        course.update(courseFormUpdate);

        assertThat(course).extracting(Course::getName, Course::getCodeUrl, Course::getDescription,
                        Course::getCompletionTimeInHours, Course::getVisibilityDescription, Course::getTargetAudience,
                        Course::getInstructor, Course::getDevelopedSkills, Course::getSubcategory)
                .containsExactly("New Course Name", "new-code-url", "Updated course description",
                        5, "PÚBLICA", "Developers", "Updated Instructor", "Updated skills", subCategory);
    }

    @Test
    @DisplayName("should give 50 points for public course")
    void should_give_higher_score_for_public_course() {
        Subcategory subcategory = new Subcategory("Java", "java-basico", new Category("Programação",
                "programacao"));
        Course course = new Course("Java Basics", "java-basico", 10, "John Doe", subcategory);
        course.setVisibility(CourseVisibility.PUBLIC);

        int score = course.calculatePopularityScore();

        assertEquals(50, score);
    }

    @Test
    @DisplayName("should not give 50 points for public course")
    void should_not_give_higher_score_for_public_course() {
        Subcategory subcategory = new Subcategory("Java", "java-basico", new Category("Programação",
                "programacao"));
        Course course = new Course("Java Basics", "java-basico", 10, "John Doe", subcategory);
        course.setVisibility(CourseVisibility.PRIVATE);

        int score = course.calculatePopularityScore();

        assertEquals(10, score);
    }

    @ParameterizedTest
    @DisplayName("should give a point for each completionTimeInHours for courses")
    @CsvSource({"1, 1", "2, 2", "3, 3", "4, 4", "5, 5", "6, 6", "7, 7", "8, 8", "9, 9", "10, 10", "11, 11",
            "12, 12", "13, 13", "14, 14", "15, 15", "16, 16", "17, 17", "18, 18", "19, 19", "20, 20"})
    void should_give_a_point_for_each_completion_time_in_hours_for_courses(int completionTimeInHours, int expectedScore) {
        Subcategory subcategory = new Subcategory("Java", "java-basico", new Category("Programação",
                "programacao"));
        Course course = new Course("Java Basics", "java-basico", completionTimeInHours, "John Doe",
                subcategory);
        course.setVisibility(CourseVisibility.PRIVATE);

        int score = course.calculatePopularityScore();

        assertEquals(expectedScore, score);
    }

    @Test
    void should_verify_if_study_guide_length_is_greater_than_200() {
        Subcategory subcategory = new Subcategory("Java", "java-basico", new Category("Programação",
                "programacao"));
        subcategory.setStudyGuide("""
        Este é o guia de estudos para o curso de Java Básico. 
        Certifique-se de revisar os seguintes tópicos antes de começar:
        1. Introdução à Programação Orientada a Objetos.
        2. Sintaxe básica da linguagem Java.
        3. Classes, Objetos, e Métodos.
        4. Controle de Fluxo e Estruturas Condicionais.
        5. Estruturas de Dados como Arrays e Listas.
        6. Práticas recomendadas no desenvolvimento com Java.
        """);
        Course course = new Course("Java Basics", "java-basico", 1, "John Doe", subcategory);
        course.setVisibility(CourseVisibility.PRIVATE);

        int score = course.calculatePopularityScore();

        assertEquals(16, score);
    }
}
