package br.com.harbitech.school.filemanipulation;

import br.com.harbitech.school.category.Category;
import br.com.harbitech.school.course.Course;
import br.com.harbitech.school.subcategory.SubCategory;

import java.io.*;
import java.util.List;

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
        printStream.println("</tr>");

        for (Category c : categories) {
            printStream.println("<tr align =center>");
            printStream.println("<td>" + c.getName() + "</td>");
            printStream.println("<td>" + c.getCodeUrl() + "</td>");
            printStream.println("<td>" + c.getDescription() + "</td>");
            printStream.println("<td>" + c.getStatus() + "</td>");
            printStream.println("<td>" + c.getOrderVisualization() + "</td>");
            printStream.println("<td>" + c.getIconPath() + "</td>");
            printStream.println("<td>" + c.getHtmlHexColorCode() + "</td>");
            printStream.println("<td>" + c.totalCourses() + "</td>");
            printStream.println("<td>" + c.totalTimeInHoursOfCourse() + "</td>");
            printStream.println("<td>" + c.subCategoryName() + "</td>");
            printStream.println("<td>" + c.subCategoryDescription() + "</td>");
            printStream.println("<td>" + c.nameCourses() + "</td>");

            printStream.println("</tr>");
        }

        printStream.println("</tr>");
        printStream.println("</table>");
        printStream.println("</body>");
        printStream.println("</head>");
        printStream.println("</html>");
    }

    private List<Category> loadData() throws IOException {
        CategoryFileReader categoryFileReader = new CategoryFileReader();
        List<Category> categories = categoryFileReader.readCategoriesFromFile("planilha-dados-escola - Categoria.csv");

        SubCategoryFileReader subCategoryFileReader = new SubCategoryFileReader();
        List<SubCategory> subCategories = subCategoryFileReader.readSubCategoriesFromFile("planilha-dados-escola - Subcategoria.csv");

        CourseFileReader courseFileReader = new CourseFileReader();
        List<Course> courses = courseFileReader.readCoursesFromFile("planilha-dados-escola - Curso.csv");

        for (SubCategory subCategory : subCategories) {
            for (Course course : courses) {
                if (isCourseBelongsToSubCategory(subCategory, course)) {
                    subCategory.getCourses().add(course);
                }
            }
        }

        for (Category category : categories) {
            for (SubCategory subCategory : subCategories) {
                if (isSubCategoryBelongsToCategory(category, subCategory)) {
                    category.getSubCategories().add(subCategory);
                }
            }
        }
        return categories;
    }

    private boolean isCourseBelongsToSubCategory(SubCategory subCategory, Course course) {
        return subCategory.getCodeUrl().equals(course.getSubCategory().getCodeUrl());
    }

    private boolean isSubCategoryBelongsToCategory(Category category, SubCategory subCategory) {
        return subCategory.getCategory().getCodeUrl().equals(category.getCodeUrl());
    }
}


