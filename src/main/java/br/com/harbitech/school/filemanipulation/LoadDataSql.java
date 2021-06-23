package br.com.harbitech.school.filemanipulation;

import br.com.harbitech.school.category.Category;
import br.com.harbitech.school.category.CategoryStatus;
import br.com.harbitech.school.course.Course;
import br.com.harbitech.school.course.CourseVisibility;
import br.com.harbitech.school.subcategory.SubCategory;
import br.com.harbitech.school.subcategory.SubCategoryStatus;

import java.io.*;
import java.util.*;

public class LoadDataSql {
    public static void main(String[] args) throws IOException {
        LoadDataSql loadDataSql = new LoadDataSql();
        loadDataSql.generate("/home/madu/Área de Trabalho/ProjetosAluraDEV/Desafio-levelup-semana1/Harbitech/harbitech_data.sql");
    }

    public void generate(String filePath) throws IOException {
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

        try (OutputStream outputStream = new FileOutputStream(filePath, false);
             PrintStream printStream = new PrintStream(outputStream)) {
            writeCategoriesInsert(categories, printStream);
            writeSubcategoriesInsert(subCategories, printStream);
            writeCourseInsert(courses, printStream);
        }
    }

    private void writeCategoriesInsert(List<Category> categories, PrintStream printStream) {
        for (Category c : categories) {
            String categoryName = c.getName();
            String categoryCodeUrl = c.getCodeUrl();
            String categoryDescription = c.getDescription();
            CategoryStatus categoryStatus = c.getStatus();
            Integer categoryOrder = c.getOrderVisualization();
            String categoryIcon = c.getIconPath();
            String categoryColor = c.getHtmlHexColorCode();

            String sqlCategory = "INSERT INTO Category (name,code_url,order_visualization,description,status" +
                    ",icon_path,html_hex_color_code)"
                    + " VALUES ('%s','%s','%s','%s','%s','%s','%s');";

            printStream.println(String.format(sqlCategory, categoryName, categoryCodeUrl, categoryOrder,
                    categoryDescription, categoryStatus, categoryIcon, categoryColor));
        }
    }

    private void writeSubcategoriesInsert(List<SubCategory> subcategories, PrintStream printStream) {
        for (SubCategory sc : subcategories) {
            String subcategoryName = sc.getName();
            String subcategoryCodeUrl = sc.getCodeUrl();
            Integer subcategoryOrder = sc.getOrderVisualization();
            String subcategoryDescription = sc.getDescription();
            SubCategoryStatus subcategoryStatus = sc.getStatus();
            String categoryCodeUrl = sc.getCategory().getCodeUrl();

            String sqlSubcategory = "INSERT INTO Subcategory (name, code_url,order_visualization,description,status,category_code_url)"
                    + " VALUES ('%s','%s','%s','%s','%s','%s');";

            printStream.println(String.format(sqlSubcategory, subcategoryName, subcategoryCodeUrl,
                    subcategoryOrder, subcategoryDescription, subcategoryStatus, categoryCodeUrl));
        }
    }

    private void writeCourseInsert(List<Course> courses, PrintStream printStream) {
        for (Course c : courses) {
            String courseName = c.getName();
            String courseCodeUrl = c.getCodeUrl();
            Integer courseCompletionTimeInHours = c.getCompletionTimeInHours();
            CourseVisibility visibility = c.getVisibility();
            String courseTargetAudience = c.getTargetAudience();
            String courseInstructor = c.getInstructor();
            String courseDescription = c.getDescription().replace("'","");
            String courseDevelopedSkills = c.getDevelopedSkills();

            String subCategoryUrlCode = c.getSubCategory().getCodeUrl();

            String sqlCourse = "INSERT INTO Course (name, code_url,completion_time_in_hours,visibility," +
                    "target_audience,instructor,description,developed_skills,subcategory_code_url)"
                    + " VALUES ('%s','%s','%s','%s','%s','%s','%s','%s','%s');";

            printStream.println(String.format(sqlCourse, courseName, courseCodeUrl,
                    courseCompletionTimeInHours,visibility,courseTargetAudience,courseInstructor,courseDescription,
                    courseDevelopedSkills,subCategoryUrlCode));
        }
    }
}




