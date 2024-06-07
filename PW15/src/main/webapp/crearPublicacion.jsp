<%-- 
    Document   : crearPublicacion
    Created on : 5 nov 2023, 21:12:34
    Author     : sadam
--%>

<%@page import="java.util.List"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/crearpubli2.css" rel="stylesheet" type="text/css"/>
        <title>Crear Publicación</title>
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
                    <a href="logout">Cerrar Sesión</a>
                </div>
                <div class="navbar-search">
                    <input type="text" id="searchInput" placeholder="Buscar...">
                    <button id="searchBtn">Buscar</button>
                </div>
            </div>
        </nav>

        <div id="divregistro" class="form-container">
            <form id="formPublicacion" action="crearPublicacionServlet" method="post">
                <label for="ftitulo">Título:</label><br>
                <input class="inputTitulo" type="text" id="ftitulo" name="ftitulo"><br>

                <label for="fdesc">Descripción:</label><br>
                <textarea class="inputDesc" id="fdesc" name="fdesc"></textarea><br><br>

               
                </select><br><br>

                <input class="btnCrear" type="submit" id="crearPublicacion" name="crearPublicacion" value="Crear">
            </form>
        </div>

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
    <footer style="background-color: #f1f1f1; padding: 20px; text-align: center; position: fixed; width: 100%; bottom: 0;">
        <div>
            <p>&copy; 2024 UANL. Todos los derechos de creacion de este programa quedan reservador para la universidad UANL.</p>
        </div>
    </footer>
</html>
