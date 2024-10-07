package br.com.harbitech.school.category;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    @Test
    void should_add_new_category() {
        Category category = assertDoesNotThrow(() -> new Category("DevOps", "dev-ops"), "Erro de validação ao criar categoria.");
        assertEquals("DevOps", category.getName());
        assertEquals("dev-ops", category.getCodeUrl());
    }

    @ParameterizedTest
    @ValueSource(strings = {"programacao2", "programação", "Programacao", "dev ops", "dev_ops"})
    @NullAndEmptySource
    @DisplayName("should throw IllegalArgumentException when codeUrl is invalid")
    void should_throw_exception_when_code_url_is_invalid(String codeUrl) {
        assertThrows(IllegalArgumentException.class, () -> new Category("Programação", codeUrl));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("should throw IllegalArgumentException when name is null or blank")
    void should_throw_exception_when_name_is_invalid(String name) {
        assertThrows(IllegalArgumentException.class, () -> new Category(name, "dev-ops"));
    }

    @Test
    void should_update_category_attributes() {
        Category category = new Category("DevOps", "dev-ops");
        CategoryFormUpdate categoryFormUpdate = new CategoryFormUpdate();

        categoryFormUpdate.setName("Ux");
        categoryFormUpdate.setCodeUrl("ux");
        categoryFormUpdate.setDescription("New description");
        categoryFormUpdate.setStatus(CategoryStatus.ACTIVE);
        categoryFormUpdate.setOrderVisualization(1);
        categoryFormUpdate.setIconPath("/path/to/icon");
        categoryFormUpdate.setHtmlHexColorCode("#FFFFFF");
        categoryFormUpdate.setStudyGuide("New Study Guide");

        category.update(categoryFormUpdate);

        assertThat(category).extracting(Category::getName, Category::getCodeUrl, Category::getDescription,
                Category::getStatus, Category::getOrderVisualization, Category::getIconPath, Category::getHtmlHexColorCode,
                Category::getStudyGuide)
                .containsExactly("Ux", "ux", "New description",
                        CategoryStatus.ACTIVE, 1,
                "/path/to/icon", "#FFFFFF", "New Study Guide");
    }
}
