package com.example.BataPeru.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DireccionDTO {
    private Long id;
    private Long usuarioId;
    private String direccionCalle;
    private String ciudad;
    private String provincia;
    private String codigoPostal;
    private String pais;
    private Boolean esPrincipal;
}

