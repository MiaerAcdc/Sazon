<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Delivery - La Sazón Peruana</title>
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
    <h2>
        <c:choose>
            <c:when test="${delivery.id > 0}">Editar Delivery #${delivery.id}</c:when>
            <c:otherwise>Registrar Nuevo Delivery</c:otherwise>
        </c:choose>
    </h2>

    <c:if test="${not empty error}">
        <div class="alert alert-error">${error}</div>
    </c:if>
    <c:if test="${not empty mensaje}">
        <div class="alert alert-success">${mensaje}</div>
    </c:if>

    <form action="${pageContext.request.contextPath}/delivery/guardar" method="post">

        <input type="hidden" name="id" value="${delivery.id}" />

        <c:if test="${delivery.id > 0}">
            <input type="hidden" name="estado" value="${delivery.estado}" />
        </c:if>

        <h3>Datos del Cliente y Pago</h3>
        <label>Nombre del Cliente:</label>
        <input type="text" name="nombreCliente" value="${delivery.nombreCliente}" required />

        <label>Dirección del Cliente:</label>
        <input type="text" name="direccionCliente" value="${delivery.direccionCliente}" required />

        <label>Teléfono del Cliente:</label>
        <input type="text" name="telefonoCliente" value="${delivery.telefonoCliente}" required />

        <label>Referencia:</label>
        <input type="text" name="referencia" value="${delivery.referencia}" /> <label>Método de Pago:</label>
        <select name="metodoPago" required>
            <c:forEach var="m" items="${metodoPagoOptions}">
                <option value="${m}" <c:if test="${m == delivery.metodoPago}">selected</c:if>>${m}</option>
            </c:forEach>
        </select>

        <h3>Detalle del Pedido (Máx. 10 Platillos)</h3>

        <c:set var="maxRows" value="10" />
        <c:set var="detallesSize" value="${not empty detalles ? detalles.size() : 0}" />
        <c:set var="numRowsToRender" value="${detallesSize > 0 ? detallesSize : 1}" />

        <div id="detallesContainer">

            <c:if test="${detallesSize > 0}">
                <c:forEach var="d" items="${detalles}" varStatus="i">
                    <div class="detalle-row">
                        <select name="platilloId" required>
                            <option value="">-- Platillo ${i.index + 1} --</option>
                            <c:forEach var="pl" items="${platillos}">
                                <option value="${pl.id}" <c:if test="${pl.id == d.platilloId}">selected</c:if>>${pl.nombre} - S/. ${pl.precio}</option>
                            </c:forEach>
                        </select>
                        <input type="number" name="cantidad" min="1" value="${d.cantidad}" required placeholder="Cantidad" style="width:100px"/>
                    </div>
                </c:forEach>
            </c:if>

            <c:forEach begin="${detallesSize > 0 ? detallesSize : 0}" end="${maxRows - 1}" varStatus="i">
                <div class="detalle-row">
                    <select name="platilloId">
                        <option value="">-- Platillo ${i.index + 1} --</option>
                        <c:forEach var="pl" items="${platillos}">
                            <option value="${pl.id}">${pl.nombre} - S/. ${pl.precio}</option>
                        </c:forEach>
                    </select>
                    <input type="number" name="cantidad" min="1" value="" placeholder="Cantidad (Opcional)" style="width:100px"/>
                </div>
            </c:forEach>

            <p style="color:#c1121f; font-size:14px; margin-top: 10px;">* Sólo los campos de Platillo y Cantidad que estén llenos serán guardados. Elimine los detalles vacíos antes de guardar.</p>

        </div>

        <div style="margin-top:12px;" class="boton-centrado">
            <button type="submit" class="btn-registrar">
                <c:choose>
                    <c:when test="${delivery.id > 0}">Guardar Cambios</c:when>
                    <c:otherwise>Registrar Delivery</c:otherwise>
                </c:choose>
            </button>
            <a href="${pageContext.request.contextPath}/delivery/list" class="btn btn-volver">Volver al Listado</a>
        </div>
    </form>
</div>

<footer class="footer">
    <p>&copy; 2025 La Sazón Peruana. Todos los derechos reservados.</p>
</footer>

</body>
</html>
