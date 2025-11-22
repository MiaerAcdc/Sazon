package com.example.sazon.delivery;

import com.example.sazon.platillo.Platillo;
import com.example.sazon.platillo.PlatilloService;
import com.example.sazon.categoria.Categoria;
import com.example.sazon.categoria.CategoriaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryDAO deliveryDAO;
    private final PlatilloService platilloService;
    private final CategoriaService categoriaService;

    public DeliveryServiceImpl(DeliveryDAO deliveryDAO, PlatilloService platilloService, CategoriaService categoriaService) {
        this.deliveryDAO = deliveryDAO;
        this.platilloService = platilloService;
        this.categoriaService = categoriaService;
    }

    @Override
    @Transactional
    public int crearDeliveryConDetalles(Delivery delivery, int[] platilloIds, int[] cantidades) {

        validarPlatillos(platilloIds, cantidades);

        delivery.setFecha(LocalDateTime.now());

        delivery.setEstado("Recibido");

        delivery.setTipo("Delivery");

        int deliveryId = deliveryDAO.guardarDelivery(delivery);

        for (int i = 0; i < platilloIds.length; i++) {
            if (platilloIds[i] > 0 && cantidades[i] > 0) {
                DetalleDelivery detalleDelivery = new DetalleDelivery(0, deliveryId, platilloIds[i], cantidades[i]);
                deliveryDAO.guardarDetalle(detalleDelivery);
            }
        }

        return deliveryId;
    }

    @Override
    @Transactional
    public void actualizarDeliveryConDetalles(Delivery delivery, int[] platilloIds, int[] cantidades) {

        delivery.setTipo("Delivery");

        validarPlatillos(platilloIds, cantidades);

        deliveryDAO.actualizarDelivery(delivery);

        deliveryDAO.eliminarDetallesPorDelivery(delivery.getId());

        for (int i = 0; i < platilloIds.length; i++) {
            if (platilloIds[i] > 0 && cantidades[i] > 0) {
                DetalleDelivery detalleDelivery = new DetalleDelivery(0, delivery.getId(), platilloIds[i], cantidades[i]);
                deliveryDAO.guardarDetalle(detalleDelivery);
            }
        }
    }

    @Override
    @Transactional
    public void eliminarDeliveryConDetalles(int deliveryId) {
        deliveryDAO.eliminarDetallesPorDelivery(deliveryId);
        deliveryDAO.eliminarDelivery(deliveryId);
    }

    @Override
    @Transactional
    public void actualizarEstadoDelivery(int deliveryId, String nuevoEstado) {
        Delivery delivery = deliveryDAO.obtenerDeliveryPorId(deliveryId);

        if (delivery == null) {
            throw new IllegalArgumentException("Delivery no encontrado.");
        }

        // --- INICIO DE CORRECCIÓN DE GESTIÓN DE ESTADOS ---

        Delivery.DeliveryStatus currentStatus;
        Delivery.DeliveryStatus nextStatus;

        // 1. Convertir el estado ACTUAL (desde la DB)
        try {
            currentStatus = Delivery.DeliveryStatus.fromString(delivery.getEstado());
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("El estado actual del Delivery guardado ('" + delivery.getEstado() + "') no es válido.");
        }

        // 2. Convertir el estado NUEVO (desde el JSP)
        try {
            nextStatus = Delivery.DeliveryStatus.fromString(nuevoEstado);
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("El nuevo estado proporcionado ('" + nuevoEstado + "') no es válido.");
        }

        // 3. Lógica de validación de transición usando el enum
        if (!Delivery.DeliveryStatus.canTransitionTo(currentStatus, nextStatus)) {
            throw new IllegalArgumentException("Transición no permitida: de '" + delivery.getEstado() + "' a '" + nuevoEstado + "'. Solo se permite avanzar al siguiente paso o Cancelar.");
        }

        // --- FIN DE CORRECCIÓN DE GESTIÓN DE ESTADOS ---

        delivery.setEstado(nuevoEstado);
        deliveryDAO.actualizarEstado(deliveryId, nuevoEstado);
    }

    @Override
    public List<Delivery> listarDeliveries() {
        return deliveryDAO.listarDeliveries();
    }

    @Override
    public List<Delivery> listarDeliveriesEntregables() {
        return deliveryDAO.listarDeliveriesPorTipo("Delivery");
    }

    @Override
    public List<DetalleDelivery> listarDetallesPorDelivery(int deliveryId) {
        return deliveryDAO.listarDetallesPorDelivery(deliveryId);
    }

    @Override
    public Delivery obtenerDeliveryPorId(int id) {
        return deliveryDAO.obtenerDeliveryPorId(id);
    }

    private void validarPlatillos(int[] platilloIds, int[] cantidades) {
        if (platilloIds.length != cantidades.length) {
            throw new IllegalArgumentException("Los datos de platillos y cantidades no coinciden.");
        }

        if (platilloIds.length == 0) {
            throw new IllegalArgumentException("Debe haber al menos un platillo seleccionado.");
        }

        for (int i = 0; i < platilloIds.length; i++) {
            if (platilloIds[i] > 0) {
                Platillo platillo = platilloService.obtenerPorId(platilloIds[i]);
                if (platillo == null) {
                    throw new IllegalArgumentException("Platillo no encontrado (ID: " + platilloIds[i] + ").");
                }
                Categoria categoria = categoriaService.obtenerPorId(platillo.getCategoriaId());
                if (!platillo.isCondicion() || !categoria.isCondicion()) {
                    throw new IllegalArgumentException("Platillo o categoría inactiva: " + platillo.getNombre());
                }
            }
        }
    }
}
