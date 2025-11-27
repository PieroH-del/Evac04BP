package com.example.BataPeru.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pedidos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private String estado;

    @Column(name = "total_pagar", precision = 10, scale = 2)
    private BigDecimal totalPagar;

    @Column(name = "direccion_envio_id")
    private Integer direccionEnvioId;

    @Column(name = "metodo_pago")
    private String metodoPago;

    @Column(name = "id_transaccion")
    private String idTransaccion;

    @Column(name = "fecha_pedido")
    private LocalDateTime fechaPedido;

    @OneToMany(mappedBy = "pedido")
    private List<DetallePedido> detalles;
}

