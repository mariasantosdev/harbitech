import br.com.harbitech.school.category.Category;
import br.com.harbitech.school.course.Course;
import br.com.harbitech.school.section.Section;
import br.com.harbitech.school.subcategory.SubCategory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class HarbitechApplication {

    public static void main(String[] args) {
//        Category programacao = new Category("Programação", "programacao");
//        System.out.println(programacao);
//
//        Category devops = new Category("DevOps", "dev-ops");
//        System.out.println(devops);
//
//        SubCategory subCategory = new SubCategory("Linux","linux",devops);
//        System.out.println(subCategory);

//        Course course = new Course("programação com java","java-spring",64,"Maria");
//        Course course = new Course("POO-Java","java-spring",20, "Maria");
//        Course course = new Course("POO-Java","java-spring",0, "Maria");
//         System.out.println(course);

//        Category semNome = new Category(null, "programacao");
//        Category nomeVazio = new Category("", "programacao");
//        Category nomeEmBranco = new Category("   ", "programacao");
//
//        Category devops = new Category("DevOps", "DEVOPS");
//        Category devops = new Category("DevOps", "DEV OPS");
//        Category devops = new Category("DevOps", "dev ops");

//        Section section = new Section("introdução ao git","git",course);
//        System.out.println(section);
//

        Category category = new Category("Devops", "dev-ops");
        SubCategory subCategory = new SubCategory("Linux","linux",category);

        var categories = new ArrayList<Category>();

        category.setDescription("Programe nas principais linguagens desde o nível iniciante até o avançado");
        category.setIconPath("https://www.alura.com.br/assets/api/formacoes/categorias/512/programacao-transparent.png");
        category.setHtmlHexColorCode("##c898");
        category.TotalTimeInHoursOfCourse();
        category.TotalCourses();
        category.setAllSubCategorys();
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
