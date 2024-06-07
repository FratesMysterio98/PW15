CREATE DEFINER=`root`@`localhost` PROCEDURE `insertarUsuario`(
    IN p_nombre VARCHAR(50),
    IN p_apellidos VARCHAR(50),
    IN p_fecha_nacimiento DATE,
    IN p_correo_electronico VARCHAR(100),
    IN p_imagen_perfil VARCHAR(255),
    IN p_nombre_usuario VARCHAR(50),
    IN p_contrasena VARCHAR(255)
)
BEGIN
    INSERT INTO usuarios (nombre, apellidos, fecha_nacimiento, correo_electronico, imagen_perfil, nombre_usuario, contrasena, fecha_creacion)
    VALUES (p_nombre, p_apellidos, p_fecha_nacimiento, p_correo_electronico, p_imagen_perfil, p_nombre_usuario, p_contrasena, NOW());
END