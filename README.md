##Projeto AlunoOnline

O AlunoOnline é um sistema de gestão acadêmica desenvolvido com o apoio do Professor Kelson Almeida. Construído sobre o framework Spring Boot, ele oferece uma infraestrutura sólida e escalável para gerenciar as principais operações acadêmicas de forma eficiente.

Este projeto é uma API backend, sem interface gráfica, projetada para atender às necessidades de gestão acadêmica de alunos, professores, disciplinas e matrículas. A API permite realizar operações essenciais como criar, listar, buscar, atualizar e excluir dados desses elementos, bem como gerenciar matrículas e atualizar informações acadêmicas.

#Sumário

Arquitetura do Projeto
Estrutura do Projeto
Tecnologias Utilizadas
Operações Disponíveis

#Arquitetura do Projeto

O sistema adota uma arquitetura em camadas para assegurar escalabilidade e facilitar a manutenção:

-Controller: Define os endpoints da API e processa as requisições HTTP.
-Service: Contém a lógica de negócio da aplicação.
-Repository: Responsável pela persistência e recuperação de dados no banco de dados.
-DTO (Data Transfer Object): Facilita a transferência de dados entre as camadas sem expor diretamente as entidades do banco.

Fluxo de Operação

1 Entrada (Request): O cliente consome a API enviando uma requisição HTTP.
2 Controller: Manipula a requisição e direciona para a camada de serviço.
3 Service: Executa a lógica de negócio.
4 Repository: Realiza operações no banco de dados.
5 Resposta (Response): Retorna o resultado da operação ao cliente.
