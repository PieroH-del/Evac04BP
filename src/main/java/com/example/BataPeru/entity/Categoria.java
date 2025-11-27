package com.example.BataPeru.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "categorias")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_padre")
    private Integer idPadre;

    @Column(nullable = false)
    private String nombre;

    private String slug;

    private Integer nivel;

    @OneToMany(mappedBy = "categoria")
    private List<Producto> productos;
}

