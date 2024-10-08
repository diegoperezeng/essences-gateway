# Essences Gateway API

Este projeto é uma API Gateway que faz a integração com um serviço de essências, utilizando o Feign Client para consumir os dados de uma API externa. Além disso, a aplicação implementa autenticação JWT para proteger as rotas e possui integração com Swagger para documentação da API.

## Estrutura do Projeto

A estrutura do projeto é a seguinte:

```
├── pom.xml
├── .mvn/wrapper/maven-wrapper.properties
└── src
    └── main
        ├── java
        │   └── com
        │       └── boticario
        │           └── essences_gateway
        │               ├── client
        │               │   └── EssencesClient.java
        │               ├── config
        │               │   ├── FeignClientConfig.java
        │               │   ├── SecurityConfig.java
        │               │   └── SpringDocConfiguration.java
        │               ├── controller
        │               │   ├── AuthController.java
        │               │   └── EssenceController.java
        │               ├── security
        │               │   ├── CustomUserDetailsService.java
        │               │   ├── JwtAuthenticationFilter.java
        │               │   ├── JwtRequestFilter.java
        │               │   └── JwtUtil.java
        │               └── service
        │                   └── EssenceService.java
        └── resources
            └── application.yml
```

## Pré-requisitos

Antes de rodar o projeto, certifique-se de ter as seguintes ferramentas instaladas:

- **Java 17** ou superior
- **Maven 3.6+**
- **Docker** (opcional, para rodar o banco de dados ou outros serviços externos)

## Configuração

### 1. Configuração do `application.yml`

O arquivo `application.yml` contém as configurações principais do projeto. Algumas variáveis de ambiente ou valores fixos precisam ser configurados, como o nome e URL do cliente Feign e as credenciais de autenticação básica.

#### Exemplo de `application.yml`:

```yaml
server:
  port: 8080

spring:
  application:
    name: Essences Gateway API

essences:
  client:
    name: essences-service
    url: http://localhost:8081
  basic:
    user: "essenceUser"
    password: "essencePassword"

users:
  mock:
    nameCrypto: "admin"
    passwordCrypto: "$2a$10$DOWSVbN6gA9XcJzZKZ1mSe2u9f3i6lR7y5tFQ1y5D9xL1c8g0s9Q2" # Senha criptografada para "password"
```

### 2. Dependências no `pom.xml`

O arquivo `pom.xml` já está configurado com todas as dependências necessárias, incluindo:

- **Spring Boot**
- **Spring Security**
- **Spring Cloud OpenFeign**
- **Resilience4j** (para rate limiting)
- **Springdoc OpenAPI** (para documentação Swagger)
- **JWT (io.jsonwebtoken)**

Certifique-se de que todas as dependências estejam instaladas corretamente ao rodar o comando `mvn clean install`.

### 3. Configuração do Feign Client

O Feign Client é configurado no arquivo `FeignClientConfig.java`, que adiciona um interceptor para autenticação básica. As credenciais são fornecidas no arquivo `application.yml`.

### 4. Configuração de Segurança

A segurança da aplicação é gerenciada pelo Spring Security, que utiliza JWT para autenticação. O arquivo `SecurityConfig.java` define as permissões de acesso às rotas e desabilita o CSRF, já que a aplicação é stateless.

O serviço `CustomUserDetailsService.java` carrega um usuário fictício para fins de autenticação. No entanto, você pode modificar este comportamento para buscar usuários de um banco de dados.

### 5. Documentação Swagger

A documentação da API é gerada automaticamente pelo Springdoc OpenAPI, e pode ser acessada em:

```
http://localhost:8080/swagger-ui.html
```

### 6. Autenticação JWT

A autenticação JWT é implementada utilizando a classe `JwtUtil.java`, que gera e valida tokens JWT. O token é gerado ao fazer login na rota `/auth/login` com um usuário e senha válidos.

Exemplo de requisição para gerar o token:

```
POST /auth/login
Content-Type: application/x-www-form-urlencoded

username=admin&password=password
```

A resposta será um token JWT que deve ser utilizado no header `Authorization` para acessar rotas protegidas:

```
Authorization: Bearer <token>
```

## Rodando o Projeto

### 1. Clonar o Repositório

Clone o repositório para sua máquina local:

```bash
git clone https://github.com/seu-usuario/essences-gateway.git
cd essences-gateway
```

### 2. Compilar o Projeto

Compile o projeto utilizando o Maven:

```bash
mvn clean install
```

### 3. Rodar o Projeto

Após a compilação, você pode rodar o projeto com o seguinte comando:

```bash
mvn spring-boot:run
```

A aplicação estará disponível em `http://localhost:8080`.

### 4. Rodar Testes

Os testes podem ser executados com o seguinte comando:

```bash
mvn test
```

Os testes de unidade são escritos utilizando **BDDMockito** para garantir a cobertura completa e o comportamento esperado do sistema.

## Endpoints Principais

### Autenticação

- **POST** `/auth/login`: Faz o login e retorna um token JWT.

### Essências

- **GET** `/essences`: Retorna a lista de essências.
- **GET** `/essences/{id}`: Retorna os detalhes de uma essência específica.

## Documentação da API

A documentação da API pode ser acessada via Swagger em:

```
http://localhost:8080/swagger-ui.html
```

## Rate Limiting

O serviço de essências utiliza **Resilience4j** para implementar rate limiting. Isso limita o número de requisições que podem ser feitas para a API de essências em um determinado período de tempo. A configuração padrão pode ser ajustada no arquivo `application.yml` ou diretamente nas anotações `@RateLimiter` no código.

## Considerações Finais

Este projeto é um exemplo de como construir uma API Gateway utilizando Spring Boot, Feign Client, JWT e Spring Security. Ele pode ser facilmente estendido para suportar mais funcionalidades, como integração com banco de dados, autenticação OAuth2, e mais.

Se você encontrar algum problema ou tiver dúvidas, sinta-se à vontade para abrir uma issue no repositório ou contribuir com melhorias.

---

**Autor**: Diego Perez
**Licença**: Apache 2.0
