package com.example.BataPeru.mapper;

import com.example.BataPeru.dto.VarianteProductoDTO;
import com.example.BataPeru.entity.VarianteProducto;
import org.springframework.stereotype.Component;

@Component
public class VarianteProductoMapper {

    public VarianteProductoDTO toDTO(VarianteProducto entity) {
        if (entity == null) return null;

        VarianteProductoDTO dto = new VarianteProductoDTO();
        dto.setId(entity.getId());
        dto.setProductoId(entity.getProducto() != null ? entity.getProducto().getId() : null);
        dto.setTallaId(entity.getTalla() != null ? entity.getTalla().getId() : null);
        dto.setColorId(entity.getColor() != null ? entity.getColor().getId() : null);
        dto.setStockCantidad(entity.getStockCantidad());

        return dto;
    }

    public VarianteProducto toEntity(VarianteProductoDTO dto) {
        if (dto == null) return null;

        VarianteProducto entity = new VarianteProducto();
        entity.setId(dto.getId());
        entity.setStockCantidad(dto.getStockCantidad());

        return entity;
    }
}

