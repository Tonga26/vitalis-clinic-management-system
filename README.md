# 🏥 Vitalis - Clinic Management System

> **Español:** Para leer la versión en español, baja hasta la sección "Versión en Español".

![Java](https://img.shields.io/badge/Java-17%2B-orange)
![Database](https://img.shields.io/badge/MySQL-8.0-blue)
![Status](https://img.shields.io/badge/Status-In%20Development-yellow)
![License](https://img.shields.io/badge/License-MIT-green)

> **Note:** This project is currently under active development. 🚧

## 📖 About the Project

**Vitalis** is a robust console-based application designed to manage patients and medical records for a fictional clinic.

The primary goal of this project is to implement a **Layered Architecture** without relying on high-level frameworks like Hibernate or Spring Data initially. This approach ensures a deep understanding of:
- **JDBC (Java Database Connectivity)** for raw SQL execution.
- **Connection Pooling** optimization using HikariCP.
- **Transaction Management** (ACID properties) manually handled.
- **OOP Principles** (Inheritance, Polymorphism, Encapsulation).

🚀 **Future Roadmap:** Once the core logic is solidified, this monolithic application will be migrated to a Web API using **Spring Boot**.

## 🛠️ Tech Stack

* **Language:** Java (JDK 17+)
* **Build Tool:** Gradle (Kotlin DSL)
* **Database:** MySQL
* **Connectivity:** JDBC API + HikariCP (High-performance JDBC connection pool)
* **Logging:** SLF4J
* **Version Control:** Git & GitHub

## 📂 Architecture / Arquitectura

The project follows a strict separation of concerns: 

* **`config`**: Database connection setup (Singleton pattern) and Transaction Manager.
* **`model`**: Entity classes representing the database tables (Rich Domain Model).
* **`dao`**: Data Access Objects for CRUD operations using JDBC.
* **`service`**: Business logic layer (Validations, calculations).
* **`main`**: Application entry point and Console User Interface (Menu).

## ⚙️ Setup & Configuration

To run this project locally, you need to configure the database credentials.

1.  **Clone the repository:**
    ```
    git clone https://github.com/Tonga26/vitalis-clinic-management-system.git
    ```

2.  **Database Setup:**
    * Create a MySQL database named `vitalis_db`.
    * Run the SQL scripts located in the `/sql` folder to create the tables.

3.  **Environment Variables:**
    * Locate the file `src/main/resources/db_example.properties`.
    * Create a copy named `db.properties` (this file is ignored by Git for security).
    * Fill in your actual MySQL credentials:
        ```properties
        db.url=jdbc:mysql://localhost:3306/vitalis_db
        db.user=YOUR_USERNAME
        db.password=YOUR_PASSWORD
        db.cant_max_con=10
        db.cant_min_con=5
        ```

---
**Author:** Gastón  
*Student of University Technician in Programming at UTN (Universidad Tecnológica Nacional)*

---

# 🏥 Vitalis - Sistema de Gestión de Clínicas (Versión en Español)

![Java](https://img.shields.io/badge/Java-17%2B-orange)
![Database](https://img.shields.io/badge/MySQL-8.0-blue)
![Estado](https://img.shields.io/badge/Estado-En%20Desarrollo-yellow)
![Licencia](https://img.shields.io/badge/Licencia-MIT-green)

> **Nota:** Este proyecto se encuentra actualmente en desarrollo activo. 🚧

## 📖 Sobre el Proyecto

**Vitalis** es una aplicación de consola robusta diseñada para gestionar pacientes e historias clínicas para un centro de salud ficticio.

El objetivo principal de este proyecto es implementar una **Arquitectura en Capas** sin depender inicialmente de frameworks de alto nivel como Hibernate o Spring Data. Este enfoque asegura una comprensión profunda de:
- **JDBC (Java Database Connectivity)** para la ejecución de SQL puro.
- **Pool de Conexiones** optimizado utilizando HikariCP.
- **Manejo de Transacciones** (propiedades ACID) gestionadas manualmente.
- **Principios POO** (Herencia, Polimorfismo, Encapsulamiento).

🚀 **Hoja de Ruta:** Una vez consolidada la lógica central, esta aplicación monolítica será migrada a una API Web utilizando **Spring Boot**.

## 🛠️ Stack Tecnológico

* **Lenguaje:** Java (JDK 17+)
* **Build Tool:** Gradle (Kotlin DSL)
* **Base de Datos:** MySQL
* **Conectividad:** API JDBC + HikariCP (Pool de conexiones de alto rendimiento)
* **Logging:** SLF4J
* **Control de Versiones:** Git & GitHub

## 📂 Arquitectura

El proyecto sigue una estricta separación de responsabilidades:

* **`config`**: Configuración de conexión a BD (Patrón Singleton) y Gestor de Transacciones.
* **`model`**: Clases de Entidad que representan las tablas de la BD (Modelo de Dominio Rico).
* **`dao`**: Objetos de Acceso a Datos para operaciones CRUD utilizando JDBC.
* **`service`**: Capa de lógica de negocio (Validaciones, reglas).
* **`main`**: Punto de entrada de la aplicación e interfaz de usuario en consola (Menú).

## ⚙️ Instalación y Configuración

Para ejecutar este proyecto localmente, necesitas configurar las credenciales de la base de datos.

1.  **Clonar el repositorio:**
    ```
    git clone https://github.com/Tonga26/vitalis-clinic-management-system.git
    ```

2.  **Configuración de Base de Datos:**
    * Crear una base de datos MySQL llamada `vitalis_db`.
    * Ejecutar los scripts SQL ubicados en la carpeta `/sql` para crear las tablas.

3.  **Variables de Entorno:**
    * Ubicar el archivo `src/main/resources/db_example.properties`.
    * Crear una copia llamada `db.properties` (este archivo es ignorado por Git por seguridad).
    * Completar con tus credenciales reales de MySQL:
        ```properties
        db.url=jdbc:mysql://localhost:3306/vitalis_db
        db.user=TU_USUARIO
        db.password=TU_CONTRASEÑA
        db.cant_max_con=10
        db.cant_min_con=5
        ```

## 📝 Tareas / To-Do

- [x] Inicialización del Proyecto y Configuración de Gradle
- [x] Configuración de Base de Datos con HikariCP
- [x] Implementación del Transaction Manager (ACID)
- [ ] Crear Entidades (Base, Paciente, HistoriaClinica)
- [ ] Implementar Capa DAO (Operaciones CRUD)
- [ ] Implementar Capa Service (Lógica de Negocio)
- [ ] Interfaz de Usuario en Consola
- [ ] Migración a Spring Boot (Web App)

---
**Autor:** Gastón  
*Estudiante de Tecnicatura en Programación en la UTN (Universidad Tecnológica Nacional)*
