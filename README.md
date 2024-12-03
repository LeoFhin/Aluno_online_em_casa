# Projeto AlunoOnline

O AlunoOnline é um sistema de gestão acadêmica desenvolvido com o apoio do Professor Kelson Almeida. Construído sobre o framework Spring Boot, ele oferece uma infraestrutura sólida e escalável para gerenciar as principais operações acadêmicas de forma eficiente.

Este projeto é uma API backend, sem interface gráfica, projetada para atender às necessidades de gestão acadêmica de alunos, professores, disciplinas e matrículas. A API permite realizar operações essenciais como criar, listar, buscar, atualizar e excluir dados desses elementos, bem como gerenciar matrículas e atualizar informações acadêmicas.

## Sumário

- Arquitetura do Projeto
- Estrutura do Projeto
- Tecnologias Utilizadas

## Arquitetura do Projeto

### O sistema adota uma arquitetura em camadas para assegurar escalabilidade e facilitar a manutenção:

- Controller: Define os endpoints da API e processa as requisições HTTP.
- Service: Contém a lógica de negócio da aplicação.
- Repository: Responsável pela persistência e recuperação de dados no banco de dados.
- DTO (Data Transfer Object): Facilita a transferência de dados entre as camadas sem expor diretamente as entidades do banco.

### Fluxo de Operação

1. Entrada (Request): O cliente consome a API enviando uma requisição HTTP.
2.  Controller: Manipula a requisição e direciona para a camada de serviço.
3. Service: Executa a lógica de negócio.
4. Repository: Realiza operações no banco de dados.
5. Resposta (Response): Retorna o resultado da operação ao cliente.

## Estrutura do Projeto

### config

Inclui as configurações gerais do sistema:

- SwaggerConfig: Configuração para gerar a documentação interativa da API com Swagger.

### controller

Contém os controladores responsáveis por expor os endpoints da API:

- AlunoController: Gerencia operações relacionadas aos alunos:
  - Criar, listar, buscar por ID, deletar e atualizar informações de alunos.
- DisciplinaController: Gerencia operações relacionadas às disciplinas:
  - Criar, listar, buscar por ID, deletar e atualizar informações de disciplinas.
- MatriculaAlunoController: Gerencia as matrículas dos alunos:
  - Criar matrícula, trancar matrícula, atualizar notas e exibir histórico do aluno.
- ProfessorController: Gerencia operações relacionadas aos professores:
  -Criar, listar, buscar por ID, deletar e atualizar informações de professores.

### dtos

Contém os objetos de transferência de dados utilizados entre as camadas do sistema:

- AtualizarNotasRequest: DTO para envio de notas de um aluno em uma disciplina.
- DisciplinasAlunoResponse: Representa as disciplinas de um aluno, incluindo nome, professor, notas e status.
- HistoricoAlunoResponse: Detalha o histórico acadêmico de um aluno, incluindo disciplinas cursadas e respectivas notas.

### enums

Define categorias ou estados usados no sistema:

- MatriculaAlunoStatusEnum: Representa os status possíveis de uma matrícula:
  - MATRICULADO: O aluno está ativo na disciplina.
  - TRANCADO: A matrícula foi trancada.
  - APROVADO: O aluno foi aprovado na disciplina.
  - REPROVADO: O aluno não atingiu a aprovação.

### model

Contém as entidades principais do sistema:

- Aluno: Representa os dados de um aluno.
- Disciplina: Representa uma disciplina oferecida pela instituição.
- MatriculaAluno: Representa a matrícula de um aluno em uma disciplina.
- Professor: Representa os dados de um professor.

### repository

Repositórios responsáveis pela interação com o banco de dados:

- AlunoRepository, DisciplinaRepository, MatriculaAlunoRepository, ProfessorRepository.

## service

Classes de serviço que implementam a lógica de negócio do sistema:

- AlunoService, DisciplinaService, MatriculaAlunoService, ProfessorService.

## Tecnologias Utilizadas

- Spring Boot: Framework para o desenvolvimento ágil de aplicações Java baseadas em microserviços. Oferece uma arquitetura robusta e de fácil configuração, permitindo a criação de aplicativos independentes.
- Spring Data JPA: Simplifica a interação com bancos de dados utilizando a API de Persistência Java (JPA). Abstrai a persistência de dados, facilitando operações CRUD.
- Spring Web: Módulo que suporta o desenvolvimento de APIs RESTful, incluindo o controle de requisições HTTP, validação e segurança.
- SQL Driver: Responsável pela comunicação com bancos de dados relacionais.
  - O projeto utiliza H2 Database, um banco de dados em memória ideal para testes e desenvolvimento.
  - Pode ser substituído por outros bancos de dados, como MySQL ou PostgreSQL, para ambientes de produção.
- Swagger: Ferramenta para documentação interativa da API, permitindo a visualização e teste direto das funcionalidades expostas.
- Lombok: Biblioteca que elimina a necessidade de escrever código repetitivo, como getters, setters e construtores. Isso melhora a legibilidade e a manutenção do código.

## Operações Disponíveis

### Aluno

As operações relacionadas aos alunos incluem:
- Criar aluno: Registra um novo aluno no sistema.
- Listar alunos: Retorna uma lista com todos os alunos cadastrados.
- Buscar aluno por ID: Recupera os dados de um aluno a partir de seu ID exclusivo.
- Deletar aluno por ID: Remove um aluno do sistema utilizando seu ID.
- Atualizar aluno: Atualiza as informações de um aluno já existente.

### Professor

As operações relacionadas aos professores incluem:
- Criar professor: Registra um novo professor no sistema.
- Listar professores: Retorna uma lista com todos os professores cadastrados.
- Buscar professor por ID: Recupera os dados de um professor a partir de seu ID exclusivo.
- Deletar professor por ID: Remove um professor do sistema utilizando seu ID.
- Atualizar professor: Atualiza as informações de um professor já existente.

### Disciplina

As operações relacionadas às disciplinas incluem:
- Criar disciplina: Adiciona uma nova disciplina ao sistema.
- Listar disciplinas: Retorna uma lista com todas as disciplinas cadastradas.
- Buscar disciplina por ID: Recupera os dados de uma disciplina a partir de seu ID exclusivo.
- Deletar disciplina por ID: Remove uma disciplina do sistema utilizando seu ID.
- Atualizar disciplina: Atualiza as informações de uma disciplina já existente.

### Matrícula

As operações relacionadas às matrículas incluem:

- Criar matrícula: Realiza a matrícula de um aluno em uma disciplina.
- Trancar matrícula: Permite trancar a matrícula de um aluno em uma disciplina, desde que o status do aluno seja MATRICULADO.
- Atualizar notas: Atualiza as notas de um aluno em uma disciplina específica.
- Exibir histórico: Exibe o histórico completo de disciplinas de um aluno, incluindo notas e status de matrícula.
#
