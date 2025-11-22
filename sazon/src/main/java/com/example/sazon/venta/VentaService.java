package com.example.sazon.venta;

import java.util.List;

public interface VentaService {
    int crearVentaConDetalles(Venta venta, int[] platilloIds, int[] cantidades);
    void actualizarVentaConDetalles(Venta venta, int[] platilloIds, int[] cantidades);
    void eliminarVentaConDetalles(int ventaId);
    List<Venta> listarVentas();
    List<Venta> listarVentasLocales();
    List<DetalleVenta> listarDetallesPorVenta(int ventaId);
    Venta obtenerVentaPorId(int id);
}