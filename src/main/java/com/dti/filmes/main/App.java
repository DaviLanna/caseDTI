package com.dti.filmes.main;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.dti.filmes.dao.FilmeDAO;
import com.dti.filmes.db.ConexaoSQLite;
import com.dti.filmes.model.Filme;

public class App {

    private static final FilmeDAO filmeDAO = new FilmeDAO();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ConexaoSQLite.criarTabelaSeNaoExistir();
        exibirMenu();
    }

    private static void exibirMenu() {
        while (true) {
            System.out.println("\n--- Gerenciador de Filmes ---");
            System.out.println("1. Listar todos os filmes");
            System.out.println("2. Buscar filme por ID");
            System.out.println("3. Cadastrar novo filme");
            System.out.println("4. Atualizar filme");
            System.out.println("5. Deletar filme");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                int opcao = Integer.parseInt(scanner.nextLine());
                processarOpcao(opcao);
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida. Por favor, insira um número.");
            }
        }
    }

    private static void processarOpcao(int opcao) {
        switch (opcao) {
            case 1: listarFilmes(); break;
            case 2: buscarFilmePorId(); break;
            case 3: cadastrarFilme(); break;
            case 4: atualizarFilme(); break;
            case 5: deletarFilme(); break;
            case 0:
                System.out.println("Saindo...");
                scanner.close();
                System.exit(0);
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    private static void listarFilmes() {
        System.out.println("\n--- Lista de Filmes ---");
        List<Filme> filmes = filmeDAO.listarTodos();
        if (filmes.isEmpty()) {
            System.out.println("Nenhum filme cadastrado.");
        } else {
            filmes.forEach(System.out::println);
        }
    }

    private static void buscarFilmePorId() {
        System.out.print("\nDigite o ID do filme a ser buscado: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Optional<Filme> filmeOpt = filmeDAO.buscarPorId(id);
            filmeOpt.ifPresentOrElse(
                System.out::println,
                () -> System.out.println("Filme com ID " + id + " não encontrado.")
            );
        } catch (NumberFormatException e) {
            System.out.println("ID inválido. Por favor, insira um número.");
        }
    }

    private static void cadastrarFilme() {
        System.out.println("\n--- Cadastro de Novo Filme ---");
        Filme filme = new Filme();

        String titulo;
        do {
            System.out.print("Título (Obrigatório): ");
            titulo = scanner.nextLine().trim();
            if (titulo.isEmpty()) System.out.println("O título não pode ser vazio.");
        } while (titulo.isEmpty());
        filme.setTitulo(titulo);

        String diretor;
        do {
            System.out.print("Diretor (Obrigatório): ");
            diretor = scanner.nextLine().trim();
            if (diretor.isEmpty()) System.out.println("O nome do diretor não pode ser vazio.");
        } while (diretor.isEmpty());
        filme.setDiretor(diretor);

        int ano = 0;
        boolean anoValido = false;
        while (!anoValido) {
            try {
                System.out.print("Ano de Lançamento (Obrigatório): ");
                ano = Integer.parseInt(scanner.nextLine());
                anoValido = true;
            } catch (NumberFormatException e) {
                System.out.println("Ano inválido. Por favor, insira um número.");
            }
        }
        filme.setAnoLancamento(ano);

        System.out.print("Data que assistiu (AAAA-MM-DD, opcional, pressione Enter para pular): ");
        String dataStr = scanner.nextLine();
        if (!dataStr.trim().isEmpty()) {
            try {
                filme.setDataAssistido(LocalDate.parse(dataStr));
            } catch (DateTimeParseException e) {
                System.out.println("Formato de data inválido. O campo será deixado em branco.");
            }
        }
        
        System.out.print("Nota (0 a 10, opcional, pressione Enter para pular): ");
        String notaStr = scanner.nextLine();
        if (!notaStr.trim().isEmpty()) {
            try {
                double nota = Double.parseDouble(notaStr);
                if (nota >= 0 && nota <= 10) {
                    filme.setNota(nota);
                } else {
                    System.out.println("Nota fora do intervalo (0-10). O campo será deixado em branco.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Formato de nota inválido. O campo será deixado em branco.");
            }
        }

        filmeDAO.cadastrar(filme);
        System.out.println("Filme cadastrado com sucesso!");
    }
    
    private static void atualizarFilme() {
        System.out.print("\nDigite o ID do filme a ser atualizado: ");
        int id;
        try {
            id = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido.");
            return;
        }

        Optional<Filme> filmeExistenteOpt = filmeDAO.buscarPorId(id);
        if (filmeExistenteOpt.isEmpty()) {
            System.out.println("Filme com ID " + id + " não encontrado.");
            return;
        }
        
        Filme filmeParaAtualizar = filmeExistenteOpt.get();
        System.out.println("Atualizando o filme: " + filmeParaAtualizar.getTitulo());
        System.out.println("Insira os novos dados. Pressione Enter para manter o valor atual.");
        
        System.out.printf("Título atual: %s. Novo título: ", filmeParaAtualizar.getTitulo());
        String novoTitulo = scanner.nextLine().trim();
        if (!novoTitulo.isEmpty()) filmeParaAtualizar.setTitulo(novoTitulo);

        System.out.printf("Diretor atual: %s. Novo diretor: ", filmeParaAtualizar.getDiretor());
        String novoDiretor = scanner.nextLine().trim();
        if (!novoDiretor.isEmpty()) filmeParaAtualizar.setDiretor(novoDiretor);

        System.out.printf("Ano atual: %d. Novo ano: ", filmeParaAtualizar.getAnoLancamento());
        String novoAnoStr = scanner.nextLine().trim();
        if (!novoAnoStr.isEmpty()) {
            try {
                filmeParaAtualizar.setAnoLancamento(Integer.parseInt(novoAnoStr));
            } catch (NumberFormatException e) {
                System.out.println("Ano inválido. Mantendo o valor anterior.");
            }
        }

        // Lógica similar para data e nota

        filmeDAO.atualizar(filmeParaAtualizar);
        System.out.println("Filme atualizado com sucesso!");
    }

    private static void deletarFilme() {
        System.out.print("\nDigite o ID do filme a ser deletado: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            if (filmeDAO.buscarPorId(id).isPresent()) {
                filmeDAO.deletar(id);
                System.out.println("Filme deletado com sucesso!");
            } else {
                System.out.println("Filme com ID " + id + " não encontrado.");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID inválido. Por favor, insira um número.");
        }
    }
}