package br.com.harbitech.school.subcategory;

import br.com.harbitech.school.category.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SubCategoryTest {

    private Category category;

    @BeforeEach
    void setUp() {
        this.category = new Category("Programação", "programacao");
    }

    @Test
    void should_add_new_subcategory() {
        Subcategory subCategory = assertDoesNotThrow(() -> new Subcategory("Java", "java", category),
                "Erro de validação ao criar uma subcategoria");
        assertThat(subCategory).isNotNull().extracting(Subcategory::getName, Subcategory::getCodeUrl)
                .containsExactly("Java", "java");
    }

    @ParameterizedTest
    @ValueSource(strings = {"computação", "Java", "Lógica de programação", "C#"})
    @NullAndEmptySource
    @DisplayName("should throw IllegalArgumentException when codeUrl is invalid")
    void should_throw_exception_when_code_url_is_invalid(String codeUrl) {
        assertThrows(IllegalArgumentException.class, () -> new Subcategory("Java", codeUrl, category),
                "Erro de validação ao criar uma subcategoria");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("should throw IllegalArgumentException when name is null or blank")
    void should_throw_exception_when_name_is_invalid(String name) {
        assertThrows(IllegalArgumentException.class, () -> new Subcategory(name, "java", category),
                "Erro de validação ao criar uma subcategoria");
    }

    @Test
    void should_validate_incorrect_subcategory_because_is_null() {
        assertThrows(IllegalArgumentException.class, () -> new Subcategory("Java", "java", null));
    }

    @Test
    void should_update_subcategory_attributes() {
        Subcategory subCategory = new Subcategory("Java", "java", category);

        SubcategoryFormUpdate subcategoryFormUpdate = new SubcategoryFormUpdate();
        subcategoryFormUpdate.setName("Updated Name");
        subcategoryFormUpdate.setCodeUrl("updated-code-url");
        subcategoryFormUpdate.setDescription("Updated description");
        subcategoryFormUpdate.setStatus(SubCategoryStatus.ACTIVE);
        subcategoryFormUpdate.setOrderVisualization(10);
        subcategoryFormUpdate.setStudyGuide("Updated study guide");
        subcategoryFormUpdate.setCategory(category);

        subCategory.update(subcategoryFormUpdate);

        assertThat(subCategory).extracting(Subcategory::getName, Subcategory::getCodeUrl, Subcategory::getDescription,
                        Subcategory::getStatus, Subcategory::getOrderVisualization,
                        Subcategory::getStudyGuide, Subcategory::getCategory)
                .containsExactly("Updated Name", "updated-code-url",
                        "Updated description", SubCategoryStatus.ACTIVE, 10, "Updated study guide", category);
    }

    @Test
    void should_return_other_order_visualization_when_its_less_than_this() {
        Subcategory thisSubcategory = new Subcategory("Java", "java", category);
        thisSubcategory.setOrderVisualization(5);

        Subcategory otherSubcategory = new Subcategory("Python", "python", category);
        otherSubcategory.setOrderVisualization(3);

        int result = thisSubcategory.compareTo(otherSubcategory);

        assertEquals(3, result, "Deveria retornar o orderVisualization da outra subcategoria quando é menor");
    }

    @Test
    void should_return_this_order_visualization_when_other_is_greater() {
        Subcategory thisSubcategory = new Subcategory("Java", "java", category);
        thisSubcategory.setOrderVisualization(5);

        Subcategory otherSubcategory = new Subcategory("Python", "python", category);
        otherSubcategory.setOrderVisualization(7);

        int result = thisSubcategory.compareTo(otherSubcategory);

        assertEquals(5, result, "Deveria retornar o orderVisualization desta subcategoria quando a outra é maior");
    }

    @Test
    void should_return_this_order_visualization_when_both_are_equal() {
        Subcategory thisSubcategory = new Subcategory("Java", "java", category);
        thisSubcategory.setOrderVisualization(5);

        Subcategory otherSubcategory = new Subcategory("Python", "python", category);
        otherSubcategory.setOrderVisualization(5);

        int result = thisSubcategory.compareTo(otherSubcategory);

        assertEquals(5, result, "Deveria retornar o orderVisualization quando ambos são iguais");
    }

}
