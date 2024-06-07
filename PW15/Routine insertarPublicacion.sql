CREATE DEFINER=`root`@`localhost` PROCEDURE `insertarPublicacion`(
    IN p_titulo VARCHAR(255),
    IN p_descripcion TEXT,
    IN p_estatus BOOLEAN,
    IN p_idUsuario INT
)
BEGIN
    INSERT INTO publicaciones (titulo, descripcion, estatus, fechaCreacion, fechaMovimiento, idUsuario)
    VALUES (p_titulo, p_descripcion, p_estatus, NOW(), NOW(), p_idUsuario);
END