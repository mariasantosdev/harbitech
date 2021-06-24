package br.com.harbitech.school.course;

import br.com.harbitech.school.category.Category;
import br.com.harbitech.school.category.CategoryStatus;
import br.com.harbitech.school.subcategory.SubCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CourseTest {

    private SubCategory subCategory;

    @BeforeEach
    public void addNewSubCategory() {
        Category category = new Category("Programação", "programacao");
        this.subCategory = new SubCategory("Java", "java", category);
    }

    @Test
    public void shouldAddNewCourse() {
        assertDoesNotThrow(() -> new Course("Git e Github para Sobrevivência",
                "git-e-github-para-sobrevivencia", 3, "Nico", subCategory),
                "Erro de validação ao criar um curso");
    }

    @Test
    public void shouldValidadeIncorrectCodeUrlBecauseHaveAccent() {
        assertThrows(IllegalArgumentException.class, () -> new Course("Git e Github para Sobrevivência",
                "git-e-github-para-sobrevivência", 3, "Nico", subCategory));
    }

    @Test
    public void shouldValidadeIncorrectCodeUrlBecauseIsUpperCase() {
        assertThrows(IllegalArgumentException.class, () -> new Course("Git e Github para Sobrevivência",
                "Git-e-github-para-sobrevivencia", 3, "Nico", subCategory));
    }

    @Test
    public void shouldValidadeIncorrectCodeUrlBecauseHaveSpace() {
        assertThrows(IllegalArgumentException.class, () -> new Course("Git e Github para Sobrevivência",
                "git e github para sobrevivencia", 3, "Nico", subCategory));
    }

    @Test
    public void shouldValidadeIncorrectCodeUrlBecauseHaveSpecialCharacters() {
        assertThrows(IllegalArgumentException.class, () -> new Course("Git e Github para Sobrevivência",
                "git_e_github_para_sobrevivencia", 3, "Nico", subCategory));
    }

    @Test
    public void shouldValidadeIncorrectNameBecauseIsBlank() {
        assertThrows(IllegalArgumentException.class, () -> new Course("",
                "git-e-github-para-sobrevivencia", 3, "Nico", subCategory));
    }

    @Test
    public void shouldValidadeIncorrectCodeUrlBecauseIsBlank() {
        assertThrows(IllegalArgumentException.class, () -> new Course("Git e Github para Sobrevivência",
                "", 3, "Nico", subCategory));
    }

    @Test
    public void shouldValidadeIncorrectInstructorBecauseIsBlank() {
        assertThrows(IllegalArgumentException.class, () -> new Course("Git e Github para Sobrevivência",
                "git-e-github-para-sobrevivencia", 3, "", subCategory));
    }

    @Test
    public void shouldValidadeIncorrectNameBecauseIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Course(null,
                "git-e-github-para-sobrevivencia", 3, "Nico", subCategory));
    }

    @Test
    public void shouldValidadeIncorrectCodeUrlBecauseIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Course("Git e Github para Sobrevivência",
                null, 3, "Nico", subCategory));
    }

    @Test
    public void shouldValidadeIncorrectInstructorBecauseIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Course("Git e Github para Sobrevivência",
                "git-e-github-para-sobrevivencia", 3, null, subCategory));
    }

    @Test
    public void shouldValidadeIncorrectSubCategoryBecauseIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Course("Git e Github para Sobrevivência",
                "git-e-github-para-sobrevivencia", 3,
                "Paulo Silveira", null));
    }

    @Test
    public void shouldValidateIncorrectDescriptionEnum() {
        assertThrows(IllegalArgumentException.class, () -> new Course("Git e Github para Sobrevivência",
                "git-e-github-para-sobrevivencia", 3, CourseVisibility.from("UMA_VISIBILIDADE_INVALIDA"),
                "Desenvolvedores em qualquer linguagem ou plataforma que desejam mais segurança para " +
                        "seus projetos " + "com as ferramentas de controle de versão Git e GitHub.",
                "Nico", "Desenvolvedores em qualquer linguagem ou plataforma devem aprender " +
                "git e github pois são ferramentas muito cobradas no mercado", "Entenda como funciona o git e conheça " +
                "comandos essenciais para se trabalhar em equipe.", subCategory));
    }

    @Test
    public void shouldValidateCorrectDescriptionEnum() {
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
    public void shouldValidateCorrectCompletionTimeInHoursBecauseIsBetweenValuesValid(){
        assertDoesNotThrow(() -> new Course("Git e Github para Sobrevivência",
                "git-e-github-para-sobrevivencia", 5, "Nico", subCategory),
                "Erro de validação ao criar um curso");
    }

    @Test
    public void shouldValidateIncorrectCompletionTimeInHoursBecauseIsGreater(){
        assertThrows(IllegalArgumentException.class, () -> new Course("Git e Github para Sobrevivência",
                "git-e-github-para-sobrevivencia", 21,
                "Paulo Silveira", subCategory));
    }

    @Test
    public void shouldValidateCorrectCompletionTimeInHoursBecauseIsExactlyValue(){
        assertDoesNotThrow(() -> new Course("Git e Github para Sobrevivência",
                "git-e-github-para-sobrevivencia", 20,
                "Paulo Silveira", subCategory),"Erro de validação ao criar um curso");
    }

    @Test
    public void shouldValidateCorrectCompletionTimeInHoursBecauseIsLimitValue(){
        assertDoesNotThrow(() -> new Course("Git e Github para Sobrevivência",
                "git-e-github-para-sobrevivencia", 1,
                "Paulo Silveira", subCategory),"Erro de validação ao criar um curso");
    }

    @Test
    public void shouldValidateIncorrectCompletionTimeInHoursBecauseIsLess(){
        assertThrows(IllegalArgumentException.class, () ->  new Course("Git e Github para Sobrevivência",
                "git-e-github-para-sobrevivencia", -1,
                "Paulo Silveira", subCategory));
    }
}
