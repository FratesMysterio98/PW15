CREATE DATABASE blogDB;

USE blogDB;

CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50),
    apellidos VARCHAR(50),
    fecha_nacimiento DATE,
    correo_electronico VARCHAR(100) UNIQUE,
    imagen_perfil VARCHAR(255),
    nombre_usuario VARCHAR(50) UNIQUE,
    contrasena VARCHAR(255),
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE publicaciones (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255),
    descripcion TEXT,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    usuario_id INT,
    eliminado BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);

CREATE TABLE categorias (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50)
);

CREATE TABLE publicacion_categoria (
    publicacion_id INT,
    categoria_id INT,
    FOREIGN KEY (publicacion_id) REFERENCES publicaciones(id),
    FOREIGN KEY (categoria_id) REFERENCES categorias(id)
);