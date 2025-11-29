package com.example.BataPeru.mapper;

import com.example.BataPeru.dto.PedidoDTO;
import com.example.BataPeru.entity.DetallePedido;
import com.example.BataPeru.entity.Pedido;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PedidoMapper {

    public PedidoDTO toDTO(Pedido entity) {
        if (entity == null) return null;

        PedidoDTO dto = new PedidoDTO();
        dto.setId(entity.getId());
        dto.setUsuarioId(entity.getUsuario() != null ? entity.getUsuario().getId() : null);
        dto.setEstado(entity.getEstado());
        dto.setTotalPagar(entity.getTotalPagar());
        dto.setDireccionEnvioId(entity.getDireccionEnvioId());
        dto.setMetodoPago(entity.getMetodoPago());
        dto.setFechaPedido(entity.getFechaPedido());

        if (entity.getDetalles() != null) {
            dto.setDetallesIds(entity.getDetalles().stream()
                    .map(DetallePedido::getId)
                    .collect(Collectors.toList()));
        }

        return dto;
    }

    public Pedido toEntity(PedidoDTO dto) {
        if (dto == null) return null;

        Pedido entity = new Pedido();
        entity.setId(dto.getId());
        entity.setEstado(dto.getEstado());
        entity.setTotalPagar(dto.getTotalPagar());
        entity.setDireccionEnvioId(dto.getDireccionEnvioId());
        entity.setMetodoPago(dto.getMetodoPago());
        entity.setFechaPedido(dto.getFechaPedido());

        return entity;
    }
}

