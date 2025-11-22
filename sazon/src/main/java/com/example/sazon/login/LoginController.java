package com.example.sazon.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public String mostrarFormulario() {
        return "login"; // login.jsp
    }

    @PostMapping("/validar")
    public String validar(@RequestParam String usuario,
                          @RequestParam String password,
                          RedirectAttributes redirectAttributes) {

        // 🔒 Lógica simple de autenticación
        if ("admin".equals(usuario) && "1234".equals(password)) {
            redirectAttributes.addFlashAttribute("mensaje", "Bienvenido " + usuario + "!");
            return "redirect:/otros/inicio";
        } else {
            redirectAttributes.addFlashAttribute("error", "Usuario o contraseña incorrectos.");
            return "redirect:/login";
        }
    }
}
