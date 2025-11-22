package com.example.sazon.venta;

import com.example.sazon.platillo.Platillo;
import com.example.sazon.platillo.PlatilloService;
import com.example.sazon.categoria.Categoria;
import com.example.sazon.categoria.CategoriaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/venta")
public class VentaController {

    private final VentaService ventaService;
    private final PlatilloService platilloService;
    private final CategoriaService categoriaService;

    public VentaController(VentaService ventaService, PlatilloService platilloService, CategoriaService categoriaService) {
        this.ventaService = ventaService;
        this.platilloService = platilloService;
        this.categoriaService = categoriaService;
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        List<Categoria> categoriasActivas = categoriaService.listar()
                .stream()
                .filter(Categoria::isCondicion)
                .collect(Collectors.toList());

        List<Platillo> platillosActivos = platilloService.listar()
                .stream()
                .filter(Platillo::isCondicion)
                .filter(pl -> categoriasActivas.stream().anyMatch(cat -> cat.getId() == pl.getCategoriaId()))
                .collect(Collectors.toList());

        model.addAttribute("platillos", platillosActivos);
        model.addAttribute("categorias", categoriasActivas);
        model.addAttribute("metodoPagoOptions", List.of("Efectivo", "Tarjeta", "Yape", "Plin"));

        model.addAttribute("venta", new Venta());

        return "venta";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Venta venta,
                          @RequestParam(value = "platilloId", required = false) String[] platilloIdsStr,
                          @RequestParam(value = "cantidad", required = false) String[] cantidadesStr,
                          RedirectAttributes redirectAttributes) {
        try {
            if (venta.getNombreCliente() == null) {
                venta.setNombreCliente("");
            }

            // Lógica de filtrado y conversión de String[] a int[]
            List<Integer> validPlatilloIds = new ArrayList<>();
            List<Integer> validCantidades = new ArrayList<>();

            if (platilloIdsStr != null && cantidadesStr != null) {
                int minLength = Math.min(platilloIdsStr.length, cantidadesStr.length);

                for (int i = 0; i < minLength; i++) {
                    String platilloId = platilloIdsStr[i];
                    String cantidad = cantidadesStr[i];

                    // Convertir la cadena vacía ("") a 0 para evitar NumberFormatException
                    int pId = platilloId.isEmpty() ? 0 : Integer.parseInt(platilloId);
                    int cant = cantidad.isEmpty() ? 0 : Integer.parseInt(cantidad);

                    // Solo incluir si ambos valores son válidos (mayores a 0)
                    if (pId > 0 && cant > 0) {
                        validPlatilloIds.add(pId);
                        validCantidades.add(cant);
                    }
                }
            }

            // Convertir las listas filtradas de vuelta a arrays primitivos
            int[] platilloIds = validPlatilloIds.stream().mapToInt(i -> i).toArray();
            int[] cantidades = validCantidades.stream().mapToInt(i -> i).toArray();

            if (platilloIds.length == 0) {
                throw new IllegalArgumentException("Debe seleccionar al menos un platillo y una cantidad válida.");
            }

            if (venta.getId() > 0) {
                ventaService.actualizarVentaConDetalles(venta, platilloIds, cantidades);
                redirectAttributes.addFlashAttribute("mensaje", "Venta actualizada correctamente.");
            } else {
                int id = ventaService.crearVentaConDetalles(venta, platilloIds, cantidades);
                redirectAttributes.addFlashAttribute("mensaje", "Venta registrada correctamente. Id: " + id);
            }

            return "redirect:/venta/list";

        } catch (Exception ex) {
            String errorMessage = "Error al guardar la venta: " + ex.getMessage();
            if (ex instanceof NumberFormatException) {
                errorMessage = "Error de formato: Asegúrese de que las cantidades sean números válidos.";
            }
            redirectAttributes.addFlashAttribute("error", errorMessage);
            return "redirect:/venta/list";
        }
    }

    @GetMapping("/list")
    public String listar(Model model, @RequestParam(value = "ver", required = false) Integer verId) {
        List<Venta> ventas = ventaService.listarVentasLocales();
        model.addAttribute("ventas", ventas);

        if (verId != null) {
            List<DetalleVenta> detalles = ventaService.listarDetallesPorVenta(verId);
            model.addAttribute("detallesVenta", detalles);
            model.addAttribute("verId", verId);
        }

        return "listadoventa";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable int id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Venta venta = ventaService.obtenerVentaPorId(id);
            if (venta == null) {
                redirectAttributes.addFlashAttribute("error", "Venta no encontrada.");
                return "redirect:/venta/list";
            }

            List<DetalleVenta> detalles = ventaService.listarDetallesPorVenta(id);

            List<Categoria> categoriasActivas = categoriaService.listar()
                    .stream()
                    .filter(Categoria::isCondicion)
                    .collect(Collectors.toList());

            List<Platillo> platillosActivos = platilloService.listar()
                    .stream()
                    .filter(Platillo::isCondicion)
                    .filter(pl -> categoriasActivas.stream().anyMatch(cat -> cat.getId() == pl.getCategoriaId()))
                    .collect(Collectors.toList());

            model.addAttribute("venta", venta);
            model.addAttribute("detalles", detalles);
            model.addAttribute("platillos", platillosActivos);
            model.addAttribute("categorias", categoriasActivas);
            model.addAttribute("metodoPagoOptions", List.of("Efectivo", "Tarjeta", "Yape", "Plin"));
            model.addAttribute("modoEdicion", true);

            return "venta";

        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("error", "Error al editar: " + ex.getMessage());
            return "redirect:/venta/list";
        }
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable int id, RedirectAttributes redirectAttributes) {
        try {
            ventaService.eliminarVentaConDetalles(id);
            redirectAttributes.addFlashAttribute("mensaje", "Venta eliminada correctamente.");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar la venta: " + ex.getMessage());
        }
        return "redirect:/venta/list";
    }
}