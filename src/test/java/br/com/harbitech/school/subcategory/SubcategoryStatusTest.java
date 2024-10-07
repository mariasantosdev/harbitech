package br.com.harbitech.school.subcategory;

import br.com.harbitech.school.category.Category;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SubcategoryStatusTest {
    @Test
    void should_validate_incorrect_description_enum() {
        Category category = new Category("Programação", "programacao");
        assertThrows(IllegalArgumentException.class, () -> new Subcategory("Java", "java",
                1, "Java é " + "uma grande plataforma presente em todo lugar: de " +
                "corporações à bancos e governo.  Desenvolva aplicações robustas com um back-end e construa APIs.",
                "Desde seu primeiro hello world até conceitos mais avançados de POO",
                SubCategoryStatus.from("UM_STATUS_INVALIDO"),category));
    }

    @Test
    void should_validate_correct_description_enum() {
        Category category = new Category("Programação", "programacao");
        assertDoesNotThrow(() -> new Subcategory("Java", "java",
                1, "Java é " + "uma grande plataforma presente em todo lugar: de " +
                "corporações à bancos e governo.  Desenvolva aplicações robustas com um back-end e construa APIs.",
                "Desde seu primeiro hello world até conceitos mais avançados de POO",
                SubCategoryStatus.from("ATIVA"), category), "Erro de validação ao criar um curso");
    }
}
