<%-- 
    Document   : registro
    Created on : 6 oct 2023, 07:51:53
    Author     : sadam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro</title>
        <link href="css/hojaEstilo1.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <form id="formRegistro" action="registroServlet" method="post" enctype="multipart/form-data">
            <label for="imgRuta">Imagen Perfil:</label><br>
            <img for="imgRuta" id="imgPerfil" name="imgPerfil">
            <input class="inputImgPerfil" type="file" id="imgRuta" name="imagen_perfil"><br>

            <label for="fname">Usuario:</label><br>
            <input class="inputRegistrar" type="text" id="txtNombreUsuario" name="nombre_usuario" required /><br>

            <label for="lname">Contraseña:</label><br>
            <input class="inputRegistrar" type="password" id="txtPassword" name="contrasena" required /><br>

            <label for="nombre">Nombre:</label><br>
            <input class="inputRegistrar" type="text" id="txtNombre" name="nombre" required /><br>

            <label for="apellidos">Apellidos:</label><br>
            <input class="inputRegistrar" type="text" id="txtApellidos" name="apellidos" required /><br>

            <label for="correo_electronico">Correo Electrónico:</label><br>
            <input class="inputRegistrar" type="email" id="txtCorreoElectronico" name="correo_electronico" required /><br>

            <label for="fechaNacimiento">Fecha de Nacimiento:</label><br>
            <input class="inputRegistrar" type="date" id="fechaNacimiento" name="fecha_nacimiento" required /><br>

            <input class="btnRegistrar" type="submit" id="Registrar" name="bRegistrar" value="Registrar">
        </form>

        <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/bootstrap.bundle.min.js"></script>
    </body>
    <footer style="background-color: #f1f1f1; padding: 20px; text-align: center; position: fixed; width: 100%; bottom: 0;">
        <div>
            <p>&copy; 2024 UANL. Todos los derechos de creacion de este programa quedan reservador para la universidad UANL.</p>
        </div>
    </footer>
</html>
