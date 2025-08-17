package com.dti.filmes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.dti.filmes.db.ConexaoSQLite;
import com.dti.filmes.model.Filme;

public class FilmeDAO {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public void cadastrar(Filme filme) {
        String sql = "INSERT INTO filmes(titulo, diretor, ano_lancamento, data_assistido, nota) VALUES(?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoSQLite.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, filme.getTitulo());
            pstmt.setString(2, filme.getDiretor());
            pstmt.setInt(3, filme.getAnoLancamento());
            
            if (filme.getDataAssistido() != null) {
                pstmt.setString(4, filme.getDataAssistido().format(DATE_FORMATTER));
            } else {
                pstmt.setNull(4, Types.VARCHAR);
            }
            
            if (filme.getNota() != null) {
                pstmt.setDouble(5, filme.getNota());
            } else {
                pstmt.setNull(5, Types.REAL);
            }
            
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar filme: " + e.getMessage());
        }
    }

    public List<Filme> listarTodos() {
        List<Filme> filmes = new ArrayList<>();
        String sql = "SELECT * FROM filmes";
        try (Connection conn = ConexaoSQLite.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                filmes.add(mapResultSetToFilme(rs));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar filmes: " + e.getMessage());
        }
        return filmes;
    }

    public Optional<Filme> buscarPorId(int id) {
        String sql = "SELECT * FROM filmes WHERE id = ?";
        try (Connection conn = ConexaoSQLite.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapResultSetToFilme(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar filme por ID: " + e.getMessage());
        }
        return Optional.empty();
    }

    public void atualizar(Filme filme) {
        String sql = "UPDATE filmes SET titulo = ?, diretor = ?, ano_lancamento = ?, data_assistido = ?, nota = ? WHERE id = ?";
        try (Connection conn = ConexaoSQLite.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, filme.getTitulo());
            pstmt.setString(2, filme.getDiretor());
            pstmt.setInt(3, filme.getAnoLancamento());
            if (filme.getDataAssistido() != null) {
                pstmt.setString(4, filme.getDataAssistido().format(DATE_FORMATTER));
            } else {
                pstmt.setNull(4, Types.VARCHAR);
            }
            if (filme.getNota() != null) {
                pstmt.setDouble(5, filme.getNota());
            } else {
                pstmt.setNull(5, Types.REAL);
            }
            pstmt.setInt(6, filme.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar filme: " + e.getMessage());
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM filmes WHERE id = ?";
        try (Connection conn = ConexaoSQLite.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao deletar filme: " + e.getMessage());
        }
    }

    private Filme mapResultSetToFilme(ResultSet rs) throws SQLException {
        Filme filme = new Filme();
        filme.setId(rs.getInt("id"));
        filme.setTitulo(rs.getString("titulo"));
        filme.setDiretor(rs.getString("diretor"));
        filme.setAnoLancamento(rs.getInt("ano_lancamento"));
        String dataAssistidoStr = rs.getString("data_assistido");
        if (dataAssistidoStr != null) {
            filme.setDataAssistido(LocalDate.parse(dataAssistidoStr, DATE_FORMATTER));
        }
        Object notaObj = rs.getObject("nota");
        if (notaObj != null) {
            filme.setNota(rs.getDouble("nota"));
        }
        return filme;
    }
}