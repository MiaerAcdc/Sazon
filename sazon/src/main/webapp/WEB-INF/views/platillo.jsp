<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Gestión de Platillos</title>
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
    <h2>Gestión de Platillos</h2>

    <!-- Formulario para agregar nuevo platillo -->
    <form action="${pageContext.request.contextPath}/platillo/guardar" method="post">
        <h3>Agregar Nuevo Platillo</h3>

        <label>Nombre:</label>
        <input type="text" name="nombre" required placeholder="Ej: Lomo Saltado">

        <label>Descripción:</label>
        <textarea name="descripcion" placeholder="Describe el platillo..."></textarea>

        <label>Categoría:</label>
        <select name="categoriaId" required>
            <option value="">-- Selecciona una categoría activa --</option>
            <c:forEach var="cat" items="${categorias}">
                <c:if test="${cat.condicion}">
                    <option value="${cat.id}">${cat.nombre}</option>
                </c:if>
            </c:forEach>
        </select>

        <label>Precio (S/):</label>
        <input type="number" step="0.01" name="precio" required>

        <label>Condición:</label>
        <select name="condicion">
            <option value="true">Activo</option>
            <option value="false">Inactivo</option>
        </select>

        <button type="submit">Agregar</button>
    </form>

    <!-- Tabla de platillos -->
    <h3>Listado de Platillos</h3>
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Descripción</th>
            <th>Categoría</th>
            <th>Precio</th>
            <th>Condición</th>
            <th>Acciones</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="pl" items="${platillos}">
            <tr>
                <td>${pl.id}</td>
                <td>${pl.nombre}</td>
                <td>${pl.descripcion}</td>
                <td>
                    <c:forEach var="cat" items="${categorias}">
                        <c:if test="${cat.id == pl.categoriaId}">
                            ${cat.nombre}
                        </c:if>
                    </c:forEach>
                </td>
                <td>S/. ${pl.precio}</td>
                <td>
                    <c:choose>
                        <c:when test="${pl.condicion}">✅ Activo</c:when>
                        <c:otherwise>❌ Inactivo</c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/platillo/editar/${pl.id}" class="btn btn-editar">✏️ Editar</a>
                    <a href="${pageContext.request.contextPath}/platillo/eliminar/${pl.id}"
                       class="btn btn-eliminar"
                       onclick="return confirm('⚠️ ¿Deseas eliminar este platillo?')">🗑️ Eliminar</a>
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
