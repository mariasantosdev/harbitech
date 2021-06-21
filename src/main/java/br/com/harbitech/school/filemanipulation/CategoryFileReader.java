package br.com.harbitech.school.filemanipulation;

import br.com.harbitech.school.category.Category;
import br.com.harbitech.school.category.CategoryStatus;

import java.io.*;
import java.util.*;

public class CategoryFileReader {

    public List<Category> readCategoriesFromFile(String filePath) throws IOException {
        InputStream inputStream = new FileInputStream(filePath);
        List<Category> categories = new ArrayList<Category>();
        try (Scanner scanner = new Scanner(inputStream, "UTF-8")) {
            scanner.nextLine();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                String[] categoryData = line.split(",");
                String categoryName = categoryData[0];
                String categoryCodeUrl = categoryData[1];
                String categoryOrder = categoryData[2];
                String categoryDescription = categoryData[3];
                String categoryStatus = categoryData[4];
                String categoryIcon = categoryData[5];
                String categoryColor = categoryData[6];

                int order = -1;

                if (!categoryOrder.isBlank()) {
                    order = Integer.parseInt(categoryOrder);
                }

                Category category = new Category(categoryName, categoryCodeUrl, categoryDescription,
                        CategoryStatus.from(categoryStatus), order, categoryIcon, categoryColor);

                categories.add(category);
            }
            System.out.println("Exibindo Categorias:\n");
        }
        return categories;
    }
}

