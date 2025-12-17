package com.example.BataPeru.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class SaludoController {
    @GetMapping("/")
    public String saludo() {
        return "¡Hola Backend de Bata Corriendo!";
    }
}
