package br.com.harbitech.school.repository;

import br.com.harbitech.school.repository.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class TesteConexao {
    public static void main(String[] args) throws SQLException {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.retrieveConnection();

        System.out.println("Fechando conexando!!");

        connection.close();
    }
}
