<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Listado de Ventas</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>

<header class="header">
    <div class="logo">
        <h1><a href="${pageContext.request.contextPath}/otros/inicio">La Sazón Peruana</a></h1>
    </div>

    <nav class="acciones">
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
    <h2>Gestión de Ventas</h2>

    <c:if test="${not empty mensaje}">
        <div class="alert alert-success">${mensaje}</div>
    </c:if>
    <c:if test="${not empty error}">
        <div class="alert alert-error">${error}</div>
    </c:if>

    <a href="${pageContext.request.contextPath}/venta/nuevo" class="top-btn">Nueva Venta</a>

    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Cliente</th>
            <th>Fecha</th>
            <th>Método</th>
            <th>Estado</th>
            <th>Acciones</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="v" items="${ventas}">
            <tr>
                <td>${v.id}</td>
                <td>${v.nombreCliente}</td>
                <td>${v.fecha}</td>
                <td>${v.metodoPago}</td>
                <td>${v.estado}</td>

                <td>
                    <a href="${pageContext.request.contextPath}/venta/list?ver=${v.id}" class="btn">Ver Detalle</a>
                    <a href="${pageContext.request.contextPath}/venta/editar/${v.id}" class="btn btn-secondary">Editar</a>

                    <form action="${pageContext.request.contextPath}/venta/eliminar/${v.id}"
                          method="post" class="no-style" style="display:inline;">
                        <button type="submit" class="btn btn-danger"
                                onclick="return confirm('¿Seguro de eliminar esta venta?');">
                            Eliminar
                        </button>
                    </form>
                </td>
            </tr>

            <!-- DETALLE EXPANDIDO -->
            <c:if test="${verId == v.id}">
                <tr>
                    <td colspan="6">
                        <div class="detalle-title">Detalle de la venta #${v.id}</div>

                        <table class="subtable">
                            <thead>
                            <tr>
                                <th>Platillo</th>
                                <th>Precio (S/)</th>
                                <th>Cantidad</th>
                                <th>Subtotal (S/)</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="d" items="${detallesVenta}">
                                <tr>
                                    <td>${d.nombrePlatillo}</td>
                                    <td>${d.precio}</td>
                                    <td>${d.cantidad}</td>
                                    <td>${d.subtotal}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>

                    </td>
                </tr>
            </c:if>
        </c:forEach>
        </tbody>

    </table>
</div>

<footer class="footer">
    <p>&copy; 2025 La Sazón Peruana. Todos los derechos reservados.</p>
</footer>

</body>
</html>
