package br.com.harbitech.school.repository.factory;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;


public class ConnectionFactory {

    public DataSource dataSource;

    public ConnectionFactory() {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost/harbitech?createDatabaseIfNotExist=true&serverTimezone=UTC");
        comboPooledDataSource.setUser("root");
        comboPooledDataSource.setPassword("czgcgj1971");

        this.dataSource = comboPooledDataSource;
    }

    public Connection retrieveConnection() throws SQLException {
        return this.dataSource.getConnection();
    }
}
