package com.example.BataPeru.mapper;

import com.example.BataPeru.dto.DetallePedidoDTO;
import com.example.BataPeru.entity.DetallePedido;
import org.springframework.stereotype.Component;

@Component
public class DetallePedidoMapper {

    public DetallePedidoDTO toDTO(DetallePedido entity) {
        if (entity == null) return null;

        DetallePedidoDTO dto = new DetallePedidoDTO();
        dto.setId(entity.getId());
        dto.setPedidoId(entity.getPedido() != null ? entity.getPedido().getId() : null);
        dto.setVarianteProductoId(entity.getVarianteProducto() != null ? entity.getVarianteProducto().getId() : null);
        dto.setCantidad(entity.getCantidad());
        dto.setPrecioUnitario(entity.getPrecioUnitario());

        return dto;
    }

    public DetallePedido toEntity(DetallePedidoDTO dto) {
        if (dto == null) return null;

        DetallePedido entity = new DetallePedido();
        entity.setId(dto.getId());
        entity.setCantidad(dto.getCantidad());
        entity.setPrecioUnitario(dto.getPrecioUnitario());

        return entity;
    }
}

