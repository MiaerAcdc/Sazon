package com.example.sazon.metricas;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MetricasRepository implements MetricasDAO {

    private final JdbcTemplate jdbcTemplate;

    public MetricasRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public double obtenerVentasTotales() {
        String sql = """
            SELECT SUM(D.Cantidad * P.Precio)
            FROM DetalleVenta D
            JOIN Platillo P ON D.Platillo = P.Id
        """;
        BigDecimal totalVentas = jdbcTemplate.queryForObject(sql, BigDecimal.class);
        return totalVentas != null ? totalVentas.doubleValue() : 0.0;
    }

    @Override
    public List<Map<String, Object>> obtenerVentasPorCategoriaPorMes() {
        String sql = """
            SELECT
                MONTH(V.Fecha) AS mes,
                C.Nombre AS categoria,
                SUM(D.Cantidad * P.Precio) AS total
            FROM DetalleVenta D
            JOIN Platillo P ON D.Platillo = P.Id
            JOIN Categoria C ON P.Categoria = C.Id
            JOIN Venta V ON D.Venta = V.Id
            GROUP BY MONTH(V.Fecha), C.Nombre
            ORDER BY MONTH(V.Fecha), C.Nombre
        """;
        return jdbcTemplate.queryForList(sql);
    }

    @Override
    public Map<String, Double> obtenerVentasPorMes() {
        String sql = """
            SELECT MONTH(V.Fecha) AS mes, SUM(D.Cantidad * P.Precio) AS total
            FROM DetalleVenta D
            JOIN Platillo P ON D.Platillo = P.Id
            JOIN Venta V ON D.Venta = V.Id
            GROUP BY MONTH(V.Fecha)
        """;
        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
        Map<String, Double> ventasPorMes = new HashMap<>();

        for (Map<String, Object> row : result) {
            int mes = (int) row.get("mes");
            BigDecimal totalVenta = (BigDecimal) row.get("total");
            ventasPorMes.put(getMesNombre(mes), totalVenta != null ? totalVenta.doubleValue() : 0.0);
        }

        return ventasPorMes;
    }

    @Override
    public List<Map<String, Object>> obtenerVentasPorTipoPorMes() {
        String sql = """
            SELECT
                MONTH(V.Fecha) AS mes,
                V.Tipo AS tipo,
                SUM(D.Cantidad * P.Precio) AS total
            FROM DetalleVenta D
            JOIN Platillo P ON D.Platillo = P.Id
            JOIN Venta V ON D.Venta = V.Id
            WHERE V.Tipo IN ('Local', 'Delivery')
            GROUP BY MONTH(V.Fecha), V.Tipo
            ORDER BY MONTH(V.Fecha), V.Tipo
        """;
        return jdbcTemplate.queryForList(sql);
    }

    private String getMesNombre(int mes) {
        switch (mes) {
            case 1: return "Ene";
            case 2: return "Feb";
            case 3: return "Mar";
            case 4: return "Abr";
            case 5: return "May";
            case 6: return "Jun";
            case 7: return "Jul";
            case 8: return "Ago";
            case 9: return "Sep";
            case 10: return "Oct";
            case 11: return "Nov";
            case 12: return "Dic";
            default: return "Desconocido";
        }
    }

    @Override
    public List<Map<String, Object>> obtenerTendenciaVentas() {
        String sql = """
            SELECT MONTH(V.Fecha) AS mes, SUM(D.Cantidad * P.Precio) AS total
            FROM DetalleVenta D
            JOIN Platillo P ON D.Platillo = P.Id
            JOIN Venta V ON D.Venta = V.Id
            GROUP BY MONTH(V.Fecha)
            ORDER BY MONTH(V.Fecha)
        """;
        return jdbcTemplate.queryForList(sql);
    }
}