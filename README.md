# 🎬 Gerenciador de Filmes em Console

Um sistema simples de cadastro, consulta, atualização e remoção de filmes, executado via terminal, utilizando Java 17, SQLite e Maven.

## Sumário

- [Sobre o Projeto](#sobre-o-projeto)
- [Funcionalidades](#funcionalidades)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Como Executar](#como-executar)
- [Scripts e Banco de Dados](#scripts-e-banco-de-dados)
- [Contribuição](#contribuição)
- [Licença](#licença)

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
- *SQLite* (persistência local)
- *Apache Maven* (build e dependências)
- *Visual Studio Code* (IDE recomendada)

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

2. *Clone o repositório:*
	 sh
	 git clone https://github.com/DaviLanna/caseDTI.git
	 cd caseDTI
	 

3. *Instale as dependências:*
	 sh
	 mvn install
	 

4. *Execute a aplicação:*
	 - Via Maven:
		 sh
		 mvn exec:java -Dexec.mainClass="com.dti.filmes.main.App"
		 
	 - Ou gere o JAR executável:
		 sh
		 mvn package
		 java -jar target/gerenciador-filmes-console-1.0-SNAPSHOT-jar-with-dependencies.jar
		 

5. *Utilize o menu interativo no terminal para gerenciar seus filmes.*

## Scripts e Banco de Dados

- O banco de dados SQLite (filmes.db) é criado automaticamente na raiz do projeto.
- O script de criação da tabela está em database/schema.sql.
- Não é necessário configurar nada manualmente: a aplicação garante a existência da tabela ao iniciar.

## Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues ou pull requests.

## Licença

Este projeto está sob a licença MIT.
