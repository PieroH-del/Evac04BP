package com.example.BataPeru.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {
    private Long id;
    private Long usuarioId;
    private String estado;
    private BigDecimal totalPagar;
    private Long direccionEnvioId;
    private String metodoPago;
    private LocalDateTime fechaPedido;
    private List<DetallePedidoDTO> detalles;
}

