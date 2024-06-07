
<%@page import="com.mycompany.pw1.models.Publicaciones"%>
<%@page import="com.mycompany.pw1.models.Usuarios"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Home</title>
    <link href="css/home2.css" rel="stylesheet" type="text/css"/>
</head>
<body>
    <!-- Barra de navegación -->
    <nav class="navbar">
        <div class="navbar-container">
            <a href="home.jsp" class="navbar-logo">BlogPrgrogaweb.com</a>
            <div class="navbar-links">
                <a href="home.jsp">Página Principal</a>
                <a href="PerfilUsuarioServlet">Mi Perfil</a>
                <a href="crearPublicacion.jsp">Hacer un Post</a>
                <a href="index.jsp">Cerrar Sesión</a>
            </div>
            <div class="navbar-search">
                <form action="${pageContext.request.contextPath}/busquedaPublicacionServlet" method="post">
                    <input type="text" id="searchInput" name="txtBusqueda" placeholder="Buscar...">
                    <button id="searchBtn" type="submit">Buscar</button>
                </form>
            </div>
        </div>
    </nav>

    <div class="perfil-info">
        <%
        Usuarios usuario = (Usuarios) request.getSession().getAttribute("usuario");
        if (usuario != null) {
            String urlImg = usuario.getImagenPerfil();
        %>
            <img src="<%=request.getContextPath() + "/" + urlImg%>" alt="Imagen de Perfil" class="perfil-img"/>
            <h1>Bienvenido, <%=usuario.getNombreUsuario()%></h1>
        <%
        } else {
        %>
            <p>Usuario no encontrado. Por favor, inicia sesión.</p>
        <%
        }
        %>
    </div>

    <ul>
    </ul>

    <ul id="listaPublicaciones">
        <% 
        List<Publicaciones> listaPublicaciones = (List<Publicaciones>) request.getAttribute("ListaPublicaciones");
        if (listaPublicaciones != null) {
            for (Publicaciones item : listaPublicaciones) { 
        %>
                <li>
                    <h2><%= item.getTitulo() %></h2>
                    <p><%= item.getDescripcion() %></p>
                    <p>Creado por: <%= item.getUsuario().getNombreUsuario() %> el <%= new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(item.getFechaCreacion()) %></p>
                    <p>Última edición: <%= new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(item.getFechaMovimiento()) %></p>
                    <a href="EditarPublicacionServlet?idPublicacion=<%= item.getIdPublicacion() %>">Editar</a>
                </li>
        <% 
            } 
        } 
        %>
    </ul>

    <nav aria-label="Paginación">
        <ul id="paginacionPublicaciones" class="pagination"></ul>
    </nav>
    
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
</body>
<footer style="background-color: #f1f1f1; padding: 20px; text-align: center; position: fixed; width: 100%; bottom: 0;">
        <div>
            <p>&copy; 2024 UANL. Todos los derechos de creacion de este programa quedan reservador para la universidad UANL.</p>
        </div>
    </footer>
</html>
