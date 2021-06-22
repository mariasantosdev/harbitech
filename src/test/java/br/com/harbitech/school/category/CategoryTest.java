package br.com.harbitech.school.category;

import br.com.harbitech.school.filemanipulation.CategoryFileReader;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryTest {

    @Test
    public void shouldAddNewCategory(){
        Category category = new Category("DevOps", "dev-ops");
        System.out.println(category);
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

    @Test
    public void shouldValidadeIncorrectCodeUrlBecauseIsNull(){
        assertThrows(IllegalArgumentException.class,() -> new Category("Dev Ops", null));
    }
    @Test
    public void shouldValidateIncorrectDescriptionEnum(){
        assertThrows(IllegalArgumentException.class,() -> new Category("Programação", "programacao",
                "Programe nas principais linguagens de programação",
                CategoryStatus.from("INAATIVA"), 1,
                "https://www.alura.com.br/assets/api/formacoes/categorias/512/programacao-transparent.png",
                "#00c86f"));
    }

    @Test
    public void shouldValidateCorrectDescriptionEnum() {
        new Category("Programação", "programacao",
                "Programe nas principais linguagens de programação",
                CategoryStatus.from("INATIVA"), 1,
                "https://www.alura.com.br/assets/api/formacoes/categorias/512/programacao-transparent.png",
                "#00c86f");
    }

    @Test
    public void should() throws IOException {
        CategoryFileReader categoryFileReader = new CategoryFileReader();
        List<Category> categories = categoryFileReader.readCategoriesFromFile
                ("planilha-dados-escola - Categoria.csv");
        categories.forEach(System.out::println);

        Map<String, Category> categoryMap = new HashMap<>();
        for (Category c : categories) {
            categoryMap.put(c.getCodeUrl(), c);
        }
    }
}
