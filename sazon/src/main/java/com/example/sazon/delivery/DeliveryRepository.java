package com.example.sazon.delivery;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class DeliveryRepository implements DeliveryDAO {

    private final JdbcTemplate jdbcTemplate;

    public DeliveryRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Delivery> deliveryMapper = (rs, rowNum) -> new Delivery(
            rs.getInt("Id"),
            rs.getTimestamp("Fecha") != null ? rs.getTimestamp("Fecha").toLocalDateTime() : null,
            rs.getString("MetodoPago"),
            rs.getString("Tipo"),
            rs.getString("Estado"),
            rs.getString("NombreCliente"),
            rs.getString("DireccionCliente"),
            rs.getString("TelefonoCliente"),
            rs.getString("Referencia")
    );

    private final RowMapper<DetalleDelivery> detalleMapper = (rs, rowNum) -> new DetalleDelivery(
            rs.getInt("Id"),
            rs.getInt("Venta"),
            rs.getInt("Platillo"),
            rs.getInt("Cantidad")
    );

    @Override
    public int guardarDelivery(Delivery delivery) {
        String sql = "INSERT INTO Venta (Fecha, MetodoPago, Tipo, Estado, NombreCliente, DireccionCliente, TelefonoCliente, Referencia) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(
                sql,
                Timestamp.valueOf(delivery.getFecha()),
                delivery.getMetodoPago(),
                delivery.getTipo(),
                delivery.getEstado(),
                delivery.getNombreCliente(),
                delivery.getDireccionCliente(),
                delivery.getTelefonoCliente(),
                delivery.getReferencia()
        );

        return jdbcTemplate.queryForObject("SELECT MAX(Id) FROM Venta", Integer.class);
    }

    @Override
    public int guardarDetalle(DetalleDelivery detalle) {
        String sql = "INSERT INTO DetalleVenta (Venta, Platillo, Cantidad) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, detalle.getVentaId(), detalle.getPlatilloId(), detalle.getCantidad());
    }

    @Override
    public List<Delivery> listarDeliveriesPorTipo(String tipo) {
        String sql = "SELECT * FROM Venta WHERE Tipo = ? ORDER BY Fecha DESC";
        return jdbcTemplate.query(sql, deliveryMapper, tipo);
    }

    @Override
    public List<Delivery> listarDeliveries() {
        return listarDeliveriesPorTipo("Delivery");
    }

    @Override
    public List<DetalleDelivery> listarDetallesPorDelivery(int deliveryId) {
        String sql = """
                SELECT dv.Id, dv.Venta, dv.Platillo, dv.Cantidad, 
                p.Nombre AS NombrePlatillo, p.Precio AS Precio
                FROM DetalleVenta dv
                JOIN Platillo p ON dv.Platillo = p.Id
                WHERE dv.Venta = ?
        """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            DetalleDelivery d = new DetalleDelivery(
                    rs.getInt("Id"),
                    rs.getInt("Venta"),
                    rs.getInt("Platillo"),
                    rs.getInt("Cantidad")
            );
            d.setNombrePlatillo(rs.getString("NombrePlatillo"));
            d.setPrecio(rs.getDouble("Precio"));
            d.setSubtotal(rs.getDouble("Precio") * rs.getInt("Cantidad"));
            return d;
        }, deliveryId);
    }

    @Override
    public Delivery obtenerDeliveryPorId(int id) {
        List<Delivery> lista = jdbcTemplate.query("SELECT * FROM Venta WHERE Id = ?", deliveryMapper, id);
        return lista.isEmpty() ? null : lista.get(0);
    }

    @Override
    public void actualizarDelivery(Delivery delivery) {
        jdbcTemplate.update(
                "UPDATE Venta SET MetodoPago=?, Tipo=?, Estado=?, NombreCliente=?, DireccionCliente=?, TelefonoCliente=?, Referencia=? WHERE Id=?",
                delivery.getMetodoPago(),
                delivery.getTipo(),
                delivery.getEstado(),
                delivery.getNombreCliente(),
                delivery.getDireccionCliente(),
                delivery.getTelefonoCliente(),
                delivery.getReferencia(),
                delivery.getId()
        );
    }

    @Override
    public void actualizarEstado(int id, String nuevoEstado) {
        jdbcTemplate.update(
                "UPDATE Venta SET Estado = ? WHERE Id = ?",
                nuevoEstado,
                id
        );
    }

    @Override
    public void eliminarDetallesPorDelivery(int deliveryId) {
        jdbcTemplate.update("DELETE FROM DetalleVenta WHERE Venta = ?", deliveryId);
    }

    @Override
    public void eliminarDelivery(int id) {
        jdbcTemplate.update("DELETE FROM Venta WHERE Id = ?", id);
    }
}
