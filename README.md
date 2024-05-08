# sisPessoa
Sistema de Gerenciamento de Pessoas (sisPessoa)

Descrição:
O sisPessoa é um sistema de gerenciamento de pessoas desenvolvido em Java, utilizando JSF e JPA. Permite realizar operações de CRUD sobre dados de pessoas e endereços associados.

Pré-requisitos:
- Java JDK 1.8
- Apache Tomcat 8.5
- PostgreSQL 12+

Configuração do Banco de Dados:
1. Crie o banco de dados com o nome 'sisPessoa'.
2. Crie as tabelas conforme os scripts SQL fornecidos no projeto(sisPessoa.sql).

Configuração do DataSource no Tomcat:
Configure o datasource no arquivo context.xml do Tomcat para permitir a conexão ao PostgreSQL.

Execução do Projeto:
1. Empacote a aplicação com Maven usando 'mvn clean package'.
2. Faça o deploy do arquivo WAR gerado no diretório 'webapps' do Tomcat.
3. Inicie o servidor Tomcat e acesse a aplicação via navegador.
4. Acesse a URL 'http://localhost:8080/sisPessoa' para acessar a aplicação.
.

Versões Utilizadas:
- Java: 1.8
- PostgreSQL Driver: 42.7.2
- Tomcat: 8.5


• Uma explicação sobre as decisões técnicas e arquiteturais do seu desafio;
 - Foi ultilizado CDI para injeção de dependência, devido já possuir tomcat configurado, por isso não optei por ultilizar EJB
• Uma justificativa para o uso de frameworks ou bibliotecas (caso sejam usadas);
   - Foi ultilizado JUnit e Mockito para os testes unitários. 
   - httpclient para realizar requisição ao serviço do Ibge para obter dados de estado e cidade
   - Foi usado json para converter resultado da requisição em um objeto json
• Instruções sobre como compilar e executar o projeto;
  - vide o item Execução do projeto
• Instruções sobre como executar os testes da solução;
  - Os testes podem ser executados ultilizado JUnit
