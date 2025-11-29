package com.example.BataPeru.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tallas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Talla {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String valor;

    private String region;

    @OneToMany(mappedBy = "talla")
    private List<VarianteProducto> variantes;
}

