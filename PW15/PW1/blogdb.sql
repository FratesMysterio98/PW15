CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellidos VARCHAR(50) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    correo_electronico VARCHAR(100) UNIQUE NOT NULL,
    imagen_perfil VARCHAR(255),
    nombre_usuario VARCHAR(50) UNIQUE NOT NULL,
    contrasena VARCHAR(255) NOT NULL,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE publicaciones (
    idPublicacion INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    descripcion TEXT NOT NULL,
    estatus BOOLEAN NOT NULL,
    fechaCreacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fechaMovimiento TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    idUsuario INT,
    FOREIGN KEY (idUsuario) REFERENCES usuarios(id)
);
CREATE TABLE categorias (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL
);
CREATE TABLE publicacion_categoria (
    idPublicacion INT,
    idCategoria INT,
    PRIMARY KEY (idPublicacion, idCategoria),
    FOREIGN KEY (idPublicacion) REFERENCES publicaciones(idPublicacion),
    FOREIGN KEY (idCategoria) REFERENCES categorias(id)
);

select* from publicaciones

