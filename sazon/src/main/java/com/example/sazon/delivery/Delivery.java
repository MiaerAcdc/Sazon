package com.example.sazon.delivery;

import java.time.LocalDateTime;

public class Delivery {

    private int id;
    private LocalDateTime fecha;
    private String metodoPago;
    private String tipo;
    private String estado;

    private String nombreCliente;
    private String direccionCliente;
    private String telefonoCliente;
    private String referencia;

    public Delivery() {}

    public Delivery(int id, LocalDateTime fecha, String metodoPago, String tipo, String estado,
                    String nombreCliente, String direccionCliente, String telefonoCliente, String referencia) {
        this.id = id;
        this.fecha = fecha;
        this.metodoPago = metodoPago;
        this.tipo = tipo;
        this.estado = estado;
        this.nombreCliente = nombreCliente;
        this.direccionCliente = direccionCliente;
        this.telefonoCliente = telefonoCliente;
        this.referencia = referencia;
    }

    public enum DeliveryStatus {
        RECIBIDO(1),
        EN_PREPARACION(2),
        EN_CAMINO(3),
        COMPLETADO(4),
        CANCELADO(5);

        private final int sequence;

        DeliveryStatus(int sequence) {
            this.sequence = sequence;
        }

        public int getSequence() {
            return sequence;
        }

        public static DeliveryStatus fromString(String status) {
            String normalizedStatus = status.toUpperCase().replace(" ", "_");

            if ("PENDIENTE".equals(normalizedStatus)) {
                return RECIBIDO;
            }

            if ("ENTREGADO".equals(normalizedStatus) || "ENTREGA".equals(normalizedStatus)) {
                return COMPLETADO;
            }

            // Corrección: Asegurarse de que no haya problemas con acentos no estándar
            normalizedStatus = normalizedStatus.replace("ACION", "ACION");

            return DeliveryStatus.valueOf(normalizedStatus);
        }

        public static boolean canTransitionTo(DeliveryStatus current, DeliveryStatus next) {
            if (current == COMPLETADO || current == CANCELADO) {
                return false;
            }

            if (next == CANCELADO) {
                return true;
            }

            return next.sequence == (current.sequence + 1);
        }
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

    public String getNombreCliente() {
        return nombreCliente;
    }
    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getDireccionCliente() {
        return direccionCliente;
    }
    public void setDireccionCliente(String direccionCliente) {
        this.direccionCliente = direccionCliente;
    }

    public String getTelefonoCliente() {
        return telefonoCliente;
    }
    public void setTelefonoCliente(String telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
    }

    public String getReferencia() {
        return referencia;
    }
    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }
}
