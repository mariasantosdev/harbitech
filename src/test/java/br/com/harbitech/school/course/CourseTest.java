package br.com.harbitech.school.course;

import br.com.harbitech.school.category.Category;
import br.com.harbitech.school.subcategory.Subcategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CourseTest {

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
        assertEquals("Git e Github para Sobrevivência",course.getName());
        assertEquals("git-e-github-para-sobrevivencia",course.getCodeUrl());
        assertEquals(3,course.getCompletionTimeInHours());
        assertEquals("Nico",course.getInstructor());
    }

    @Test
    void should_validate_incorrect_code_url_because_have_accent() {
        assertThrows(IllegalArgumentException.class, () -> new Course("Git e Github para Sobrevivência",
                "git-e-github-para-sobrevivência", 3, "Nico", subCategory));
    }

    @Test
    void should_validate_incorrect_code_url_because_is_upper_case() {
        assertThrows(IllegalArgumentException.class, () -> new Course("Git e Github para Sobrevivência",
                "Git-e-github-para-sobrevivencia", 3, "Nico", subCategory));
    }

    @Test
    void should_validate_incorrect_code_url_because_have_space() {
        assertThrows(IllegalArgumentException.class, () -> new Course("Git e Github para Sobrevivência",
                "git e github para sobrevivencia", 3, "Nico", subCategory));
    }

    @Test
    void should_validate_incorrect_code_url_because_have_special_characters() {
        assertThrows(IllegalArgumentException.class, () -> new Course("Git e Github para Sobrevivência",
                "git_e_github_para_sobrevivencia", 3, "Nico", subCategory));
    }

    @Test
    void should_validate_incorrect_name_because_is_blank() {
        assertThrows(IllegalArgumentException.class, () -> new Course("",
                "git-e-github-para-sobrevivencia", 3, "Nico", subCategory));
    }

    @Test
    void should_validate_incorrect_code_url_because_is_blank() {
        assertThrows(IllegalArgumentException.class, () -> new Course("Git e Github para Sobrevivência",
                "", 3, "Nico", subCategory));
    }

    @Test
    void should_validate_incorrect_instructor_because_is_blank() {
        assertThrows(IllegalArgumentException.class, () -> new Course("Git e Github para Sobrevivência",
                "git-e-github-para-sobrevivencia", 3, "", subCategory));
    }

    @Test
    void should_validate_incorrect_name_because_is_null() {
        assertThrows(IllegalArgumentException.class, () -> new Course(null,
                "git-e-github-para-sobrevivencia", 3, "Nico", subCategory));
    }

    @Test
    void should_validate_incorrect_code_url_because_is_null() {
        assertThrows(IllegalArgumentException.class, () -> new Course("Git e Github para Sobrevivência",
                null, 3, "Nico", subCategory));
    }

    @Test
    void should_validate_incorrect_instructor_because_is_null() {
        assertThrows(IllegalArgumentException.class, () -> new Course("Git e Github para Sobrevivência",
                "git-e-github-para-sobrevivencia", 3, null, subCategory));
    }

    @Test
    void should_validate_incorrect_subcategory_because_is_null() {
        assertThrows(IllegalArgumentException.class, () -> new Course("Git e Github para Sobrevivência",
                "git-e-github-para-sobrevivencia", 3,
                "Paulo Silveira", null));
    }

    @Test
    void should_validate_incorrect_description_enum() {
        assertThrows(IllegalArgumentException.class, () -> new Course("Git e Github para Sobrevivência",
                "git-e-github-para-sobrevivencia", 3, CourseVisibility.from("UMA_VISIBILIDADE_INVALIDA"),
                "Desenvolvedores em qualquer linguagem ou plataforma que desejam mais segurança para " +
                        "seus projetos " + "com as ferramentas de controle de versão Git e GitHub.",
                "Nico", "Desenvolvedores em qualquer linguagem ou plataforma devem aprender " +
                "git e github pois são ferramentas muito cobradas no mercado", "Entenda como funciona o git e conheça " +
                "comandos essenciais para se trabalhar em equipe.", subCategory));
    }

    @Test
    void should_validate_correct_description_enum() {
        assertDoesNotThrow (()-> (new Course("Git e Github para Sobrevivência",
                "git-e-github-para-sobrevivencia", 3, CourseVisibility.from("PÚBLICA"),
                "Desenvolvedores em qualquer linguagem ou plataforma que desejam mais segurança para " +
                        "seus projetos " + "com as ferramentas de controle de versão Git e GitHub.",
                "Nico", "Desenvolvedores em qualquer linguagem ou plataforma devem aprender " +
                "git e github pois são ferramentas muito cobradas no mercado", "Entenda como funciona o" +
                " git e conheça comandos essenciais para se trabalhar em equipe.", subCategory)),
                "Erro de validação ao criar um curso");
    }

    @Test
    void should_validate_correct_completion_time_in_hours_because_is_between_values_valid(){
        assertDoesNotThrow(() -> new Course("Git e Github para Sobrevivência",
                "git-e-github-para-sobrevivencia", 5, "Nico", subCategory),
                "Erro de validação ao criar um curso");
    }

    @Test
    void should_validate_incorrect_completion_time_in_hours_because_is_greater(){
        assertThrows(IllegalArgumentException.class, () -> new Course("Git e Github para Sobrevivência",
                "git-e-github-para-sobrevivencia", 21,
                "Paulo Silveira", subCategory));
    }

    @Test
    void should_validate_correct_completion_time_in_hours_because_is_exactly_value(){
        assertDoesNotThrow(() -> new Course("Git e Github para Sobrevivência",
                "git-e-github-para-sobrevivencia", 20,
                "Paulo Silveira", subCategory),"Erro de validação ao criar um curso");
    }

    @Test
    void should_validate_correct_completion_time_in_hours_because_is_limit_value(){
        assertDoesNotThrow(() -> new Course("Git e Github para Sobrevivência",
                "git-e-github-para-sobrevivencia", 1,
                "Paulo Silveira", subCategory),"Erro de validação ao criar um curso");
    }

    @Test
    void should_validate_incorrect_completion_time_in_hours_because_is_less(){
        assertThrows(IllegalArgumentException.class, () ->  new Course("Git e Github para Sobrevivência",
                "git-e-github-para-sobrevivencia", -1,
                "Paulo Silveira", subCategory));
    }
}
