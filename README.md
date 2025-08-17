Gerenciador de Filmes em Console

Recurso Escolhido: Filme

O recurso central da aplicação é o Filme, modelado com os seguintes atributos:

Atributos Obrigatórios:

Título: String

Diretor: String

Ano de Lançamento: int

Atributos Opcionais:

Data em que foi assistido: LocalDate - A data específica em que o usuário assistiu ao filme (armazenado como TEXT no formato "AAAA-MM-DD" no banco de dados).

Nota: Float - Uma nota de 0 a 10 atribuída pelo usuário (armazenada como REAL no banco de dados).

Tecnologias Utilizadas:

Linguagem Principal: Java 17

Banco de Dados: SQLite

Gerenciador de Build e Dependências: Apache Maven

IDE de Desenvolvimento: Visual Studio Code

Decisões das Tecnologias:

Escolhi a linguagem Java por dois fatores principais: ser orientada a objetos e minha familiaridade pessoal com a linguagem. Já cheguei a utilizá-la em um projeto similar do qual participei na faculdade, onde também deveríamos manipular dados e construir um back-end para uma aplicação, utilizando principalmente Java.

Para a estrutura e o gerenciamento do projeto, optei pelo Apache Maven. O uso do Maven foi importante principalmente pelo gerenciamento de dependências. Em vez de adicionar bibliotecas externas (arquivos .jar) manualmente ao projeto, o Maven permite que elas sejam declaradas de forma simples no arquivo pom.xml. Neste projeto, por exemplo, a dependência do driver JDBC do SQLite foi incluída com apenas algumas linhas, e o Maven se encarregou de baixar e disponibilizar a biblioteca automaticamente.

Como Configurar e Executar:

Para executar o projeto, é necessário ter o seguinte software instalado:

Java (JDK) - Versão 17 ou superior.

Apache Maven.

VSCode (IDE recomendada).

Clone o repositório do projeto. O VSCode vai requisitar a instalação das dependências necessárias incluídas no pom.xml (Se não baixa-las automaticamente, execute o comando "mvn install" no terminal); após isso, apenas rode o arquivo App.java. Um menu de opções aparecerá no terminal, e o usuário poderá escolher suas opções de forma intuitiva.
