---
# **User Management System with Spring Boot & JPA** 🚀

A robust and scalable RESTful API built using **Spring Boot 3**, **Spring Data JPA**, and **PostgreSQL** for seamless user data management.

![image](https://github.com/user-attachments/assets/c63cd961-b9c0-4a34-9418-536d86493956)
![image](https://github.com/user-attachments/assets/645047d3-60f6-4789-a87b-74f428ca5341)
![image](https://github.com/user-attachments/assets/0b36d9e7-6d58-4040-a2d7-b101ddd1a44f)

---
## 📦 Project Metadata

| Key              | Value                                                  |
| ---------------- | ------------------------------------------------------ |
| **Group**        | `com.example`                                          |
| **Artifact**     | `demo`                                                 |
| **Name**         | `userManagementApi`                                    |
| **Description**  | User Management API using Spring Boot as first Project |
| **Package Name** | `com.example.demo`                                     |
| **Java Version** | `24`                                                   |
| **Spring Boot**  | `3.5.3`                                                |
| **Build Tool**   | `Maven`                                                |
| **Packaging**    | `Jar`                                                  |

---

## 🧩 Dependencies Used

| Dependency               | Purpose                                                       |
| ------------------------ | ------------------------------------------------------------- |
| **Spring Web**           | Build RESTful web services using Spring MVC                   |
| **Spring Data JPA**      | ORM for database operations using Hibernate                   |
| **PostgreSQL Driver**    | JDBC driver to connect with PostgreSQL                        |
| **Validation (Jakarta)** | Apply field-level constraints like `@NotBlank`, `@Email` etc. |
| **Spring Boot DevTools** | Enables live reload and speeds up development workflow        |

---

## ⚙️ Features

| Feature                      | Description                                                                |
| ---------------------------- | -------------------------------------------------------------------------- |
| 🧾 **CRUD Operations**       | Create, Read, Update, Delete users                                         |
| 📄 **Pagination & Sorting**  | REST endpoints support pageable/sortable queries                           |
| ✅ **Validation**             | Input validated using Jakarta Bean annotations                             |
| 🕒 **Timestamps**            | Auto-generation of `createdAt` and `updatedAt` using Hibernate annotations |
| 🛢️ **Database Integration** | Uses PostgreSQL as backend DB                                              |
| 🧪 **Testing**               | Unit and integration tests written using `MockMvc` and JUnit               |

---
```
## ⚡ Quick Start

### 1. Clone the Repository

git clone https://github.com/Yogii9071/userManagementApi
cd userManagementApi

### 2. Configure the Database
Option A: Edit `src/main/resources/application.properties

spring.datasource.url=jdbc:postgresql://localhost:5432/userdb
spring.datasource.username=postgres
spring.datasource.password=yogi1234

Option B: Set environment variables
Linux / macOS / WSL

export DB_URL=jdbc:postgresql://localhost:5432/userdb
export DB_USER=postgres
export DB_PASS=yogi1234

# 3 Boot the app
./mvnw spring-boot:run          # hot‑reload dev mode
# or
./mvnw clean package
java -jar target/userManagementApi-0.0.1-SNAPSHOT.jar
```



---

## 🚀 Getting Started

### 🔧 Prerequisites

- Java 24
- PostgreSQL installed and running
- Maven installed

### 🛠️ Setup Instructions

```bash
# Clone the repository
git clone https://github.com/Yogii9071/userManagementApi
cd userManagementApi
````

**Step 1: Create PostgreSQL Database**

```sql
CREATE DATABASE userdb;
```

**Step 2: Update `application.properties`**

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/userdb
spring.datasource.username=postgres
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

**Step 3: Run the Application**

```bash
./mvnw spring-boot:run
```

---

## 📡 REST API Endpoints

| Method | Endpoint          | Description           |
| ------ | ----------------- | --------------------- |
| POST   | `/api/users`      | Create multiple users |
| GET    | `/api/users/all`  | Get all users         |
| GET    | `/api/users/{id}` | Get user by ID        |
| PUT    | `/api/users/{id}` | Update user by ID     |
| DELETE | `/api/users/{id}` | Delete user by ID     |
| GET    | `/api/users/page` | Get paginated users   |

---

## ✅ Validation Rules

| Field   | Constraint                   |
| ------- | ---------------------------- |
| `name`  | NotBlank, 2–100 characters   |
| `email` | NotBlank, Valid Email format |

---

## 🧪 Testing

### Run All Tests

```bash
./mvnw test
```

### Coverage

* ✅ `GET /api/users/{id}`
* ✅ `POST /api/users`
* ✅ Validation error handling
* ✅ Pagination logic

---

## 🗃️ Entity Schema

### `User` Entity

| Field       | Type          | Description                  |
| ----------- | ------------- | ---------------------------- |
| `id`        | Long          | Auto-generated primary key   |
| `name`      | String        | Required, 2–100 characters   |
| `email`     | String        | Required, valid email format |
| `createdAt` | LocalDateTime | Auto-set on creation         |
| `updatedAt` | LocalDateTime | Auto-updated on update       |

---

## 🙌 Acknowledgements

* [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/)
* [PostgreSQL JDBC Driver](https://jdbc.postgresql.org/)
* [Jakarta Validation](https://jakarta.ee/specifications/bean-validation/)

---
👨‍💻 Crafted With Care By
Yogesh Kumar — Passionate Backend Developer & Tech Enthusiast
---
