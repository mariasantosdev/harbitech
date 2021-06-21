package br.com.harbitech.school.category;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CategoryTest {

    private Category category;

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
    public void shouldValidadeIncorrectCodeUrlBecauseHaveSpecialCharacters(){
        assertThrows(IllegalArgumentException.class,() -> new Category("DevOps", "dev_ops"));
    }

    @Test
    public void shouldValidadeIncorrectCodeUrlBecauseHaveSpecialCharacters2(){
        assertThrows(IllegalArgumentException.class,() -> new Category("DevOps", "dev_ops"));
    }

}
