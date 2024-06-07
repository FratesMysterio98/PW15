CREATE DEFINER=`root`@`localhost` PROCEDURE `CrearPublicacion`(
    IN p_titulo VARCHAR(255),
    IN p_descripcion TEXT,
    IN p_usuario_id INT,
    IN p_eliminado BOOLEAN
)
BEGIN
    INSERT INTO publicaciones (titulo, descripcion, fecha_creacion, usuario_id, eliminado)
    VALUES (p_titulo, p_descripcion, NOW(), p_usuario_id, p_eliminado);
END