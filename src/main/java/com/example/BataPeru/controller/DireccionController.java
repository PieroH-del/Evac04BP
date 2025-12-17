package com.example.BataPeru.controller;

import com.example.BataPeru.dto.DireccionDTO;
import com.example.BataPeru.service.DireccionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/direcciones")
@RequiredArgsConstructor
public class DireccionController {

    private final DireccionService direccionService;

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<DireccionDTO>> obtenerDireccionesPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(direccionService.obtenerDireccionesPorUsuario(usuarioId));
    }

    @PostMapping
    public ResponseEntity<DireccionDTO> crearDireccion(@RequestBody DireccionDTO direccionDTO) {
        DireccionDTO nuevaDireccion = direccionService.crearDireccion(direccionDTO);
        return ResponseEntity.ok(nuevaDireccion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DireccionDTO> actualizarDireccion(@PathVariable Long id, @RequestBody DireccionDTO direccionDTO) {
        return ResponseEntity.ok(direccionService.actualizarDireccion(id, direccionDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDireccion(@PathVariable Long id) {
        direccionService.eliminarDireccion(id);
        return ResponseEntity.noContent().build();
    }
}
