package com.example.BataPeru.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "variantes_producto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VarianteProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "talla_id")
    private Talla talla;

    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color color;


    @Column(name = "stock_cantidad")
    private Integer stockCantidad;

    @OneToMany(mappedBy = "varianteProducto")
    private List<DetallePedido> detallesPedido;
}

