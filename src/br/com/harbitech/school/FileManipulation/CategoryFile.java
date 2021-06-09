package br.com.harbitech.school.FileManipulation;

import br.com.harbitech.school.category.Category;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class CategoryFile {

    public static void main(String[] args) {


        try {
            Scanner scanner = new Scanner(new File("planilha-dados-escola - Categoria.csv"), "UTF-8");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.getMessage();
        }

        Category category = new Category("Devops", "dev-ops");

//        category.setHtmlHexColorCode("#lx123");
//        category.setDescription("Aprenda linux e estude Git!");

        var categories = new ArrayList<Category>();

        categories.add(category);

        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream
                    ("planilha-dados-escola - Categoria.csv", true));

            output.writeObject("\n" + category.toString());
            output.close();
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
