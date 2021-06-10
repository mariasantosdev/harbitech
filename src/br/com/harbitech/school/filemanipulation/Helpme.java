package br.com.harbitech.school.filemanipulation;

import br.com.harbitech.school.category.Category;
import br.com.harbitech.school.category.CategoryStatus;

import java.io.*;
import java.util.Scanner;

public class Helpme {

        public static void main(String[] args) throws IOException {

//            try (InputStream inputStream = new FileInputStream("planilha-dados-escola - Categoria.csv");
//           InputStream inputStream = new URL("https://docs.google.com/spreadsheets/d/1hfd2m8dfBCDDtgdeOnJZ4_9L8qQggPPutjH83FWr3W8/export?format=csv&id=1hfd2m8dfBCDDtgdeOnJZ4_9L8qQggPPutjH83FWr3W8&gid=869176443").openStream();
//                 Scanner scanner = new Scanner(inputStream, "UTF-8");
//                 OutputStream outputStream = new FileOutputStream("planilha-dados-escola - Categoria.html");
//                 PrintStream printStream = new PrintStream(outputStream)) {
//           PrintStream printStream = System.out) {
//
//                printStream.println("<html>");
//                printStream.println("<body>");
//                printStream.println("<table>");
//
//                int contador = 0;
//                while (scanner.hasNextLine()) {
//
//                    printStream.println("<tr>");
//
//                    if (contador > 0) {
//                        String[] categoryData = line.split(",");
//                        String categoryName = categoryData[0];
//                        String categoryCodeUrl = categoryData[1];
//                        String categoryOrder = categoryData[2];
//                        String categoryDescription = categoryData[3];
//                        String categoryStatus = categoryData[4];
//                        String categoryIcon = categoryData[5];
//                        String categoryColor = categoryData[6];
//
//                        int order = -1;
//                        if (!categoryOrder.isBlank()) {
//                            order = Integer.parseInt(categoryOrder);
//                        }
//
//                        Category category = new Category(categoryName, categoryCodeUrl, categoryDescription,
//                                null, CategoryStatus.from(categoryStatus), order, categoryIcon, categoryColor);
//                        printStream.println("<td>" + category.getName() + "</td>");
//                    }
//                    contador++;
//
//                    printStream.println("</tr>");
//                }
//                printStream.println("</table>");
//                printStream.println("</body>");
//                printStream.println("</html>");
//
//            }
        }
    }

