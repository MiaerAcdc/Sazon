package com.example.sazon.venta;

import java.util.List;

public interface VentaDAO {
    int guardarVenta(Venta venta);
    int guardarDetalle(DetalleVenta detalle);
    List<Venta> listarVentas();
    List<Venta> listarVentasPorTipo(String tipo);
    List<DetalleVenta> listarDetallesPorVenta(int ventaId);
    Venta obtenerVentaPorId(int id);
    void actualizarVenta(Venta venta);
    void eliminarDetallesPorVenta(int ventaId);
    void eliminarVenta(int id);
}
