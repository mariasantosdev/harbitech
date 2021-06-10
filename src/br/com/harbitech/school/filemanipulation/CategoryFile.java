package br.com.harbitech.school.filemanipulation;

import br.com.harbitech.school.category.Category;
import br.com.harbitech.school.category.CategoryStatus;

import java.io.*;
import java.util.Scanner;

public class CategoryFile {

    public static void main(String[] args) throws IOException {

        try (InputStream inputStream = new FileInputStream("planilha-dados-escola - Categoria.csv");
             Scanner scanner = new Scanner(inputStream, "UTF-8")) {

            int contador = 0;
            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();
                if (contador > 0) {
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

                    Category category = new Category(categoryName, categoryCodeUrl, categoryDescription, null,
                            CategoryStatus.from(categoryStatus), order, categoryIcon, categoryColor);
                   System.out.println(category.toString());
                }
                contador++;

            }
        }
    }
}