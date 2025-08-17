package com.dti.filmes.db;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexaoSQLite {

    private static final String DB_URL = "jdbc:sqlite:database/filmes.db";

    public static Connection conectar() {
        try {
            return DriverManager.getConnection(DB_URL);
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            return null;
        }
    }
    
    public static void criarTabelaSeNaoExistir() {
        String sql; 
        
        try {
            // Garante que a pasta 'database' exista.
            Files.createDirectories(Paths.get("database"));
            sql = new String(Files.readAllBytes(Paths.get("database/schema.sql")));
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo schema.sql: " + e.getMessage());
            System.err.println("Verifique se o arquivo 'schema.sql' está na pasta 'database' na raiz do projeto.");
            return;
        }

        try (Connection conn = conectar();
             Statement stmt = (conn != null) ? conn.createStatement() : null) {
            
            if (stmt != null) {
                stmt.execute(sql);
            } else {
                System.err.println("Não foi possível criar a tabela pois a conexão com o banco de dados falhou.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao criar a tabela: " + e.getMessage());
        }
    }
}