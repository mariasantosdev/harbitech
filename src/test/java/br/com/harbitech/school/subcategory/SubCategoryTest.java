package br.com.harbitech.school.subcategory;

import br.com.harbitech.school.category.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SubCategoryTest {

    private Category category;

    @BeforeEach
    public void setUp(){
        this.category = new Category("Programação", "programacao");
    }

    @Test
    public void shouldAddNewSubCategory() {
        Subcategory subCategory = assertDoesNotThrow(() -> new Subcategory("Java", "java", category),
                "Erro de validação ao criar uma subcategoria");
        assertEquals("Java",subCategory.getName());
        assertEquals("java",subCategory.getCodeUrl());
    }

    @Test
    public void shouldValidadeIncorrectCodeUrlBecauseHaveAccent() {
        assertThrows(IllegalArgumentException.class, () -> new Subcategory("Computação","computação"
                ,category));
    }

    @Test
    public void shouldValidadeIncorrectCodeUrlBecauseIsUpperCase() {
        assertThrows(IllegalArgumentException.class, () -> new Subcategory("Java","Java",category));
    }

    @Test
    public void shouldValidadeIncorrectCodeUrlBecauseHaveSpace() {
        assertThrows(IllegalArgumentException.class, () -> new Subcategory("Lógica de programação",
                "logica de programacao",category));
    }

    @Test
    public void shouldValidadeIncorrectCodeUrlBecauseHaveSpecialCharacters() {
        assertThrows(IllegalArgumentException.class, () ->  new Subcategory("C#",
                "c#",category));
    }

    @Test
    public void shouldValidadeIncorrectNameBecauseIsBlank() {
        assertThrows(IllegalArgumentException.class, () -> new Subcategory("","java",category));
    }

    @Test
    public void shouldValidadeIncorrectCodeUrlBecauseIsBlank() {
        assertThrows(IllegalArgumentException.class, () ->  new Subcategory("Java","",category));
    }

    @Test
    public void shouldValidadeIncorrectNameBecauseIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Subcategory(null,"java",category));
    }

    @Test
    public void shouldValidadeIncorrectCodeUrlBecauseIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Subcategory("Java",null,category));
    }

    @Test
    public void shouldValidadeIncorrectSubCategoryBecauseIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Subcategory("Java","java",null));
    }
//
//    @Test
//    public void shouldValidateIncorrectDescriptionEnum() {
//        assertThrows(IllegalArgumentException.class, () -> new Subcategory("Java", "java",
//                1, "Java é " + "uma grande plataforma presente em todo lugar: de " +
//                "corporações à bancos e governo.  Desenvolva aplicações robustas com um back-end e construa APIs.",
//                "Desde seu primeiro hello world até conceitos mais avançados de POO",
//                SubCategoryStatus.from("UM_STATUS_INVALIDO"),category));
//    }
//
//    @Test
//    public void shouldValidateCorrectDescriptionEnum() {
//        assertDoesNotThrow(() -> new Subcategory("Java", "java",
//                1, "Java é " + "uma grande plataforma presente em todo lugar: de " +
//                "corporações à bancos e governo.  Desenvolva aplicações robustas com um back-end e construa APIs.",
//                "Desde seu primeiro hello world até conceitos mais avançados de POO",
//                SubCategoryStatus.from("ATIVA"), category), "Erro de validação ao criar um curso");
//    }
}
