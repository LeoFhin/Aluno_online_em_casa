Exercício 01
--------------------------------------------------------------------------------------------------------------------------------------------------------

**1. Criação da API com Spring Boot**

*Controlador (AlunoController.java)*

-A classe foi anotada com @RestController, que indica ao Spring que essa classe será um controlador de uma API RESTful.
-O mapeamento da rota foi definido usando @RequestMapping("/alunos"), estabelecendo que todas as operações relativas a alunos ocorrerão na rota /alunos.
-O método de criação de aluno foi anotado com @PostMapping, indicando que ele será acessado via requisições HTTP POST.
-Utilizou @RequestBody para receber os dados do aluno no corpo da requisição em formato JSON, e @ResponseStatus(HttpStatus.CREATED) para retornar o status HTTP 201 (Created) em caso de sucesso.

*Modelo (Aluno.java)*

-A entidade Aluno foi anotada com @Entity, indicando que ela é uma entidade JPA que será persistida no banco de dados.
-A chave primária foi definida com @Id e configurada para ser gerada automaticamente através de @GeneratedValue(strategy = GenerationType.IDENTITY).
-Utilizou Lombok para gerar automaticamente métodos getters, setters e outros com a anotação @Data.

*Serviço (AlunoService.java)*

-A classe de serviço foi anotada com @Service, e contém a lógica de negócio para lidar com as operações de aluno.
-Utilizou @Autowired para injetar a dependência do repositório, permitindo a comunicação com o banco de dados através do método criarAluno, que chama o repositório para salvar o aluno.

*Repositório (AlunoRepository.java)*

-A interface AlunoRepository extende JpaRepository, fornecendo os métodos CRUD (Create, Read, Update, Delete) para a entidade Aluno.

*2. Teste da API com Insomnia ou Postman*

Configuração da Requisição

Método HTTP: POST
URL: http://localhost:8080/alunos (a rota definida no controlador)

![Captura de tela 2024-09-10 130849](https://github.com/user-attachments/assets/b3aed0e4-623f-42f1-8553-3dde9ade7367)

Após enviar a requisição, a API deve retornar o status 201 (Created), o que significa que o aluno foi criado com sucesso no banco de dados.

*3. Banco de Dados*

-O banco de dados utilizado foi o PostgreSQL, acessível na porta 5432.
-A URL de conexão JDBC para o banco foi configurada como: jdbc:postgresql://localhost:5432/aluno_online_projeto.
Assim, toda a estrutura foi testada localmente com o Insomnia/Postman, onde a criação de novos alunos foi validada com o retorno correto do status 201 e a persistência dos dados no banco de dados PostgreSQL.

![Captura de tela 2024-09-10 133915](https://github.com/user-attachments/assets/ca9874ee-8ffd-4b04-900d-9d48718fff29)
