<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Listado de Deliveries</title>
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

<div class="container listadodelivery">
    <h2>Gestión de Deliveries</h2>

    <c:if test="${not empty mensaje}">
        <div class="alert alert-success">${mensaje}</div>
    </c:if>
    <c:if test="${not empty error}">
        <div class="alert alert-error">${error}</div>
    </c:if>

    <a href="${pageContext.request.contextPath}/delivery/nuevo" class="top-btn">Nuevo Delivery</a>

    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Cliente</th>
            <th>Fecha</th>
            <th>Método de Pago</th>
            <th>Estado</th>
            <th>Dirección</th>
            <th>Teléfono</th>
            <th>Referencia</th>
            <th>Acciones</th>
        </tr>
        </thead>

        <tbody>
        <c:set var="secuenciaEstados" value="${['Recibido', 'En preparación', 'En camino', 'Completado']}" />

        <c:forEach var="d" items="${deliveries}">
            <tr>
                <td>${d.id}</td>
                <td>${d.nombreCliente}</td>
                <td>${d.fecha}</td>
                <td>${d.metodoPago}</td>

                <td>
                    <c:set var="currentStatus" value="${d.estado}" />
                    <c:set var="isFinal" value="${currentStatus == 'Completado' || currentStatus == 'Cancelado'}" />

                    <form action="${pageContext.request.contextPath}/delivery/actualizar-estado/${d.id}" method="post" class="no-style">
                        <select name="nuevoEstado" required style="width: 150px; padding: 5px;">

                            <option value="${currentStatus}" selected disabled>${currentStatus}</option>

                            <c:if test="${!isFinal}">
                                <c:set var="nextStateFound" value="${false}" />

                                <c:forEach var="state" items="${secuenciaEstados}" varStatus="loop">
                                    <c:if test="${loop.index > 0}">
                                        <c:if test="${secuenciaEstados[loop.index - 1] == currentStatus && !nextStateFound}">
                                            <option value="${state}">${state}</option>
                                            <c:set var="nextStateFound" value="${true}" />
                                        </c:if>
                                    </c:if>
                                </c:forEach>

                                <c:if test="${currentStatus != 'Cancelado'}">
                                    <option value="Cancelado">Cancelado</option>
                                </c:if>
                            </c:if>
                        </select>

                        <c:choose>
                            <c:when test="${isFinal}">
                                <button type="button" class="small-btn btn-success-custom" disabled>Finalizado</button>
                            </c:when>
                            <c:otherwise>
                                <button type="submit" class="small-btn btn-info-custom" style="margin-top: 5px;">Actualizar</button>
                            </c:otherwise>
                        </c:choose>
                    </form>
                </td>

                <td>${d.direccionCliente}</td>
                <td>${d.telefonoCliente}</td>
                <td>${d.referencia}</td>

                <td>
                    <a href="${pageContext.request.contextPath}/delivery/list?ver=${d.id}" class="btn">Ver Detalle</a>
                    <a href="${pageContext.request.contextPath}/delivery/editar/${d.id}" class="btn btn-secondary">Editar</a>

                    <form action="${pageContext.request.contextPath}/delivery/eliminar/${d.id}"
                          method="post" class="no-style" style="display:inline;">
                        <button type="submit" class="btn btn-danger">
                            Eliminar
                        </button>
                    </form>
                </td>
            </tr>

            <c:if test="${verId == d.id}">
                <tr>
                    <td colspan="9">
                        <div class="detalle-title">Detalle del Delivery #${d.id}</div>

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
                            <c:forEach var="detalle" items="${detallesDelivery}">
                                <tr>
                                    <td>${detalle.nombrePlatillo}</td>
                                    <td>${detalle.precio}</td>
                                    <td>${detalle.cantidad}</td>
                                    <td>${detalle.subtotal}</td>
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