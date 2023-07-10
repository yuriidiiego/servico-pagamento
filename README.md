# Servico de Pagamento API 💳

API para o gerenciamento de pagamentos de débitos de pessoas físicas e jurídicas, como parte do teste técnico da FADESP (Fundação Amparo e Desenvolvimento da Pesquisa). Essa API oferece uma série de recursos, permitindo que os usuários possam:

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
| 📚 Springdoc OpenAPI   | Biblioteca para geração de documentação da API com o OpenAPI (anteriormente Swagger).        |
| 🐘 PostgreSQL          | Sistema de gerenciamento de banco de dados relacional.                                        |

## Endpoints da API 🚦

A tabela abaixo lista todos os endpoints disponíveis na API, juntamente com suas descrições:

| Endpoint                                     | Método   | Rota                                    | Payload                                                | Param                                                     | Descrição                                                          |
|----------------------------------------------|----------|-----------------------------------------|--------------------------------------------------------|-----------------------------------------------------------|--------------------------------------------------------------------|
| 🔒 Criar um novo pagamento                    | ✉️ POST   | /pagamentos                             | PagamentoRequest (`{"codigoDebito": 12345, "cpfCnpj": "12345678901", "metodoPagamento": CARTAO_CREDITO, "numeroCartao?": 1234567890123456, "valor": 100.00}`)                                | -                                                         | Recebe um novo pagamento                                           |
| 🔄 Atualizar o status de um pagamento         | 🔄 PATCH  | /pagamentos/{pagamentoId}/status         | -                                                      | pagamentoId (Long, required = true)<br>novoStatus (Enum)   | Atualiza o status de um pagamento                                   |
| 🔍 Listar pagamentos                          | 🔎 GET    | /pagamentos                             | -                                                      | `{"codigoDebito": 12345, "cpfCnpj": "12345678901", "status": "..."}` (required = false)     | Lista pagamentos com filtros opcionais                              |
| ❌ Deletar um pagamento                       | 🗑️ DELETE | /pagamentos/{pagamentoId}               | -                                                      | pagamentoId (Long, required = true)                        | Deleta um pagamento                                                 |

## Executando o Projeto ▶️

Siga as etapas abaixo para executar o projeto em seu ambiente de desenvolvimento:

1. Certifique-se de ter o Docker e o Docker Compose instalados em sua máquina.
2. Clone o repositório do projeto com o comando `git clone https://github.com/yuriidiiego/servico-pagamento.git`.
3. Acesse o diretório do projeto.
4. Execute o comando `docker-compose up -d` para iniciar os containers do projeto.

5. Após iniciar o projeto, você pode testar a API de pagamentos de duas maneiras:

    - **Swagger**: Acesse a [documentação da API](http://localhost:8080/servico-pagamento/swagger-ui/index.html) para visualizar todos os endpoints, métodos, autenticação e payloads disponíveis. O Swagger fornece uma interface interativa para testar e explorar a API.

    - **Postman**: Importe o arquivo `servico-pagamento.postman_collection` que está localizado na pasta principal do projeto Spring Boot para o Postman. O arquivo contém uma coleção de requisições pré-configuradas para os endpoints da API. Você pode usar essas requisições para testar a API diretamente no Postman.

## Acessando o Banco de Dados 💾

Acesse o banco de dados utilizado pela API para visualizar e gerenciar os dados dos pagamentos.

- :link: **URL**: `jdbc:postgresql://localhost:5432/servico-pagamento`
- :bust_in_silhouette: **Usuário**: `postgres`
- :key: **Senha**: `k29DlaweP65`
- :house: **Host**: `localhost`
- :door: **Porta**:  `5432`
- :file_cabinet: **Banco de dados**: `servico-pagamento`

Use as credenciais acima para se conectar ao banco de dados PostgreSQL. Você pode utilizar ferramentas como pgAdmin, DBeaver ou o cliente de linha de comando `psql` para acessar e executar consultas no banco de dados.

Certifique-se de ter o PostgreSQL instalado em sua máquina e configurado corretamente antes de acessar o banco de dados.
