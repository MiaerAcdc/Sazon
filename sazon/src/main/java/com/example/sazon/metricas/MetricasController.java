package com.example.sazon.metricas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import java.util.Map;

@Controller
public class MetricasController {

    private final MetricasService metricaService;

    @Autowired
    public MetricasController(MetricasService metricaService) {
        this.metricaService = metricaService;
    }

    @GetMapping("/metricas/dashboard")
    public String dashboard(Model model) {

        model.addAttribute("metricaService", metricaService);

        double ventasTotales = metricaService.obtenerVentasTotales();
        model.addAttribute("ventasTotales", ventasTotales);

        model.addAttribute("ventasPorMesYCategoria", metricaService.obtenerVentasPorCategoriaPorMes());

        model.addAttribute("ventasPorTipoPorMes", metricaService.obtenerVentasPorTipoPorMes());

        List<Map<String, Object>> tendenciaVentas = metricaService.obtenerTendenciaVentas();
        model.addAttribute("tendenciaVentas", tendenciaVentas);

        return "dashboard";
    }
}