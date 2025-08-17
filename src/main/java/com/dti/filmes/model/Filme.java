package com.dti.filmes.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Filme {
    private int id;
    private String titulo; // Obrigatório
    private String diretor; // Obrigatório
    private int anoLancamento; // Obrigatório
    private LocalDate dataAssistido; // Opcional
    private Double nota; // Opcional

    // Métodos Getters e Setters

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getDiretor() { return diretor; }
    public void setDiretor(String diretor) { this.diretor = diretor; }
    public int getAnoLancamento() { return anoLancamento; }
    public void setAnoLancamento(int anoLancamento) { this.anoLancamento = anoLancamento; }
    public LocalDate getDataAssistido() { return dataAssistido; }
    public void setDataAssistido(LocalDate dataAssistido) { this.dataAssistido = dataAssistido; }
    public Double getNota() { return nota; }
    public void setNota(Double nota) { this.nota = nota; }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = (dataAssistido != null) ? dataAssistido.format(formatter) : "Não assistido";
        String notaFormatada = (nota != null) ? String.format("%.1f/10", nota) : "Sem nota";

        return String.format(
            "ID: %d | Título: %s | Diretor: %s | Ano: %d | Assistido em: %s | Nota: %s",
            id, titulo, diretor, anoLancamento, dataFormatada, notaFormatada
        );
    }
}
