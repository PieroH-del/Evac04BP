package com.example.BataPeru.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ColorDTO {
    private Long id;
    private String nombre;
    private String codigoHex;
    private List<Long> variantesIds;
}

