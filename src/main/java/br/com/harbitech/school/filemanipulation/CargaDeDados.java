package br.com.harbitech.school.filemanipulation;

import br.com.harbitech.school.category.Category;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CargaDeDados {
    public static void main(String[] args) throws IOException {

        CategoryFileReader categoryFileReader = new CategoryFileReader();
        List<Category> categories = categoryFileReader.readCategoriesFromFile
                ("planilha-dados-escola - Categoria.csv");
        categories.forEach(System.out::println);

        Map<String, Category> categoryMap = new HashMap<>();
        for (
                Category c : categories) {
            categoryMap.put(c.getCodeUrl(), c);
        }

        String sql = "INSERT INTO categories (code_url, name, description, study_guide, status, " +
                "order_visualization,icon_path, html_hex_color_code)" +
                " values ('%s', '%s', '%s', '%s', '%s', %s, %s);";


    }
}
