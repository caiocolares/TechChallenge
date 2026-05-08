# Oficina Mecânica - Sistema de Gestão de Ordens de Serviço (MVP)

Este projeto é o Back-end da primeira versão (MVP) do sistema de gestão para uma oficina mecânica de médio porte. O foco principal é a gestão eficiente de ordens de serviço, clientes, veículos e peças, garantindo qualidade e escalabilidade.

## 🛠 Stack Tecnológica

*   **Linguagem:** Java 21 (LTS)
*   **Framework:** Spring Boot 4.0.6
*   **Gerenciador de Dependências:** Maven
*   **Banco de Dados:** PostgreSQL
* **Segurança:** Spring Security com Keycloak
* **Autenticação/Autorização:** Keycloak (Identity and Access Management)
* **Especificações:** Jakarta EE

## 📐 Arquitetura e Design

A aplicação foi desenvolvida seguindo os princípios de **Clean Architecture** e **Arquitetura Hexagonal**, combinados com **Domain-Driven Design (DDD)** para garantir um modelo de domínio rico e isolado.

*   **Modelos Ricos:** Lógica de negócio encapsulada nas entidades de domínio.
*   **Value Objects:** Uso de Java Records para imutabilidade e encapsulamento de atributos.
*   **Separação de Camadas:** Isolamento estrito entre `Domain`, `Application`, `Infrastructure` e `Presentation`.
*   **Padrões Utilizados:** Repository Pattern, Factory, Strategy e conceitos de CQRS.

## 📂 Estrutura do Projeto

*   `src/main/java/br/com/fiap/techchallenge/domain`: Núcleo da aplicação (Entidades, VOs, Interfaces de Repositório).
*   `src/main/java/br/com/fiap/techchallenge/application`: Casos de uso e orquestração.
*   `src/main/java/br/com/fiap/techchallenge/infrastructure`: Implementações técnicas (Persistência, Configurações).
*   `src/main/java/br/com/fiap/techchallenge/presentation`: Camada de entrada (Controllers REST).

## 🚀 Como Executar

### Pré-requisitos
*   JDK 21
*   Maven 3.9+
*   Docker e Docker Compose
* Keycloak (provisionado via Docker Compose)

### Passos para execução

1.  **Clonar o repositório:**
    ```bash
    git clone https://github.com/caiocolares/TechChallenge.git
    cd TechChallenge
    ```

2. **Configurar o Banco de Dados e Keycloak (via Docker):**
   ```bash
   docker-compose up -d
   ```
   O Keycloak estará disponível em `http://localhost:8080` com as credenciais padrão configuradas no docker-compose.

3. **Configurar Realm e Client no Keycloak:**
    * Acesse o console administrativo do Keycloak
    * Crie um realm para a aplicação (ex: `oficina-mecanica`)
    * Crie um client para o backend (ex: `oficina-backend`)
    * Configure as roles necessárias (ex: `ADMIN`, `MECANICO`, `ATENDENTE`)

4. **Compilar e Rodar a Aplicação:**
   ```bash
   ./mvnw spring-boot:run
   ```

## 🧪 Testes

Para executar os testes unitários e de integração:
```bash
./mvnw test
```

---
Desenvolvido como parte do Tech Challenge da FIAP.
