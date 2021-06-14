package br.com.harbitech.school.filemanipulation;

import br.com.harbitech.school.category.Category;
import br.com.harbitech.school.subcategory.SubCategory;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class TestFilesManipulation {
    public static void main(String[] args) throws IOException {
        CategoryFileReader categoryFileReader = new CategoryFileReader();
        List<Category> categories = categoryFileReader.readCategoriesFromFile
                ("planilha-dados-escola - Categoria.csv");
        categories.forEach(System.out::println);

        SubCategoryFileReader subCategoryFileReader = new SubCategoryFileReader();
        List<SubCategory> subCategories = subCategoryFileReader.readSubCategoriesFromFile
                ("planilha-dados-escola - Subcategoria.csv");
        subCategories.forEach(System.out::println);
    }
}

