package com.example.sazon.metricas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class MetricasService {

    private final MetricasDAO metricaDAO;
    private static final List<String> CATEGORIAS_CLAVE = Arrays.asList(
            "Platos de Fondo", "Entradas", "Postres", "Bebidas", "Promociones"
    );

    @Autowired
    public MetricasService(MetricasDAO metricaDAO) {
        this.metricaDAO = metricaDAO;
    }

    public double obtenerVentasTotales() {
        return metricaDAO.obtenerVentasTotales();
    }

    public Map<String, Map<String, Double>> obtenerVentasPorCategoriaPorMes() {
        List<Map<String, Object>> resultados = metricaDAO.obtenerVentasPorCategoriaPorMes();

        Map<String, Map<String, Double>> ventasPorMesYCategoria = new LinkedHashMap<>();

        for (Map<String, Object> fila : resultados) {
            int mesNum = (int) fila.get("mes");
            String mesNombre = getMesNombre(mesNum);
            String categoria = (String) fila.get("categoria");

            BigDecimal totalVentaBD = (BigDecimal) fila.get("total");
            double totalVenta = totalVentaBD != null ? totalVentaBD.doubleValue() : 0.0;

            if (CATEGORIAS_CLAVE.contains(categoria)) {
                Map<String, Double> ventasPorCategoria = ventasPorMesYCategoria.computeIfAbsent(
                        mesNombre,
                        k -> new HashMap<>()
                );
                ventasPorCategoria.put(categoria, totalVenta);
            }
        }

        Map<String, Map<String, Double>> resultadoFinal = new LinkedHashMap<>();

        for (Map.Entry<String, Map<String, Double>> mesEntry : ventasPorMesYCategoria.entrySet()) {
            String mesNombre = mesEntry.getKey();
            Map<String, Double> ventasActuales = mesEntry.getValue();

            Map<String, Double> ventasCompletas = new LinkedHashMap<>();

            for (String categoriaClave : CATEGORIAS_CLAVE) {
                ventasCompletas.put(
                        categoriaClave,
                        ventasActuales.getOrDefault(categoriaClave, 0.0)
                );
            }

            resultadoFinal.put(mesNombre, ventasCompletas);
        }

        return resultadoFinal;
    }

    public Map<String, Map<String, Double>> obtenerVentasPorTipoPorMes() {
        List<Map<String, Object>> resultados = metricaDAO.obtenerVentasPorTipoPorMes();
        Map<String, Map<String, Double>> ventasPorTipoPorMes = new LinkedHashMap<>();

        for (Map<String, Object> fila : resultados) {
            int mesNum = (int) fila.get("mes");
            String mesNombre = getMesNombre(mesNum);
            String tipo = (String) fila.get("tipo");

            BigDecimal totalVentaBD = (BigDecimal) fila.get("total");
            double totalVenta = totalVentaBD != null ? totalVentaBD.doubleValue() : 0.0;

            Map<String, Double> ventasPorTipo = ventasPorTipoPorMes.computeIfAbsent(
                    mesNombre,
                    k -> new HashMap<>()
            );

            ventasPorTipo.put(tipo.toLowerCase(), totalVenta);
        }

        return ventasPorTipoPorMes;
    }

    public String getMesNombre(int mes) {
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

    public Map<String, Double> obtenerVentasPorMes() {
        List<Map<String, Object>> result = metricaDAO.obtenerTendenciaVentas();
        Map<String, Double> ventasPorMes = new LinkedHashMap<>();

        for (Map<String, Object> row : result) {
            int mes = (int) row.get("mes");
            BigDecimal totalVenta = (BigDecimal) row.get("total");
            ventasPorMes.put(getMesNombre(mes), totalVenta != null ? totalVenta.doubleValue() : 0.0);
        }

        return ventasPorMes;
    }

    public List<Map<String, Object>> obtenerTendenciaVentas() {
        return metricaDAO.obtenerTendenciaVentas();
    }
}