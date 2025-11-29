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
public class ProductoDTO {
    private Long id;
    private Long marcaId;
    private Long categoriaId;
    private String nombre;
    private String descripcion;
    private BigDecimal precioRegular;
    private String genero;
    private String material;
    private Boolean activo;
    private LocalDateTime fechaCreacion;
    private List<Long> variantesIds;
    private List<Long> imagenesIds;
}

