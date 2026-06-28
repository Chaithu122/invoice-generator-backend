# 🧾 Invoice Generator Application - Backend

## 📖 About the Project

The Invoice Generator Backend is a Spring Boot application designed to manage invoice-related operations efficiently. It provides REST APIs to create, store, retrieve, and delete invoice data using a MySQL database.

This backend works with the frontend application to generate professional invoices in a simple and fast way.

## ✨ Features

- Create new invoices
- Store invoice details in MySQL database
- Retrieve all invoices
- Get invoice details by ID
- Delete invoices
- RESTful API support for frontend integration

  ## 🛠 Tech Stack

- Java 
- Spring Boot
- Spring Data JPA
- MySQL Database
- Maven Build Tool
- REST APIs

## 🔐 Security & Authentication

This project uses Spring Security with JWT (JSON Web Token) for authentication and authorization.

### 🔹 Features

- User registration and login
- Password encryption using BCrypt
- JWT token generation after login
- Secure REST APIs using token-based authentication
- Role-based authorization (USER / ADMIN)
- Stateless session management

  ## 🚀 Project Setup

### 1️⃣ Clone the Repository
```
git clone <your-repository-link>
```

### 2️⃣ Open the Project
```
Open the project in STS / IntelliJ / VS Code
```

### 3️⃣ Create MySQL Database
```
CREATE DATABASE invoice_db;
```

### 4️⃣ Configure application.properties
```
Set your database credentials and JWT secret key.
```

### 5️⃣ Install Dependencies
```
mvn clean install
```

### 6️⃣ Run the Application
mvn spring-boot:run



