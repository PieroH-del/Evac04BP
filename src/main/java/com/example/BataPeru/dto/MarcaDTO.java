package com.example.BataPeru.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MarcaDTO {
    private Long id;
    private String nombre;
    private String logoUrl;
    private Boolean activo;
    private LocalDateTime fechaCreacion;
    private List<Long> productosIds;
}

