<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Publicidad - La Sazón Peruana</title>
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

<!-- Contenedor principal -->
<div class="container">
  <h2>Promociones y Novedades</h2>
  <p class="intro-text">Descubre nuestras ofertas especiales, cupones y eventos exclusivos.</p>

  <div class="promo-item">
    <img src="${pageContext.request.contextPath}/resources/img/promo1.jpg" alt="Promo Ceviche" class="promo-img">
    <div class="promo-info">
      <h3>¡Ceviche 2x1 los viernes!</h3>
      <p>Disfruta de nuestro ceviche clásico en promoción todos los viernes. 🎉</p>
    </div>
  </div>

  <div class="promo-item">
    <img src="${pageContext.request.contextPath}/resources/img/cupon.jpg" alt="Cupón descuento" class="promo-img">
    <div class="promo-info">
      <h3>Cupón 10% de descuento</h3>
      <p>Presenta este cupón en caja y obtén un 10% en tu próxima compra.</p>
    </div>
  </div>

  <div class="promo-item">
    <img src="${pageContext.request.contextPath}/resources/img/plato-nuevo.jpg" alt="Plato nuevo" class="promo-img">
    <div class="promo-info">
      <h3>Nuevo plato: Arroz con Pato</h3>
      <p>Prueba nuestro arroz con pato al estilo norteño, ¡recién agregado al menú!</p>
    </div>
  </div>

  <div class="promo-item">
    <img src="${pageContext.request.contextPath}/resources/img/evento.jpg" alt="Evento Gastronómico" class="promo-img">
    <div class="promo-info">
      <h3>Noche Criolla - Sábado 15</h3>
      <p>Acompáñanos en una noche llena de música y gastronomía peruana. 🥳</p>
    </div>
  </div>
</div>

<footer class="footer">
  <p>&copy; 2025 La Sazón Peruana. Todos los derechos reservados.</p>
</footer>

</body>
</html>
