<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Editar Categoría</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<header class="header">
    <div class="logo">
        <h1><a href="${pageContext.request.contextPath}/otros/inicio">La Sazón Peruana</a></h1>
    </div>

    <nav class="acciones">
        <!-- Ejemplo de rutas que podrías mapear en tus controladores -->
        <a href="${pageContext.request.contextPath}/platillo/list">Platillos</a>
        <a href="${pageContext.request.contextPath}/delivery/list">Delivery</a>
        <a href="${pageContext.request.contextPath}/otros/publicidad">Publicidad</a>
        <a href="${pageContext.request.contextPath}/categoria/list">Catálogo</a>
        <a href="${pageContext.request.contextPath}/venta/list">Gestión de Pedidos</a>
        <a href="${pageContext.request.contextPath}/metricas/dashboard">Metricas</a>
        <a href="${pageContext.request.contextPath}/otros/contacto">Contacto</a>
        <a href="${pageContext.request.contextPath}/login" class="btn-login">Iniciar Sesión</a>
    </nav>
</header>

<div class="container">
    <h2>Editar Categoría</h2>

    <form action="${pageContext.request.contextPath}/categoria/actualizar" method="post">
        <input type="hidden" name="id" value="${categoria.id}"/>

        <label>Nombre:</label>
        <input type="text" name="nombre" value="${categoria.nombre}" required/>

        <label>Descripción:</label>
        <textarea name="descripcion">${categoria.descripcion}</textarea>

        <label>Condición:</label>
        <select name="condicion">
            <option value="true" <c:if test="${categoria.condicion}">selected</c:if>>Activa</option>
            <option value="false" <c:if test="${!categoria.condicion}">selected</c:if>>Inactiva</option>
        </select>

        <button type="submit">Actualizar</button>
        <a href="${pageContext.request.contextPath}/categoria/list" class="btn-volver">Volver</a>
    </form>
</div>

<footer class="footer">
  <p>&copy; 2025 La Sazón Peruana. Todos los derechos reservados.</p>
</footer>

</body>
</html>
