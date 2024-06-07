<%@page import="com.mycompany.pw1.models.Usuarios"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Perfil de Usuario</title>
    <link href="css/home.css" rel="stylesheet" type="text/css"/>
</head>
<body>
    <!-- Barra de navegación -->
    <nav class="navbar">
        <div class="navbar-container">
            <a href="index.jsp" class="navbar-logo">Blog.com</a>
            <div class="navbar-links">
                <a href="home.jsp">Página Principal</a>
                <a href="PerfilUsuarioServlet">Mi Perfil</a>
                <a href="crearPublicacion.jsp">Hacer un Post</a>
                <a href="logout.jsp">Cerrar Sesión</a>
            </div>
        </div>
    </nav>

    <h1>Perfil de Usuario</h1>
    <% 
    Usuarios usuario = (Usuarios) request.getSession().getAttribute("usuario");
    if (usuario != null) {
    %>
    <form action="${pageContext.request.contextPath}/ActualizarPerfilServlet" method="post" enctype="multipart/form-data">
        <label for="nombre">Nombre:</label><br>
        <input type="text" id="nombre" name="nombre" value="<%= usuario.getNombre() %>"><br><br>

        <label for="apellidos">Apellidos:</label><br>
        <input type="text" id="apellidos" name="apellidos" value="<%= usuario.getApellidos() %>"><br><br>

        <label for="fecha_nacimiento">Fecha de Nacimiento:</label><br>
        <input type="date" id="fecha_nacimiento" name="fecha_nacimiento" value="<%= usuario.getFechaNacimiento() != null ? new java.text.SimpleDateFormat("yyyy-MM-dd").format(usuario.getFechaNacimiento()) : "" %>"><br><br>

        <label for="correo_electronico">Correo Electrónico:</label><br>
        <input type="email" id="correo_electronico" name="correo_electronico" value="<%= usuario.getCorreoElectronico() %>"><br><br>

        <label for="nombre_usuario">Nombre de Usuario:</label><br>
        <input type="text" id="nombre_usuario" name="nombre_usuario" value="<%= usuario.getNombreUsuario() %>"><br><br>

        <label for="contrasena">Contraseña:</label><br>
        <input type="password" id="contrasena" name="contrasena" value="<%= usuario.getContrasena() %>"><br><br>

        <label for="imagen_perfil">Imagen de Perfil:</label><br>
        <input type="file" id="imagen_perfil" name="imagen_perfil"><br><br>

        <input type="submit" value="Actualizar">
    </form>
    <% } else { %>
    <p>Usuario no encontrado. Por favor, inicia sesión.</p>
    <% } %>
</body>
<footer style="background-color: #f1f1f1; padding: 20px; text-align: center; position: fixed; width: 100%; bottom: 0;">
        <div>
            <p>&copy; 2024 UANL. Todos los derechos de creacion de este programa quedan reservador para la universidad UANL.</p>
        </div>
    </footer>
</html>
