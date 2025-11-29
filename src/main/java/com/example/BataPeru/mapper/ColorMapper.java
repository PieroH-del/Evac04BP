package com.example.BataPeru.mapper;

import com.example.BataPeru.dto.ColorDTO;
import com.example.BataPeru.entity.Color;
import com.example.BataPeru.entity.VarianteProducto;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ColorMapper {

    public ColorDTO toDTO(Color entity) {
        if (entity == null) return null;

        ColorDTO dto = new ColorDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setCodigoHex(entity.getCodigoHex());

        if (entity.getVariantes() != null) {
            dto.setVariantesIds(entity.getVariantes().stream()
                    .map(VarianteProducto::getId)
                    .collect(Collectors.toList()));
        }

        return dto;
    }

    public Color toEntity(ColorDTO dto) {
        if (dto == null) return null;

        Color entity = new Color();
        entity.setId(dto.getId());
        entity.setNombre(dto.getNombre());
        entity.setCodigoHex(dto.getCodigoHex());

        return entity;
    }
}

