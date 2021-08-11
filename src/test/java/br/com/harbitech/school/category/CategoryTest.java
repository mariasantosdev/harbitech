package br.com.harbitech.school.category;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class CategoryTest {

    @Test
    void shouldAddNewCategory(){
        Category category = assertDoesNotThrow(() -> new Category("DevOps", "dev-ops"),"Erro de validação ao criar categoria.");
        assertEquals("DevOps",category.getName());
        assertEquals("dev-ops",category.getCodeUrl());
    }

    @Test
    void shouldValidadeIncorrectCodeUrlBecauseHaveNumber(){
        assertThrows(IllegalArgumentException.class,() -> new Category("Programação", "programacao2"));
    }

    @Test
    void shouldValidadeIncorrectCodeUrlBecauseHaveAccent(){
        assertThrows(IllegalArgumentException.class,() -> new Category("Programação", "programação"));
    }

    @Test
    void shouldValidadeIncorrectCodeUrlBecauseIsUpperCase(){
        assertThrows(IllegalArgumentException.class,() -> new Category("Programação", "Programacao"));
    }

    @Test
    void shouldValidadeIncorrectCodeUrlBecauseHaveSpace(){
        assertThrows(IllegalArgumentException.class,() -> new Category("DevOps", "dev ops"));
    }

    @Test
    void shouldValidadeIncorrectCodeUrlBecauseHaveSpecialCharacter(){
        assertThrows(IllegalArgumentException.class,() -> new Category("DevOps", "dev_ops"));
    }

    @Test
    void shouldValidadeIncorrectCodeUrlBecauseIsBlank(){
        assertThrows(IllegalArgumentException.class,() -> new Category("DevOps", ""));
    }

    @Test
    void shouldValidadeIncorrectNameBecauseIsBlank(){
        assertThrows(IllegalArgumentException.class,() -> new Category("", "dev-ops"));
    }

    @Test
    void shouldValidadeIncorrectNameBecauseIsNull(){
        assertThrows(IllegalArgumentException.class,() -> new Category(null, "dev-ops"));
    }

    @Test
    void shouldValidadeIncorrectCodeUrlBecauseIsNull(){
        assertThrows(IllegalArgumentException.class,() -> new Category("Dev Ops", null));
    }
    @Test
    void shouldValidateIncorrectDescriptionEnum(){
        assertThrows(IllegalArgumentException.class,() -> new Category("Programação", "programacao",
                CategoryStatus.from("UMA_CATEGORIA_INVALIDA"),1,
                "https://www.alura.com.br/assets/api/formacoes/categorias/512/programacao-transparent.png",
                "#00c86f","Aprenda as principais linguagens", "Programe nas principais linguagens de programação"));
    }

    @Test
    void shouldValidateCorrectDescriptionEnum() {
       assertDoesNotThrow(()-> new Category("Programação", "programacao",
               CategoryStatus.from("INATIVA"),1,
               "https://www.alura.com.br/assets/api/formacoes/categorias/512/programacao-transparent.png",
               "#00c86f","Aprenda as principais linguagens", "Programe nas principais linguagens de programação"));
    }
}
