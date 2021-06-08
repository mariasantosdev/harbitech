package br.com.harbitech.school.category;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class CategoryFileManipulation {
    public static void main(String[] args) throws Exception {

        Category category = new Category("Devops", "dev-ops");

        List<Category> categories = new ArrayList<Category>();

        categories.add(category);
        try {
            Scanner scanner = new Scanner(new File("planilha-dados-escola - Categoria.csv"), "UTF-8");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.getMessage();
        }

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