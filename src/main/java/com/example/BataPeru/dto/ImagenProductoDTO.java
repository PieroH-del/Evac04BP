package com.example.BataPeru.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImagenProductoDTO {
    private Long id;
    private Long productoId;
    private Long colorId;
    private String urlImagen;
}

