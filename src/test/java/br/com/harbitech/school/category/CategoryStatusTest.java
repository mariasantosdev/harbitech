package br.com.harbitech.school.category;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CategoryStatusTest {
    @Test
    @DisplayName("should throw IllegalArgumentException when is an invalid category description")
    void should_throw_exception_when_is_an_invalid_category_description() {
        assertThrows(IllegalArgumentException.class, () -> CategoryStatus.from("AN_INVALID_CATEGORY"));
    }

    @Test
    @DisplayName("should not throw IllegalArgumentException when is a valid category description")
    void should_not_throw_exception_is_a_valid_category_description() {
        assertDoesNotThrow(() -> CategoryStatus.from("INATIVA"));
    }
}
