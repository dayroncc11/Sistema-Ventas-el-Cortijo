package pe.edu.cibertec.elcortijo.gestionventas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/")
    public String dashboard() {
        return "index.html";
    }

    @GetMapping("/login")
    public String login() {
        return "login.html";
    }
}