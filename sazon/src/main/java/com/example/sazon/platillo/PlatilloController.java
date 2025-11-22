package com.example.sazon.platillo;

import com.example.sazon.categoria.CategoriaService;
import com.example.sazon.categoria.Categoria;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/platillo")
public class PlatilloController {

    private final PlatilloService platilloService;
    private final CategoriaService categoriaService;

    public PlatilloController(PlatilloService platilloService, CategoriaService categoriaService) {
        this.platilloService = platilloService;
        this.categoriaService = categoriaService;
    }

    @GetMapping("/list")
    public String listar(Model model) {
        model.addAttribute("platillos", platilloService.listar());
        model.addAttribute("categorias", categoriaService.listar());
        model.addAttribute("nuevoPlatillo", new Platillo());
        return "platillo";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("nuevoPlatillo") Platillo platillo, RedirectAttributes redirectAttributes) {
        Categoria categoria = categoriaService.obtenerPorId(platillo.getCategoriaId());

        if (categoria == null || !categoria.isCondicion()) {
            redirectAttributes.addFlashAttribute("error", "⚠️ La categoría seleccionada está inactiva. No se puede registrar el platillo.");
            return "redirect:/platillo/list";
        }

        platilloService.guardar(platillo);
        redirectAttributes.addFlashAttribute("mensaje", "Platillo registrado correctamente.");
        return "redirect:/platillo/list";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable int id, Model model) {
        Platillo platillo = platilloService.obtenerPorId(id);
        model.addAttribute("platillo", platillo);
        model.addAttribute("categorias", categoriaService.listar());
        return "editarplatillo";
    }

    @PostMapping("/actualizar")
    public String actualizar(@ModelAttribute("platilloEditar") Platillo platillo, RedirectAttributes redirectAttributes) {
        Categoria categoria = categoriaService.obtenerPorId(platillo.getCategoriaId());

        if (categoria == null || !categoria.isCondicion()) {
            redirectAttributes.addFlashAttribute("error", "⚠️ No se puede asignar una categoría inactiva.");
            return "redirect:/platillo/list";
        }

        platilloService.actualizar(platillo);
        redirectAttributes.addFlashAttribute("mensaje", "Platillo actualizado correctamente.");
        return "redirect:/platillo/list";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable int id, RedirectAttributes redirectAttributes) {
        try {
            platilloService.eliminar(id);
            redirectAttributes.addFlashAttribute("mensaje", "Platillo eliminado correctamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Ocurrió un error al eliminar el platillo.");
        }
        return "redirect:/platillo/list";
    }
}
