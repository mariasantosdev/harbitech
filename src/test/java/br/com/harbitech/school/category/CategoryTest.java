package br.com.harbitech.school.category;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class CategoryTest {

    @Test
    public void shouldAddNewCategory(){
        Category category = assertDoesNotThrow(() -> new Category("DevOps", "dev-ops"),"Erro de validação ao criar categoria.");
        assertEquals("DevOps",category.getName());
        assertEquals("dev-ops",category.getCodeUrl());
    }

    @Test
    public void shouldValidadeIncorrectCodeUrlBecauseHaveNumber(){
        assertThrows(IllegalArgumentException.class,() -> new Category("Programação", "programacao2"));
    }

    @Test
    public void shouldValidadeIncorrectCodeUrlBecauseHaveAccent(){
        assertThrows(IllegalArgumentException.class,() -> new Category("Programação", "programação"));
    }

    @Test
    public void shouldValidadeIncorrectCodeUrlBecauseIsUpperCase(){
        assertThrows(IllegalArgumentException.class,() -> new Category("Programação", "Programacao"));
    }

    @Test
    public void shouldValidadeIncorrectCodeUrlBecauseHaveSpace(){
        assertThrows(IllegalArgumentException.class,() -> new Category("DevOps", "dev ops"));
    }

    @Test
    public void shouldValidadeIncorrectCodeUrlBecauseHaveSpecialCharacter(){
        assertThrows(IllegalArgumentException.class,() -> new Category("DevOps", "dev_ops"));
    }

    @Test
    public void shouldValidadeIncorrectCodeUrlBecauseIsBlank(){
        assertThrows(IllegalArgumentException.class,() -> new Category("DevOps", ""));
    }

    @Test
    public void shouldValidadeIncorrectNameBecauseIsBlank(){
        assertThrows(IllegalArgumentException.class,() -> new Category("", "dev-ops"));
    }

    @Test
    public void shouldValidadeIncorrectNameBecauseIsNull(){
        assertThrows(IllegalArgumentException.class,() -> new Category(null, "dev-ops"));
    }

//    @Test
//    public void shouldValidadeIncorrectCodeUrlBecauseIsNull(){
//        assertThrows(IllegalArgumentException.class,() -> new Category("Dev Ops", null));
//    }
//    @Test
//    public void shouldValidateIncorrectDescriptionEnum(){
//        assertThrows(IllegalArgumentException.class,() -> new Category("Programação", "programacao",
//                "Programe nas principais linguagens de programação",
//                CategoryStatus.from("UMA_CATEGORIA_INVALIDA"), 1,
//                "https://www.alura.com.br/assets/api/formacoes/categorias/512/programacao-transparent.png",
//                "#00c86f"));
//    }
//
//    @Test
//    public void shouldValidateCorrectDescriptionEnum() {
//       assertDoesNotThrow(()-> (new Category("Programação", "programacao",
//                "Programe nas principais linguagens de programação",
//                CategoryStatus.from("INATIVA"), 1,
//                "https://www.alura.com.br/assets/api/formacoes/categorias/512/programacao-transparent.png",
//                "#00c86f")),"Erro de validação ao criar categoria.");
//    }
}
