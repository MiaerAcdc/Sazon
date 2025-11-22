package com.example.sazon.delivery;

import java.util.List;

public interface DeliveryService {

    int crearDeliveryConDetalles(Delivery delivery, int[] platilloIds, int[] cantidades);

    void actualizarDeliveryConDetalles(Delivery delivery, int[] platilloIds, int[] cantidades);

    void eliminarDeliveryConDetalles(int deliveryId);

    void actualizarEstadoDelivery(int deliveryId, String nuevoEstado);

    List<Delivery> listarDeliveries();

    List<Delivery> listarDeliveriesEntregables();

    List<DetalleDelivery> listarDetallesPorDelivery(int deliveryId);

    Delivery obtenerDeliveryPorId(int id);
}
