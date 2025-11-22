package com.example.sazon.categoria;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping("/list")
    public String listar(Model model) {
        model.addAttribute("categorias", categoriaService.listar());
        model.addAttribute("nuevaCategoria", new Categoria());
        return "categoria";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("nuevaCategoria") Categoria categoria) {
        categoriaService.guardar(categoria);
        return "redirect:/categoria/list";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable int id, Model model) {
        Categoria categoria = categoriaService.obtenerPorId(id);
        model.addAttribute("categoria", categoria);
        return "editarcategoria";
    }

    @PostMapping("/actualizar")
    public String actualizar(@ModelAttribute("categoriaEditar") Categoria categoria) {
        categoriaService.actualizar(categoria);
        return "redirect:/categoria/list";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable int id, RedirectAttributes redirectAttributes) {
        try {
            categoriaService.eliminar(id);
            redirectAttributes.addFlashAttribute("mensaje", "Categoría eliminada correctamente.");
        } catch (IllegalStateException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Ocurrió un error al eliminar la categoría.");
        }
        return "redirect:/categoria/list";
    }
}
