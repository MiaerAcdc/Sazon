package com.example.sazon.metricas;

import java.util.List;
import java.util.Map;

public interface MetricasDAO {

    double obtenerVentasTotales();

    List<Map<String, Object>> obtenerVentasPorCategoriaPorMes();

    Map<String, Double> obtenerVentasPorMes();

    List<Map<String, Object>> obtenerTendenciaVentas();

    List<Map<String, Object>> obtenerVentasPorTipoPorMes();
}