package br.com.harbitech.school.filemanipulation;

import br.com.harbitech.school.category.Category;
import br.com.harbitech.school.category.CategoryStatus;
import br.com.harbitech.school.course.Course;

import java.io.*;
import java.util.Scanner;

public class CategoryHtmlFile {

    public static void main(String[] args) throws IOException {

        try (InputStream inputStream = new FileInputStream("planilha-dados-escola - Categoria.csv");
             Scanner scanner = new Scanner(inputStream, "UTF-8");
             OutputStream outputStream = new FileOutputStream("planilha-dados-escola - Categoria.html");
             PrintStream printStream = new PrintStream(outputStream)) {

            printStream.println("<html>");
            printStream.println("<body>");
            printStream.println("<table>");

            int contador = 0;
            while (scanner.hasNextLine()) {

                printStream.println("<tr>");

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
                    printStream.println("<td>" + category.getName() + "</td>");
                    printStream.println("<td>" + category.getCodeUrl() + "</td>");
                    printStream.println("<td>" + category.getDescription() + "</td>");
                    printStream.println("<td>" + category.getStatus() + "</td>");
                    printStream.println("<td>" + category.getOrderVisualization() + "</td>");
                    printStream.println("<td>" + category.getIconPath() + "</td>");
                    printStream.println("<td>" + category.getHtmlHexColorCode() + "</td>");
                }
                contador++;

                printStream.println("</tr>");
            }

            printStream.println("</table>");
            printStream.println("</body>");
            printStream.println("</html>");

        }
    }
}

