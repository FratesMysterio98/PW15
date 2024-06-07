<%@page import="com.mycompany.pw1.models.Usuarios"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Perfil del Usuario</title>
    <link href="css/perfil.css" rel="stylesheet" type="text/css"/>
</head>
<body>
    <nav class="navbar">
        <div class="navbar-container">
            <a href="index.jsp" class="navbar-logo">Blog.com</a>
            <div class="navbar-links">
                <a href="home.jsp">Página Principal</a>
                <a href="perfil.jsp">Mi Perfil</a>
                <a href="crearPublicacion.jsp">Hacer un Post</a>
                <a href="logout.jsp">Cerrar Sesión</a>
            </div>
        </div>
    </nav>

    <h1>Perfil del Usuario</h1>
    <%
        Usuarios usuario = (Usuarios) request.getSession().getAttribute("usuario");
        if (usuario != null) {
    %>
        <div class="usuario-info">
            <img src="<%=request.getContextPath() + "/" + usuario.getImagenPerfil()%>" alt="Imagen de Perfil" />
            <p><strong>Nombre de Usuario:</strong> <%=usuario.getNombreUsuario()%></p>
            <p><strong>Nombre:</strong> <%=usuario.getNombre()%></p>
            <p><strong>Apellidos:</strong> <%=usuario.getApellidos()%></p>
            <p><strong>Correo Electrónico:</strong> <%=usuario.getCorreoElectronico()%></p>
            <p><strong>Fecha de Nacimiento:</strong> <%=usuario.getFechaNacimiento()%></p>
            <p><strong>Fecha de Creación:</strong> <%=usuario.getFechaCreacion()%></p>
        </div>
    <%
        } else {
    %>
        <p>Usuario no disponible</p>
    <%
        }
    %>
</body>
</html>


