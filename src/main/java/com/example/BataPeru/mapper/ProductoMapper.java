package com.example.BataPeru.mapper;

import com.example.BataPeru.dto.ProductoDTO;
import com.example.BataPeru.entity.ImagenProducto;
import com.example.BataPeru.entity.Producto;
import com.example.BataPeru.entity.VarianteProducto;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ProductoMapper {

    public ProductoDTO toDTO(Producto entity) {
        if (entity == null) return null;

        ProductoDTO dto = new ProductoDTO();
        dto.setId(entity.getId());
        dto.setMarcaId(entity.getMarca() != null ? entity.getMarca().getId() : null);
        dto.setCategoriaId(entity.getCategoria() != null ? entity.getCategoria().getId() : null);
        dto.setNombre(entity.getNombre());
        dto.setDescripcion(entity.getDescripcion());
        dto.setPrecioRegular(entity.getPrecioRegular());
        dto.setGenero(entity.getGenero());
        dto.setMaterial(entity.getMaterial());
        dto.setActivo(entity.getActivo());
        dto.setFechaCreacion(entity.getFechaCreacion());

        if (entity.getVariantes() != null) {
            dto.setVariantesIds(entity.getVariantes().stream()
                    .map(VarianteProducto::getId)
                    .collect(Collectors.toList()));
        }

        if (entity.getImagenes() != null) {
            dto.setImagenesIds(entity.getImagenes().stream()
                    .map(ImagenProducto::getId)
                    .collect(Collectors.toList()));
        }

        return dto;
    }

    public Producto toEntity(ProductoDTO dto) {
        if (dto == null) return null;

        Producto entity = new Producto();
        entity.setId(dto.getId());
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        entity.setPrecioRegular(dto.getPrecioRegular());
        entity.setGenero(dto.getGenero());
        entity.setMaterial(dto.getMaterial());
        entity.setActivo(dto.getActivo());
        entity.setFechaCreacion(dto.getFechaCreacion());

        return entity;
    }
}

