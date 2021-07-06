package br.com.harbitech.school.validation;

import br.com.harbitech.school.category.Category;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static br.com.harbitech.school.validation.ValidationUtil.*;

public class ValidationUtilTest {

    @Test
    public void shouldValidadeIncorrectCodeUrlBecauseHaveSpace(){
        assertThrows(IllegalArgumentException.class,() -> validateUrl("programacao ",
                "Digite uma URL sem espaço"));
    }

    @Test
    public void shouldValidadeIncorrectCodeUrlBecauseHaveAccent() {
        assertThrows(IllegalArgumentException.class, () -> validateUrl("programacão",
                "Digite uma URL sem acento"));
    }

    @Test
    public void shouldValidadeIncorrectCodeUrlBecauseIsUpperCase() {
        assertThrows(IllegalArgumentException.class, () -> validateUrl("Programacao",
                "Digite uma URL sem letras maiúsculas"));
    }

    @Test
    public void shouldValidadeIncorrectCodeUrlBecauseHaveSpecialCharacters() {
        assertThrows(IllegalArgumentException.class, () -> validateUrl("dev_ops",
                "Digite uma URL sem caracteres especiais"));
    }

    @Test
    public void shouldValidadeCorrectCodeUrl() {
        assertDoesNotThrow(() -> validateUrl("dev-ops", ""));
    }

    @Test
    public void shouldValidadeIncorrectTextBecauseIsNull() {
        assertThrows(IllegalArgumentException.class, () -> validateNonBlankText(null,
                "Digite um texto"));
    }

    @Test
    public void shouldValidadeIncorrectTextBecauseIsBlank() {
        assertThrows(IllegalArgumentException.class, () -> validateNonBlankText("",
                "Digite um texto"));
    }

    @Test
    public void shouldValidadeCorrectText() {
        assertDoesNotThrow(() -> validateNonBlankText("Dev_Ops", ""));
    }

    @Test
    public void shouldValidadeIncorrectClassBecauseDoesNotExist() {
        assertThrows(IllegalArgumentException.class, () -> validateNonNullClass(null,""));
    }

    @Test
    public void shouldValidadeExistenceOfClass() {
        Category category = new Category("Programação","programacao");
        assertDoesNotThrow(() -> validateNonNullClass(category,""));
    }

    @Test
    public void shouldValidadeIncorrectIntervalBecauseValueIsLess() {
        assertThrows(IllegalArgumentException.class, () -> validateInterval(3,4,25,
                "Passe um valor maior"));
    }

    @Test
    public void shouldValidadeIncorrectIntervalBecauseValueIsGreater() {
        assertThrows(IllegalArgumentException.class, () -> validateInterval(26,4,25,
                "Passe um valor menor"));
    }

    @Test
    public void shouldValidateCorrectIntervalBecauseValueIsMaximumValue(){
        assertDoesNotThrow(() -> validateInterval(25,4,25, "Passe um valor menor"));
    }

    @Test
    public void shouldValidateCorrectIntervalBecauseValueIsMinimumValue(){
        assertDoesNotThrow(() -> validateInterval(4,4,25, ""));
    }

    @Test
    public void shouldValidateCorrectIntervalBecauseValueIsBetweenValid() {
        assertDoesNotThrow(() -> validateInterval(10, 4, 25, ""));
    }
}
