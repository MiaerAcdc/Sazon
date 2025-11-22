package com.example.sazon.delivery;

import java.time.LocalDateTime;

public class Delivery {

    private int id;
    private LocalDateTime fecha;
    private String metodoPago;
    private String tipo;
    private String estado; // Almacena el nombre del estado (String)

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

    // Enumeración para manejar los estados del Delivery
    public enum DeliveryStatus {
        RECIBIDO(1),
        EN_PREPARACION(2),
        EN_CAMINO(3),
        COMPLETADO(4),
        CANCELADO(5); // Manejado como un estado final, fuera de la secuencia normal de avance

        private final int sequence;

        DeliveryStatus(int sequence) {
            this.sequence = sequence;
        }

        public int getSequence() {
            return sequence;
        }

        // Método estático para obtener el estado a partir de su nombre (String)
        public static DeliveryStatus fromString(String status) {
            return DeliveryStatus.valueOf(status.toUpperCase().replace(" ", "_"));
        }

        // Lógica de validación de transición: No permite retroceder y solo permite avanzar al siguiente paso o cancelar
        public static boolean canTransitionTo(DeliveryStatus current, DeliveryStatus next) {
            if (current == COMPLETADO || current == CANCELADO) {
                return false; // No se puede cambiar el estado de un pedido completado o cancelado
            }

            if (next == CANCELADO) {
                return true; // Se permite cancelar desde cualquier estado intermedio
            }

            // Permite avanzar solo al estado inmediatamente siguiente
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
