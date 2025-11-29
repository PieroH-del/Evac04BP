package com.example.BataPeru.mapper;
import com.example.BataPeru.dto.ImagenProductoDTO;
import com.example.BataPeru.entity.ImagenProducto;
import org.springframework.stereotype.Component;
@Component
public class ImagenProductoMapper {
    public ImagenProductoDTO toDTO(ImagenProducto entity) {
        if (entity == null) return null;
        ImagenProductoDTO dto = new ImagenProductoDTO();
        dto.setId(entity.getId());
        dto.setProductoId(entity.getProducto() != null ? entity.getProducto().getId() : null);
        dto.setColorId(entity.getColor() != null ? entity.getColor().getId() : null);
        dto.setUrlImagen(entity.getUrlImagen());
        return dto;
    }
    public ImagenProducto toEntity(ImagenProductoDTO dto) {
        if (dto == null) return null;
        ImagenProducto entity = new ImagenProducto();
        entity.setId(dto.getId());
        entity.setUrlImagen(dto.getUrlImagen());
        return entity;
    }
}

