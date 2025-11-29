package com.example.BataPeru.mapper;
import com.example.BataPeru.dto.CategoriaDTO;
import com.example.BataPeru.entity.Categoria;
import com.example.BataPeru.entity.Producto;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;
@Component
public class CategoriaMapper {
    public CategoriaDTO toDTO(Categoria entity) {
        if (entity == null) return null;
        CategoriaDTO dto = new CategoriaDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        if (entity.getProductos() != null) {
            dto.setProductosIds(entity.getProductos().stream()
                    .map(Producto::getId)
                    .collect(Collectors.toList()));
        }
        return dto;
    }
    public Categoria toEntity(CategoriaDTO dto) {
        if (dto == null) return null;
        Categoria entity = new Categoria();
        entity.setId(dto.getId());
        entity.setNombre(dto.getNombre());
        return entity;
    }
}

