package br.com.harbitech.school.filemanipulation;

import br.com.harbitech.school.category.Category;
import br.com.harbitech.school.subcategory.SubCategory;
import br.com.harbitech.school.subcategory.SubCategoryStatus;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class SubCategoryFileReader {

    public List<SubCategory> readSubCategoriesFromFile(String filePath, Map<String,Category> categoryMap) throws IOException {

        InputStream inputStream = new FileInputStream(filePath);
        List<SubCategory> subCategories = new LinkedList<SubCategory>();
        try (Scanner scanner = new Scanner(inputStream, "UTF-8")) {
            scanner.nextLine();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                String[] subcategoryData = line.split(",");
                String subCategoryName = subcategoryData[0];
                String subCategoryCodeUrl = subcategoryData[1];
                String subCategoryOrder = subcategoryData[2];
                String subcategoryDescription = subcategoryData[3];
                String subcategoryStatus = subcategoryData[4];
                String categoryUrlCode = subcategoryData[5];

                int order = -1;

                if (!subCategoryOrder.isBlank()) {
                    order = Integer.parseInt(subCategoryOrder);
                }

                Category category = Optional.ofNullable(categoryMap.get(categoryUrlCode))
                        .orElseThrow(() -> new IllegalArgumentException("Código da subcategoria não encontrado" + categoryUrlCode));

                SubCategory subCategory = new SubCategory(subCategoryName, subCategoryCodeUrl,
                        order, subcategoryDescription, null,
                        SubCategoryStatus.from(subcategoryStatus), category);
                subCategories.add(subCategory);
            }
            System.out.println("Exibindo sub-categorias:\n");
        }
        return subCategories;
    }
}


