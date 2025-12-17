package com.example.BataPeru.controller;

import com.example.BataPeru.dto.MarcaDTO;
import com.example.BataPeru.service.MarcaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/marcas")
@RequiredArgsConstructor
public class MarcaController {

    private final MarcaService marcaService;

    @GetMapping
    public ResponseEntity<List<MarcaDTO>> listarMarcas() {
        return ResponseEntity.ok(marcaService.findAll());
    }

    @PostMapping
    public ResponseEntity<MarcaDTO> crearMarca(@RequestBody MarcaDTO marcaDTO) {
        return ResponseEntity.ok(marcaService.save(marcaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMarca(@PathVariable Long id) {
        marcaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
