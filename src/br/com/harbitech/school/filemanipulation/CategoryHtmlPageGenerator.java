package br.com.harbitech.school.filemanipulation;

import br.com.harbitech.school.category.Category;
import br.com.harbitech.school.course.Course;
import br.com.harbitech.school.subcategory.SubCategory;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryHtmlPageGenerator {

    public static void main(String[] args) throws IOException {
        CategoryHtmlPageGenerator categoryHtmlReportGenerator = new CategoryHtmlPageGenerator();
        categoryHtmlReportGenerator.generate("planilha-dados-escola - Categoria.html");
    }

    public void generate(String filePath) throws IOException {
        List<Category> categories = loadData();

        try (OutputStream outputStream = new FileOutputStream(filePath);
             PrintStream printStream = new PrintStream(outputStream)) {
            writePage(categories, printStream);
        }
    }

    private void writePage(List<Category> categories, PrintStream printStream) {
        printStream.println("<html>");
        printStream.println("<head>");
        printStream.println("<body>");
        printStream.println("<table border=1 frame=void rules=rows>");
        printStream.println("<tr>");
        printStream.println("<th>" + "Nome" + "</th>");
        printStream.println("<th>" + "Descrição" + "</th>");
        printStream.println("<th>" + "Ícone" + "</th>");
        printStream.println("<th>" + "Cor" + "</th>");
        printStream.println("<th>" + "Número de cursos" + "</th>");
        printStream.println("<th>" + "Total de horas estimadas dos cursos" + "</th>");
        printStream.println("<th>" + "Subcategorias" + "</th>");
        printStream.println("</tr>");

        for (Category c : categories) {
            printStream.println("<tr align =center>");
            printStream.println("<td>" + c.getName() + "</td>");
            printStream.println("<td>" + c.getDescription() + "</td>");
            printStream.println("<td>" + c.getIconPath() + "</td>");
            printStream.println("<td>" + c.getHtmlHexColorCode() + "</td>");
            printStream.println("<td>" + c.totalCourses() + "</td>");
            printStream.println("<td>" + c.totalTimeInHoursOfCourse() + "</td>");
            printStream.println("<td>");
            for (SubCategory sc : c.getSubCategories()) {
                printStream.println(sc.getName() + "<br>");
                printStream.println("<br>");
                printStream.println(sc.getDescription() + "<br>");
                printStream.println("<br>");
                printStream.println(sc.nameOfCourses() + "<br>");
                printStream.println("<br>");
            }
            printStream.println("</td>");
            printStream.println("</tr>");
        }
        printStream.println("</table>");
        printStream.println("</body>");
        printStream.println("</head>");
        printStream.println("</html>");
    }

    private List<Category> loadData() throws IOException {
        CategoryFileReader categoryFileReader = new CategoryFileReader();
        List<Category> categories = categoryFileReader.readCategoriesFromFile("planilha-dados-escola - Categoria.csv");

        Map<String, Category> categoryMap = new HashMap<>();
        for (Category c : categories) {
            categoryMap.put(c.getCodeUrl(), c);
        }

        SubCategoryFileReader subCategoryFileReader = new SubCategoryFileReader();
        List<SubCategory> subCategories = subCategoryFileReader.readSubCategoriesFromFile("planilha-dados-escola - Subcategoria.csv", categoryMap);

        Map<String, SubCategory> subCategoryMap = new HashMap<>();
        for (SubCategory sc : subCategories) {
            subCategoryMap.put(sc.getCodeUrl(), sc);
        }

        CourseFileReader courseFileReader = new CourseFileReader();
        List<Course> courses = courseFileReader.readCoursesFromFile("planilha-dados-escola - Curso2.csv", subCategoryMap);
        return categories;

//        Map<String, SubCategory> subCategoryMap = new HashMap<>();
//        for (SubCategory sc : subCategories) {
//            subCategoryMap.put(sc.getCodeUrl(), sc);
//        }
//
//        CourseFileReader courseFileReader = new CourseFileReader();
//        List<Course> courses = courseFileReader.readCoursesFromFile("planilha-dados-escola - Curso.csv", subCategoryMap);
//        return categories;

    }
}


