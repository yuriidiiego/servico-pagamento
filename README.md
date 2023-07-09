# Servico de Pagamento API 💳

API para gerenciamento de pagamentos de débitos de pessoas físicas e jurídicas. A API permite:

1. Criar um novo pagamento.
2. Atualizar o status de um pagamento.
3. Listar pagamentos com filtros.
4. Deletar um pagamento.

## Tecnologias Utilizadas 🛠️

| Tecnologia          | Descrição                                                                                    |
|---------------------|----------------------------------------------------------------------------------------------|
| ⚙️ Spring Boot         | Framework Java para desenvolvimento de aplicativos web e API REST.                            |
| 💾 Spring Data JPA     | Biblioteca do Spring para integração com bancos de dados relacionais usando JPA.             |
| 🌐 Spring Web          | Módulo do Spring para desenvolvimento de aplicativos web com suporte a API REST.             |
| 🏷️ Spring Validation   | Biblioteca do Spring para validação de dados e manipulação de erros.                         |
| 🗃️ H2 Database         | Banco de dados em memória para desenvolvimento e teste.                                      |
| 📚 Springdoc OpenAPI   | Biblioteca para geração de documentação da API com o OpenAPI (anteriormente Swagger).        |
| 🐘 PostgreSQL          | Sistema de gerenciamento de banco de dados relacional.                                        |

## Endpoints da API 🚦

A tabela abaixo lista todos os endpoints disponíveis na API, juntamente com suas descrições:

| Endpoint                            | Método   | Rota                                      | Payload                                                                                                                                                                                                      | Descrição                                                 |
|-------------------------------------|----------|-------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------|
| Criar um novo pagamento              | ✅ POST  | `/pagamentos`                             | `{"codigoDebito": 12345, "cpfCnpjPagador": "12345678901", "metodoPagamento": {...}, "valorPagamento": 100.00}`                                                                                               | Cria um novo pagamento                                    |
| Atualizar o status de um pagamento   | ⚡️ PATCH | `/pagamentos/{pagamentoId}/status`         | `{"novoStatus": "..."}`                                                                                                                                                                                      | Atualiza o status de um pagamento pelo ID                  |
| Listar pagamentos com filtros        | 🔍 GET   | `/pagamentos`                             | `{"codigoDebito": 12345 (opcional), "cpfCnpjPagador": "12345678901" (opcional), "statusPagamento": "..." (opcional)}`                                                                                       | Retorna uma lista de pagamentos com filtros (opcionais)   |
| Deletar um pagamento                 | ❌ DELETE| `/pagamentos/{pagamentoId}`                | N/A                                                                                                                                                                                                          | Deleta um pagamento pelo ID                               |

## Executando o Projeto ▶️

Siga as etapas abaixo para executar o projeto em seu ambiente de desenvolvimento:

1. Certifique-se de ter o Docker e o Docker Compose instalados em sua máquina.
2. Clone o repositório do projeto.
3. Acesse o diretório do projeto.
4. Rode o comando mvn clean package -DskipTests para gerar o .jar do projeto.
5. Execute o comando `docker-compose up -d` para iniciar os containers do projeto.

6. Após iniciar o projeto, você pode testar a API de denúncia de duas maneiras:

    - **Swagger**: Acesse a [documentação da API](http://localhost:3000/servico-pagamento/swagger-ui/index.html) para visualizar todos os endpoints, métodos, autenticação e payloads disponíveis. O Swagger fornece uma interface interativa para testar e explorar a API.

    - **Postman**: Importe o arquivo `Denuncia.postman_collection` que está localizado na pasta principal do projeto Spring Boot para o Postman. O arquivo contém uma coleção de requisições pré-configuradas para os endpoints da API. Você pode usar essas requisições para testar a API diretamente no Postman.
