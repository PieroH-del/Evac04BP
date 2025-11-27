package com.example.BataPeru.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "productos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "marca_id")
    private Marca marca;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @Column(nullable = false)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "sku_base")
    private String skuBase;

    @Column(name = "precio_regular", precision = 10, scale = 2)
    private BigDecimal precioRegular;

    @Column(name = "precio_oferta", precision = 10, scale = 2)
    private BigDecimal precioOferta;

    private String genero;

    @Column(name = "material_capellada")
    private String materialCapellada;

    @Column(name = "material_forro")
    private String materialForro;

    @Column(name = "material_suela")
    private String materialSuela;

    private Boolean activo;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @OneToMany(mappedBy = "producto")
    private List<VarianteProducto> variantes;

    @OneToMany(mappedBy = "producto")
    private List<ImagenProducto> imagenes;

    @OneToMany(mappedBy = "producto")
    private List<Resena> resenas;
}

