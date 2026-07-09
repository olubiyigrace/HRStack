# HRStack

<p align="center">
  <strong>A modern, multi-tenant Human Resource Management System (HRMS) built with Spring Boot for African Small and Medium-sized Businesses (SMBs).</strong>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-17-orange" alt="Java">
  <img src="https://img.shields.io/badge/Spring%20Boot-3.5.16-brightgreen" alt="Spring Boot">
  <img src="https://img.shields.io/badge/PostgreSQL-17-blue" alt="PostgreSQL">
  <img src="https://img.shields.io/badge/Redis-7-red" alt="Redis">
  <img src="https://img.shields.io/badge/RabbitMQ-Enabled-orange" alt="RabbitMQ">
  <img src="https://img.shields.io/badge/Flyway-Database%20Migration-red" alt="Flyway">
  <img src="https://img.shields.io/badge/Cloudinary-Media%20Storage-blueviolet" alt="Cloudinary">
  <img src="https://img.shields.io/badge/License-MIT-lightgrey" alt="License">
</p>

---

## 📖 Overview

HRStack is a **SaaS** Human Resource Management System designed to help organizations manage their workforce from a centralized platform.

The application is built around a **multi-tenant architecture**, allowing multiple organizations to securely use the same application while keeping their data completely isolated.

The project is being developed with scalability, maintainability, and security in mind using modern Spring technologies and cloud services.

---

## 📑 Table of Contents

