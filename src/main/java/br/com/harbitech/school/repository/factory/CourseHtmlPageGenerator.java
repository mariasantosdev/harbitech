package br.com.harbitech.school.repository.factory;

import java.sql.Connection;
import java.sql.SQLException;

public class CourseHtmlPageGenerator {
    public static void main(String[] args) throws SQLException {
        try(Connection connection = new ConnectionFactory().retrieveConnection()) {

        }

    }
}
