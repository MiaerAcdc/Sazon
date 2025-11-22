package com.example.sazon.venta;

public class DetalleVenta {
    private int id;
    private int ventaId;
    private int platilloId;
    private int cantidad;
    private String nombrePlatillo;
    private double precio;
    private double subtotal;

    public DetalleVenta() {}

    public DetalleVenta(int id, int ventaId, int platilloId, int cantidad) {
        this.id = id;
        this.ventaId = ventaId;
        this.platilloId = platilloId;
        this.cantidad = cantidad;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getVentaId() { return ventaId; }
    public void setVentaId(int ventaId) { this.ventaId = ventaId; }

    public int getPlatilloId() { return platilloId; }
    public void setPlatilloId(int platilloId) { this.platilloId = platilloId; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public String getNombrePlatillo() { return nombrePlatillo; }
    public void setNombrePlatillo(String nombrePlatillo) { this.nombrePlatillo = nombrePlatillo; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public double getSubtotal() { return subtotal; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }
}
