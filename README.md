# 🏗️ Spring Clean Architecture

A production-ready REST API built with **Spring Boot** following **Clean Architecture** principles — featuring JPA dynamic filters with the Specification pattern, layered exception handling, and a fully decoupled domain layer.

---

## 📋 Table of Contents

- [Overview](#overview)
- [Architecture](#architecture)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [API Endpoints](#api-endpoints)
- [Dynamic Filters](#dynamic-filters)
- [Exception Handling](#exception-handling)
- [Design Decisions](#design-decisions)

---

## Overview

This project demonstrates how to apply **Clean Architecture** in a real Spring Boot application. The core idea is simple: the domain layer knows nothing about frameworks, databases, or HTTP — it only knows business rules.

Each layer has one responsibility and one only:

| Layer | Responsibility |
|---|---|
| `controller` | Receive HTTP requests, delegate to use cases |
| `application` | Orchestrate business logic through use cases |
| `domain` | Entities, ports, exceptions, DTOs |
| `infrastructure` | JPA, Hibernate, Specification, adapters |

---

## Architecture

```
┌─────────────────────────────────────┐
│           Controller                │  ← HTTP in/out
├─────────────────────────────────────┤
│         Use Cases                   │  ← one class per action
├─────────────────────────────────────┤
│           Domain                    │  ← entities, ports, exceptions
│   (knows nothing about JPA/Spring)  │
├─────────────────────────────────────┤
│        Infrastructure               │  ← JPA, Spec, Adapter
└─────────────────────────────────────┘
```

### The Port & Adapter pattern

The domain defines a `UserRepositoryPort` interface — a contract. It has no idea how data is stored. The `UserRepositoryAdapter` in infrastructure implements that contract using Spring Data JPA.

This means you could swap MySQL for MongoDB tomorrow and touch **zero domain code**.

```
UseCase → UserRepositoryPort (domain contract)
                ↓
         UserRepositoryAdapter (infrastructure — uses JPA)
                ↓
         UserJpaRepository (Spring Data)
                ↓
            MySQL
```

---

## Tech Stack

- **Java 17**
- **Spring Boot 3**
- **Spring Data JPA**
- **Hibernate**
- **MySQL**
- **Lombok**
- **Maven**

---

## Project Structure

```
src/main/java/com/clean/architeture/
│
├── controller/
│   ├── UserController.java
│   └── handler/
│       └── GlobalHandlerException.java
│
├── application/
│   ├── mapper/
│   │   └── UserMapper.java
│   └── usecase/user/
│       ├── FindUserUseCase.java
│       ├── FindUserByIdUseCase.java
│       ├── SaveUserUseCase.java
│       ├── UpdateUserUseCase.java
│       └── DeleteUserUseCase.java
│
├── domain/
│   ├── entity/
│   │   └── User.java
│   ├── port/
│   │   └── UserRepositoryPort.java
│   ├── exception/
│   │   ├── DomainException.java
│   │   └── UserNotFoundException.java
│   └── dto/
│       ├── filter/UserFilter.java
│       ├── request/UserRequestDTO.java
│       └── response/UserResponseDTO.java
│
└── infrastructure/
    └── persistence/user/
        ├── UserJpaRepository.java
        ├── UserRepositoryAdapter.java
        └── spec/
            └── UserSpec.java
```

---

## Getting Started

### Prerequisites

- Java 17+
- Maven 3.8+
- MySQL 8+

### 1. Clone the repository

```bash
git clone https://github.com/your-username/spring-clean-architecture.git
cd spring-clean-architecture
```

### 2. Create the database

```sql
CREATE DATABASE clean_arch_db;
```

### 3. Configure `application.properties`

```properties
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/clean_arch_db
spring.datasource.username=your_username
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 4. Populate with sample data

```sql
INSERT INTO users (email, cpf, phone) VALUES
('ana.silva@email.com',      '123.456.789-01', '(11) 98765-4321'),
('carlos.souza@email.com',   '234.567.890-12', '(21) 97654-3210'),
('mariana.lima@email.com',   '345.678.901-23', '(31) 96543-2109'),
('pedro.costa@email.com',    '456.789.012-34', '(41) 95432-1098'),
('julia.santos@email.com',   '567.890.123-45', '(51) 94321-0987'),
('lucas.ferreira@email.com', '678.901.234-56', '(61) 93210-9876'),
('beatriz.alves@email.com',  '789.012.345-67', '(71) 92109-8765'),
('rafael.mendes@email.com',  '890.123.456-78', '(81) 91098-7654'),
('camila.rocha@email.com',   '901.234.567-89', '(91) 90987-6543'),
('thiago.melo@email.com',    '012.345.678-90', '(11) 99876-5432'),
('larissa.nunes@email.com',  '111.222.333-44', '(21) 98765-1234'),
('gabriel.pires@email.com',  '222.333.444-55', '(31) 97654-2345'),
('fernanda.cruz@email.com',  '333.444.555-66', '(41) 96543-3456'),
('diego.barbosa@email.com',  '444.555.666-77', '(51) 95432-4567');
```

### 5. Run

```bash
mvn spring-boot:run
```

API will be available at `http://localhost:8081`

---

## API Endpoints

### Create user
```http
POST /user
Content-Type: application/json

{
    "email": "ana.silva@email.com",
    "cpf": "123.456.789-01",
    "phone": "(11) 98765-4321"
}
```

Response `201 Created`:
```json
{
    "id": 1,
    "email": "ana.silva@email.com",
    "cpf": "123.456.789-01",
    "phone": "(11) 98765-4321"
}
```

---

### List users (with optional filters)
```http
GET /user
GET /user?email=ana
GET /user?cpf=123.456.789-01
GET /user?phone=11
```

Response `200 OK`:
```json
[
    {
        "id": 1,
        "email": "ana.silva@email.com",
        "cpf": "123.456.789-01",
        "phone": "(11) 98765-4321"
    }
]
```

---

### Find user by ID
```http
GET /user/{id}
```

---

### Update user
```http
PUT /user/{id}
Content-Type: application/json

{
    "email": "novo@email.com",
    "cpf": "123.456.789-01",
    "phone": "(11) 99999-9999"
}
```

---

### Delete user
```http
DELETE /user/{id}
```

Response `204 No Content`

---

## Dynamic Filters

The API uses the **Specification pattern** to build dynamic `WHERE` clauses. Every filter is optional — only the ones provided are applied.

```
GET /user                          → SELECT * FROM users
GET /user?email=ana                → SELECT * FROM users WHERE email LIKE '%ana%'
GET /user?email=ana&phone=11       → SELECT * FROM users WHERE email LIKE '%ana%' AND phone LIKE '%11%'
```

Each filter is an isolated, reusable `Specification`:

```java
private static Specification<User> withEmail(String email) {
    return (root, query, cb) -> {
        if (email == null) return null; // ignored if not provided
        return cb.like(cb.lower(root.get("email")), "%" + email.toLowerCase() + "%");
    };
}
```

---

## Exception Handling

All exceptions are handled globally by `GlobalHandlerException`. Every error returns the same structured response:

```json
{
    "localDateTime": "2026-03-19T15:54:57.378",
    "httpStatus": 404,
    "message": "User not found!",
    "errors": null
}
```

| Exception | HTTP Status | When |
|---|---|---|
| `UserNotFoundException` | 404 | User not found by ID |
| `MethodArgumentNotValidException` | 400 | Invalid request fields |
| `DataIntegrityViolationException` | 409 | Duplicate email or constraint |
| `OptimisticLockingFailureException` | 409 | Concurrent update conflict |
| `DataAccessResourceFailureException` | 503 | Database unavailable |
| `Exception` | 500 | Unexpected error |

---

## Design Decisions

**Why one use case per action instead of one service?**
Each use case has a single responsibility. It's easier to test, easier to read, and changes to one action never affect others.

**Why Port & Adapter instead of injecting JpaRepository directly?**
The domain layer stays completely decoupled from JPA. If the persistence layer changes, only the adapter changes — zero impact on business logic.

**Why `@Transactional(readOnly = true)` on find use cases?**
Read-only transactions skip dirty checking, reduce memory usage, and signal clearly that no data will be modified.

**Why `final` fields with `@RequiredArgsConstructor`?**
Lombok generates a constructor with all `final` fields, which Spring uses for dependency injection. Cleaner than `@Autowired` and immutable by design.

---

## License

MIT
