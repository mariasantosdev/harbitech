package br.com.harbitech.school.category;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class CategoryFileManipulation {

    public static void main(String[] args) throws Exception {

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
