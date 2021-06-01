import br.com.harbitech.school.category.Category;
import br.com.harbitech.school.subcategory.SubCategory;

public class HarbitechApplication {

    public static void main(String[] args) {
        Category programacao = new Category("Programação", "programacao");
        System.out.println(programacao);

        Category devops = new Category("DevOps", "dev-ops");
        System.out.println(devops);

        SubCategory subCategory = new SubCategory("Linux","dev-ops",programacao);

//        Category semNome = new Category(null, "programacao");
//        Category nomeVazio = new Category("", "programacao");
//        Category nomeEmBranco = new Category("   ", "programacao");
//
//        Category devops = new Category("DevOps", "DEVOPS");
//        Category devops = new Category("DevOps", "DEV OPS");
//        Category devops = new Category("DevOps", "dev ops");

    }

}
