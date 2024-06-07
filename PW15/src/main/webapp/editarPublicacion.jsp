<%@page import="com.mycompany.pw1.models.Publicaciones"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Editar Publicación</title>
    <link href="css/editpublicss.css" rel="stylesheet" type="text/css"/>
</head>
<body>
    <h1>Editar Publicación</h1>
    <%
    Publicaciones publicacion = (Publicaciones) request.getAttribute("publicacion");
    if (publicacion != null) {
    %>
    <form action="${pageContext.request.contextPath}/ActualizarPublicacionServlet" method="post">
        <input type="hidden" name="idPublicacion" value="<%= publicacion.getIdPublicacion() %>">
        
        <label for="titulo">Título:</label><br>
        <input type="text" id="titulo" name="titulo" value="<%= publicacion.getTitulo() %>"><br><br>
        
        <label for="descripcion">Descripción:</label><br>
        <textarea id="descripcion" name="descripcion"><%= publicacion.getDescripcion() %></textarea><br><br>
        
        <label for="estatus">Estatus:</label><br>
        <input type="checkbox" id="estatus" name="estatus" <%= publicacion.isEstatus() ? "checked" : "" %>><br><br>
        
        <input type="submit" value="Actualizar">
    </form>
    <% } else { %>
    <p>Publicación no encontrada.</p>
    <% } %>
</body>
<footer style="background-color: #f1f1f1; padding: 20px; text-align: center; position: fixed; width: 100%; bottom: 0;">
        <div>
            <p>&copy; 2024 UANL. Todos los derechos de creacion de este programa quedan reservador para la universidad UANL.</p>
        </div>
    </footer>
</html>
