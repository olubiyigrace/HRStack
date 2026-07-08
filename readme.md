# HRStack

> A modern, multi-tenant Human Resource Management System (HRMS) built with Spring Boot for African Small and Medium-sized Businesses (SMBs).

---

## 📖 Overview

HRStack is a SaaS Human Resource Management System designed to help organizations manage employees securely from a centralized platform. The application follows a multi-tenant architecture, allowing multiple organizations (tenants) to use the same application while keeping their data isolated.

The backend is built with **Spring Boot 3**, leveraging **PostgreSQL**, **Redis**, **RabbitMQ**, **Spring Security**, and **JWT Authentication** to provide a scalable, secure, and production-ready foundation.

---

## ✨ Features Implemented

### Authentication & Authorization

- User Registration
- Secure User Login
- JWT Access Token Authentication
- Refresh Token Authentication
- Password Encryption using BCrypt
- Spring Security Integration
- Role-Based Access Control (RBAC)
- Stateless Authentication

---

### Multi-Tenant Architecture

- Tenant Context using ThreadLocal
- Tenant-aware request processing
- Organization data isolation
- SaaS-ready application foundation

---

### User Management

- User Registration
- User Authentication
- User Invitation System
- Accept Invitation
- Password Setup for Invited Users
- Login Alert Notifications

---

### Email Services

- HTML Email Templates with Thymeleaf
- User Invitation Emails
- Login Alert Emails
- Email Verification
- Password Setup Emails

---

### Infrastructure

- PostgreSQL Database
- Redis Integration
- RabbitMQ Integration
- Docker Compose Configuration
- Environment Variable Configuration

---

### API Documentation

- OpenAPI Documentation
- Swagger UI

---

## 🛠 Technology Stack

| Technology | Version |
|------------|---------|
| Java | 21+     |
| Spring Boot | 3.5.16  |
| Spring Security | 7       |
| PostgreSQL | 17      |
| Redis | 7       |
| RabbitMQ | Latest  |
| Maven | Latest  |
| Docker | Latest  |
| Thymeleaf | Latest  |
| JWT (JJWT) | Latest  |
| Lombok | Latest  |

---

## 📂 Project Structure

```text
src
├── main
│   ├── java
│   │   └── com.hrstack
│   │       ├── config
│   │       ├── controllers
│   │       ├── dto
│   │       ├── entities
│   │       ├── enums
│   │       ├── exceptions
│   │       ├── mappers
│   │       ├── orders
│   │       ├── properties
│   │       ├── repository
│   │       ├── security
│   │       ├── services
│   │       ├── tenant
│   │       ├── utils
│   │       └── HrStackApplication
│   │
│   └── resources
│       ├── certs
│       ├── db.migration.common
│       ├── static
│       ├── templates
│       └── static
│
└── test
```

---

## 🏗 Architecture

```text
                 Client
                    │
                    ▼
          Spring Security Filter
                    │
                    ▼
          Authentication Filter
                    │
                    ▼
           Tenant Resolution
                    │
                    ▼
             REST Controllers
                    │
                    ▼
             Service Layer
                    │
                    ▼
           Repository Layer
                    │
                    ▼
               PostgreSQL

         Redis                RabbitMQ
            │                     │
            ▼                     ▼
      Authentication Cache   Async Messaging

                    │
                    ▼
              Email Service
                    │
                    ▼
         Thymeleaf Email Templates
```

---

## 🔐 Authentication Flow

```text
Register
    │
    ▼
Verify Email
    │
    ▼
Login
    │
    ▼
Receive JWT Access Token
    │
    ▼
Access Protected Endpoints
    │
    ▼
Refresh Token
```

---

## 🏢 Multi-Tenant Design

HRStack uses a tenant-aware architecture where each incoming request is associated with a specific organization (tenant). The current tenant is stored using a `ThreadLocal` (`TenantContext`) throughout the lifecycle of the request.

This approach provides:

- Secure tenant isolation
- Organization-specific data access
- Scalable SaaS architecture

---

## 🐳 Docker Services

The project includes Docker Compose support for:

- PostgreSQL
- Redis
- RabbitMQ

Start all services:

```bash
docker compose up -d
```

---

## 🚀 Getting Started

### Clone the Repository

```bash
git clone https://github.com/yourusername/hrstack.git
```

```bash
cd hrstack
```

---

### Configure Environment Variables

Example:

```env
DB_NAME=hrstack
DB_PASSWORD=your_password
DB_PORT=5443

JWT_SECRET=your_secret_key

MAIL_USERNAME=example@gmail.com
MAIL_PASSWORD=your_email_password

REDIS_HOST=localhost
REDIS_PORT=6379

RABBITMQ_HOST=localhost
RABBITMQ_PORT=5672
```

---

### Start Infrastructure

```bash
docker compose up -d
```

---

### Build the Project

```bash
mvn clean install
```

---

### Run the Application

```bash
mvn spring-boot:run
```

---

## 📘 API Documentation

Once the application is running, access Swagger UI at:

```
http://localhost:8080/swagger-ui/index.html
```

---

## 📌 Current Modules

| Module | Status |
|----------|:------:|
| Authentication | ✅ |
| Authorization | ✅ |
| JWT Security | ✅ |
| Refresh Tokens | ✅ |
| User Registration | ✅ |
| User Login | ✅ |
| Invitation System | ✅ |
| Email Notifications | ✅ |
| Thymeleaf Email Templates | ✅ |
| PostgreSQL | ✅ |
| Redis | ✅ |
| RabbitMQ | ✅ |
| Docker Support | ✅ |
| Swagger Documentation | ✅ |
| Multi-Tenant Foundation | ✅ |
| Employee Management | 🚧 |
| Leave Management | 🚧 |
| Departments | 🚧 |
| Attendance | 🚧 |
| Performance Reviews | 🚧 |
| Payroll | 📅 Planned |

---

## 🛣 Roadmap

Upcoming features include:

- Employee Directory
- Departments
- Leave Requests
- Leave Approval Workflow
- Attendance Management
- Employee Document Management
- Organization Settings
- Audit Logs
- Notification Center
- Performance Reviews
- Payroll Integration
- Analytics & Reporting
- Google OAuth Login
- Two-Factor Authentication (2FA)

---

## 🧩 Development Principles

- Clean Architecture
- SOLID Principles
- RESTful API Design
- Dependency Injection
- Secure Authentication
- Layered Architecture
- Multi-Tenant Ready
- Dockerized Development Environment
- Scalable and Maintainable Codebase

---

## 💻 Authors

**Grace O. Olubiyi, Ayodeji B. Igbinlola & Precious A. Adeleye**

Backend Java Developers

---

## 📄 License

This project is currently under active development and is intended for educational and portfolio purposes. Licensing will be added in future releases.

























### need frontend url for invitedUser's login page for redirection
### need frontend url for normal login page to redirect user to change password and revoke session