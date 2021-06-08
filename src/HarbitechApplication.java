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

        List<Category> categories = new ArrayList<Category>();

        Category category = new Category("Devops", "dev-ops");
        categories.add(category);

        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream
                ("planilha-dados-escola - Categoria.csv",true));

        output.writeObject("\n" + category.toString());
        output.close();


        Scanner scanner = new Scanner(new File("planilha-dados-escola - Categoria.csv"), "UTF-8");
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            System.out.println(line);

            Scanner lineScanner = new Scanner(line);
        }
        scanner.close();

    }
}


