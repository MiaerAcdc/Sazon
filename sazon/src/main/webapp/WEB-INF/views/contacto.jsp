<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Contacto - La Sazón Peruana</title>
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
  <h2>Contáctanos</h2>
  <p class="intro-text">Estamos aquí para atenderte. Puedes comunicarte con nosotros a través de los siguientes medios:</p>

  <div class="contact-info">
    <p><strong>Teléfono:</strong> +51 987 654 321</p>
    <p><strong>Correo:</strong> contacto@lasazonperuana.com</p>
    <p><strong>Dirección:</strong> Av. Los Sabores 123, Huancayo, Perú</p>
    <p><strong>Horario de atención:</strong> Lunes a Domingo, 10:00 AM - 10:00 PM</p>
  </div>

  <h3>Encuéntranos en el mapa</h3>
  <div class="mapa">
    <iframe
      src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3900.8061534595623!2d-75.21088512525056!3d-12.065644088138877!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x910e965f36394f7b%3A0x82a82c9fdf45c6a7!2sParque%20Constituci%C3%B3n!5e0!3m2!1ses-419!2spe!4v1694454000000!5m2!1ses-419!2spe"
      width="100%" height="350" style="border:0;" allowfullscreen="" loading="lazy"></iframe>
  </div>
</div>

<footer class="footer">
  <p>&copy; 2025 La Sazón Peruana. Todos los derechos reservados.</p>
</footer>

</body>
</html>
