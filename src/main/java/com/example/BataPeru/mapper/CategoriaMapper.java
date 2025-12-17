package com.example.BataPeru.mapper;

import com.example.BataPeru.dto.CategoriaDTO;
import com.example.BataPeru.entity.Categoria;
import com.example.BataPeru.entity.Producto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {

    @Mapping(source = "productos", target = "productosIds", qualifiedByName = "productosToIds")
    CategoriaDTO toDTO(Categoria entity);

    Categoria toEntity(CategoriaDTO dto);

    @Named("productosToIds")
    default List<Long> productosToIds(List<Producto> productos) {
        if (productos == null) {
            return null;
        }
        return productos.stream()
                .map(Producto::getId)
                .collect(Collectors.toList());
    }
}
