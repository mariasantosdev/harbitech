import br.com.harbitech.school.category.Category;
import br.com.harbitech.school.course.Course;
import br.com.harbitech.school.section.Section;
import br.com.harbitech.school.subcategory.SubCategory;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;


public class HarbitechApplication {

    public static void main(String[] args) throws Exception {
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
        Locale.setDefault(new Locale("PT", "br"));
        Scanner scanner = new Scanner(new File("planilha-dados-escola - Categoria.csv"));
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            System.out.println(line);
        }

        List<Category> list = new ArrayList<Category>();

        list.add(new Category("DevOps", "dev-ops"));

        Category category = new Category("DevOps", "dev-ops");
        list.add(category);

        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream
                ("planilha-dados-escola - Categoria.csv"));
        output.writeObject(category);


        scanner.close();

        output.close();


        }
    }


