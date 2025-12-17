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
        return ResponseEntity.ok(marcaService.obtenerTodos());
    }

    @PostMapping
    public ResponseEntity<MarcaDTO> crearMarca(@RequestBody MarcaDTO marcaDTO) {
        if (marcaDTO.getNombre() == null || marcaDTO.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la marca es obligatorio");
        }
        return ResponseEntity.ok(marcaService.crear(marcaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMarca(@PathVariable Long id) {
        marcaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
