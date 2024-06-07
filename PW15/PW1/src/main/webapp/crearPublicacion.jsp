<%-- 
    Document   : crearPublicacion
    Created on : 5 nov 2023, 21:12:34
    Author     : sadam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/crearpublicacion.css" rel="stylesheet" type="text/css"/>
        <title>Crear Publicación</title>
    </head>
    <body>
        <div id="divregistro" style="background-color:red;">
            <form id="formPublicacion" action="crearPublicacionServlet" method="post">
                <label for="ftitulo">Titulo:</label><br>
                <input class="inputTitulo" type="text" id="ftitulo" name="ftitulo"><br>
                <label for="fdesc">Descripcion:</label><br>
                <input class="inputDesc" type="text" id="fdesc" name="fdesc">
                <br><br>
                <input class="btnCrear" type="submit" id="crearPublicacion" name="crearPublicacion" value="Crear">
            </form>
        </div>
        <script src="C:/Users/angry/OneDrive/Escritorio/PW1/src/main/webapp/crearPublicacion.jsp"></script>
    </body>
</html>
