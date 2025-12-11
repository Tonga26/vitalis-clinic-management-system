CREATE DATABASE IF NOT EXISTS vitalis_db
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE vitalis_db;

CREATE TABLE paciente (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    eliminado BOOLEAN DEFAULT FALSE,
    nombre VARCHAR(80) NOT NULL,
    apellido VARCHAR(80) NOT NULL,
    dni VARCHAR(15) NOT NULL UNIQUE,
    fecha_nacimiento DATE NULL
);

CREATE TABLE historia_clinica (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    eliminado BOOLEAN DEFAULT FALSE,
    nro_historia VARCHAR(20) UNIQUE,
    grupo_sanguineo VARCHAR(3) NULL,
    antecedentes TEXT NULL,
    medicacion_actual TEXT NULL,
    observaciones TEXT NULL,
    paciente_id BIGINT NOT NULL UNIQUE,
    fecha_apertura DATE NULL,
    CONSTRAINT fk_historia_paciente FOREIGN KEY (paciente_id) REFERENCES paciente(id),
    CONSTRAINT chk_grupo_sanguineo CHECK (grupo_sanguineo IS NULL OR grupo_sanguineo
    IN ('A+', 'A-', 'B+', 'B-', 'AB+', 'AB-', 'O+', 'O-'))
);

