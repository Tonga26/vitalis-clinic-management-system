🚀 New Version Available: Check out the modern Full-Stack version of this project built with Spring Boot and React here: https://github.com/Tonga26/vitalis-platform

# 🏥 Vitalis - Clinic Management System

> **Español:** Para leer la versión en español, baja hasta la sección "Versión en Español".

![Java](https://img.shields.io/badge/Java-21%2B-orange)
![Database](https://img.shields.io/badge/MySQL-8.0-blue)
![Status](https://img.shields.io/badge/Status-JDBC%20Phase%20Completed-success)
![License](https://img.shields.io/badge/License-MIT-green)

> **Current Status:** The Core JDBC Console Application is **Complete**. The project is now transitioning to **Phase 2: Web Migration**. 🚀

## 📖 About the Project

**Vitalis** is a robust application designed to manage patients and medical records for a fictional clinic.

**Phase 1 (Completed):**
The primary goal of the first phase was to implement a **Layered Architecture** without relying on high-level frameworks like Hibernate or Spring Data. This ensured a deep understanding of:
- **JDBC (Java Database Connectivity)** for raw SQL execution and mapping.
- **Connection Pooling** optimization using HikariCP.
- **Transaction Management** (ACID properties) manually handled via Services.
- **OOP Principles** (Inheritance, Polymorphism, Encapsulation) and **DAO Pattern**.

**Phase 2 (In Progress):**
We are now migrating this monolithic console application to a modern **Full Stack Web Application**.
- **Backend:** Migrating logic to **Spring Boot** (REST API).
- **Frontend:** Building a reactive user interface with **React**.

## 🛠️ Tech Stack

### Core (Phase 1)
* **Language:** Java (JDK 21+)
* **Build Tool:** Gradle (Kotlin DSL)
* **Database:** MySQL
* **Connectivity:** JDBC API + HikariCP
* **Logging:** SLF4J

### Future Stack (Phase 2 Roadmap)
* **Framework:** Spring Boot 3 (Web, Data JPA, Validation)
* **Frontend:** React.js + Tailwind CSS (Planned)
* **API Documentation:** OpenAPI / Swagger

## 📂 Architecture (Phase 1)

The project follows a strict separation of concerns: 

* **`config`**: Database connection setup (Singleton pattern) and Transaction Manager.
* **`model`**: Entity classes representing the database tables (Rich Domain Model).
* **`dao`**: Data Access Objects for CRUD operations using JDBC.
* **`service`**: Business logic layer (Validaciones, transactional atomic operations).
* **`main`**: Application entry point and Console User Interface (Menu Handler).

## ⚙️ Setup & Configuration (Console Version)

To run the JDBC Console version locally:

1.  **Clone the repository:**

    ```
    git clone https://github.com/Tonga26/vitalis-clinic-management-system.git
    ```

2.  **Database Setup:**

    * Create a MySQL database named `vitalis_db`.
    
    * Run the SQL scripts located in the `/sql` folder to create the tables.

3.  **Environment Variables:**

    * Locate the file `src/main/resources/db_example.properties`.
    
    * Create a copy named `db.properties` (this file is ignored by Git for security).
    
    * Fill in your actual MySQL credentials:
    
     ```
     db.url=jdbc:mysql//localhost:3306/vitalis_db
     db.user=YOUR_USERNAME
     db.password=YOUR_PASSWORD
     db.cant_max_con=10
     db.cant_min_con=5
     ```
---
**Author:** Gastón Giorgio
*Student of University Technician in Programming at UTN (Universidad Tecnológica Nacional)*

---

# 🏥 Vitalis - Sistema de Gestión de Clínicas (Versión en Español)

![Java](https://img.shields.io/badge/Java-21%2B-orange)
![Database](https://img.shields.io/badge/MySQL-8.0-blue)
![Estado](https://img.shields.io/badge/Estado-Fase%20JDBC%20Completada-success)
![Licencia](https://img.shields.io/badge/Licencia-MIT-green)

> **Estado Actual:** La aplicación de consola JDBC está **Finalizada**. El proyecto está transicionando a la **Fase 2: Migración Web**. 🚀

## 📖 Sobre el Proyecto

**Vitalis** es una aplicación robusta diseñada para gestionar pacientes e historias clínicas para un centro de salud ficticio.

**Fase 1 (Completada):**
El objetivo principal fue implementar una **Arquitectura en Capas** sin depender de frameworks de alto nivel. Esto aseguró una comprensión profunda de:
- **JDBC (Java Database Connectivity)** para la ejecución de SQL puro.
- **Pool de Conexiones** optimizado utilizando HikariCP.
- **Manejo de Transacciones** (propiedades ACID) gestionadas manualmente.
- **Patrón DAO** y principios sólidos de POO.

**Fase 2 (En Progreso):**
Actualmente estamos migrando esta aplicación monolítica de consola a una **Aplicación Web Full Stack**.
- **Backend:** Migración de la lógica a **Spring Boot** (REST API).
- **Frontend:** Desarrollo de una interfaz moderna con **React**.

## 🛠️ Stack Tecnológico

### Core (Fase 1)
* **Lenguaje:** Java (JDK 21+)
* **Build Tool:** Gradle (Kotlin DSL)
* **Base de Datos:** MySQL
* **Conectividad:** API JDBC + HikariCP
* **Logging:** SLF4J

### Stack Futuro (Hoja de Ruta Fase 2)
* **Framework:** Spring Boot 3 (Web, Data JPA, Validation)
* **Frontend:** React.js + Tailwind CSS (Planeado)
* **Documentación API:** OpenAPI / Swagger

## 📂 Arquitectura (Fase 1)

El proyecto sigue una estricta separación de responsabilidades:

* **`config`**: Configuración de conexión a BD (Patrón Singleton) y Gestor de Transacciones.
* **`model`**: Clases de Entidad que representan las tablas de la BD (Modelo de Dominio Rico).
* **`dao`**: Objetos de Acceso a Datos para operaciones CRUD utilizando JDBC.
* **`service`**: Capa de lógica de negocio (Atomicidad, Validaciones).
* **`main`**: Punto de entrada de la aplicación e interfaz de usuario en consola (Menu Handler).

## ⚙️ Instalación y Configuración (Versión Consola)

Para ejecutar la versión JDBC localmente:

1.  **Clonar el repositorio:**

    ```
    git clone https://github.com/Tonga26/vitalis-clinic-management-system.git
    ```

2.  **Configuración de Base de Datos:**

    * Crear una base de datos MySQL llamada `vitalis_db`.
    
    * Ejecutar los scripts SQL ubicados en la carpeta `/sql` para crear las tablas.

3.  **Variables de Entorno:**

    * Ubicar el archivo `src/main/resources/db_example.properties`.
    
    * Crear una copia llamada `db.properties` (este archivo es ignorado por Git por seguridad).
    
    * Completar con tus credenciales reales de MySQL:
    
     ```
     db.url=jdbc:mysql://localhost:3306/vitalis_db
     db.user=TU_USUARIO
     db.password=TU_PASSWORD
     db.cant_max_con=10
     db.cant_min_con=5
     ```
---
**Autor:** Gastón Giorgio
*Estudiante de la Tecnicatura en Programación en la Universidad Tecnológica Nacional (UTN)*

## 📝 Progreso / Roadmap

### Phase 1: JDBC Console App (Completed)
- [x] Inicialización del Proyecto y Configuración de Gradle
- [x] Configuración de Base de Datos con HikariCP
- [x] Implementación del Transaction Manager (ACID)
- [x] Crear Entidades (Paciente, HistoriaClinica)
- [x] Implementar Capa DAO (Operaciones CRUD)
- [x] Implementar Capa Service (Lógica de Negocio)
- [x] Interfaz de Usuario en Consola (Menu System)

### Phase 2: Spring Boot & React Migration (Upcoming)
- [ ] Inicializar proyecto Spring Boot
- [ ] Migrar lógica de JDBC a Spring Data JPA
- [ ] Crear controladores REST (API Endpoints)
- [ ] Configurar React y crear componentes visuales
- [ ] Integrar Backend y Frontend
