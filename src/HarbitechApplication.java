import br.com.harbitech.school.category.Category;

public class HarbitechApplication {

    public static void main(String[] args) {
        Category programacao = new Category("Programação", "programacao");
        System.out.println(programacao);

        Category devops = new Category("DevOps", "dev-ops");
        System.out.println(devops);

//        Category semNome = new Category(null, "programacao");
//        Category nomeVazio = new Category("", "programacao");
//        Category nomeEmBranco = new Category("   ", "programacao");
//
//        Category devops = new Category("DevOps", "DEVOPS");
//        Category devops = new Category("DevOps", "DEV OPS");
//        Category devops = new Category("DevOps", "dev ops");

    }

}
