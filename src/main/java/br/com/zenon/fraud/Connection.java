package br.com.zenon.fraud;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection {

    private Connection() {
    }

    public static java.sql.Connection getConnection() {
        try {
            return  DriverManager.getConnection("jdbc:mysql://localhost:3306/zenon_frauds", "root", "senha123");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar conexao com database " + e);
        }
    }
}
