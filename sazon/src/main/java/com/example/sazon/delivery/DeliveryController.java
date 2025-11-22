package com.example.sazon.delivery;

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
import java.util.stream.IntStream;

@Controller
@RequestMapping("/delivery")
public class DeliveryController {

    private final DeliveryService deliveryService;
    private final PlatilloService platilloService;
    private final CategoriaService categoriaService;

    public DeliveryController(DeliveryService deliveryService,
                              PlatilloService platilloService,
                              CategoriaService categoriaService) {

        this.deliveryService = deliveryService;
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
                .filter(pl -> categoriasActivas.stream()
                        .anyMatch(cat -> cat.getId() == pl.getCategoriaId()))
                .collect(Collectors.toList());

        model.addAttribute("platillos", platillosActivos);
        model.addAttribute("categorias", categoriasActivas);

        model.addAttribute("metodoPagoOptions", List.of("Efectivo", "Tarjeta", "Yape", "Plin"));

        model.addAttribute("delivery", new Delivery());

        return "delivery";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Delivery delivery,
                          @RequestParam(value = "platilloId", required = false) String[] platilloIdsStr,
                          @RequestParam(value = "cantidad", required = false) String[] cantidadesStr,
                          RedirectAttributes redirectAttributes) {
        try {
            if (delivery.getNombreCliente() == null) delivery.setNombreCliente("");
            if (delivery.getDireccionCliente() == null) delivery.setDireccionCliente("");
            if (delivery.getTelefonoCliente() == null) delivery.setTelefonoCliente("");
            if (delivery.getReferencia() == null) delivery.setReferencia("");

            List<Integer> validPlatilloIds = new ArrayList<>();
            List<Integer> validCantidades = new ArrayList<>();

            if (platilloIdsStr != null && cantidadesStr != null) {
                int minLength = Math.min(platilloIdsStr.length, cantidadesStr.length);

                for (int i = 0; i < minLength; i++) {
                    String platilloId = platilloIdsStr[i];
                    String cantidad = cantidadesStr[i];

                    int pId = platilloId.isEmpty() ? 0 : Integer.parseInt(platilloId);
                    int cant = cantidad.isEmpty() ? 0 : Integer.parseInt(cantidad);

                    if (pId > 0 && cant > 0) {
                        validPlatilloIds.add(pId);
                        validCantidades.add(cant);
                    }
                }
            }

            int[] platilloIds = validPlatilloIds.stream().mapToInt(i -> i).toArray();
            int[] cantidades = validCantidades.stream().mapToInt(i -> i).toArray();

            if (platilloIds.length == 0) {
                throw new IllegalArgumentException("Debe seleccionar al menos un platillo y una cantidad válida.");
            }

            if (delivery.getId() > 0) {
                deliveryService.actualizarDeliveryConDetalles(delivery, platilloIds, cantidades);
                redirectAttributes.addFlashAttribute("mensaje", "Delivery actualizado correctamente.");
            } else {
                int id = deliveryService.crearDeliveryConDetalles(delivery, platilloIds, cantidades);
                redirectAttributes.addFlashAttribute("mensaje", "Delivery registrado correctamente. Id: " + id);
            }

            return "redirect:/delivery/list";

        } catch (Exception ex) {
            String errorMessage = "Error al guardar delivery: " + ex.getMessage();
            if (ex instanceof NumberFormatException) {
                errorMessage = "Error de formato: Asegúrese de que las cantidades sean números válidos.";
            }
            redirectAttributes.addFlashAttribute("error", errorMessage);
            return "redirect:/delivery/list";
        }
    }

    // ⭐ MÉTODO CORREGIDO: Mapeo ajustado para coincidir con el formulario POST
    @PostMapping("/actualizar-estado/{id}") // <--- Quitamos {nuevoEstado} de la URL
    public String actualizarEstado(@PathVariable int id,
                                   @RequestParam String nuevoEstado, // <--- Recibimos el estado como RequestParam
                                   RedirectAttributes redirectAttributes) {
        try {
            deliveryService.actualizarEstadoDelivery(id, nuevoEstado);
            redirectAttributes.addFlashAttribute("mensaje", "Estado del Delivery #" + id + " actualizado a '" + nuevoEstado + "'.");

        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("error", "Error al actualizar estado: " + ex.getMessage());
        }
        return "redirect:/delivery/list";
    }

    @GetMapping("/list")
    public String listar(Model model,
                         @RequestParam(value = "ver", required = false) Integer verId) {

        List<Delivery> deliveries = deliveryService.listarDeliveriesEntregables();
        model.addAttribute("deliveries", deliveries);

        if (verId != null) {
            List<DetalleDelivery> detalles = deliveryService.listarDetallesPorDelivery(verId);
            model.addAttribute("detallesDelivery", detalles);
            model.addAttribute("verId", verId);
        }

        return "listadodelivery";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable int id,
                         Model model,
                         RedirectAttributes redirectAttributes) {

        try {
            Delivery delivery = deliveryService.obtenerDeliveryPorId(id);
            if (delivery == null) {
                redirectAttributes.addFlashAttribute("error", "Delivery no encontrado.");
                return "redirect:/delivery/list";
            }

            List<DetalleDelivery> detalles = deliveryService.listarDetallesPorDelivery(id);

            List<Categoria> categoriasActivas = categoriaService.listar()
                    .stream()
                    .filter(Categoria::isCondicion)
                    .collect(Collectors.toList());

            List<Platillo> platillosActivos = platilloService.listar()
                    .stream()
                    .filter(Platillo::isCondicion)
                    .filter(pl -> categoriasActivas
                            .stream()
                            .anyMatch(cat -> cat.getId() == pl.getCategoriaId()))
                    .collect(Collectors.toList());

            model.addAttribute("delivery", delivery);
            model.addAttribute("detalles", detalles);
            model.addAttribute("platillos", platillosActivos);
            model.addAttribute("categorias", categoriasActivas);
            model.addAttribute("metodoPagoOptions", List.of("Efectivo", "Tarjeta", "Yape", "Plin"));
            model.addAttribute("modoEdicion", true);

            return "delivery";

        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("error", "Error al editar delivery: " + ex.getMessage());
            return "redirect:/delivery/list";
        }
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable int id, RedirectAttributes redirectAttributes) {
        try {
            deliveryService.eliminarDeliveryConDetalles(id);
            redirectAttributes.addFlashAttribute("mensaje", "Delivery eliminado correctamente.");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar delivery: " + ex.getMessage());
        }
        return "redirect:/delivery/list";
    }
}
