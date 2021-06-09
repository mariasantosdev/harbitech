package br.com.harbitech.school.FileManipulation;

import br.com.harbitech.school.category.Category;
import br.com.harbitech.school.course.Course;
import br.com.harbitech.school.subcategory.SubCategory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class CategoryHtmlFile {

    public static void main(String[] args) {
        Category category = new Category("Devops", "dev-ops");
        SubCategory subCategory = new SubCategory("Linux", "linux", category);
        Course course = new Course("JAVA-POO","java-poo",4,"Nico");

        var categories = new ArrayList<Category>();

        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream
                    ("planilha-dados-escola - Categoria.html", true));

            category.setDescription("Programe nas principais linguagens desde o nível iniciante até o avançado");
            category.setIconPath("https://www.alura.com.br/assets/api/formacoes/categorias/512/programacao-transparent.png");
            category.setHtmlHexColorCode("##c898");
            category.totalTimeInHoursOfCourse();
            category.totalCourses();
            category.setAllSubCategorys();
            categories.add(category);

            output.writeObject("\n" + category.toString());
            output.close();
        } catch (
                IOException e) {
            e.getMessage();
        }
    }
}
