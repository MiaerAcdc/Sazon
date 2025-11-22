<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Restaurante Criollo - La Sazón Peruana</title>
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
  <h2>Bienvenido a La Sazón Peruana</h2>
  <p class="intro-text">Descubre el auténtico sabor criollo del Perú. Ordena tus platos favoritos y disfruta del delivery gratis.</p>

  <div class="home-section">
    <img src="${pageContext.request.contextPath}/resources/img/plato-criollo.png" alt="Plato criollo" class="home-img">
    <div class="home-info">
      <h3>Auténtico Sabor Criollo</h3>
      <p>Disfruta de nuestros platillos tradicionales preparados con los mejores ingredientes.</p>
      <a href="${pageContext.request.contextPath}/categoria/list" class="btn">Ver Catálogo</a>
    </div>
  </div>
</div>

<footer class="footer">
  <p>&copy; 2025 La Sazón Peruana. Todos los derechos reservados.</p>
</footer>

</body>
</html>
