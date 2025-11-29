package com.example.BataPeru.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VarianteProductoDTO {
    private Long id;
    private Long productoId;
    private Long tallaId;
    private Long colorId;
    private Integer stockCantidad;
}

