package com.example.BataPeru.mapper;

import com.example.BataPeru.dto.MarcaDTO;
import com.example.BataPeru.entity.Marca;
import com.example.BataPeru.entity.Producto;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class MarcaMapper {

    public MarcaDTO toDTO(Marca entity) {
        if (entity == null) return null;

        MarcaDTO dto = new MarcaDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setLogoUrl(entity.getLogoUrl());
        dto.setActivo(entity.getActivo());
        dto.setFechaCreacion(entity.getFechaCreacion());

        if (entity.getProductos() != null) {
            dto.setProductosIds(entity.getProductos().stream()
                    .map(Producto::getId)
                    .collect(Collectors.toList()));
        }

        return dto;
    }

    public Marca toEntity(MarcaDTO dto) {
        if (dto == null) return null;

        Marca entity = new Marca();
        entity.setId(dto.getId());
        entity.setNombre(dto.getNombre());
        entity.setLogoUrl(dto.getLogoUrl());
        entity.setActivo(dto.getActivo());
        entity.setFechaCreacion(dto.getFechaCreacion());

        return entity;
    }
}

