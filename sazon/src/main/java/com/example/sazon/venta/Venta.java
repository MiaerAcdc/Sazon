package com.example.sazon.venta;

import java.time.LocalDateTime;

public class Venta {

    private int id;
    private LocalDateTime fecha;
    private String metodoPago;
    private String tipo;
    private String estado;

    // Nuevo campo
    private String nombreCliente;

    public Venta() {}

    public Venta(int id, LocalDateTime fecha, String metodoPago, String tipo, String estado, String nombreCliente) {
        this.id = id;
        this.fecha = fecha;
        this.metodoPago = metodoPago;
        this.tipo = tipo;
        this.estado = estado;
        this.nombreCliente = nombreCliente;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getMetodoPago() {
        return metodoPago;
    }
    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }

    // Getter y Setter del nuevo campo
    public String getNombreCliente() {
        return nombreCliente;
    }
    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }
}
