## Projeto Gestão de Funcionários

Este é um projeto Spring Boot para gerenciar funcionários e departamentos em uma aplicação web. Ele inclui operações CRUD para funcionários e departamentos, persistência de dados usando banco de dados H2 para funcionários e MongoDB para usuários, além de configuração de segurança usando Spring Security. O projeto também pode ser executado como um contêiner Docker.

### Funcionalidades

- Cadastro, consulta, atualização e exclusão de funcionários.
- Cadastro, consulta, atualização e exclusão de departamentos.
- Autenticação de usuários usando Spring Security (com autenticação desabilitada para fins de demonstração).
- Armazenamento de usuários em MongoDB.
- Testes de integração para os endpoints usando JUnit e Mockito.
- Documentação e exemplos de uso usando Postman.

### Pré-requisitos

- Java JDK 17 ou superior instalado.
- Maven para construir o projeto (ou você pode usar o wrapper `mvnw` fornecido).

### Tecnologias Utilizadas

- Spring Boot
- Spring Data JPA
- H2 Database (persistência para funcionários)
- MongoDB (persistência para usuários)
- Spring Security
- Docker

### Configuração do Projeto

1. **Clonar o Repositório**

   ```bash
   git clone https://github.com/FabioPelagaggi/crud-gestaofuncionarios.git
   cd crud-gestaofuncionarios
   ```

2. **Compilar e Construir o Projeto**

   ```bash
   ./mvnw clean package
   ```

   Isso irá compilar o projeto, executar os testes e construir um arquivo JAR executável na pasta `target/`.

3. **Executar o Projeto**

   Para executar o projeto Spring Boot diretamente:

   ```bash
   java -jar target/gestaofuncionarios-0.0.1-SNAPSHOT.jar
   ```

   Ou, para executar como um contêiner Docker:

   ```bash
   docker build -t gestaofuncionarios .
   docker run -p 8080:8080 gestaofuncionarios
   ```

   Certifique-se de ajustar os comandos conforme necessário para o nome do seu projeto e versão do JAR gerado.

### Testando os Endpoints

Você pode usar o Postman para testar os endpoints da API RESTful fornecidos pelo projeto. Aqui estão exemplos dos endpoints disponíveis para funcionários e departamentos:

#### Funcionários

1. **Criar um novo Funcionário**
   
   Método: POST
   ```
   http://localhost:8080/funcionarios
   
   Corpo da requisição (JSON):
   {
       "nome": "João Silva",
       "endereco": "Rua das Flores, 123",
       "telefone": "(11) 99999-9999",
       "email": "joao.silva@example.com",
       "dataNascimento": "1990-05-15"
   }
   ```

2. **Listar todos os Funcionários**
   
   Método: GET
   ```
   http://localhost:8080/funcionarios
   ```

3. **Buscar um Funcionário por Id**
   
   Método: GET
   ```
   http://localhost:8080/funcionarios/{id}
   ```
   Substitua `{id}` pelo ID do funcionário desejado.

4. **Atualizar um Funcionário existente**
   
   Método: PUT
   ```
   http://localhost:8080/funcionarios/{id}
   
   Corpo da requisição (JSON):
   {
       "nome": "João da Silva",
       "endereco": "Rua das Flores, 123",
       "telefone": "(11) 99999-9999",
       "email": "joao.silva@example.com",
       "dataNascimento": "1990-05-15"
   }
   ```
   Substitua `{id}` pelo ID do funcionário que deseja atualizar.

5. **Deletar um Funcionário**
   
   Método: DELETE
   ```
   http://localhost:8080/funcionarios/{id}
   ```
   Substitua `{id}` pelo ID do funcionário que deseja deletar.

#### Departamentos

1. **Criar um novo Departamento**
   
   Método: POST
   ```
   http://localhost:8080/departamentos
   
   Corpo da requisição (JSON):
   {
       "nome": "TI",
       "local": "Andar 3, Bloco A"
   }
   ```

2. **Listar todos os Departamentos**
   
   Método: GET
   ```
   http://localhost:8080/departamentos
   ```

3. **Buscar um Departamento por Id**
   
   Método: GET
   ```
   http://localhost:8080/departamentos/{id}
   ```
   Substitua `{id}` pelo ID do departamento desejado.

4. **Atualizar um Departamento existente**
   
   Método: PUT
   ```
   http://localhost:8080/departamentos/{id}
   
   Corpo da requisição (JSON):
   {
       "nome": "Recursos Humanos",
       "local": "Andar 2, Bloco B"
   }
   ```
   Substitua `{id}` pelo ID do departamento que deseja atualizar.

5. **Deletar um Departamento**
   
   Método: DELETE
   ```
   http://localhost:8080/departamentos/{id}
   ```
   Substitua `{id}` pelo ID do departamento que deseja deletar.

### Notas Importantes

- Este projeto foi configurado com autenticação desabilitada para facilitar a demonstração e os testes. Em um ambiente de produção, a configuração de segurança deve ser ajustada conforme os requisitos de segurança da aplicação.
- Certifique-se de ter o ambiente Java configurado corretamente e as variáveis de ambiente necessárias (como `JAVA_HOME` e `PATH` para o Maven ou Gradle) configuradas conforme seu sistema operacional.