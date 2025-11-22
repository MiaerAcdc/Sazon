package com.example.sazon.otros;

import com.example.sazon.categoria.Categoria;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/otros")
public class OtrosController {

    @GetMapping("/inicio")
    public String inicio() {
        return "index";
    }

    @GetMapping("/publicidad")
    public String publicidad() {
        return "publicidad";
    }

    @GetMapping("/contacto")
    public String contacto() {
        return "contacto";
    }
}