- [📖 Overview](#-overview)
- [✨ Current Features](#-current-features)
    - [🔐 Authentication & Security](#-authentication--security)
    - [👥 User Management](#-user-management)
    - [🏢 Multi-Tenant Architecture](#-multi-tenant-architecture)
    - [📧 Email Services](#-email-services)
    - [☁️ Cloud Media Management](#️-cloud-media-management)
    - [🗄 Database](#-database)
    - [⚡ Infrastructure](#-infrastructure)
    - [📚 API Documentation](#-api-documentation)
- [🛠 Technology Stack](#-technology-stack)
- [📂 Project Structure](#-project-structure)
- [🏗 Architecture](#-architecture)
- [🔐 Authentication Flow](#-authentication-flow)
- [🏢 Multi-Tenancy](#-multi-tenancy)
- [🗄 Database Migration](#-database-migration)
- [☁️ Cloudinary Integration](#️-cloudinary-integration)
- [⚡ Redis](#-redis)
- [📨 RabbitMQ](#-rabbitmq)
- [🐳 Docker](#-docker)
- [🚀 Getting Started](#-getting-started)
- [📖 API Documentation](#-api-documentation-1)
- [📌 Implemented Modules](#-implemented-modules)
- [🚀 Roadmap](#-roadmap)
- [🧱 Development Principles](#-development-principles)
- [👩‍💻 Author](#-author)
- [📄 License](#-license)

# ✨ Current Features

## 🔐 Authentication & Security

- Secure User Registration
- User Login
- JWT Authentication
- Refresh Token Authentication
- BCrypt Password Encryption
- Spring Security Integration
- Stateless Authentication
- Role-Based Access Control (RBAC)

---

## 👥 User Management

- User Registration
- User Authentication
- User Invitation System
- Accept Invitation
- Password Creation for Invited Users
- Login Alert Notifications
- Email Verification Support

---

## 🏢 Multi-Tenant Architecture

- Tenant-aware request processing
- ThreadLocal Tenant Context
- Organization data isolation
- SaaS-ready architecture

---

## 📧 Email Services

- HTML Email Templates
- Thymeleaf Email Rendering
- Invitation Emails
- Login Alert Emails
- Password Setup Emails

---

## ☁️ Cloud Media Management

- Cloudinary Integration
- Secure Image Upload
- Cloud-Based Storage
- Public Image URL Generation
- Optimized Image Delivery

---

## 🗄 Database

- PostgreSQL
- Flyway Database Migrations
- Spring Data JPA
- Hibernate ORM

---

## ⚡ Infrastructure

- Redis Integration
- RabbitMQ Integration
- Docker Compose Support
- Environment-based Configuration

---

## 📚 API Documentation

- OpenAPI 3
- Swagger UI

---

# 🛠 Technology Stack

| Technology | Purpose |
|------------|---------|
| Java 21 | Programming Language |
| Spring Boot 3.5.16 | Backend Framework |
| Spring Security 6 | Authentication & Authorization |
| Spring Data JPA | Data Access Layer |
| Hibernate 6 | ORM |
| PostgreSQL 17 | Relational Database |
| Flyway | Database Version Control |
| Redis 7 | Caching |
| RabbitMQ | Asynchronous Messaging |
| Cloudinary | Cloud Media Storage |
| Thymeleaf | Email Templates |
| Spring Mail | Email Service |
| Spring Validation | Request Validation |
| SpringDoc OpenAPI | API Documentation |
| JWT (JJWT) | Token Authentication |
| Lombok | Boilerplate Reduction |
| Maven | Dependency Management |
| Docker | Containerization |
| Docker Compose | Local Infrastructure |

---

# 📂 Project Structure

```text
HRStack
│
├── src
│   ├── main
│   │
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
│   ├── resources
│   │   ├── db
│   │   │   └── migration
│   │   ├── templates
│   │   ├── certs
│   │   ├── static
│   │   └── application.yml
│   │
│   └── test
│
├── docker-compose.yml
├── pom.xml
└── README.md
```

---

# 🏗 Architecture

```text
                   Client
                      │
                      ▼
             Spring Security
                      │
                      ▼
          JWT Authentication Filter
                      │
                      ▼
           Tenant Resolution Layer
                      │
                      ▼
             REST Controllers
                      │
                      ▼
              Business Services
                      │
                      ▼
             Repository Layer
                      │
                      ▼
                 PostgreSQL
                      │
     ┌────────────────┼─────────────────┐
     │                │                 │
     ▼                ▼                 ▼
   Redis         RabbitMQ         Cloudinary
     │                │                 │
     ▼                ▼                 ▼
 Authentication   Async Tasks      Image Storage
     Cache
                      │
                      ▼
               Email Service
                      │
                      ▼
         Thymeleaf Email Templates
```

---

# 🔐 Authentication Flow

```text
User Registration
        │
        ▼
Email Verification
        │
        ▼
Login
        │
        ▼
JWT Access Token
        │
        ▼
Protected APIs
        │
        ▼
Refresh Token
```

---

# 🏢 Multi-Tenancy

HRStack follows a **tenant-per-organization** model.

Each request is associated with an organization and processed using a `TenantContext` powered by `ThreadLocal`.

This provides:

- Secure tenant isolation
- Shared infrastructure
- Organization-specific data
- Scalable SaaS deployment

---

# 🗄 Database Migration

Database schema management is handled using **Flyway**.

Migration scripts are located in:

```text
src/main/resources/db/migration
```

Example:

```text
V1__create_roles.sql
V2__create_users.sql
V3__create_permissions.sql
```

Flyway automatically applies pending migrations during application startup.

---

# ☁️ Cloudinary Integration

HRStack uses **Cloudinary** for cloud-based media storage.

Current capabilities include:

- Secure image uploads
- Cloud-hosted storage
- Optimized media delivery
- Public URL generation
- Scalable asset management

---

# ⚡ Redis

Redis is used for high-speed, in-memory data storage.

Current use cases include:

- Application caching
- Temporary data storage
- Performance optimization

---

# 📨 RabbitMQ

RabbitMQ is integrated to support asynchronous processing.

Current and planned use cases include:

- Email processing
- Background jobs
- Event-driven communication
- Notification services

---

# 🐳 Docker

Docker Compose provisions all required development services.

Included containers:

- PostgreSQL
- Redis
- RabbitMQ

Start services:

```bash
docker compose up -d
```

Stop services:

```bash
docker compose down
```

---

# 🚀 Getting Started

## Clone the Repository

```bash
git clone https://github.com/yourusername/hrstack.git
```

```bash
cd hrstack
```

---

## Configure Environment Variables

Example:

```env
DB_NAME=hrstack
DB_PASSWORD=your_password
DB_PORT=5443

JWT_SECRET=your_secret

MAIL_USERNAME=your_email@gmail.com
MAIL_PASSWORD=your_password

REDIS_HOST=localhost
REDIS_PORT=6379

RABBITMQ_HOST=localhost
RABBITMQ_PORT=5672

CLOUDINARY_CLOUD_NAME=your_cloud_name
CLOUDINARY_API_KEY=your_api_key
CLOUDINARY_API_SECRET=your_api_secret
```

---

## Start Docker Containers

```bash
docker compose up -d
```

---

## Build

```bash
mvn clean install
```

---

## Run

```bash
mvn spring-boot:run
```

---

# 📖 API Documentation

After starting the application, visit:

```text
http://localhost:8080/swagger-ui/index.html
```

---

# 📌 Implemented Modules

| Module | Status |
|----------|:------:|
| Authentication | ✅ |
| Authorization | ✅ |
| JWT Authentication | ✅ |
| User Registration | ✅ |
| Login | ✅ |
| User Invitation | ✅ |
| Email Notifications | ✅ |
| Thymeleaf Templates | ✅ |
| Multi-Tenant Architecture | ✅ |
| PostgreSQL Integration | ✅ |
| Flyway Migration | ✅ |
| Spring Data JPA | ✅ |
| Redis Integration | ✅ |
| RabbitMQ Integration | ✅ |
| Cloudinary Integration | ✅ |
| Docker Support | ✅ |
| Swagger Documentation | ✅ |
| Employee Management | 🚧 In Progress |
| Leave Management | 🚧 In Progress |
| Departments | 🚧 Planned |
| Attendance | 🚧 Planned |
| Performance Management | 🚧 Planned |
| Payroll | 📅 Planned |

---

# 🚀 Roadmap

Upcoming features include:

- Employee Directory
- Organization Management
- Department Management
- Leave Requests & Approvals
- Attendance Tracking
- Employee Onboarding
- Employee Document Management
- Performance Reviews
- Organization Settings
- Notifications
- Audit Logs
- Payroll Management
- Reports & Analytics
- Google OAuth Login
- Two-Factor Authentication (2FA)

---

# 🧱 Development Principles

HRStack is built around modern backend engineering practices:

- Clean Architecture
- SOLID Principles
- RESTful API Design
- Layered Architecture
- Dependency Injection
- Stateless Authentication
- Multi-Tenant Design
- Database Versioning with Flyway
- Cloud-Based Media Management
- Asynchronous Processing
- Scalable Infrastructure
- Production-Ready Configuration

---

---

## 💻 Authors

**Grace O. Olubiyi, Ayodeji B. Igbinlola & Precious A. Adeleye**

Backend Java Developers

---

### Skills

- Java
- Spring Boot
- Spring Security
- REST APIs
- PostgreSQL
- Flyway
- Redis
- RabbitMQ
- Cloudinary
- Docker
- Maven

---

# 📄 License

This project is currently under active development as part of a portfolio and learning journey. A formal open-source license will be added in a future release.


### need frontend url for invitedUser's login page for redirection
### need frontend url for normal login page to redirect user to change password and revoke session
