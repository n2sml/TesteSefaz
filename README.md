# TesteSefaz
Projeto realizado para o desafio técnico solicitado pela Sefaz para a vaga de desenvolvedor Java.

## Sobre
Meu foco neste projeto foi implementar todos os requistos obrigatórios do desafio. Gostaria de enfatizar o uso da linguagem na criação do projeto, onde o estruturei e o incrementei praticamente do zero, sem o uso de frameworks ou de outros projetos prontos na Internet, objetivando demonstrar a minha afinidade com a linguagem, bem como a minha capacidade de superar desafios.

### Estruturar a aplicação em camadas
Baseado no modelo MVC, desenvolvi e organizei os diretórios da seguinte forma:
Model - (/src/main/java/model): Classes responsáveis pela modelagem dos objetos do sistema (Usuario e Telefone) e pela persistência deles (UsuarioDAO).
View - (/src/main/webapp): Arquivos .jsp que guardam as estruturas das páginas que serão exibidas ao usuário. 
Controller - (/src/main/java/controller): Servlets responsáveis por receber e tratar as requisições, fazer chamadas ao DAO e redirecionar o usuário ao JSP certo. Modularizado de acordo com a função no sistema (criação de usuário, edição de usuário, login, etc).

### Uso de banco de dados relacional/SQL (preferencialmente HSQLDB ou H2)
Tomei esse requisito como um desafio. Nunca havia utilizado nenhum desses bancos, e optei por usar o HSQLDB. O banco está pronto pra uso, acompanha um arquivo com as queries usadas e instruções para rodar localmente.

### Processo de build utilizando Maven
O projeto utiliza o Maven para trazer as dependências e montar/executar.

### Persistência utilizando JDBC ou JPA 
Outro desafio (mesmo não sendo inédito pra mim o uso do JDBC). Toda a comunicação com o HSQLDB é feita no UsuarioDAO usando apenas JDBC.

### Página com Detalhamento de Informações 
Ao selecionar um item da lista, o usuário é redirecionado à uma página com todas as informações solicitadas no desafio;

### Utilizar no mínimo Java 8 
É pré-requisito para o uso do servidor de aplicação (Tomcat por exemplo) e do Maven o uso do Java 8.

### Utilizar na interface JSF/Primefaces ou JSP com jQuery e Ajax
Esse projeto utiliza JSPs para a modelagem do seu front-end, jQuery e AJAX para fazer redirecionamentos em casos específicos de rotas.


## Bibliotecas e Frameworks 
Desenvolvi esse projeto utilizando as seguintes ferramentas:
* [Netbeans IDE 8.2](https://netbeans.org/downloads/8.2/) - Ambiente integrado de desenvolvimento utilizado
* [Apache Maven](https://maven.apache.org) - Controle de dependências, compilação e execução do projeto
* [HSQLDB](http://hsqldb.org) - Banco de Dados Relacional (SQL)


## Rodando o banco
Antes de iniciar aaplicação, deve-se iniciar o banco HSQLDB. Para isso, basta em hsqldb/data e executar o seguinte comando no terminal: 
```
java --cp ../lib/hsqldb.jar org.hsqldb.server.Server --database.0 file:sampledb/sampledb --dbname.0 sampledb
```
Uma cópia dos comandos usados (queries) para gerar as tabelas do banco e alguns exemplos de insert para inicializá-lo podem ser encontrados em hsqldb/Queries do HSQL.txt.

## Rodando a aplicação localmente no NetBeans
Utilizando o Netbeans 8.2 (recomendado), basta seguir o caminho Arquivo > Abrir Projeto > Selecionar o projeto. Ele será exibido com um ícone representando se tratar de um projeto Maven. Após selecioná-lo, basta clicar com o botão direito sobre o projeto > Limpar e construir, aguarde até o processo terminar (vide barra de progresso no canto inferior direito da tela) e em seguida botão direito do mouse sobre o projeto > executar.
A aplicação, por default, será iniciada no Oracle Glassfish (se assim for escolhido durante a instalação do Netbeans). Caso ele não seja selecionado durante a instalação, basta clicar com o botão direito do mouse sobre o projeto > propriedades > executar > servidor > selecionar um servidor pré-instalado da sua preferência, como por exemplo o Apache Tomcat.
Por se tratar de uma aplicação totalmente em Servlets, qualquer servidor de aplicação com suporte à aplicações .war (teoricamente) devem ser capazes de executar esse projeto.

## Autor 
Nícolas Soares da Silva Miguel, estudante de Análise e Desenvolvimento de Sistemas do Instituto Federal de Pernambuco (IFPE).
