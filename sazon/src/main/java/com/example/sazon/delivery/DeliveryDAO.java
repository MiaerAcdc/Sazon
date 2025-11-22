package com.example.sazon.delivery;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryDAO {

    int guardarDelivery(Delivery delivery);

    int guardarDetalle(DetalleDelivery detalle);

    List<Delivery> listarDeliveriesPorTipo(String tipo);

    List<Delivery> listarDeliveries();

    List<DetalleDelivery> listarDetallesPorDelivery(int deliveryId);

    Delivery obtenerDeliveryPorId(int id);

    void actualizarDelivery(Delivery delivery);

    void actualizarEstado(int id, String nuevoEstado);

    void eliminarDetallesPorDelivery(int deliveryId);

    void eliminarDelivery(int id);
}
