CREATE DEFINER=`root`@`localhost` PROCEDURE `AsignarCategoriaAPublicacion`(
    IN p_publicacion_id INT,
    IN p_categoria_id INT
)
BEGIN
    INSERT INTO publicacion_categoria (publicacion_id, categoria_id)
    VALUES (p_publicacion_id, p_categoria_id);
END   