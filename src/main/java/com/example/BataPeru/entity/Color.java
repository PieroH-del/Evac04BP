package com.example.BataPeru.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "colores")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Color {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(name = "codigo_hex")
    private String codigoHex;

    @OneToMany(mappedBy = "color")
    private List<VarianteProducto> variantes;

    @OneToMany(mappedBy = "color")
    private List<ImagenProducto> imagenes;
}

