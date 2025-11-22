<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="es">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Métricas - La Sazón Peruana</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
</head>
<body>

<header class="header">
    <div class="logo">
        <h1><a href="${pageContext.request.contextPath}/index">La Sazón Peruana</a></h1>
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

<main>
    <h2>📊 Dashboard de Métricas - La Sazón Peruana</h2>

    <div class="kpi-grid">
        <div class="kpi-card">
            <h3>Ventas Totales</h3>
            <p class="valor">S/ ${ventasTotales}</p>
            <p class="desc">Ventas Totales Registradas</p>
        </div>
    </div>

    <section class="graficos">

        <div class="grafico">
            <h3>Ventas por Categoría por Mes</h3>

            <div class="leyenda-categorias">
                <div><div class="leyenda-color cat-platos"></div> Platos Fondo</div>
                <div><div class="leyenda-color cat-entradas"></div> Entradas</div>
                <div><div class="leyenda-color cat-bebidas"></div> Bebidas</div>
                <div><div class="leyenda-color cat-postres"></div> Postres</div>
                <div><div class="leyenda-color cat-promociones"></div> Promociones</div>
            </div>

            <c:forEach var="mesEntry" items="${ventasPorMesYCategoria}">
                <h4>${mesEntry.key}</h4>
                <div class="categorias">

                    <c:set var="totalVentasMes" value="0" />
                    <c:forEach var="catEntry" items="${mesEntry.value}">
                        <c:set var="totalVentasMes" value="${totalVentasMes + catEntry.value}" />
                    </c:forEach>

                    <c:set var="divisorSeguro" value="${totalVentasMes > 0 ? totalVentasMes : 1}" />

                    <c:forEach var="categoriaEntry" items="${mesEntry.value}">

                        <c:set var="porcentaje" value="${(categoriaEntry.value / divisorSeguro) * 100}" />

                        <c:set var="claseCat" value="cat-otro"/>
                        <c:choose>
                            <c:when test="${categoriaEntry.key eq 'Platos de Fondo'}"><c:set var="claseCat" value="cat-platos"/></c:when>
                            <c:when test="${categoriaEntry.key eq 'Entradas'}"><c:set var="claseCat" value="cat-entradas"/></c:when>
                            <c:when test="${categoriaEntry.key eq 'Bebidas'}"><c:set var="claseCat" value="cat-bebidas"/></c:when>
                            <c:when test="${categoriaEntry.key eq 'Postres'}"><c:set var="claseCat" value="cat-postres"/></c:when>
                            <c:when test="${categoriaEntry.key eq 'Promociones'}"><c:set var="claseCat" value="cat-promociones"/></c:when>
                        </c:choose>

                        <div class="categoria">
                            <span class="nombre-categoria" style="min-width: 90px;">${categoriaEntry.key}</span>

                            <div class="barra-cat" title="${categoriaEntry.key}: S/ ${categoriaEntry.value}">
                                <div class="progreso-interno ${claseCat}" style="width: ${porcentaje}%"></div>
                            </div>

                            <span class="monto">S/ ${categoriaEntry.value}</span>
                        </div>
                    </c:forEach>

                </div>
                <hr>
            </c:forEach>
        </div>

        <div class="grafico">
            <h3>Ventas por Mes</h3>
            <div class="barras-doble">

                <c:set var="escala" value="1.1" />
                <c:forEach var="mesEntry" items="${ventasPorTipoPorMes}">
                    <div class="mes">

                        <c:set var="localTotal" value="${mesEntry.value.local}" />
                        <c:set var="deliveryTotal" value="${mesEntry.value.delivery}" />
                        <c:set var="totalMes" value="${localTotal + deliveryTotal}" />

                        <c:set var="alturaLocal" value="${localTotal * escala}" />
                        <c:set var="alturaEnvio" value="${deliveryTotal * escala}" />

                        <div class="grupo-barras">
                            <div class="barra local"
                                 style="height: ${alturaLocal}px"
                                 title="Local S/ ${localTotal}">
                            </div>

                            <div class="barra envio"
                                 style="height: ${alturaEnvio}px"
                                 title="Delivery S/ ${deliveryTotal}">
                            </div>
                        </div>

                        <span>${mesEntry.key}<br>S/ ${totalMes}</span>
                    </div>
                </c:forEach>

            </div>

            <div class="leyenda">
                <div><div class="color azul"></div> Local</div>
                <div><div class="color naranja"></div> Delivery</div>
            </div>
        </div>

        <div class="grafico">
            <h3 style="text-align:center;">Tendencia de Ventas</h3>

            <c:set var="datos" value="${tendenciaVentas}" />

            <c:if test="${not empty datos}">

                <c:set var="ancho" value="300" />
                <c:set var="alto" value="130" />

                <c:set var="maxVenta" value="0.0" />
                <c:forEach var="item" items="${datos}">
                    <c:if test="${item.total.doubleValue() > maxVenta}">
                        <c:set var="maxVenta" value="${item.total.doubleValue()}" />
                    </c:if>
                </c:forEach>

                <c:if test="${maxVenta == 0.0}">
                    <c:set var="maxVenta" value="1.0" />
                </c:if>

                <c:set var="escalaY" value="${alto / maxVenta}" />

                <c:set var="numMeses" value="${datos.size()}" />
                <c:set var="distanciaX" value="${numMeses > 1 ? ancho / (numMeses - 1) : 0}" />

                <div class="line-chart">
                    <svg viewBox="0 0 ${ancho} ${alto + 15}">

                        <c:set var="puntosSVG" value="" />
                        <c:set var="currentX" value="0" />
                        <c:forEach var="item" items="${datos}" varStatus="loop">
                            <c:set var="totalDouble" value="${item.total.doubleValue()}" />
                            <c:set var="y" value="${alto - (totalDouble * escalaY)}" />

                            <c:set var="puntosSVG" value="${puntosSVG}${currentX},${y} " />

                            <c:set var="currentX" value="${currentX + distanciaX}" />
                        </c:forEach>
                        <polyline points="${puntosSVG}"
                                  fill="none"
                                  stroke="#007bff"
                                  stroke-width="2" />

                        <c:set var="currentX" value="0" />
                        <c:forEach var="item" items="${datos}" varStatus="loop">
                            <c:set var="totalDouble" value="${item.total.doubleValue()}" />
                            <c:set var="y" value="${alto - (totalDouble * escalaY)}" />

                            <c:set var="mesNum" value="${item.mes}" />
                            <c:set var="mesNombre" value="${metricaService.getMesNombre(mesNum)}" />


                            <circle cx="${currentX}" cy="${y}" r="3" fill="#007bff" stroke="#fff" stroke-width="1.5" />

                            <text x="${currentX}" y="${alto + 10}"
                                  text-anchor="middle" font-size="8" fill="#555">
                                    ${mesNombre}
                            </text>

                            <c:set var="labelY" value="${y - 5}" />
                            <c:if test="${y < 15}"><c:set var="labelY" value="${y + 12}" /></c:if>

                            <text x="${currentX}" y="${labelY}"
                                  text-anchor="middle" font-size="9" font-weight="bold" fill="#333">
                                S/ <c:out value="${item.total}" />
                            </text>

                            <c:set var="currentX" value="${currentX + distanciaX}" />
                        </c:forEach>

                    </svg>
                </div>
            </c:if>

            <c:if test="${empty datos}">
                <div class="line-chart" style="height: 180px; display: flex; align-items: center; justify-content: center;">
                    <p style="color: #ccc; font-size: 14px;">Datos de tendencia no disponibles.</p>
                </div>
            </c:if>

        </div>

    </section>
</main>

</body>
</html>