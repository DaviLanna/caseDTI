# 🎬 Gerenciador de Filmes em Console

Um sistema simples de cadastro, consulta, atualização e remoção de filmes, executado via terminal, utilizando Java 17, SQLite e Maven.

## Sumário

- [Sobre o Projeto](#sobre-o-projeto)
- [Funcionalidades](#funcionalidades)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Decisões](#decisões)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Como Executar](#como-executar)
- [Scripts e Banco de Dados](#scripts-e-banco-de-dados)

---

## Sobre o Projeto

Este projeto é um gerenciador de filmes para uso em linha de comando. Permite cadastrar, listar, buscar, atualizar e remover filmes de um banco de dados SQLite local. O objetivo é demonstrar o uso de Java moderno, boas práticas de organização de código, persistência de dados e integração com Maven.

## Funcionalidades

- Listar todos os filmes cadastrados
- Buscar filme por ID
- Cadastrar novo filme
- Atualizar informações de um filme existente
- Remover filme do banco de dados

### Modelo de Filme

- *Obrigatórios:*
	- Título (String)
	- Diretor (String)
	- Ano de Lançamento (int)
- *Opcionais:*
	- Data em que foi assistido (LocalDate, formato AAAA-MM-DD)
	- Nota (Float, de 0 a 10)

## Tecnologias Utilizadas

- *Java 17*
- *SQLite* (banco de dados)
- *Apache Maven* (build e dependências)
- *Visual Studio Code* (IDE recomendada)

## Decisões

Escolhi a linguagem Java por dois fatores principais: ser orientada a objetos e minha familiaridade pessoal com a linguagem. Já cheguei a utilizá-la em um projeto similar do qual participei na faculdade, onde também deveríamos manipular dados e construir um back-end para uma aplicação, utilizando principalmente Java.

Para a estrutura e o gerenciamento do projeto, optei pelo Apache Maven. O uso do Maven foi importante principalmente pelo gerenciamento de dependências. Em vez de adicionar bibliotecas externas (arquivos .jar) manualmente ao projeto, o Maven permite que elas sejam declaradas de forma simples no arquivo pom.xml. Neste projeto, por exemplo, a dependência do driver JDBC do SQLite foi incluída com apenas algumas linhas, e o Maven se encarregou de baixar e disponibilizar a biblioteca automaticamente.

## Estrutura do Projeto

```
caseDTI/
│
├── database/
│   └── schema.sql
├── src/
│   └── main/
│       └── java/
│           └── com/dti/filmes/
│               ├── dao/FilmeDAO.java
│               ├── db/ConexaoSQLite.java
│               ├── main/App.java
│               └── model/Filme.java
├── filmes.db
├── pom.xml
└── README.md
```

## Como Executar

1. *Pré-requisitos:*
	 - Java JDK 17+
	 - Apache Maven
	 - VSCode (IDE Recomendada)
	 - Extensão SQLite de "alexcvzz" (Recomendado para visualizar a tabela "filmes")

2. *Clone o repositório:*
	 sh
	 git clone https://github.com/DaviLanna/caseDTI.git

	 cd caseDTI

4. *Execute a aplicação:*
	 - Ao clonar o repositório e abri-lo, as dependências devem ser instaladas automaticamente. Caso contrário, execute o comando "mvn install" no terminal.
	 - Execute o arquivo "App.java", um menu de opções intuitivo deve aparecer no terminal para o usuário.	 

5. *Utilize o menu interativo no terminal para gerenciar seus filmes.*

## Scripts e Banco de Dados

- O banco de dados SQLite (filmes.db) é criado automaticamente na raiz do projeto.
- O script de criação da tabela está em database/schema.sql.
- Não é necessário configurar nada manualmente: a aplicação garante a existência da tabela ao iniciar.
