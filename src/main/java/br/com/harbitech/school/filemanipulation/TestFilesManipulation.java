package br.com.harbitech.school.filemanipulation;

import br.com.harbitech.school.category.Category;
import br.com.harbitech.school.course.Course;
import br.com.harbitech.school.subcategory.Subcategory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestFilesManipulation {
    public static void main(String[] args) throws IOException {
        CategoryFileReader categoryFileReader = new CategoryFileReader();
        List<Category> categories = categoryFileReader.readCategoriesFromFile
                ("planilha-dados-escola - Categoria.csv");
        categories.forEach(System.out::println);

        Map<String, Category> categoryMap = new HashMap<>();
        for (Category c : categories) {
            categoryMap.put(c.getCodeUrl(), c);
        }

        SubCategoryFileReader subCategoryFileReader = new SubCategoryFileReader();
        List<Subcategory> subCategories = subCategoryFileReader.readSubCategoriesFromFile("planilha-dados-escola - Subcategoria.csv", categoryMap);
        subCategories.forEach(System.out::println);

        Map<String, Subcategory> subCategoryMap = new HashMap<>();
        for (Subcategory sc : subCategories) {
            subCategoryMap.put(sc.getCodeUrl(), sc);
        }

        CourseFileReader courseFileReader = new CourseFileReader();
        List<Course> courses = courseFileReader.readCoursesFromFile("planilha-dados-escola - Curso2.csv", subCategoryMap);
        courses.forEach(System.out::println);

//                Map<String, SubCategory> subCategoryMap = new HashMap<>();
//        for (SubCategory sc : subCategories) {
//            subCategoryMap.put(sc.getCodeUrl(), sc);
//        }
//
//        CourseFileReader courseFileReader = new CourseFileReader();
//        List<Course> courses = courseFileReader.readCoursesFromFile("planilha-dados-escola - Curso.csv", subCategoryMap);
//        courses.forEach(System.out::println);

    }
}

