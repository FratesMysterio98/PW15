<%@page import="com.mycompany.pw1.models.Publicaciones"%>
<%@page import="com.mycompany.pw1.models.Usuarios"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Home</title>
    <link href="css/home.css" rel="stylesheet" type="text/css"/>
</head>
<body>
    <!-- Barra de navegación -->
    <nav class="navbar">
        <div class="navbar-container">
            <a href="index.jsp" class="navbar-logo">Blog.com</a>
            <div class="navbar-links">
                <a href="home.jsp">Página Principal</a>
                <a href="perfil.jsp">Mi Perfil</a>
                <a href="crearPublicacion.jsp">Hacer un Post</a>
                <a href="logout.jsp">Cerrar Sesión</a>
            </div>
            <div class="navbar-search">
                <input type="text" id="searchInput" placeholder="Buscar...">
                <button id="searchBtn">Buscar</button>
            </div>
        </div>
    </nav>

    <h1>Home</h1>
    <form id="formBuscarPublicacion" action="${pageContext.request.contextPath}/busquedaPublicacionServlet" method="post">
        <label for="txtBusqueda">Título:</label><br>
        <input class="inputTitulo" type="text" id="txtBusqueda" name="txtBusqueda"><br><br>
        <input class="btnBuscar" type="submit" id="buscarPublicacion" name="buscarPublicacion" value="Buscar">
    </form>
    <div class="relative-container">
    </div>
    <% 
    Usuarios usuario = (Usuarios) request.getSession().getAttribute("usuario");
    if (usuario != null) {
        String idUsuario = String.valueOf(usuario.getId());
        List<Publicaciones> listaPublicaciones = (List<Publicaciones>) request.getAttribute("ListaPublicaciones");
        String urlImg = usuario.getImagenPerfil();
    %>
        <h1>Usuario ID: <%=idUsuario%></h1>
        <img src="<%=request.getContextPath() + "/" + urlImg%>" alt="Imagen de Perfil" />
        <ul id="listaPublicaciones">
            <% if (listaPublicaciones != null) {
                for (Publicaciones item : listaPublicaciones) { %>
                    <li>
                        <h2><%=item.getTitulo()%></h2>
                        <p><%=item.getDescripcion()%></p>
                    </li>
            <% } } %>
        </ul>
    <%
    } else {
    %>
        <p>Usuario no disponible. Por favor, inicie sesión.</p>
    <%
    }
    %>
    <nav aria-label="Paginación">
        <ul id="paginacionPublicaciones" class="pagination"></ul>
    </nav>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script>
        $(document).ready(function(){
            $("#searchBtn").click(function(){
                var searchQuery = $("#searchInput").val();
                window.location.href = "https://www.bing.com/search?q=" + searchQuery;
            });
        });
    </script>
</body>
</html>
