package com.example.BataPeru.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComentarioDTO {
    private Long id;
    private Long usuarioId;
    private Long productoId;
    private Integer puntuacion;
    private String comentario;
    private LocalDateTime fecha;
}

