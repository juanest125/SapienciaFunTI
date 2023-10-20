-- Crear la tabla Personas
CREATE TABLE Personas
(
    id        SERIAL PRIMARY KEY,
    nombre    VARCHAR(255),
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Crear la tabla Comentarios
CREATE TABLE Comentarios
(
    id         SERIAL PRIMARY KEY,
    persona_id INT,
    comentario TEXT,
    timestamp  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (persona_id) REFERENCES Personas (id)
);

-- Insertar 10 personas
INSERT INTO Personas (nombre)
VALUES ('Persona 1'),
       ('Persona 2'),
       ('Persona 3'),
       ('Persona 4'),
       ('Persona 5'),
       ('Persona 6'),
       ('Persona 7'),
       ('Persona 8'),
       ('Persona 9'),
       ('Persona 10');

-- Insertar comentarios aleatorios
INSERT INTO Comentarios (persona_id, comentario)
VALUES (1, 'Comentario sobre comida 1'),
       (1, 'Comentario sobre comida 2'),
       (2, 'Comentario sobre comida 3'),
       (2, 'Comentario sobre comida 4'),
       (3, 'Comentario sobre comida 5'),
       (3, 'Comentario sobre comida 6'),
       (4, 'Comentario sobre comida 7'),
       (4, 'Comentario sobre comida 8'),
       (5, 'Comentario sobre comida 9'),
       (5, 'Comentario sobre comida 10');