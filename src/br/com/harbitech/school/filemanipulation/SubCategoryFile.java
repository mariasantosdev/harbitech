package br.com.harbitech.school.filemanipulation;

import br.com.harbitech.school.category.Category;
import br.com.harbitech.school.subcategory.SubCategory;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class SubCategoryFile {

    public static void main(String[] args) {

        try {
            Scanner scanner = new Scanner(new File("planilha-dados-escola - Subcategoria.csv"), "UTF-8");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
            }
            scanner.close();
        } catch (
                FileNotFoundException e) {
            e.getMessage();
        }

        Category devops = new Category("Devops", "dev-ops");
        SubCategory subCategory = new SubCategory("Linux", "linux", devops);

//        subCategory.setOrderVisualization(1);
//        subCategory.setDescription("Aprenda sobre linux");

        var subCategories = new ArrayList<SubCategory>();

        subCategories.add(subCategory);

        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream
                    ("planilha-dados-escola - Subcategoria.csv", true));

            output.writeObject("\n" + subCategory.toString());
            output.close();
        } catch (
                IOException e) {
            e.getMessage();
        }
    }
}
