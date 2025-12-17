package com.example.BataPeru.controller;

import com.example.BataPeru.dto.PedidoDTO;
import com.example.BataPeru.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<PedidoDTO> crearPedido(@RequestBody PedidoDTO pedidoDTO) {
        try {
            PedidoDTO nuevoPedido = pedidoService.crearPedido(pedidoDTO);
            return new ResponseEntity<>(nuevoPedido, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null); // En un caso real, devolver un DTO de error
        }
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<PedidoDTO>> obtenerPedidosPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(pedidoService.findByUsuarioId(usuarioId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> obtenerPedidoPorId(@PathVariable Long id) {
        return pedidoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{id}/estado")
    public ResponseEntity<PedidoDTO> actualizarEstadoPedido(@PathVariable Long id, @RequestBody String estado) {
        try {
            PedidoDTO pedidoActualizado = pedidoService.actualizarEstado(id, estado);
            return ResponseEntity.ok(pedidoActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
