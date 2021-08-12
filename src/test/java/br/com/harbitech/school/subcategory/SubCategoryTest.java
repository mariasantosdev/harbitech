package br.com.harbitech.school.subcategory;

import br.com.harbitech.school.category.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SubCategoryTest {

    private Category category;

    @BeforeEach
    void setUp(){
        this.category = new Category("Programação", "programacao");
    }

    @Test
    void should_add_new_subcategory() {
        Subcategory subCategory = assertDoesNotThrow(() -> new Subcategory("Java", "java", category),
                "Erro de validação ao criar uma subcategoria");
        assertEquals("Java",subCategory.getName());
        assertEquals("java",subCategory.getCodeUrl());
    }

    @Test
    void should_validate_incorrect_code_url_because_have_accent() {
        assertThrows(IllegalArgumentException.class, () -> new Subcategory("Computação","computação"
                ,category));
    }

    @Test
    void should_validate_incorrect_code_url_because_is_upper_case() {
        assertThrows(IllegalArgumentException.class, () -> new Subcategory("Java","Java",category));
    }

    @Test
    void should_validate_incorrect_code_url_because_have_space() {
        assertThrows(IllegalArgumentException.class, () -> new Subcategory("Lógica de programação",
                "logica de programacao",category));
    }

    @Test
    void should_validate_incorrect_code_url_because_have_special_characters() {
        assertThrows(IllegalArgumentException.class, () ->  new Subcategory("C#",
                "c#",category));
    }

    @Test
    void should_validate_incorrect_name_because_is_blank() {
        assertThrows(IllegalArgumentException.class, () -> new Subcategory("","java",category));
    }

    @Test
    void should_validate_incorrect_code_url_because_is_blank() {
        assertThrows(IllegalArgumentException.class, () ->  new Subcategory("Java","",category));
    }

    @Test
    void should_validate_incorrect_name_because_is_null() {
        assertThrows(IllegalArgumentException.class, () -> new Subcategory(null,"java",category));
    }

    @Test
    void should_validate_incorrect_code_url_because_is_null() {
        assertThrows(IllegalArgumentException.class, () -> new Subcategory("Java",null,category));
    }

    @Test
    void should_validate_incorrect_subcategory_because_is_null() {
        assertThrows(IllegalArgumentException.class, () -> new Subcategory("Java","java",null));
    }

    @Test
    void should_validate_incorrect_description_enum() {
        assertThrows(IllegalArgumentException.class, () -> new Subcategory("Java", "java",
                1, "Java é " + "uma grande plataforma presente em todo lugar: de " +
                "corporações à bancos e governo.  Desenvolva aplicações robustas com um back-end e construa APIs.",
                "Desde seu primeiro hello world até conceitos mais avançados de POO",
                SubCategoryStatus.from("UM_STATUS_INVALIDO"),category));
    }

    @Test
    void should_validate_correct_description_enum() {
        assertDoesNotThrow(() -> new Subcategory("Java", "java",
                1, "Java é " + "uma grande plataforma presente em todo lugar: de " +
                "corporações à bancos e governo.  Desenvolva aplicações robustas com um back-end e construa APIs.",
                "Desde seu primeiro hello world até conceitos mais avançados de POO",
                SubCategoryStatus.from("ATIVA"), category), "Erro de validação ao criar um curso");
    }
}
