package com.example.sazon.venta;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class VentaRepository implements VentaDAO {

    private final JdbcTemplate jdbcTemplate;

    public VentaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Venta> ventaMapper = (rs, rowNum) -> new Venta(
            rs.getInt("Id"),
            rs.getTimestamp("Fecha") != null ? rs.getTimestamp("Fecha").toLocalDateTime() : null,
            rs.getString("MetodoPago"),
            rs.getString("Tipo"),
            rs.getString("Estado"),
            rs.getString("NombreCliente")
    );

    private final RowMapper<DetalleVenta> detalleMapper = (rs, rowNum) -> new DetalleVenta(
            rs.getInt("Id"),
            rs.getInt("Venta"),
            rs.getInt("Platillo"),
            rs.getInt("Cantidad")
    );

    @Override
    public int guardarVenta(Venta venta) {
        String sql = "INSERT INTO Venta (Fecha, MetodoPago, Tipo, Estado, NombreCliente) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(
                sql,
                Timestamp.valueOf(venta.getFecha()),
                venta.getMetodoPago(),
                venta.getTipo(),
                venta.getEstado(),
                venta.getNombreCliente()
        );

        return jdbcTemplate.queryForObject("SELECT MAX(Id) FROM Venta", Integer.class);
    }

    @Override
    public int guardarDetalle(DetalleVenta detalle) {
        String sql = "INSERT INTO DetalleVenta (Venta, Platillo, Cantidad) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, detalle.getVentaId(), detalle.getPlatilloId(), detalle.getCantidad());
    }

    @Override
    public List<Venta> listarVentas() {
        return jdbcTemplate.query("SELECT * FROM Venta ORDER BY Fecha DESC", ventaMapper);
    }

    @Override
    public List<Venta> listarVentasPorTipo(String tipo) {
        String sql = "SELECT * FROM Venta WHERE Tipo = ? ORDER BY Fecha DESC";
        return jdbcTemplate.query(sql, ventaMapper, tipo);
    }

    @Override
    public List<DetalleVenta> listarDetallesPorVenta(int ventaId) {
        String sql = """
        
                SELECT dv.Id, dv.Venta, dv.Platillo, dv.Cantidad, 
               p.Nombre AS NombrePlatillo, p.Precio AS Precio
        FROM DetalleVenta dv
        JOIN Platillo p ON dv.Platillo = p.Id
        WHERE dv.Venta = ?
        """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            DetalleVenta d = new DetalleVenta(
                    rs.getInt("Id"),
                    rs.getInt("Venta"),
                    rs.getInt("Platillo"),
                    rs.getInt("Cantidad")
            );
            d.setNombrePlatillo(rs.getString("NombrePlatillo"));
            d.setPrecio(rs.getDouble("Precio"));
            d.setSubtotal(rs.getDouble("Precio") * rs.getInt("Cantidad"));
            return d;
        }, ventaId);
    }

    @Override
    public Venta obtenerVentaPorId(int id) {
        List<Venta> lista = jdbcTemplate.query("SELECT * FROM Venta WHERE Id = ?", ventaMapper, id);
        return lista.isEmpty() ? null : lista.get(0);
    }

    @Override
    public void actualizarVenta(Venta venta) {
        jdbcTemplate.update(
                "UPDATE Venta SET MetodoPago=?, Tipo=?, Estado=?, NombreCliente=? WHERE Id=?",
                venta.getMetodoPago(),
                venta.getTipo(),
                venta.getEstado(),
                venta.getNombreCliente(),
                venta.getId()
        );
    }

    @Override
    public void eliminarDetallesPorVenta(int ventaId) {
        jdbcTemplate.update("DELETE FROM DetalleVenta WHERE Venta = ?", ventaId);
    }

    @Override
    public void eliminarVenta(int id) {
        jdbcTemplate.update("DELETE FROM Venta WHERE Id = ?", id);
    }
}