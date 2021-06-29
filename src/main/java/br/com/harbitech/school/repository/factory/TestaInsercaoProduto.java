package br.com.harbitech.school.repository.factory;

import br.com.harbitech.school.category.Category;
import br.com.harbitech.school.course.Course;
import br.com.harbitech.school.course.CourseVisibility;
import br.com.harbitech.school.repository.dao.CourseDAO;
import br.com.harbitech.school.subcategory.SubCategory;

import java.sql.Connection;
import java.sql.SQLException;

public class TestaInsercaoProduto {
    public static void main(String[] args) throws SQLException {
        Category category = new Category ("Programacao","programacao");

        SubCategory subCategory = new SubCategory("Java","java-e-persistencia",category);

        Course jdbc = new Course("Java e JDBC: Trabalhando com um banco de dados","jdbc",
                12, CourseVisibility.from("PÚBLICA"),"Pessoas com uma base de POO e BD",
                "João Vitor", "\n" +
                "\n" +
                "    Comunique-se com um banco de dados relacional\n" +
                "    Indo além do Statement e do ResultSet\n" +
                "    Encapsule o acesso em um DAO\n" +
                "    Connection pool, datasources e outros recursos importantes\n" +
                "\n","Entender melhor o banco de dados e um CRUD",subCategory);

        try(Connection connection = new ConnectionFactory().retrieveConnection()){
            CourseDAO courseDAO = new CourseDAO(connection);
            courseDAO.save(jdbc);
        }
    }
}
