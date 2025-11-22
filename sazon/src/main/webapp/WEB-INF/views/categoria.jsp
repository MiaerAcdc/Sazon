<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Gestión de Categorías</title>
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
    <h2>Gestión de Categorías</h2>

    <form action="${pageContext.request.contextPath}/categoria/guardar" method="post">
        <h3>Agregar Nueva Categoría</h3>

        <label>Nombre:</label>
        <input type="text" name="nombre" required placeholder="Ej: Postres, Bebidas...">

        <label>Descripción:</label>
        <textarea name="descripcion" placeholder="Describe la categoría..."></textarea>

        <label>Condición:</label>
        <select name="condicion">
            <option value="true">Activa</option>
            <option value="false">Inactiva</option>
        </select>

        <button type="submit">Agregar</button>
    </form>

    <h3>Listado de Categorías</h3>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Descripción</th>
                <th>Condición</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="cat" items="${categorias}">
                <tr>
                    <td>${cat.id}</td>
                    <td>${cat.nombre}</td>
                    <td>${cat.descripcion}</td>
                    <td>
                        <c:choose>
                            <c:when test="${cat.condicion}">✅ Activa</c:when>
                            <c:otherwise>❌ Inactiva</c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/categoria/editar/${cat.id}" class="btn btn-editar">✏️ Editar</a>
                        <a href="${pageContext.request.contextPath}/categoria/eliminar/${cat.id}" class="btn btn-eliminar"
                           onclick="return confirm('⚠️ ¿Deseas eliminar esta categoría?')">🗑️ Eliminar</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<footer class="footer">
  <p>&copy; 2025 La Sazón Peruana. Todos los derechos reservados.</p>
</footer>

</body>
</html>
