<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login Page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="${pageContext.request.contextPath}/css/hojaEstilo1.css" rel="stylesheet" type="text/css"/>
</head>
<body>
    <form id="form" action="${pageContext.request.contextPath}/login" method="post">
        <label for="fusuario">Usuario:</label><br>
        <input class="inputLogin" type="text" id="fusuario" name="fusuario" required /><br>
        <label for="fcontra">Contraseña:</label><br>
        <input class="inputLogin" type="password" id="fcontra" name="fcontra" required /><br>
        <br>
        <input class="btnLogin" type="submit" id="Iniciar" value="Ingresar">
        <br>
        <button type="button" class="btnRegistrar" onclick="location.href='registro.jsp'">
            Registrarse
        </button>
    </form>

    <%-- Mostrar mensaje de error si existe --%>
    <c:if test="${not empty error}">
        <p style="color: red;">${error}</p>
    </c:if>

    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
</body>
<footer style="background-color: #f1f1f1; padding: 20px; text-align: center; position: fixed; width: 100%; bottom: 0;">
        <div>
            <p>&copy; 2024 UANL. Todos los derechos de creacion de este programa quedan reservador para la universidad UANL.</p>
        </div>
    </footer>
</html>
