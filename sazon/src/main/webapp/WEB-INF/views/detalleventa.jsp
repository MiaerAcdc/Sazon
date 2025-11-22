<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Detalles Venta</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="container">
    <h2>Detalles de la venta</h2>

    <a href="${pageContext.request.contextPath}/venta/list" class="btn btn-secondary">Volver</a>

    <table>
        <thead>
            <tr><th>Id</th><th>Platillo (id)</th><th>Cantidad</th></tr>
        </thead>
        <tbody>
            <c:forEach var="d" items="${detalles}">
                <tr>
                    <td>${d.id}</td>
                    <td>${d.platilloId}</td>
                    <td>${d.cantidad}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
