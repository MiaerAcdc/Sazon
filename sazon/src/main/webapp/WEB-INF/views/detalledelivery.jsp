<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Detalles Delivery</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<div class="container">
    <h2>Detalles de la Delivery #${delivery.id}</h2>

    <a href="${pageContext.request.contextPath}/delivery/list" class="btn btn-secondary">Volver</a>

    <div>
        <p><strong>Cliente:</strong> ${delivery.nombreCliente}</p>
        <p><strong>Dirección:</strong> ${delivery.direccionCliente}</p>
        <p><strong>Teléfono:</strong> ${delivery.telefonoCliente}</p>
        <p><strong>Referencia:</strong> ${delivery.referencia}</p>
        <p><strong>Fecha:</strong> ${delivery.fecha}</p>
        <p><strong>Método de Pago:</strong> ${delivery.metodoPago}</p>
        <p><strong>Estado:</strong> ${delivery.estado}</p>
    </div>

    <h3>Detalle de los Platillos</h3>
    <table>
        <thead>
        <tr>
            <th>Id</th>
            <th>Platillo</th>
            <th>Cantidad</th>
            <th>Precio (S/)</th>
            <th>Subtotal (S/)</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="d" items="${detallesDelivery}">
            <tr>
                <td>${d.id}</td>
                <td>${d.nombrePlatillo}</td>
                <td>${d.cantidad}</td>
                <td>${d.precio}</td>
                <td>${d.subtotal}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
