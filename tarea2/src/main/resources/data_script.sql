-- Creación de la base de datos
CREATE DATABASE tarea2_juan;
USE tarea2_juan;

-- Creación de la tabla Planta
CREATE TABLE Planta (
    codigo VARCHAR(50) UNIQUE NOT NULL,
    nombrecomun VARCHAR(100) NOT NULL,
    nombrecientifico VARCHAR(100) NOT NULL,
    PRIMARY KEY (codigo)
);

-- Creación de la tabla Ejemplar
CREATE TABLE Ejemplar (
    id BIGINT AUTO_INCREMENT UNIQUE NOT NULL,
    nombre VARCHAR(100),
    codigo_planta VARCHAR(50) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (codigo_planta) REFERENCES Planta(codigo) ON DELETE CASCADE ON UPDATE CASCADE -- No puede haber un ejemplar de una planta no registrada
);

-- Creación de un evento para actualizar el campo 'nombre' de los ejemplares
DELIMITER //
CREATE EVENT actualizar_nombres_ejemplares
ON SCHEDULE EVERY 1 MINUTE
DO
  UPDATE Ejemplar
  SET nombre = CONCAT(codigo_planta, '_', id)
  WHERE nombre IS NULL OR nombre = '';//
DELIMITER ;

-- Creación de la tabla Persona
CREATE TABLE Persona (
    id BIGINT AUTO_INCREMENT UNIQUE NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    PRIMARY KEY (id)
);

-- Creación de la tabla Credenciales
CREATE TABLE Credenciales (
    id BIGINT NOT NULL, 
    usuario VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id) REFERENCES Persona(id) ON DELETE CASCADE ON UPDATE CASCADE -- No puede haber unas credenciales sin asignar a una persona
);

-- Creación de la tabla Mensaje
CREATE TABLE Mensaje (
    id BIGINT AUTO_INCREMENT UNIQUE NOT NULL,
    fechahora DATETIME NOT NULL,
    mensaje TEXT NOT NULL,
    id_ejemplar BIGINT NOT NULL,
    id_persona BIGINT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_ejemplar) REFERENCES Ejemplar(id) ON DELETE CASCADE ON UPDATE CASCADE, -- No puede haber mensajes sobre ejemplares inexisentes
    FOREIGN KEY (id_persona) REFERENCES Persona(id) ON DELETE CASCADE ON UPDATE CASCADE -- No puede haber mensajes de personas inexistentes
);

-- Datos de prueba:
-- Plantas
INSERT INTO Planta (codigo, nombrecomun, nombrecientifico) VALUES 
('PL001', 'Maculís', 'Tabebuia rosea'),
('PL002', 'Helecho plateado', 'Cyathea dealbata'),
('PL003', 'Orquídea', 'Phalaenopsis amabilis');

-- Ejemplares (los nombres se generarán automáticamente por el evento)
INSERT INTO Ejemplar (codigo_planta) VALUES 
('PL001'), -- Generará nombre: "PL001_1" usando el evento
('PL002'), -- Generará nombre: "PL002_2" usando el evento
('PL003'); -- Generará nombre: "PL003_3" usando el evento

-- Personas
INSERT INTO Persona (nombre, email) VALUES 
('Juan García', 'juangarcia@educastur.es'),
('Luis DBB', 'luisdbb@educastur.es');

-- Credenciales
INSERT INTO Credenciales (id, usuario, password) VALUES 
(1, 'juanga', 'password123'),
(2, 'luisdbb', 'password1234');

-- Mensajes (asegurarse de que id_ejemplar y id_persona existan)
INSERT INTO Mensaje (fechahora, mensaje, id_ejemplar, id_persona) VALUES 
('2024-10-24 10:30:00', 'El ejemplar está en buen estado', 1, 1),
('2024-10-24 11:00:00', 'El ejemplar necesita más agua', 2, 2);