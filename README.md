# 📝 Task Manager API

![Java](https://img.shields.io/badge/Java-21-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.4-brightgreen)
![H2 Database](https://img.shields.io/badge/Database-H2-orange)
![Lombok](https://img.shields.io/badge/Lombok-1.18.32-red)
![Validation](https://img.shields.io/badge/Validation-Jakarta-purple)
![License](https://img.shields.io/badge/License-MIT-yellow)

> **A production-grade REST API built while learning Spring Boot 3. This project follows clean architecture, atomic Git commits, and enterprise-level best practices.**

---

## 🚀 Project Overview

This is the **first project** in my Spring Boot portfolio journey. It is a Task Management Backend API that allows clients to perform full CRUD (Create, Read, Update, Delete) operations. 

The goal of this project was to master the **core fundamentals** of Spring Boot without getting distracted by complex frontend frameworks. 

---

## ✨ Key Features

- ✅ **Full CRUD Operations** - Create, view, update, and delete tasks seamlessly.
- ✅ **Three-Layer Architecture** - Clear separation between `Controller` (web layer), `Service` (business logic), and `Repository` (data access).
- ✅ **Global Exception Handling** - Consistent, beautiful JSON error responses (404 & 400) using `@ControllerAdvice`.
- ✅ **DTOs & Validation** - Uses `TaskRequestDTO` and `TaskResponseDTO` to hide internal entity fields and validate incoming data with `@Valid`.
- ✅ **In-Memory H2 Database** - Run instantly without installing any external database. Includes a web console to view your data.
- ✅ **Atomic Git History** - The commit history is clean, with each commit representing a single, working feature (great for portfolio reviews!).

---

## 🛠️ Tech Stack

| Layer       | Technology |
| :---        | :---       |
| **Language**| Java 21 (LTS) |
| **Framework** | Spring Boot 3.4.4 |
| **Build Tool** | Apache Maven |
| **Database** | H2 (In-Memory) with Hibernate/JPA |
| **Utilities** | Lombok (reduces boilerplate code) |
| **Validation**| Jakarta Bean Validation (`@NotBlank`, `@Size`, `@Valid`) |

---

## 🏃‍♂️ Getting Started (Run Locally)

Follow these steps to get the API up and running on your machine in under 2 minutes.

### Prerequisites
- Java 21 installed (`java -version` to check)
- IntelliJ IDEA, VS Code, or any IDE of your choice.

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/yuhantama-dev/task-manager.git
   cd task-manager
   ```

2. **Run the application**
   - Open the project in your IDE.
   - Locate the main class: `src/main/java/com/yourname/taskmanager/TaskManagerApplication.java`.
   - Click the **Run** button (green play button).

3. **Access the H2 Database Console**
   - Open your browser and go to: `http://localhost:8080/h2-console`
   - **JDBC URL**: `jdbc:h2:mem:taskdb`
   - **Username**: `sa`
   - **Password**: *(Leave empty)*
   - Click **Connect**.

---

## 📡 API Endpoints & Examples

All endpoints are prefixed with `/api/tasks`. Below are the available routes and sample `curl` commands.

| Method | Endpoint | Description | Status Code |
| :--- | :--- | :--- | :--- |
| **POST** | `/api/tasks` | Create a new task | 201 Created |
| **GET** | `/api/tasks` | Retrieve all tasks | 200 OK |
| **GET** | `/api/tasks/{id}` | Retrieve a single task by ID | 200 OK / 404 Not Found |
| **PUT** | `/api/tasks/{id}` | Fully update a task by ID | 200 OK / 404 Not Found |
| **DELETE** | `/api/tasks/{id}` | Delete a task by ID | 204 No Content / 404 Not Found |

---

### 1. Create a Task (POST)
**Request** (`POST /api/tasks`)
```json
{
    "title": "Learn Spring Boot",
    "description": "Build a portfolio project with clean Git history",
    "completed": false
}
```

**Response** (`201 Created`)
```json
{
    "id": 1,
    "title": "Learn Spring Boot",
    "description": "Build a portfolio project with clean Git history",
    "completed": false,
    "createdAt": "2026-06-30T12:34:56.123"
}
```

---

### 2. Get All Tasks (GET)
**Request** (`GET /api/tasks`)

**Response** (`200 OK`)
```json
[
    {
        "id": 1,
        "title": "Learn Spring Boot",
        "description": "Build a portfolio project",
        "completed": false,
        "createdAt": "2026-06-30T12:34:56.123"
    }
]
```

---

### 3. Get Task by ID (GET)
**Request** (`GET /api/tasks/1`)

**Response** (`200 OK`)
```json
{
    "id": 1,
    "title": "Learn Spring Boot",
    "description": "Build a portfolio project",
    "completed": false,
    "createdAt": "2026-06-30T12:34:56.123"
}
```

**Error Response** (`404 Not Found`)
```json
{
    "timestamp": "2026-06-30T12:35:00.123",
    "status": 404,
    "error": "Not Found",
    "message": "Task not found with id: 999",
    "path": "/api/tasks/999"
}
```

---

### 4. Update a Task (PUT)
**Request** (`PUT /api/tasks/1`)
```json
{
    "title": "Learn Spring Boot Advanced",
    "description": "Master Security and Microservices",
    "completed": true
}
```

**Response** (`200 OK`)
```json
{
    "id": 1,
    "title": "Learn Spring Boot Advanced",
    "description": "Master Security and Microservices",
    "completed": true,
    "createdAt": "2026-06-30T12:34:56.123"
}
```

---

### 5. Delete a Task (DELETE)
**Request** (`DELETE /api/tasks/1`)

**Response** (`204 No Content`) - *No body returned*

---

## 📁 Project Structure (Layered Architecture)

```
src/main/java/com/yourname/taskmanager/
├── TaskManagerApplication.java          # Application Entry Point
├── Task.java                            # JPA Entity (Database Table)
├── controller/
│   └── TaskController.java              # REST Endpoints (Web Layer)
├── service/
│   ├── TaskService.java                 # Business Logic (Service Layer)
│   └── TaskMapper.java                  # Entity <-> DTO Converter
├── repository/
│   └── TaskRepository.java              # Database Access (Repository Layer)
├── dto/
│   ├── TaskRequestDTO.java              # Incoming Data Structure
│   └── TaskResponseDTO.java             # Outgoing Data Structure
└── exception/
    ├── ResourceNotFoundException.java   # Custom 404 Exception
    ├── ErrorResponse.java               # Standard Error JSON Format
    └── GlobalExceptionHandler.java      # Global Exception Interceptor
```

---

## 🗂️ Atomic Git Commit History

This repository showcases **professional Git practices**. I followed a strict "atomic commit" strategy where each commit represents one fully working feature.

- `init: project skeleton with dependencies`
- `feat: add Task entity and JPA mapping`
- `feat: implement TaskRepository interface`
- `feat: implement TaskService (business logic)`
- `feat: implement TaskController (REST endpoints)`
- `feat: add global exception handling`
- `feat: introduce DTOs and validation`

---

## 📚 What I Learned

- How to set up a Spring Boot 3 project using Spring Initializr.
- The importance of the **Layered Architecture** (Controller-Service-Repository).
- How to map Java objects to database tables using JPA/Hibernate.
- How to expose RESTful endpoints with precise HTTP status codes (`201`, `404`, `204`).
- How to handle exceptions globally to return structured JSON errors instead of ugly stack traces.
- Why **DTOs** are critical for security and decoupling internal entities from external clients.

---

## 🚧 Future Improvements (Coming Next!)

1. **Add JWT Security** - Implement User Registration and Login (Authentication) so tasks are user-specific.
2. **Connect to PostgreSQL** - Swap out H2 for a production-grade PostgreSQL database.
3. **Frontend Integration** - Build a React.js or Vue.js frontend that consumes this API.
4. **Unit Testing** - Add JUnit and Mockito tests to ensure the service layer logic is bug-free.

---

## 🤝 Connect with Me

[![GitHub](https://img.shields.io/badge/GitHub-yuhantama--dev-181717?style=social&logo=github)](https://github.com/yuhantama-dev)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-yuhanadit-0A66C2?style=social&logo=linkedin)](https://linkedin.com/in/yuhanadit)

---

## 📄 License

This project is open-source and available under the [MIT License](LICENSE).