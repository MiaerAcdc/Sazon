<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Iniciar Sesión - La Sazón Peruana</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style-login.css">

    <script>
        window.onload = function() {
            <c:if test="${not empty mensaje}">
                alert("${mensaje}");
            </c:if>
            <c:if test="${not empty error}">
                alert("${error}");
            </c:if>
        }
    </script>
</head>

<body>
<div class="login-wrapper">
    <div class="login-card">
        <div class="login-logo">
            🍽️
        </div>
        <h1>La Sazón Peruana</h1>
        <h2>Iniciar Sesión</h2>

        <form action="${pageContext.request.contextPath}/login/validar" method="post">
            <div class="input-group">
                <label for="usuario">Usuario</label>
                <input type="text" id="usuario" name="usuario" placeholder="Ingresa tu usuario" required>
            </div>

            <div class="input-group">
                <label for="password">Contraseña</label>
                <input type="password" id="password" name="password" placeholder="Ingresa tu contraseña" required>
            </div>

            <button type="submit" class="btn-login">Ingresar</button>
        </form>

        <div class="extra-links">
        </div>
    </div>
</div>
</body>
</html>

