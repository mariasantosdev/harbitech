package br.com.harbitech.school.filemanipulation;

import br.com.harbitech.school.category.Category;

import java.io.*;
import java.util.List;

public class CategoryHtmlFile {

    public static void main(String[] args) throws IOException {

        try (OutputStream outputStream = new FileOutputStream("planilha-dados-escola - Categoria.html");
             PrintStream printStream = new PrintStream(outputStream)) {

            printStream.println("<html>");
            printStream.println("<body>");
            printStream.println("<table>");
            printStream.println("<tr>");
            printStream.println("</tr>");

            CategoryFileReader categoryFileReader = new CategoryFileReader();
            List<Category> categories = categoryFileReader.readCategoriesFromFile
                    ("planilha-dados-escola - Categoria.csv");
            for (Category c: categories){
                printStream.println("<tr>");
                printStream.println("<td>" + c.getName() + "</td>");
                printStream.println("<td>" + c.getCodeUrl() + "</td>");
                printStream.println("<td>" + c.getDescription() + "</td>");
                printStream.println("<td>" + c.getStatus() + "</td>");
                printStream.println("<td>" + c.getOrderVisualization() + "</td>");
                printStream.println("<td>" + c.getIconPath() + "</td>");
                printStream.println("<td>" + c.getHtmlHexColorCode() + "</td>");
                printStream.println("<td>" + c.totalTimeInHoursOfCourse() + "</td>");

                printStream.println("</tr>");
            }

            printStream.println("</tr>");
            printStream.println("</table>");
            printStream.println("</body>");
            printStream.println("</html>");
        }
    }
}


