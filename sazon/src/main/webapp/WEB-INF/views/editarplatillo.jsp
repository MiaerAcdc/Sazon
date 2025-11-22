<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Editar Platillo</title>
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
    <h2>Editar Platillo</h2>

    <form action="${pageContext.request.contextPath}/platillo/actualizar" method="post">
        <input type="hidden" name="id" value="${platillo.id}"/>

        <label>Nombre:</label>
        <input type="text" name="nombre" value="${platillo.nombre}" required/>

        <label>Descripción:</label>
        <textarea name="descripcion">${platillo.descripcion}</textarea>

        <label>Categoría:</label>
        <select name="categoriaId" required>
            <c:forEach var="cat" items="${categorias}">
                <c:if test="${cat.condicion}">
                    <option value="${cat.id}" ${cat.id == platillo.categoriaId ? 'selected' : ''}>
                        ${cat.nombre}
                    </option>
                </c:if>
            </c:forEach>
        </select>

        <label>Precio (S/):</label>
        <input type="number" step="0.01" name="precio" value="${platillo.precio}" required/>

        <label>Condición:</label>
        <select name="condicion">
            <option value="true" ${platillo.condicion ? 'selected' : ''}>Activo</option>
            <option value="false" ${!platillo.condicion ? 'selected' : ''}>Inactivo</option>
        </select>

        <button type="submit">Actualizar</button>
        <a href="${pageContext.request.contextPath}/platillo/list" class="btn-volver">Volver</a>
    </form>
</div>

<footer class="footer">
  <p>&copy; 2025 La Sazón Peruana. Todos los derechos reservados.</p>
</footer>

</body>
</html>

