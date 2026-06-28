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
```
mvn spring-boot:run
```

## ⚙ Application Properties

Configure your database and JWT settings in `application.properties`.

```properties
# Server Port
server.port=8080

# MySQL Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/invoice_db
spring.datasource.username=root
spring.datasource.password=your_password

# JPA Settings
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# JWT Secret Key
jwt.secret=your_secret_key
jwt.expiration=86400000
```

## 🌐 API Endpoints

### 🔹 Authentication APIs

- POST `/api/auth/register` → Register new user  
- POST `/api/auth/login` → Login user and generate JWT token

### 🔹 Invoice APIs (Protected)
- POST `/api/invoices` → Create new invoice  
- GET `/api/invoices` → Get all invoices  
- GET `/api/invoices/{id}` → Get invoice by ID  
- DELETE `/api/invoices/{id}` → Delete invoice

### 🔐 Note:
All invoice APIs require JWT token in request header:
Authorization: Bearer <token>

## ▶ How to Run the Project

### 1️⃣ Start MySQL Server
Make sure MySQL is running and database is created:
CREATE DATABASE invoice_db;

### 2️⃣ Configure Backend
Update `application.properties` with your MySQL username and password.

### 3️⃣ Build the Project
mvn clean install

### 4️⃣ Run the Application
mvn spring-boot:run

OR run main class:
InvoiceBackendApplication.java

### 5️⃣ Test APIs
Use Postman to test endpoints:
- Register User
- Login User (get JWT token)
- Access Invoice APIs using token






