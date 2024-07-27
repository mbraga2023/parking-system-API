# User, Car, and ParkingHistory Diagram

This diagram shows the relationships between `User`, `Car`, and `ParkingHistory` in our system.

```mermaid
classDiagram
    class User {
        +String name
        +String phone
        +String email
        +List<Car> cars
    }

    class Car {
        +String manufacturer
        +String model
        +String color
        +int year
        +String plate
        +String type
        +List<ParkingHistory> history
    }

    class ParkingHistory {
        +String checkin
        +String checkout
    }

    User "1" -- "0..*" Car : owns
    Car "1" -- "0..*" ParkingHistory : has

# Projeto de Sistema de Estacionamento

Este projeto é uma aplicação de sistema de estacionamento desenvolvida com Spring Boot. O objetivo é gerenciar usuários, carros e históricos de estacionamento.

## Funcionalidades

- **CRUD de Usuários:** Criação, leitura, atualização e exclusão de usuários.
- **Gerenciamento de Carros:** Associar múltiplos carros a cada usuário e gerenciar seus históricos.
- **Histórico de Estacionamento:** Manter o controle dos registros de entrada e saída dos carros.

## Tecnologias Utilizadas

- **Spring Boot:** Framework principal para construção da aplicação.
- **H2 Database:** Banco de dados em memória para desenvolvimento e testes.
- **PostgreSQL:** Banco de dados utilizado em produção.
- **Swagger:** Documentação e interface de teste da API.

## Swagger

Para visualizar e interagir com a API através de uma interface gráfica, use o Swagger UI. O Swagger fornece uma documentação interativa da API, permitindo que você visualize todos os endpoints disponíveis e faça requisições diretamente do navegador.

### Acessar Swagger UI

A interface do Swagger UI está disponível em:

[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

## Endpoints da API

### URL de Produção

A API está disponível em:

[https://parking-system-api-production-1618.up.railway.app](https://parking-system-api-production-1618.up.railway.app)

### Usuários

- **Criar Usuário:** `POST /api/users`
- **Buscar Usuário por ID:** `GET /api/users/{id}`
- **Listar Todos os Usuários:** `GET /api/users`
- **Atualizar Usuário:** `PUT /api/users/{id}`
- **Excluir Usuário:** `DELETE /api/users/{id}`

### Carros

- **Criar Carro:** `POST /api/cars`
- **Buscar Carro por ID:** `GET /api/cars/{id}`
- **Listar Todos os Carros:** `GET /api/cars`
- **Atualizar Carro:** `PUT /api/cars/{id}`
- **Excluir Carro:** `DELETE /api/cars/{id}`

### Histórico de Estacionamento

- **Criar Histórico:** `POST /api/parking-history`
- **Buscar Histórico por ID:** `GET /api/parking-history/{id}`
- **Listar Todos os Históricos:** `GET /api/parking-history`
- **Atualizar Histórico:** `PUT /api/parking-history/{id}`
- **Excluir Histórico:** `DELETE /api/parking-history/{id}`

## Como Rodar o Projeto

1. **Clone o repositório:**

    ```bash
    git clone <URL_DO_REPOSITORIO>
    ```

2. **Navegue até o diretório do projeto:**

    ```bash
    cd nome-do-projeto
    ```

3. **Compile e execute o projeto com Maven:**

    ```bash
    mvn spring-boot:run
    ```

4. **Acesse a aplicação local em [http://localhost:8080](http://localhost:8080).**

## Contribuição

Sinta-se à vontade para contribuir para o projeto. Se você encontrar problemas ou tiver sugestões de melhorias, abra um issue ou envie um pull request.

## Licença

Este projeto é licenciado sob a Licença MIT.

