package br.com.harbitech.school.filemanipulation;

import br.com.harbitech.school.course.Course;
import br.com.harbitech.school.subcategory.SubCategory;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class SubCategoryFile {

    public static void main(String[] args) throws IOException {

        try (InputStream inputStream = new FileInputStream("planilha-dados-escola - Subcategoria.csv");
             Scanner scanner = new Scanner(inputStream, "UTF-8")) {

            LinkedList<SubCategory> subCategories = new LinkedList<SubCategory>();
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] categoryData = line.split(",");
                String categoryName = categoryData[0];

                SubCategory subCategory = new SubCategory(categoryName);
                subCategories.push(subCategory);
            }
            System.out.println("Exibindo subcategorias\n");

            while (!subCategories.isEmpty()) {
                SubCategory subCategory = subCategories.pop();
                System.out.println(subCategory.getName());
            }
        }
    }
}

