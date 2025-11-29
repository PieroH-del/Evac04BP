package com.example.BataPeru.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "direcciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Direccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(name = "direccion_calle")
    private String direccionCalle;

    private String ciudad;

    private String provincia;

    @Column(name = "codigo_postal")
    private String codigoPostal;

    private String pais;

    @Column(name = "es_principal")
    private Boolean esPrincipal;
}

